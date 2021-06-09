package org.firstinspires.ftc.teamcode.auton.blue.right;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.drive.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.TurnToCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.FeedRingsCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.ShooterFeeder;
import org.firstinspires.ftc.teamcode.subsystems.ShooterWheels;

public class BlueRightShootingSequence extends SequentialCommandGroup {
    public BlueRightShootingSequence(Drivetrain drivetrain, ShooterWheels shooterWheels, ShooterFeeder feeder) {
        final int HG_SPEED = 3600;
        final int POWERSHOT_SPEED = 3000;

        addCommands(
                // Setup
                new InstantCommand(feeder::retractFeed),


                //new WaitCommand(10000),
                new InstantCommand(() -> shooterWheels.setShooterRPM(HG_SPEED), shooterWheels),

                new DriveForwardCommand(drivetrain, 60),
                new TurnToCommand(drivetrain, -12, true),

                // Shoot 3 rings
                new FeedRingsCommand(feeder, 5, 100),
                
                new InstantCommand(() -> shooterWheels.setShooterRPM(0), shooterWheels),

                new TurnToCommand(drivetrain, 0,true)
                );
    }
}
    //new SplineCommand(drivetrain, new Vector2d(-42, 12), Math.toRadians(180)),
    //new DriveForwardCommand(drivetrain, -40),
    //new TurnToCommand(drivetrain, 170),