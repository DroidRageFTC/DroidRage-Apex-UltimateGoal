package org.firstinspires.ftc.teamcode.auton.intake.intaketestRemovable;

import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmodes.MatchOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

@Autonomous(name = "IntakeTest", group = "Intake Auto Test")
public class intaketest extends MatchOpMode {
    private MotorEx intakeMotor;
    private MotorEx bintakeMotor;

    private Intake intake;

    @Override
    public void robotInit() {
        // Intake hardware Initializations
        intakeMotor = new MotorEx(hardwareMap, "intake");
        bintakeMotor = new MotorEx(hardwareMap, "bintake");

        intake = new Intake(intakeMotor, bintakeMotor, telemetry);
    }

    @Override
    public void matchStart() {
        schedule(
                new IntakeOP(intake, telemetry)
        );
    }
}
