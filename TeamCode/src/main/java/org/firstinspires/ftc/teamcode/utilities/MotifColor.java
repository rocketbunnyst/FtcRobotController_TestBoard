package org.firstinspires.ftc.teamcode.utilities;

// Thanks to Kalipso-Robotics
//https://github.com/Kalipso-Robotics/FtcRobotController/tree/master/TeamCode

public enum MotifColor {
    PURPLE, GREEN, NONE;

    public boolean isPurpleOrGreen() {
        return this == MotifColor.PURPLE || this == MotifColor.GREEN;
    }
}
