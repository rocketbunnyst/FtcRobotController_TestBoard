package org.firstinspires.ftc.teamcode.utilities;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.launcher.Launcher;

public abstract class SampleCommandTeleop extends LinearOpMode {
    public GamepadEx g1;
    public GamepadEx g2;
//    public BaseRobot robot;
    public Launcher shooter;
    public IntakeSubsystem intake;

    @Override
    public void runOpMode() throws InterruptedException {
        g1 = new GamepadEx(gamepad1);
        g2 = new GamepadEx(gamepad2);

//        robot = new BaseRobot(hardwareMap, new Pose2d(0,0,0));
       shooter = new Launcher(hardwareMap, telemetry);
       intake = new IntakeSubsystem(hardwareMap, telemetry);

        onInit();
        waitForStart();
        onStart();
        while(opModeIsActive() && !isStopRequested()){
            onLoop();
            g1.readButtons();
            g2.readButtons();
            CommandScheduler.getInstance().run();

        }
        onStop();
        CommandScheduler.getInstance().reset();
    }

    /**
     * This method is run once upon the initialization of the Teleop
     */
    public abstract void onInit();

    /**
     * This method is run once upon start of the Teleop
     */
    public abstract void onStart();

    /**
     * This method is repeated as long as the Teleop is active
     */
    public abstract void onLoop();

    /**
     * This method is run if the Teleop is manually stopped
     */
    public abstract void onStop();

}

