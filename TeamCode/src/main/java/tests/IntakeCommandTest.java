package tests;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.IntakeStopTestCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeTestCommand;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.utilities.SampleCommandTeleop;

@TeleOp
public class IntakeCommandTest extends SampleCommandTeleop {

    private IntakeSubsystem intake;
    private IntakeTestCommand intakeTestCommand;
    private IntakeStopTestCommand intakeStopTestCommand;

    @Override
    public void onInit() {

        intake = new IntakeSubsystem(hardwareMap, telemetry);

        intakeTestCommand = new IntakeTestCommand(intake);
        intakeStopTestCommand = new IntakeStopTestCommand(intake);
    }

    @Override
    public void onStart() {

        g1.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(intakeTestCommand);

        g1.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(intakeStopTestCommand);

    }

    @Override
    public void onLoop() {
        intake.printTelemetry(telemetry);
        // Print Instructions every loop
        telemetry.addLine("INTAKE CONTROLS");
        telemetry.addLine("Press B to start Intake");
        telemetry.addLine("Press X to stop Intake");
        telemetry.addLine("Intake will run until stopped by Button OR Sensor");
    }

    @Override
    public void onStop() {

    }
}
