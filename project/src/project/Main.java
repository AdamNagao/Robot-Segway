package project;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.HiTechnicGyro;
import lejos.utility.Stopwatch;

public class Main {

	static float kp = 1;
	static float ki = 1;
	static float kd = 1;
	static float output;
	static EV3GyroSensor Gyro = new EV3GyroSensor(SensorPort.S1);

	
	public static void main(String[] args){
		
		EV3 ev3brick =(EV3) BrickFinder.getLocal();
		TextLCD text = ev3brick.getTextLCD();
		
		text.drawString("Position the Robot for Calibration, then hit enter",0,0);
		Button.ENTER.waitForPressAndRelease();
		
		Gyro.reset();  //Zero the gyro
		Gyro.getAngleMode(); //Tell the gyro we are in angular velocity mode
		PID.SetTunings(kp, ki,kd); //Set out PIDs
		
		
		Button.ENTER.waitForPressAndRelease();
		while(true){
			//Calculate the output of the PID Alg
			PID.Compute();
			
			//Set Motors to calculated output
			Motor.A.setSpeed(output);
			Motor.B.setSpeed(output);
		}
	}
}


