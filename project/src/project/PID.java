package project;

public class PID {

	
	/*working variables**/
	static float lastTime;
	static float Input, Output, Setpoint;
	static float errSum, lastErr;
	static float kp, ki, kd;
	
	public static void Compute(){
		/*How long since we last calculated*/
		float now = millis();
		float timeChange =(now - lastTime);
		/*Compute all the working error variables*/
		float error = Setpoint - Input;
		errSum += (error * timeChange);
		float dErr = (error - lastErr) / timeChange;
		/*Compute PID Output*/
		Output = kp * error + ki * errSum + kd * dErr;
		/*Remember some variables for next time*/
		lastErr = error;
		lastTime = now;
	}
	
	public static void SetTunings(float Kp, float Ki, float Kd){
		kp = Kp;
		ki = Ki;
		kd = Kd;
	}
}
