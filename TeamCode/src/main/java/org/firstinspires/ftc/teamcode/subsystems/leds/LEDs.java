package org.firstinspires.ftc.teamcode.subsystems.leds;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.HashMap;
import java.util.Map;

/** Maps LED mode to Blinkin patterns. */
public class LEDs implements LedsInterface{

    RevBlinkinLedDriver blinkin;
    LedMode currentMode;
    RevBlinkinLedDriver.BlinkinPattern currentPattern;

    private static final Map<LedMode, RevBlinkinLedDriver.BlinkinPattern> modeLookup = new HashMap<>();

    // TEAM_COLORS, ALLIANCE_RED, ALLIANCE_BLUE,INTAKING_FRONT, INTAKING_REAR,INTAKING_THRU,
    // MOTIF_DETECTED, LAUNCHER_ATSPEED, GREEN_NEXT, PURPLE_NEXT, ELEVATING, BLACK

    static {
        modeLookup.put(LedMode.TEAM_COLORS, RevBlinkinLedDriver.BlinkinPattern.RAINBOW_FOREST_PALETTE);
        modeLookup.put(LedMode.ALLIANCE_RED, RevBlinkinLedDriver.BlinkinPattern.BREATH_RED);
        modeLookup.put(LedMode.ALLIANCE_BLUE, RevBlinkinLedDriver.BlinkinPattern.BREATH_BLUE);
        modeLookup.put(LedMode.INTAKING_FRONT, RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_FOREST_PALETTE );
        modeLookup.put(LedMode.INTAKING_REAR, RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_OCEAN_PALETTE);
        modeLookup.put(LedMode.INTAKING_THRU, RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_RAINBOW_PALETTE);
        modeLookup.put(LedMode.GREEN_NEXT, RevBlinkinLedDriver.BlinkinPattern.DARK_GREEN);
        modeLookup.put(LedMode.PURPLE_NEXT, RevBlinkinLedDriver.BlinkinPattern.VIOLET);
        modeLookup.put(LedMode.BLACK, RevBlinkinLedDriver.BlinkinPattern.BLACK);
        modeLookup.put(LedMode.ELEVATING, RevBlinkinLedDriver.BlinkinPattern.STROBE_GOLD);
        modeLookup.put(LedMode.LAUNCHER_ATSPEED, RevBlinkinLedDriver.BlinkinPattern.RAINBOW_RAINBOW_PALETTE);
        modeLookup.put(LedMode.MOTIF_DETECTED, RevBlinkinLedDriver.BlinkinPattern.CP1_2_TWINKLES);
    }

    public LEDs(HardwareMap hardwareMap) {
        blinkin = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");
    }

    @Override
    public void setMode(LedMode mode) {
        if (modeLookup.containsKey(mode)) {
            blinkin.setPattern(modeLookup.get(mode));
            currentPattern = modeLookup.get(mode);
            currentMode = mode;
        } else {
            blinkin.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
            currentMode = LedMode.BLACK;
            currentPattern = RevBlinkinLedDriver.BlinkinPattern.BLACK;
        }
    }

    public LedMode getCurrentMode() {
        return currentMode;
    }

    public RevBlinkinLedDriver.BlinkinPattern getCurrentPattern() {
        return currentPattern;
    }
}
