package org.usfirst.frc.team1806.robot.subsystems;

import org.usfirst.frc.team1806.robot.Constants;
import org.usfirst.frc.team1806.robot.Robot;

public class SWATFlywheelController {
	//A flywheel controller based on the one written in LABVIEW for the 2013 FRC Season by Dillon Woollums
	//Implemented in JAVA by Dillon Woollums

	Constants constants;
	double current;
	double target;
	double cushioningZone;
	double min;
	double max;
	
	SWATFlywheelController(double smoothAdjustmentZone){
		//smooth adjustment zone is the +- area around the target rpm where smaller adjustments are made Suggested value: 50
		constants = new Constants();
		current = Robot.flywheelSS.getRPM();
		target = 0;
		min = 0;
		max = 0;
		cushioningZone = smoothAdjustmentZone;
	}
	
	public void set(double targetRPM, double minRPM, double maxRPM){
		//for 2017 applications I suggest setting target RPM at minRPM + (maxRPM-MinRPM) * .9 or so
		target = targetRPM;
		min = minRPM;
		max = maxRPM;
	}
	
	public double getInvertedPWMScaleOutput(double currentRPM, double targetRPM){
		//returns a value from -1 to 0
		return -getPWMScaleOutput();
	}
	
	public double getPWMScaleOutput(){
		current = Robot.flywheelSS.getRPM();
		//returns a value from 0 to 1
		
		if(target <= 0){
			return 0;
		}
		else if (Math.abs(current - target) > cushioningZone){
			//if we're outside the small adjustment zone
			return current - target > 0? 0.0:1.0; //return 0 if we're over the target, 1 if below
		}
		else{
			//we're in the smooth adjustment zone, use square roots to model upside down parabolic power band of motor
			//and use multipliers to make it speed up when below and slow down when above the target speed.
			if(current - target > 0){
				return Math.sqrt(target)/Math.sqrt(constants.maxFlywheelSpeed) * constants.rpmOverTargetMult;
			}
			else{
				return Math.sqrt(target)/Math.sqrt(constants.maxFlywheelSpeed) * constants.rpmUnderTargetMult;
			}
			}
		}
	public boolean isInRange(){
		current = Robot.flywheelSS.getRPM();
		return min < current && current < max;
	}
}
