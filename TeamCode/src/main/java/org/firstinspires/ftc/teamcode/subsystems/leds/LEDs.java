package org.firstinspires.ftc.teamcode.subsystems.leds;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.HashMap;
import java.util.Map;

/** Maps LED mode to Blinkin patterns. */
public class LEDs implements LedsInterface{

    RevBlinkinLedDriver blinkin;

    private static final Map<LedMode, RevBlinkinLedDriver.BlinkinPattern> modeLookup = new HashMap<>();

    // TEAM_COLORS, ALLIANCE_RED, ALLIANCE_BLUE, INTAKING_FRONT, INTAKING_REAR, INTAKING_THRU,
    // MOTIF_DETECTED, LAUNCHER_ATSPEED, GREEN_NEXT, PURPLE_NEXT, ELEVATING, BLACK

    static {
        modeLookup.put(LedMode.TEAM_COLORS, RevBlinkinLedDriver.BlinkinPattern.RAINBOW_FOREST_PALETTE);
        modeLookup.put(LedMode.ALLIANCE_RED, RevBlinkinLedDriver.BlinkinPattern.BREATH_RED);
    }

    public LEDs(HardwareMap hardwareMap) {
        blinkin = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");
    }

    @Override
    public void setMode(LedMode mode) {
        if (modeLookup.containsKey(mode)) {
            blinkin.setPattern(modeLookup.get(mode));
        } else {
            blinkin.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
        }
    }
}
