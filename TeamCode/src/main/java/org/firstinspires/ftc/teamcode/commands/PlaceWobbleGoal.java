package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.subsystems.WobbleGoalArm;

public class PlaceWobbleGoal extends SequentialCommandGroup {
    private WobbleGoalArm wobbleGoal;

    public PlaceWobbleGoal(WobbleGoalArm wobbleGoal) {
        addCommands(
                new InstantCommand(wobbleGoal::lowerArm, wobbleGoal),
                new WaitCommand(1000),
                new InstantCommand(wobbleGoal::openClaw, wobbleGoal),
                new WaitCommand(500),
                new InstantCommand(wobbleGoal::liftArm, wobbleGoal),
                new WaitCommand(300)
        );
    }
}
