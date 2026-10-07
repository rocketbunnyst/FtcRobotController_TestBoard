package org.firstinspires.ftc.teamcode.subsystems.launcher;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

@Config
@TeleOp
public class SingleMotorSpeedControl extends LinearOpMode {
    public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(1.26, 0, 0, 12.6);

    public static double MOTOR_TICKS_PER_REV = 28;
    public static double MOTOR_MAX_RPM = 5400; //was 5400 when wheel came apart

    private final FtcDashboard dashboard = FtcDashboard.getInstance();

    private VoltageSensor batteryVoltageSensor;

    public static double targetVelo = 0.0;

    @Override
    public void runOpMode() throws InterruptedException {
        // Change my id
        DcMotorEx myMotor = hardwareMap.get(DcMotorEx.class, "motor");

        // Reverse as appropriate
        // myMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        for (LynxModule module : hardwareMap.getAll(LynxModule.class)) {
            module.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        MotorConfigurationType motorConfigurationType = myMotor.getMotorType().clone();
        motorConfigurationType.setAchieveableMaxRPMFraction(1.0);
        myMotor.setMotorType(motorConfigurationType);

        myMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        myMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        batteryVoltageSensor = hardwareMap.voltageSensor.iterator().next();
        setPIDFCoefficients(myMotor, MOTOR_VELO_PID);

        double lastKp = 0.0;
        double lastKi = 0.0;
        double lastKd = 0.0;
        double lastKf = getMotorVelocityF();

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        telemetry.addLine("Ready!");
        telemetry.update();
        telemetry.clearAll();

        waitForStart();

        if (isStopRequested()) return;

        while (!isStopRequested() && opModeIsActive()) {

            if(gamepad1.xWasPressed()) { targetVelo = 0;}
            if(gamepad1.bWasPressed()) { targetVelo = 500;}

            if(gamepad1.dpadDownWasPressed()) { targetVelo = targetVelo-25;}
            if(gamepad1.dpadUpWasPressed()) {  targetVelo = targetVelo+25;}

            if(targetVelo >= 1500) {targetVelo = 1500;} //stops it from going too fast

            myMotor.setVelocity(targetVelo);

            telemetry.addData("targetVelocity", targetVelo);
            telemetry.addLine("1500 is max allowed targetVelocity");
            telemetry.addLine("X to STOP, B to START, DpadUP/DOWN to change speed");

            double motorVelo = myMotor.getVelocity();
            telemetry.addData("velocity", motorVelo);
            telemetry.addData("error", targetVelo - motorVelo);

            setPIDFCoefficients(myMotor, MOTOR_VELO_PID);

            telemetry.update();
        }
    }

    private void setPIDFCoefficients(DcMotorEx motor, PIDFCoefficients coefficients) {
        motor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(
                coefficients.p, coefficients.i, coefficients.d, coefficients.f * 12 / batteryVoltageSensor.getVoltage()
        ));
    }

    public double getMotorVelocityF() {
        // see https://docs.google.com/document/d/1tyWrXDfMidwYyP_5H4mZyVgaEswhOC35gvdmP-V-5hA/edit#heading=h.61g9ixenznbx
        return 32767 * 60.0 / (MOTOR_MAX_RPM * MOTOR_TICKS_PER_REV);
    }
}
