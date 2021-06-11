package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Util;

import java.util.logging.Level;
@Config
public class Intake extends SubsystemBase {

    public static double INTAKE_SPEED = 1;
    public static double OUTAKE_SPEED = -1;
    public static double SLOW_INTAKE_SPEED = 0.75;

    Telemetry telemetry;
    private MotorEx intake;
    private MotorEx bintake;
    public Intake(MotorEx intake, MotorEx bintake, Telemetry tl) {
        this.intake = intake;
        this.bintake = bintake;
        intake.setInverted(true);
        bintake.setInverted(true);
        this.telemetry = tl;
    }
    public Intake(MotorEx intake, MotorEx bintake, Telemetry tl, boolean slow) {
        this.intake = intake;
        this.bintake = bintake;
        intake.setInverted(true);
        bintake.setInverted(true);
        this.telemetry = tl;
        INTAKE_SPEED = slow ? Intake.SLOW_INTAKE_SPEED : INTAKE_SPEED;
    }

    @Override
    public void periodic() {
        Util.logger(this, telemetry, Level.INFO, "Current Intake Speed", intake.get());
    }

    public void set(double speed) {
        intake.set(speed);
        bintake.set(speed);
    }

    public void intake() {
        set(INTAKE_SPEED);
    }


    public void outtake() {
        set(OUTAKE_SPEED);
    }

    public void stop() {
        intake.stopMotor();
        bintake.stopMotor();
    }
}
