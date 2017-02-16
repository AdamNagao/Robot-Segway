package project;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.utility.Delay;

public class Main {

	static float kp = 1;
	static float ki = 1;
	static float kd = 1;
	static float output;
	static int theta;
	
	static float[] AngleSamples = new float[3];
	public static void main(String[] args) throws InterruptedException{
		
		EV3 ev3brick =(EV3) BrickFinder.getLocal();
		TextLCD text = ev3brick.getTextLCD();
		EV3GyroSensor Gyro = new EV3GyroSensor(SensorPort.S1);
		
		text.drawString("Position the Robot for Calibration, then hit enter",0,0);
	
		Button.ENTER.waitForPressAndRelease();
		
		Gyro.reset();  //Zero the gyro
		
		Gyro.getAngleMode(); //Tell the gyro we are in angle mode
		
		PID.SetTunings(kp, ki,kd); //Set PIDs
		
		text.drawString("Press Enter to run Robot!",0,0);
		
		Button.ENTER.waitForPressAndRelease();
		
		text.drawString("Robot Running!",0,0);
		
		while(!Button.ENTER.isDown()){   //Apparently infinite while loops break things, so loop until enter is pressed
			
			Gyro.getAngleMode().fetchSample(AngleSamples, 0);   //Take a sample from the gyro and write to the AngleSamples Array starting at index 0
			text.drawString("Angle X is " + AngleSamples[0], 0, 3);  //Print Gyro Angle for debugging
	        Delay.msDelay(10);   //This may or may not be needed, but was in the tutorial I read
			
			output = PID.Compute(); 	//Calculate the output of the PID Alg
			
			//Set Motors to calculated output. If we have a negative error, then correct by driving forward. Else positive error, correct by driving back
			if(output < 0){
				Motor.A.setSpeed(output);
				Motor.B.setSpeed(output);
				Motor.A.forward();
				Motor.B.forward();
			} else {
				Motor.A.setSpeed(output);
				Motor.B.setSpeed(output);
				Motor.A.backward();
				Motor.B.backward();
			}
		}
	}
	
}


