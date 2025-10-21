package tests;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.shooter.Shooter;
import org.firstinspires.ftc.teamcode.utilities.SampleCommandTeleop;

@TeleOp
public class ShooterRPMTesting extends SampleCommandTeleop {

    private Shooter shooter;

//    private FtcDashboard dash; //enable once in main Roadrunner project

    @Override
    public void onInit() {
        shooter = new Shooter(hardwareMap, telemetry);
    }

    @Override
    public void onStart() {
        //DPAD_UP = RPM + 100; DPAD_DOWN = RPM - 100;
        g1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(() -> shooter.setTargetRPM(shooter.getCurrentRPM() - 100));
        g1.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(() -> shooter.setTargetRPM(shooter.getCurrentRPM() + 100));

        //X = SPIN UP SHOOTER
        g1.getGamepadButton(GamepadKeys.Button.X).whileHeld(shooter::spinUp);
        g1.getGamepadButton(GamepadKeys.Button.Y).whileHeld(shooter::stop);




    }

    @Override
    public void onLoop() {
        // Print intake telemetry every loop
        shooter.printTelemetry(telemetry);

        telemetry.addLine("SHOOTER CONTROLS");
        telemetry.addLine("Press X to spin up shooter");
        telemetry.addLine("Press again or Hold X to update target RPM");
        telemetry.addLine("Press dpad Up to increase RPM");
        telemetry.addLine("Press dpad Down to decrease RPM");
        telemetry.addLine("Press Y to stop shooter");
        telemetry.addLine();
    }

    @Override
    public void onStop() {
        shooter.stop();
    }

}