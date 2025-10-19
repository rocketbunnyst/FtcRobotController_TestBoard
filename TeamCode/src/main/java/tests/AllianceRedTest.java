package tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.utilities.AllianceSetup;

@TeleOp
public class AllianceRedTest extends OpMode {
    protected AllianceSetup allianceSetup = AllianceSetup.RED; // Default value is red, unless started from the Blue teleop, in which case it changes to Blue.
    //Use getPolarity as a factor or indicator of which alliance

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        telemetry.addData("Alliance",allianceSetup);
        telemetry.addData("Alliance Polarity", allianceSetup.getPolarity());
    }
}
