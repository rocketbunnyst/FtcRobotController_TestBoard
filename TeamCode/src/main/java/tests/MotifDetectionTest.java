package tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.vision.MotifCamera;
import org.firstinspires.ftc.teamcode.subsystems.vision.MotifDetection;

@Disabled
@TeleOp
public class MotifDetectionTest extends LinearOpMode {
    MotifDetection motifDetection;
    MotifCamera motifCamera;

    @Override
    public void runOpMode() throws InterruptedException {
        motifCamera = new MotifCamera(hardwareMap);
        motifDetection = new MotifDetection(motifCamera);

        waitForStart();
        while (opModeIsActive()) {
            motifDetection.updateCheckDone();
            telemetry.addData("Motif Pattern", motifDetection.getMotifPattern());
            if (motifDetection.getIsDone()
//                    || motifDetection.hasTimedOut()
            ){
                break;
            }
        }
    motifDetection.close();
    }
}
