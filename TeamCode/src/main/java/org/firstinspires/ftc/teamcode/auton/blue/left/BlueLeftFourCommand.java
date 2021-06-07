package org.firstinspires.ftc.teamcode.auton.blue.left;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.PlaceWobbleGoal;
import org.firstinspires.ftc.teamcode.commands.drive.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.TurnCommand;
import org.firstinspires.ftc.teamcode.commands.drive.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.ShooterFeeder;
import org.firstinspires.ftc.teamcode.subsystems.ShooterWheels;
import org.firstinspires.ftc.teamcode.subsystems.Vision;
import org.firstinspires.ftc.teamcode.subsystems.WobbleGoalArm;

public class BlueLeftFourCommand extends SequentialCommandGroup {
    public BlueLeftFourCommand(Drivetrain drivetrain, ShooterWheels shooterWheels, ShooterFeeder feeder, Intake intake, WobbleGoalArm wobbleGoalArm, Vision vision, Telemetry telemetry) {
        final int HG_SPEED = 3600;
        final int POWERSHOT_SPEED = 3000;

        addCommands(
                new org.firstinspires.ftc.teamcode.auton.blue.left.BlueLeftShootingSequence(drivetrain, shooterWheels, feeder),

                new DriveForwardCommand(drivetrain, 45),
                new TurnToCommand(drivetrain, -45, true),
                new DriveForwardCommand(drivetrain, 10),
                new PlaceWobbleGoal(wobbleGoalArm),
                new TurnToCommand(drivetrain, 0, true),
                new DriveForwardCommand(drivetrain, -40)
                );
    }
}
    //new SplineCommand(drivetrain, new Vector2d(-30, 24), Math.toRadians(15), false)
    //new DriveForwardCommand(drivetrain, -40),
    //