package org.firstinspires.ftc.teamcode.utilities;

// Thanks to Kalipso-Robotics
//https://github.com/Kalipso-Robotics/FtcRobotController/tree/master/TeamCode

public enum AllianceSetup {

    RED(-1),
    BLUE(1);

    private final int polarity;

    AllianceSetup(int polarity) {
        this.polarity = polarity;
    }

    public int getPolarity() {
        return polarity;
    }
}
