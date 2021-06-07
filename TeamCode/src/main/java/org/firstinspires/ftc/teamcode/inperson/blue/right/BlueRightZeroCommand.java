package org.firstinspires.ftc.teamcode.inperson.blue.right;

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

public class BlueRightZeroCommand extends SequentialCommandGroup {
    public BlueRightZeroCommand(Drivetrain drivetrain, ShooterWheels shooterWheels, ShooterFeeder feeder, Intake intake, WobbleGoalArm wobbleGoalArm, Vision vision, Telemetry telemetry) {
        final int HG_SPEED = 360;
        final int POWERSHOT_SPEED = 3000;

        addCommands(
                
                
                new BlueRightShootingSequence(drivetrain, shooterWheels, feeder),

                //Place Wobble Goal
                new DriveForwardCommand(drivetrain, -50),
                new TurnToCommand(drivetrain,90, true),
                new DriveForwardCommand(drivetrain, 35),
                new TurnToCommand(drivetrain, 0),
                new DriveForwardCommand(drivetrain, 15),
                new PlaceWobbleGoal(wobbleGoalArm),
                new DriveForwardCommand(drivetrain, -10),
                new TurnToCommand(drivetrain, 90, true),
                new DriveForwardCommand(drivetrain, -40),
                new TurnToCommand(drivetrain, 0),
                new DriveForwardCommand(drivetrain, 30)
                );
    }
}
    //new SplineCommand(drivetrain, new Vector2d(-42, 12), Math.toRadians(180)),
    //new DriveForwardCommand(drivetrain, -40),
    //new TurnToCommand(drivetrain, 170),