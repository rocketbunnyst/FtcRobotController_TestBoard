package org.firstinspires.ftc.teamcode.utilities;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.leds.LEDs;
import org.firstinspires.ftc.teamcode.subsystems.leds.LedsInterface;
import org.firstinspires.ftc.teamcode.utilities.SampleCommandTeleop;

@TeleOp
public class LedTestTeleop extends SampleCommandTeleop {
    private LEDs leds;

    @Override
    public void onInit() {
        leds = new LEDs(hardwareMap);
    }

    @Override
    public void onStart() {
    g1.getGamepadButton(GamepadKeys.Button.X).whenPressed(() -> leds.setMode(LedsInterface.LedMode.TEAM_COLORS));
    g1.getGamepadButton(GamepadKeys.Button.Y).whenPressed(() -> leds.setMode(LedsInterface.LedMode.ALLIANCE_BLUE));
    g1.getGamepadButton(GamepadKeys.Button.A).whenPressed(() -> leds.setMode(LedsInterface.LedMode.INTAKING_FRONT));
        g1.getGamepadButton(GamepadKeys.Button.B).whenPressed(() -> leds.setMode(LedsInterface.LedMode.INTAKING_REAR));
        g1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(() -> leds.setMode(LedsInterface.LedMode.INTAKING_THRU));
    g1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(() -> leds.setMode(LedsInterface.LedMode.ELEVATING));
        g1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(() -> leds.setMode(LedsInterface.LedMode.LAUNCHER_ATSPEED));
        g1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(() -> leds.setMode(LedsInterface.LedMode.BLACK));
        g1.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(() -> leds.setMode(LedsInterface.LedMode.MOTIF_DETECTED));
        }

    @Override
    public void onLoop() {
        telemetry.addLine("LED CONTROLS");
        telemetry.addLine("Press X for team colors");
        telemetry.addLine("Press Y for alliance blue");
        telemetry.addLine("Press A for intaking front");
        telemetry.addLine("Press the right bumper for elevating");
        telemetry.addLine("Press the left bumper for launcher at speed");
    }
    @Override
    public void onStop() {}
}
