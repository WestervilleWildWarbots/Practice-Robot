package org.usfirst.frc.team3591.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import com.ctre.CANTalon;

/**
 * This is a demo program showing how to use Mecanum control with the RobotDrive
 * class.
 */
public class Robot extends SampleRobot {
	RobotDrive robotDrive;

	// Channels for the wheels
	final int kFrontLeftChannel = 11;
	final int kRearLeftChannel = 12;
	final int kFrontRightChannel = 21;
	final int kRearRightChannel = 22;

	public CANTalon frontLeft = new CANTalon(kFrontLeftChannel);
	public CANTalon rearLeft = new CANTalon(kRearLeftChannel);
	public CANTalon frontRight = new CANTalon(kFrontRightChannel);
	public CANTalon rearRight = new CANTalon(kRearRightChannel);
	
	
	// The channel on the driver station that the joystick is connected to
	final int kJoystickChannel = 0;

	Joystick stick = new Joystick(kJoystickChannel);

	public Robot() {
		robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
		robotDrive.setInvertedMotor(MotorType.kFrontLeft, true); // invert the
																	// left side
																	// motors
		robotDrive.setInvertedMotor(MotorType.kRearLeft, true); // you may need
																// to change or
																// remove this
																// to match your
																// robot
		robotDrive.setExpiration(0.1);
	}

	/**
	 * Runs the motors with Mecanum drive.
	 */
	@Override
	public void operatorControl() {
		robotDrive.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {

			// Use the joystick X axis for lateral movement, Y axis for forward
			// movement, and Z axis for rotation.
			// This sample does not use field-oriented drive, so the gyro input
			// is set to zero.
			robotDrive.mecanumDrive_Cartesian(-stick.getX(), -stick.getY(), -stick.getZ() / 2, 0);

			Timer.delay(0.005); // wait 5ms to avoid hogging CPU cycles
		}
	}
}
