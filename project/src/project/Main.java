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
		/*
		Motor.A.setSpeed(1000);
		Motor.A.forward();
		Motor.B.setSpeed(1000);
		Motor.B.forward();
		Thread.sleep(5000);
		*/
		Button.ENTER.waitForPressAndRelease();
		
		Gyro.reset();  //Zero the gyro
		Gyro.getAngleMode(); //Tell the gyro we are in angular velocity mode
		PID.SetTunings(kp, ki,kd); //Set out PIDs
		
		
		while(!Button.ENTER.isDown()){
			Gyro.getAngleMode().fetchSample(AngleSamples, 0);
			text.drawString("Angle X is " + AngleSamples[0], 0, 3);

	         Delay.msDelay(10);
			
			
			Thread.sleep(500);
			//Calculate the output of the PID Alg
			output = PID.Compute();
			
			//Set Motors to calculated output
			Motor.A.setSpeed(output);
			Motor.B.setSpeed(output);
		}
	}
	
}


