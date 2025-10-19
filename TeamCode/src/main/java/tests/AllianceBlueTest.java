package tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.utilities.AllianceSetup;

//The only thing this does is switch alliance from Red to Blue on the main use file.
//Can use this to start a version of Alliance Red Test that has alliance as Blue!

@TeleOp
public class AllianceBlueTest extends AllianceRedTest{
    public AllianceBlueTest() {
        allianceSetup = AllianceSetup.BLUE;
    }

}
