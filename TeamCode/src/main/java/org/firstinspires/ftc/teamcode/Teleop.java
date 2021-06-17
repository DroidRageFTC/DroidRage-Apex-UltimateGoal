package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.commands.drive.ArcadeDriveCommand;
import org.firstinspires.ftc.teamcode.commands.drive.SlowArcadeDriveCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.FeedRingsCommand;
import org.firstinspires.ftc.teamcode.roadrunner.SampleTankDrive;
import org.firstinspires.ftc.teamcode.opmodes.MatchOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.ShooterFeeder;
import org.firstinspires.ftc.teamcode.subsystems.ShooterWheels;
import org.firstinspires.ftc.teamcode.subsystems.WobbleGoalArm;
@Config
@TeleOp(name = "TeleOp")
public class Teleop extends MatchOpMode {
    // Motors
    private MotorEx leftBackDriveMotor, rightBackDriveMotor, leftFrontDriveMotor, rightFrontDriveMotor;
    private MotorEx intakeMotor;
    private MotorEx backIntakeMotor;
    private DcMotorEx shooterMotorFront, shooterMotorBack;
    private ServoEx feedServo;
    private TouchSensor wobbleTouchSensor;
    // Gamepad
    private GamepadEx driverGamepad, operatorGamepad;

    // Subsystems
    private Drivetrain drivetrain;
    private ShooterWheels shooterWheels;
    private ShooterFeeder feeder;
    private Intake intake;
    private WobbleGoalArm wobbleGoalArm;


    private Button intakeButton, outtakeButton;
    private Button slowModeTrigger, tripleFeedButton, singleFeedButton, shootButton, powershotButton, toggleClawButton, liftArmButton, lowerArmButton, dropArmButton;
    private Button lowMidWobbleButton;
    private Button autoPowershotButton;
    private Button increaseSpeedButton;

    public Teleop() {
    }

    // Thomas is a dumdum
    @Override
    public void robotInit() {
        //Drivetrain Hardware Initializations
        // Intake hardware Initializations
        intakeMotor = new MotorEx(hardwareMap, "intake");
        backIntakeMotor = new MotorEx(hardwareMap, "bintake");

        // Shooter hardware initializations
        shooterMotorBack = (DcMotorEx) hardwareMap.get(DcMotor.class, "shooter_back");
        shooterMotorFront = (DcMotorEx) hardwareMap.get(DcMotor.class, "shooter_front");
        //intakeServo = new SimpleServo(hardwareMap, "intake_wall_servo", 0, 180);

        feedServo = new SimpleServo(hardwareMap, "feed_servo", 0, 230);


        wobbleTouchSensor = hardwareMap.get(TouchSensor.class, "Touch");

        // Subsystems
        drivetrain = new Drivetrain(new SampleTankDrive(hardwareMap),telemetry);
        drivetrain.init();
        intake = new Intake(intakeMotor, backIntakeMotor, telemetry);
        shooterWheels = new ShooterWheels(shooterMotorFront, shooterMotorBack, telemetry);
        feeder = new ShooterFeeder(feedServo, telemetry);
        wobbleGoalArm = new WobbleGoalArm(hardwareMap, telemetry);

        gamepad1.setJoystickDeadzone(0.0f);
        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);
    }

    @Override
    public void configureButtons() {

        slowModeTrigger = (new GamepadButton(driverGamepad, GamepadKeys.Button.RIGHT_BUMPER)).whileHeld(new SlowArcadeDriveCommand(drivetrain, driverGamepad));

        singleFeedButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.A)).whenPressed(new FeedRingsCommand(feeder, 1));
        // TRIPLE SHOT SPEED *********************
        tripleFeedButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.LEFT_BUMPER)).whenPressed(new FeedRingsCommand(feeder, 5, 55));
        shootButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.RIGHT_BUMPER)).toggleWhenPressed(
                new InstantCommand(() -> shooterWheels.setShooterRPM(ShooterWheels.TARGET_SPEED), shooterWheels),
                new InstantCommand(() -> shooterWheels.setShooterRPM(0), shooterWheels));
        powershotButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.Y)).toggleWhenPressed(
                new InstantCommand(() -> shooterWheels.setShooterRPM(3000), shooterWheels),
                new InstantCommand(() -> shooterWheels.setShooterRPM(0), shooterWheels));
        intakeButton = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.LEFT_TRIGGER)).whileHeld(intake::intake).whenReleased(intake::stop);
        (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.LEFT_TRIGGER)).whileHeld(new InstantCommand(intake::intake, intake)).whenReleased(intake::stop);

        outtakeButton = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER)).whileHeld(intake::outtake).whenReleased(intake::stop);

        toggleClawButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_LEFT)).toggleWhenPressed(
                new InstantCommand(wobbleGoalArm::openClaw, wobbleGoalArm),
                new InstantCommand(wobbleGoalArm::closeClaw, wobbleGoalArm)
        );

        liftArmButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_UP)).whenPressed(wobbleGoalArm::liftArm);
        lowerArmButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_DOWN)).whenPressed(wobbleGoalArm::lowerArm);
        dropArmButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.A)).whenPressed(wobbleGoalArm::dropArm);

        (new GamepadButton(operatorGamepad, GamepadKeys.Button.B)).whenPressed(() -> shooterWheels.adjustShooterRPM(75));
        (new GamepadButton(operatorGamepad, GamepadKeys.Button.X)).whenPressed(() -> shooterWheels.adjustShooterRPM(-75));
        //(new GamepadButton(driverGamepad, GamepadKeys.Button.RIGHT_STICK_BUTTON)).toggleWhenPressed(new InstantCommand(intake::dropIntake, intake), new InstantCommand(intake::liftIntake, intake));
        drivetrain.setDefaultCommand(new ArcadeDriveCommand(drivetrain, driverGamepad));
    }


    @Override
    public void matchStart() {
        schedule(new InstantCommand(feeder::retractFeed));
    }

    @Override
    public void robotPeriodic() {
    }
}
/*
Slow mode = driver right bumper
single shot = driver A
vision = driver left stick button
power shot = driver Y
intake = driver left trigger
outake = driver right trigger



Tripple shot = Operator Left Bumper
rev shooter = operator right bumper
toggle open/close claw = operator dpad left
lift claw = dpad up
lower claw = dpad down
raise shooter speed = operator B
lower shooter speed = operator X


*/



