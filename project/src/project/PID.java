package project;

import lejos.utility.Stopwatch;

public class PID {

	
	/*working variables**/
	static float lastTime;
	static float Input, Output; 
	static float Setpoint = 0;  //Setpoint is angle zero where the robot is standing straight up
	static float errSum, lastErr;
	static float kp, ki, kd;
	
	static Stopwatch sw = new Stopwatch(); 
	
	
	public static float Compute(){
		Input = Main.AngleSamples[0];   //Input is the most recent angle acquired by the gyro
		
		/*How long since we last calculated*/
		float now = sw.elapsed(); //elapsed(): time elapsed in milliseconds
		
		float timeChange =(now - lastTime);

		float error = Setpoint - Input;
		errSum += (error * timeChange);
		float dErr = (error - lastErr) / timeChange;

		Output = kp * error + ki * errSum + kd * dErr;

		lastErr = error;
		lastTime = now;
		return Output;
	}
	

	public static void SetTunings(float Kp, float Ki, float Kd){
		kp = Kp;
		ki = Ki;
		kd = Kd;
	}
}
