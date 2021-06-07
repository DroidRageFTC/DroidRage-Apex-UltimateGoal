package org.firstinspires.ftc.teamcode.inperson.red.right;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.PlaceWobbleGoal;
import org.firstinspires.ftc.teamcode.commands.drive.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.TurnCommand;
import org.firstinspires.ftc.teamcode.commands.drive.TurnToCommand;
import org.firstinspires.ftc.teamcode.inperson.red.left.RedLeftShootingSequence;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.ShooterFeeder;
import org.firstinspires.ftc.teamcode.subsystems.ShooterWheels;
import org.firstinspires.ftc.teamcode.subsystems.Vision;
import org.firstinspires.ftc.teamcode.subsystems.WobbleGoalArm;

public class RedRightOneCommand extends SequentialCommandGroup {
    public RedRightOneCommand(Drivetrain drivetrain, ShooterWheels shooterWheels, ShooterFeeder feeder, Intake intake, WobbleGoalArm wobbleGoalArm, Vision vision, Telemetry telemetry) {
        final int HG_SPEED = 3800;
        final int POWERSHOT_SPEED = 3000;

        addCommands(
                new RedRightShootingSequence(drivetrain, shooterWheels, feeder),

                new DriveForwardCommand(drivetrain, -35),
                new TurnToCommand(drivetrain, -90, true),
                new DriveForwardCommand(drivetrain, -8),
                new PlaceWobbleGoal(wobbleGoalArm),
                new DriveForwardCommand(drivetrain, -4),
                new TurnCommand(drivetrain, 90),
                new DriveForwardCommand(drivetrain, 20)

                );
    }
}
    //new SplineCommand(drivetrain, new Vector2d(-42, 12), Math.toRadians(180)),
    //new DriveForwardCommand(drivetrain, -40),
    //new SplineCommand(drivetrain, new Vector2d(-30, 24), Math.toRadians(15), false),