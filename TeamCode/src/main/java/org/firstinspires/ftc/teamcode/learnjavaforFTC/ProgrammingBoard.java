package org.firstinspires.ftc.teamcode.learnjavaforFTC;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ProgrammingBoard {

    // Declare variables and motors/sensors
    private DigitalChannel touchSensor;
    private DcMotor motor;
    private double ticksPerRotation;
    private Servo servo;

    public void init(HardwareMap hwMap) {

        // Initialize motors/sensors (tell the program what named port to look for on the robot)
        // and give variables their initial values

        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);

        motor = hwMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ticksPerRotation = motor.getMotorType().getTicksPerRev();

        servo = hwMap.get(Servo.class, "servo");
    }

    // Define Additional Methods (other than the native ones) that can be called by your OpModes

    // Finds out if the touch sensor is pressed
    public boolean isTouchSensorPressed() {
        return !touchSensor.getState();
    }

    // Sets the speed of the motor to the number passed
    public void setMotorSpeed(double speed) {
        motor.setPower(speed);
    }

    // Returns the number of times the motor has fully rotated
    public double getMotorRotations() {
        return motor.getCurrentPosition() / ticksPerRotation;
    }

    // Sets the servo position to the number passed
    public void setServoPosition(double position){
        servo.setPosition(position);
    }
}
