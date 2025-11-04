package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem;

public class IntakeStopTestCommand extends SequentialCommandGroup {

    public IntakeStopTestCommand(IntakeSubsystem intake) {

        addCommands(
                new RunCommand(intake::stopFront)
        );

        addRequirements(intake);
    }
}
