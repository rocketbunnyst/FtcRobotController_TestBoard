package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem;

public class IntakeTestCommand extends SequentialCommandGroup {

    public IntakeTestCommand(IntakeSubsystem intake) {

        addCommands(
                new InstantCommand(intake::inboundFront),
                new WaitUntilCommand(intake::frontPossession),
                new InstantCommand(intake::stopFront)
        );

        addRequirements(intake);
    }
}
