package org.firstinspires.ftc.teamcode.subsystems.launcher;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utilities.Constants;

// Thanks to https://github.com/14468-undefined/14468-DECODE-V2/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/subsystem/ShooterSubsystem.java

public class Launcher extends SubsystemBase {
    //===========MOTORS==========\\
    private final MotorEx launcherRight;
//    private final MotorEx shooterLeft;

    //============Servos===========\\
    //private final Servo hood;

    int targetRPM = Constants.launcherConstants.FAR_ZONE_SHOT_RPM;

//    private Telemetry telemetry;
//    private HardwareMap hardwareMap;

    public Launcher(HardwareMap hardwareMap, Telemetry telemetry) {

        // ================== MOTORS ================== \\
        launcherRight = new MotorEx(hardwareMap, "motor");
//        shooterLeft = new MotorEx(hardwareMap, "launcherLeft");

        //set to vel control so its constant instead of just power
        launcherRight.setRunMode(MotorEx.RunMode.VelocityControl);
//        shooterLeft.setRunMode(MotorEx.RunMode.VelocityControl);

        //reverse
        launcherRight.setInverted(true);
//        shooterLeft.setInverted(false);

        // ================== SERVOS ================== \\
        //hood = hardwareMap.get(Servo.class, "hood");

    }

    public void spinUpRight(){
        launcherRight.setRunMode(Motor.RunMode.VelocityControl);
//        shooterLeft.setRunMode(Motor.RunMode.VelocityControl);

        double velocity = rpmToTicksPerSecond(targetRPM);
        launcherRight.setVelocity(velocity);
    }
    public void spinUpLeft(){

        launcherRight.setRunMode(Motor.RunMode.VelocityControl);
//        shooterLeft.setRunMode(Motor.RunMode.VelocityControl);

        double velocity = rpmToTicksPerSecond(targetRPM);
//        shooterLeft.setVelocity(velocity);
    }
    // Spin up to speed
    public void spinUp() {
        double velocity = rpmToTicksPerSecond(targetRPM);

        launcherRight.setRunMode(Motor.RunMode.VelocityControl);
//        shooterLeft.setRunMode(Motor.RunMode.VelocityControl);

        launcherRight.setVelocity(velocity);
//        shooterLeft.setVelocity(velocity);

    }

    public void spinUpReverse(){
        double velocity = rpmToTicksPerSecond(targetRPM);

        launcherRight.setRunMode(Motor.RunMode.VelocityControl);
//        shooterLeft.setRunMode(Motor.RunMode.VelocityControl);

        launcherRight.setVelocity(-velocity);
//        shooterLeft.setVelocity(-velocity);
    }

    public void setTargetRPM(int RPM){
        targetRPM = RPM;
    }


    // Stop both wheels
    public void stop() {

//        shooterLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);//coast if power is 0
        launcherRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);

//        shooterLeft.setRunMode(Motor.RunMode.RawPower);
        launcherRight.setRunMode(Motor.RunMode.RawPower);

//        shooterLeft.set(0);
        launcherRight.set(0);


    }

    //make sure they are close to right speed
    public boolean atSpeed() {
        double velocity = rpmToTicksPerSecond(targetRPM);

//        double avgVelocity = (shooterRight.getVelocity() + shooterLeft.getVelocity()) / 2.0; // use for 2 motors
        double avgVelocity = (launcherRight.getVelocity()); //use for one motor

        return Math.abs(avgVelocity - velocity) < 100;
    }

    // convert rpm to tps
    private static final double TICKS_PER_REV = 28; //TODO: Tune
    private double rpmToTicksPerSecond(double rpm) {
        return (rpm * TICKS_PER_REV) / 60.0;
    }
    private double tpstoRPM(double tps){
        return (tps * 60) / TICKS_PER_REV;
    }


    public int getCurrentRPM() {
        return targetRPM;
    }

    public void printTelemetry(Telemetry telemetry) {
        telemetry.addLine("LAUNCHER SUBSYSTEM");
        telemetry.addData("Target RPM", targetRPM);
        telemetry.addData("Right RPM", tpstoRPM(launcherRight.getVelocity()));
        //        telemetry.addData("Left RPM", tpstoRPM(shooterLeft.getVelocity()));
        telemetry.addData("Velocity in TPS", launcherRight.getVelocity());
        telemetry.addData("At Speed?", atSpeed());
        telemetry.update();
    }

}
