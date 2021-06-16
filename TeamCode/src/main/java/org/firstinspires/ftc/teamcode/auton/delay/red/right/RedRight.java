package org.firstinspires.ftc.teamcode.auton.delay.red.right;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SelectCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.auton.VisionConstants;
import org.firstinspires.ftc.teamcode.pipelines.RingPipelineEx;
import org.firstinspires.ftc.teamcode.roadrunner.SampleTankDrive;
import org.firstinspires.ftc.teamcode.opmodes.MatchOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.ShooterFeeder;
import org.firstinspires.ftc.teamcode.subsystems.ShooterWheels;
import org.firstinspires.ftc.teamcode.subsystems.Vision;
import org.firstinspires.ftc.teamcode.subsystems.WobbleGoalArm;

import java.util.HashMap;

@Autonomous(name = "DELAYED Red Right Autonomous", group = "Delayed Regular Auto")
public class RedRight extends MatchOpMode {
    public static double startPoseX = -62.5;
    public static double startPoseY = 0;
    public static double startPoseHeading = 0;
    public static double RED_CAMERA_WIDTH = .02;
    // Motors
    private MotorEx leftBackDriveMotor, rightBackDriveMotor, leftFrontDriveMotor, rightFrontDriveMotor;
    private MotorEx intakeMotor;
    private MotorEx bintakeMotor;
    private DcMotorEx shooterMotorFront, shooterMotorBack;
    private ServoEx arm;
    private ServoEx feedServo;
    private TouchSensor wobbleTouchSensor;
    private ServoEx intakeServo;

    // Gamepad
    private GamepadEx driverGamepad;

    // Subsystems
    private Drivetrain drivetrain;
    private ShooterWheels shooterWheels;
    private ShooterFeeder feeder;
    private Intake intake;
    private WobbleGoalArm wobbleGoalArm;
    private Vision vision;

    @Override
    public void robotInit() {
        // Drivetrain Hardware Initializations
        // Intake hardware Initializations
        intakeMotor = new MotorEx(hardwareMap, "intake");
        bintakeMotor = new MotorEx(hardwareMap, "bintake");

        //intakeServo = new SimpleServo(hardwareMap, "intake_wall_servo", 0, 180);

        // Shooter hardware initializations
        shooterMotorBack = (DcMotorEx) hardwareMap.get(DcMotor.class, "shooter_back");
        shooterMotorFront = (DcMotorEx) hardwareMap.get(DcMotor.class, "shooter_front");

        feedServo = new SimpleServo(hardwareMap, "feed_servo", 0, 230);

        // Wobble Harware initializations
        wobbleTouchSensor = hardwareMap.get(TouchSensor.class, "Touch");

        // Subsystems
        drivetrain = new Drivetrain(new SampleTankDrive(hardwareMap), telemetry);
        drivetrain.init();
        intake = new Intake(intakeMotor, bintakeMotor, telemetry);
        shooterWheels = new ShooterWheels(shooterMotorFront, shooterMotorBack, telemetry);
        feeder = new ShooterFeeder(feedServo, telemetry);
        wobbleGoalArm = new WobbleGoalArm(hardwareMap, telemetry);
        vision = new Vision(hardwareMap, telemetry, VisionConstants.RED_RIGHT_VISION.TOP_HEIGHT, VisionConstants.RED_RIGHT_VISION.BOTTOM_HEIGHT, VisionConstants.RED_RIGHT_VISION.WIDTH);
        drivetrain.setPoseEstimate(new Pose2d(startPoseX, startPoseY, Math.toRadians(startPoseHeading)));

    }

    @Override
    public void disabledPeriodic() {
        vision.periodic();
    }

    @Override
    public void matchStart() {
        feeder.retractFeed();
        wobbleGoalArm.closeClaw();
        schedule(
                new SelectCommand(new HashMap<Object, Command>() {{
                    put(RingPipelineEx.Stack.FOUR, new SequentialCommandGroup(
                            new RedRightFourCommand(drivetrain, shooterWheels, feeder, intake, wobbleGoalArm, vision, telemetry)
                    ));
                    put(RingPipelineEx.Stack.ONE, new SequentialCommandGroup(
                            new RedRightOneCommand(drivetrain, shooterWheels, feeder, intake, wobbleGoalArm, vision, telemetry)
                    ));
                    put(RingPipelineEx.Stack.ZERO, new SequentialCommandGroup(
                            new RedRightZeroCommand(drivetrain, shooterWheels, feeder, intake, wobbleGoalArm, vision, telemetry)
                    ));
                }}, vision::getCurrentStack).andThen(new InstantCommand(() -> stop()))
        );

    }
}
