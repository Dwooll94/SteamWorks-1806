package org.usfirst.frc.team1806.robot.commands.drivetrain;

import org.usfirst.frc.team1806.robot.OI;
import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.States;
import org.usfirst.frc.team1806.robot.Commands.DrivingRequest;
import org.usfirst.frc.team1806.robot.States.Driving;
import org.usfirst.frc.team1806.robot.States.Shifter;
import org.usfirst.frc.team1806.robot.commands.VibrateForSeconds;
import org.usfirst.frc.team1806.robot.utils.DriveHelper;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class InverseDrive extends Command {
    States states;
    double deadZone = .2;
    
    private DriveHelper throttleHelper;
    private DriveHelper steeringHelper;
    
    public InverseDrive() {
    	states = new States();
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveSS);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	states.drivingTracker = Driving.INVERSE;
    	throttleHelper = new DriveHelper(deadZone, 2);
    	steeringHelper = new DriveHelper(deadZone, 2);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	new VibrateForSeconds(.3, 1).start();
		if(states.drivingTracker == Driving.INVERSE){
			Robot.driveSS.execute(-throttleHelper.processDriveInput(Robot.oi.dlsY), steeringHelper.processDriveInput(Robot.oi.drsX));
		} 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.states.drivingTracker = States.Driving.DRIVING;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.states.drivingTracker = States.Driving.DRIVING;
    }
}
