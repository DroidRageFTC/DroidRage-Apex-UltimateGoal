package org.firstinspires.ftc.teamcode.auton.intake.red.left;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.PlaceWobbleGoal;
import org.firstinspires.ftc.teamcode.commands.drive.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.ShooterFeeder;
import org.firstinspires.ftc.teamcode.subsystems.ShooterWheels;
import org.firstinspires.ftc.teamcode.subsystems.Vision;
import org.firstinspires.ftc.teamcode.subsystems.WobbleGoalArm;

public class RedLeftOneCommand extends SequentialCommandGroup {
    public RedLeftOneCommand(Drivetrain drivetrain, ShooterWheels shooterWheels, ShooterFeeder feeder, Intake intake, WobbleGoalArm wobbleGoalArm, Vision vision, Telemetry telemetry) {
        final int HG_SPEED = 3600;
        final int POWERSHOT_SPEED = 3000;

        addCommands(
                new RedLeftShootingSequence(drivetrain, shooterWheels, feeder),

                new DriveForwardCommand(drivetrain, 40),
                new TurnToCommand(drivetrain, -90, true),
                new DriveForwardCommand(drivetrain, 5),
                new PlaceWobbleGoal(wobbleGoalArm),
                new DriveForwardCommand(drivetrain, 5),
                new TurnToCommand(drivetrain, 0, true),
                new DriveForwardCommand(drivetrain, -30)
                );
    }
}
    //new SplineCommand(drivetrain, new Vector2d(-42, 12), Math.toRadians(180)),
    //new DriveForwardCommand(drivetrain, -40),
    //new SplineCommand(drivetrain, new Vector2d(-30, 24), Math.toRadians(15), false),