package org.firstinspires.ftc.teamcode.subsystems.shooter;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utilities.Constants;

// Thanks to https://github.com/14468-undefined/14468-DECODE-V2/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/subsystem/ShooterSubsystem.java

public class Shooter extends SubsystemBase {
    //===========MOTORS==========\\
    private final MotorEx shooterRight;
//    private final MotorEx shooterLeft;

    //============Servos===========\\
    //private final Servo hood;

    int targetRPM = Constants.shooterConstants.FAR_ZONE_SHOT_RPM;

//    private Telemetry telemetry;
//    private HardwareMap hardwareMap;

    public Shooter(HardwareMap hardwareMap, Telemetry telemetry) {

        // ================== MOTORS ================== \\
        shooterRight = new MotorEx(hardwareMap, "motor");
//        shooterLeft = new MotorEx(hardwareMap, "shooterLeft");

        //set to vel control so its constant instead of just power
        shooterRight.setRunMode(MotorEx.RunMode.VelocityControl);
//        shooterLeft.setRunMode(MotorEx.RunMode.VelocityControl);

        //reverse
        shooterRight.setInverted(false);
//        shooterLeft.setInverted(false);

        // ================== SERVOS ================== \\
        //hood = hardwareMap.get(Servo.class, "hood");

    }

    public void spinUpRight(){
        shooterRight.setRunMode(Motor.RunMode.VelocityControl);
//        shooterLeft.setRunMode(Motor.RunMode.VelocityControl);

        double velocity = rpmToTicksPerSecond(targetRPM);
        shooterRight.setVelocity(velocity);
    }
    public void spinUpLeft(){

        shooterRight.setRunMode(Motor.RunMode.VelocityControl);
//        shooterLeft.setRunMode(Motor.RunMode.VelocityControl);

        double velocity = rpmToTicksPerSecond(targetRPM);
//        shooterLeft.setVelocity(velocity);
    }
    // Spin up to speed
    public void spinUp() {
        double velocity = rpmToTicksPerSecond(targetRPM);

        shooterRight.setRunMode(Motor.RunMode.VelocityControl);
//        shooterLeft.setRunMode(Motor.RunMode.VelocityControl);

        shooterRight.setVelocity(velocity);
//        shooterLeft.setVelocity(velocity);

    }

    public void spinUpReverse(){
        double velocity = rpmToTicksPerSecond(targetRPM);

        shooterRight.setRunMode(Motor.RunMode.VelocityControl);
//        shooterLeft.setRunMode(Motor.RunMode.VelocityControl);

        shooterRight.setVelocity(-velocity);
//        shooterLeft.setVelocity(-velocity);
    }

    public void setTargetRPM(int RPM){
        targetRPM = RPM;
    }


    // Stop both wheels
    public void stop() {

//        shooterLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);//coast if power is 0
        shooterRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);

//        shooterLeft.setRunMode(Motor.RunMode.RawPower);
        shooterRight.setRunMode(Motor.RunMode.RawPower);

//        shooterLeft.set(0);
        shooterRight.set(0);


    }

    //make sure they are close to right speed
    public boolean atSpeed() {
        double velocity = rpmToTicksPerSecond(targetRPM);

//        double avgVelocity = (shooterRight.getVelocity() + shooterLeft.getVelocity()) / 2.0; // use for 2 motors
        double avgVelocity = (shooterRight.getVelocity()); //use for one motor

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
        telemetry.addLine("SHOOTER SUBSYSTEM");
        telemetry.addData("Target RPM", targetRPM);
        telemetry.addData("Right RPM", tpstoRPM(shooterRight.getVelocity()));
        //        telemetry.addData("Left RPM", tpstoRPM(shooterLeft.getVelocity()));
        telemetry.addData("Velocity in TPS", shooterRight.getVelocity());
        telemetry.addData("At Speed?", atSpeed());
        telemetry.update();
    }

}
