package org.usfirst.frc.team1806.robot.utils;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DriveHelperTest {

	private static double ACCEPTABLE_DELTA = 0.0001;
	@Rule
	ExpectedException thrownException = ExpectedException.none();
	
	@Test
	public void testDriveHelperHappyPathExp2(){
		DriveHelper testHelper = new DriveHelper(.2, 3);
		assertEquals(testHelper.processDriveInput(-1), -1.0, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(-0.5), -0.25, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(-0.201),-0.0404, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(-0.02), -.04, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(-0.19), 0, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(-0.10), 0, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(-0.0001), 0, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(0), 0, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(0.0001), 0, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(0.10), 0, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(0.19), 0, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(0.2),.04, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(0.201), 0.0404, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(0.5), 0.25, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(1), 1.0, ACCEPTABLE_DELTA);
		
	}
	
	@Test
	public void testDriveHelperHappyPathExp3(){
		DriveHelper testHelper = new DriveHelper(.2, 3);
		assertEquals(testHelper.processDriveInput(-1), -1.0, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(-0.5), -0.125, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(-0.201),-0.0081, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(-0.02), -.008, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(-0.19), 0, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(-0.10), 0, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(-0.0001), 0, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(0), 0, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(0.0001), 0, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(0.10), 0, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(0.19), 0, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(0.2),.008, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(0.201), 0.0081, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(0.5), 0.125, ACCEPTABLE_DELTA);
		assertEquals(testHelper.processDriveInput(1), 1.0, ACCEPTABLE_DELTA);
		
	}
	
	@Test
	public void testConstructorDeadZoneWayTooLow(){
		thrownException.expect(IllegalArgumentException.class);
		new DriveHelper(-20, 3);
	}
	
	@Test
	public void testConstructorDeadZoneTooLow(){
		thrownException.expect(IllegalArgumentException.class);
		new DriveHelper(-.01, 3);
	}
	
	@Test
	public void testDeadZoneZero(){
		DriveHelper testHelper  = new DriveHelper(0, 3);
		assertEquals(testHelper.processDriveInput(0.02), 0.000008, 0.0000001);
	}
	
	@Test
	public void testDeadZoneNearlyOne(){
		DriveHelper testHelper  = new DriveHelper(0.99, 3);
		assertEquals(testHelper.processDriveInput(0.98), 0, ACCEPTABLE_DELTA);
	}
	
	@Test
	public void testConstructorDeadZoneOne(){
		thrownException.expect(IllegalArgumentException.class);
		new DriveHelper(1, 3);
	}
	
	@Test
	public void testConstructorDeadZoneWayTooHigh(){
		thrownException.expect(IllegalArgumentException.class);
		new DriveHelper(20, 3);
	}
	
	@Test
	public void testConstructorPowerZero(){
		thrownException.expect(IllegalArgumentException.class);
		new DriveHelper(3, 0);
	}

}
