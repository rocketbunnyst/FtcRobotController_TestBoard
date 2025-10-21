package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSimpleFSM extends SubsystemBase {

    //============Servos===========\\
    private final CRServo frontIntake;

    // ================== SENSORS ================== \\
    private final NormalizedColorSensor frontColorSens;

    public IntakeSimpleFSM(HardwareMap hardwareMap, Telemetry telemetry) {

        // ================== SERVOS ================== \\
        frontIntake = hardwareMap.get(CRServo.class, "frontIntake");

        frontIntake.setDirection(CRServo.Direction.FORWARD);

        // ================== SENSORS ================== \\
        frontColorSens = hardwareMap.get(NormalizedColorSensor.class, "frontColor");
    }


}
