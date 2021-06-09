package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

public class IntakeStartCommand extends SequentialCommandGroup {
    private Intake intake;

    public IntakeStartCommand(Intake intake) {
        addCommands(
                new InstantCommand(intake::intake, intake)
        );
    }
}
