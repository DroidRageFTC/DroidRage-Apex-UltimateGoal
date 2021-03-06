package org.firstinspires.ftc.teamcode.roadrunner.opmode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.commands.RunCommand;
import org.firstinspires.ftc.teamcode.commands.drive.TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.roadrunner.SampleTankDrive;
import org.firstinspires.ftc.teamcode.opmodes.MatchOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;


/*
 * Op mode for preliminary tuning of the follower PID coefficients (located in the drive base
 * classes). The robot drives back and forth in a straight line indefinitely. Utilization of the
 * dashboard is recommended for this tuning routine. To access the dashboard, connect your computer
 * to the RC's WiFi network. In your browser, navigate to https://192.168.49.1:8080/dash if you're
 * using the RC phone or https://192.168.43.1:8080/dash if you are using the Control Hub. Once
 * you've successfully connected, start the program, and your robot will begin moving forward and
 * backward. You should observe the target position (green) and your pose estimate (blue) and adjust
 * your follower PID coefficients such that you follow the target position as accurately as possible.
 * If you are using SampleTankDrive, you should be tuning TRANSLATIONAL_PID and HEADING_PID.
 * If you are using SampleTankDrive, you should be tuning AXIAL_PID, CROSS_TRACK_PID, and HEADING_PID.
 * These coefficients can be tuned live in dashboard.
 *
 * This opmode is designed as a convenient, coarse tuning for the follower PID coefficients. It
 * is recommended that you use the FollowerPIDTuner opmode for further fine tuning.
 */
@Config
@Disabled
@Autonomous(group = "drive")
public class BackAndForth extends MatchOpMode {

    public static double DISTANCE = 50;
    Drivetrain drive;

    Trajectory trajectoryForward;
    Trajectory trajectoryBackward;

    @Override
    public void robotInit() {
        drive = new Drivetrain(new SampleTankDrive(hardwareMap), telemetry);
        trajectoryForward = drive.trajectoryBuilder(new Pose2d())
                .forward(DISTANCE)
                .build();
        trajectoryBackward = drive.trajectoryBuilder(trajectoryForward.end())
                .back(DISTANCE)
                .build();
    }

    @Override
    public void matchStart() {
        TrajectoryFollowerCommand forwardFollower = new TrajectoryFollowerCommand(drive, trajectoryForward);
        TrajectoryFollowerCommand  backwardFollower = new TrajectoryFollowerCommand(drive,
                drive.trajectoryBuilder(trajectoryForward.end())
                        .back(DISTANCE)
                        .build()
        );
        SequentialCommandGroup backAndForthCommand = new SequentialCommandGroup(forwardFollower, backwardFollower);
        schedule(new RunCommand(() -> {
            if (backAndForthCommand.isFinished() || !backAndForthCommand.isScheduled()) {
                backAndForthCommand.schedule();
            }
        }));
    }


}