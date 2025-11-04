package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.utilities.Constants;

public class IntakeSubsystem extends SubsystemBase {

    //============Servos===========\\
    private final CRServo frontIntake;


    // ================== SENSORS ================== \\
    private final RevColorSensorV3 frontColorSens;

    double possessionDistance = Constants.intakeConstants.DISTANCE_FOR_POSSESSION;
    double intaking = Constants.intakeConstants.INTAKE_POWER;
    double outtaking = Constants.intakeConstants.REVERSE_INTAKE_POWER;

    boolean possession = false; // Variable telling whether we have possession of a game piece or not

    //=========== TELEMETRY ===========\\
    private final Telemetry telemetry;

    public IntakeSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {

        this.telemetry = telemetry;

        // ================== SERVOS ================== \\
        frontIntake = hardwareMap.get(CRServo.class, "frontIntake");

        frontIntake.setDirection(CRServo.Direction.FORWARD);

        // ================== SENSORS ================== \\
        frontColorSens = hardwareMap.get(RevColorSensorV3.class, "frontColor");

    }

    //============== CONTROL METHODS ==============\\

    // DISTANCE SENSOR METHODS ==========================\\

    public boolean frontPossession() { // returns true if there is an artifact in distance
        possession = frontColorSens.getDistance(DistanceUnit.CM) < possessionDistance;
        return possession;
    }

    // IN-BOUND METHODS ==========================\\
    public void inboundFront() {
        frontIntake.setPower(intaking);
    }

    // OUT-BOUND METHODS ==========================\\
    public void outboundFront() {
        frontIntake.setPower(outtaking);
    }

    // STOPPING METHODS ==========================\\
    public void stopFront() {
        frontIntake.setPower(0);
    }

    public void printTelemetry(Telemetry telemetry) {
        telemetry.addLine("INTAKE SUBSYSTEM");
        telemetry.addData("Front Sensor Distance", frontColorSens.getDistance(DistanceUnit.CM));
        telemetry.update();
    }

}
