package org.firstinspires.ftc.teamcode.auton.delayintake.blue.left;

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

import org.firstinspires.ftc.teamcode.auton.values.outside.OutsideFourValues;

public class BlueLeftFourCommand extends SequentialCommandGroup {
    public BlueLeftFourCommand(Drivetrain drivetrain, ShooterWheels shooterWheels, ShooterFeeder feeder, Intake intake, WobbleGoalArm wobbleGoalArm, Vision vision, Telemetry telemetry) {
        final int HG_SPEED = 3600;
        final int POWERSHOT_SPEED = 3000;

        OutsideFourValues distance = new OutsideFourValues();
        OutsideFourValues angle = new OutsideFourValues();

        addCommands(
                new BlueLeftShootingSequence(drivetrain, shooterWheels, feeder),

                new DriveForwardCommand(drivetrain, distance.distanceOne),
                new TurnToCommand(drivetrain, -angle.angleOne, true),
                new DriveForwardCommand(drivetrain, distance.distanceTwo),
                new PlaceWobbleGoal(wobbleGoalArm),
                new TurnToCommand(drivetrain, angle.angleTwo, true),
                new DriveForwardCommand(drivetrain, distance.distanceThree)
                );
    }
}
    //new SplineCommand(drivetrain, new Vector2d(-30, 24), Math.toRadians(15), false)
    //new DriveForwardCommand(drivetrain, -40),
    //