package tests;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.launcher.Launcher;
import org.firstinspires.ftc.teamcode.utilities.SampleCommandTeleop;

@TeleOp
public class LauncherRPMTesting extends SampleCommandTeleop {

    private Launcher launcher;

//    private FtcDashboard dash; //enable once in main Roadrunner project

    @Override
    public void onInit() {
        launcher = new Launcher(hardwareMap, telemetry);
    }

    @Override
    public void onStart() {
        //DPAD_UP = RPM + 100; DPAD_DOWN = RPM - 100;
        g1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(() -> launcher.setTargetRPM(launcher.getLauncherRPM() - 100));
        g1.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(() -> launcher.setTargetRPM(launcher.getLauncherRPM() + 100));

        //X = SPIN UP SHOOTER
        g1.getGamepadButton(GamepadKeys.Button.X).whileHeld(launcher::spinUp);
        g1.getGamepadButton(GamepadKeys.Button.Y).whileHeld(launcher::eStop);

    }

    @Override
    public void onLoop() {
        // Print intake telemetry every loop
        launcher.printTelemetry(telemetry);

        telemetry.addLine("LAUNCHER CONTROLS");
        telemetry.addLine("Press X to spin up launcher");
        telemetry.addLine("Press again or Hold X to update target RPM");
        telemetry.addLine("Press dpad Up to increase RPM");
        telemetry.addLine("Press dpad Down to decrease RPM");
        telemetry.addLine("Press Y to stop launcher");
        telemetry.addLine();
    }

    @Override
    public void onStop() {
        launcher.eStop();
    }

}