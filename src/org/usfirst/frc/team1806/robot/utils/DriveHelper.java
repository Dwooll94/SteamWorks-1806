package org.usfirst.frc.team1806.robot.utils;

/**
 * @author Dillon Woollums
 * @date 2017-10-29
 * Drive helper applies the deadzone and any exponentiation to the drive input as needed.
 */
public class DriveHelper {
	
	private static String INV_ARG_EXCEPTION_MESSAGE = "Fatal Exception, input argument to DriveHelper was invalid:";
	private double deadZone;
	private double inputExponent;

	/**
	 * Instantiates a new drive helper
	 *
	 * @param deadZone the dead zone for the axis.
	 * @param inputExponent the input exponentiation desire for the axis.
	 * @throws IllegalArgumentException if {@code deadZone} is less than zero or greater than or equal to 1, or if inputExponent is 0.
	 */
	public DriveHelper(double deadZone, double inputExponent){
		if(deadZone < 0 || deadZone >= 1){
			throw new IllegalArgumentException(INV_ARG_EXCEPTION_MESSAGE + "deadZone");
		}
		if(inputExponent == 0){
			throw new IllegalArgumentException(INV_ARG_EXCEPTION_MESSAGE + "inputExponent");
		}
		this.deadZone = deadZone;
		this.inputExponent = inputExponent;
	}

	/**
	 * Process drive input.
	 *
	 * @param input the value of the input from the controller [-1,1]
	 * @return a double representing the filtered input
	 */
	public double processDriveInput(double input){
		if(input > 0){
			return Math.abs(applyControlExponentiation(applyDeadZone(input)));
		}
		else {
			return -Math.abs(applyControlExponentiation(applyDeadZone(input)));
		}
	}
	
	private double applyDeadZone(double input){
		if(Math.abs(input) < deadZone){
			return 0;
		}
		return input;
	}
	
	private double applyControlExponentiation(double input){
		return Math.pow(applyDeadZone(input), inputExponent);
	}
}
