package org.firstinspires.ftc.teamcode.auton.intake.intaketestRemovable;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.IntakeStartCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

public class IntakeOP extends SequentialCommandGroup {
    public IntakeOP(Intake intake, Telemetry telemetry) {
        addCommands(
                new IntakeStartCommand(intake),
                new WaitCommand(3000),
                new IntakeStopCommand(intake)
        );
    }
}
