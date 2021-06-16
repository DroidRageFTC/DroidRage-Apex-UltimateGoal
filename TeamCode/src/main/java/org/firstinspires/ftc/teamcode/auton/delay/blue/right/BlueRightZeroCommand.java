package org.firstinspires.ftc.teamcode.auton.delay.blue.right;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

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

import org.firstinspires.ftc.teamcode.auton.values.inside.InsideZeroValues;
import org.firstinspires.ftc.teamcode.auton.values.delay;

public class BlueRightZeroCommand extends SequentialCommandGroup {
    public BlueRightZeroCommand(Drivetrain drivetrain, ShooterWheels shooterWheels, ShooterFeeder feeder, Intake intake, WobbleGoalArm wobbleGoalArm, Vision vision, Telemetry telemetry) {
        final int HG_SPEED = 3600;
        final int POWERSHOT_SPEED = 3000;

        InsideZeroValues distance = new InsideZeroValues();
        InsideZeroValues angle = new InsideZeroValues();
        delay delay = new delay();

        addCommands(
                new WaitCommand(delay.insideZeroDelay*1000),
                
                new BlueRightShootingSequence(drivetrain, shooterWheels, feeder),

                //Place Wobble Goal
                new DriveForwardCommand(drivetrain, distance.distanceOne),
                new TurnToCommand(drivetrain, -angle.angleOne, true),
                new DriveForwardCommand(drivetrain, angle.distanceTwo),
                new TurnToCommand(drivetrain, angle.angleTwo),
                new DriveForwardCommand(drivetrain, distance.distanceThree),
                new PlaceWobbleGoal(wobbleGoalArm),
                new DriveForwardCommand(drivetrain, distance.distanceFour),
                new TurnToCommand(drivetrain, -angle.angleThree, true),
                new DriveForwardCommand(drivetrain, distance.distanceFive),
                new TurnToCommand(drivetrain, angle.angleFour),
                new DriveForwardCommand(drivetrain, distance.distanceSix)
                );
    }
}
    //new SplineCommand(drivetrain, new Vector2d(-42, 12), Math.toRadians(180)),
    //new DriveForwardCommand(drivetrain, -40),
    //new TurnToCommand(drivetrain, 170),