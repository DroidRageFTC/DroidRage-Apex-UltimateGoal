package org.firstinspires.ftc.teamcode.auton.intake.blue.right;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.auton.values.inside.InsideFourShootValues;
import org.firstinspires.ftc.teamcode.commands.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.commands.PlaceWobbleGoal;
import org.firstinspires.ftc.teamcode.commands.drive.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.TurnToCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.FeedRingsCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeStartCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.ShooterFeeder;
import org.firstinspires.ftc.teamcode.subsystems.ShooterWheels;
import org.firstinspires.ftc.teamcode.subsystems.Vision;
import org.firstinspires.ftc.teamcode.subsystems.WobbleGoalArm;


import org.firstinspires.ftc.teamcode.auton.values.inside.InsideFourValues;

public class BlueRightFourCommand extends SequentialCommandGroup {
    public BlueRightFourCommand(Drivetrain drivetrain, ShooterWheels shooterWheels, ShooterFeeder feeder, Intake intake, WobbleGoalArm wobbleGoalArm, Vision vision, Telemetry telemetry) {
        final int HG_SPEED = 3600;
        final int POWERSHOT_SPEED = 3000;

        InsideFourValues distance = new InsideFourValues();
        InsideFourValues angle = new InsideFourValues();

        addCommands(
                //shoot
                new InstantCommand(feeder::retractFeed),

                new InstantCommand(() -> shooterWheels.setShooterRPM(HG_SPEED), shooterWheels),

                new DriveForwardCommand(drivetrain, 60),
                new TurnToCommand(drivetrain, -12, true),

                // Shoot 3 rings
                new FeedRingsCommand(feeder, 5, 100),

                new InstantCommand(() -> shooterWheels.setShooterRPM(0), shooterWheels),

                // lift wobble goal so it doesnt collide with intake
                new InstantCommand(wobbleGoalArm::dropArm, wobbleGoalArm),

                // go back and intake more rings
                new TurnToCommand(drivetrain, 40,true),
                new IntakeStartCommand(intake),
                new DriveForwardCommand(drivetrain, -40),
                new IntakeStopCommand(intake),
                new DriveForwardCommand(drivetrain, 40),
                new TurnToCommand(drivetrain, -40,true),

                // shoot the rings picked up from intake
                new InstantCommand(() -> shooterWheels.setShooterRPM(HG_SPEED), shooterWheels),
                new WaitCommand(3000),
                new FeedRingsCommand(feeder, 5, 100),
                new InstantCommand(() -> shooterWheels.setShooterRPM(0), shooterWheels),

                //back to original position
                new TurnToCommand(drivetrain, 0,true),

                new DriveForwardCommand(drivetrain, distance.distanceOne),
                new TurnToCommand(drivetrain, -angle.angleOne, true),
                new DriveForwardCommand(drivetrain, distance.distanceTwo),
                new PlaceWobbleGoal(wobbleGoalArm),
                new DriveForwardCommand(drivetrain, distance.distanceThree),
                new TurnToCommand(drivetrain, angle.angleTwo, true),
                new DriveForwardCommand(drivetrain, distance.distanceFour)
                );
    }
}
    //new SplineCommand(drivetrain, new Vector2d(-30, 24), Math.toRadians(15), false)
    //new DriveForwardCommand(drivetrain, -40),
    //