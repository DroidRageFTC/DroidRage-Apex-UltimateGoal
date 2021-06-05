package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Util;

import java.util.logging.Level;

@Config
public class WobbleGoalArm extends SubsystemBase {
    private Telemetry telemetry;
    private ServoEx arm;
    private ServoEx leftClaw, rightClaw;


    public WobbleGoalArm(HardwareMap hw, Telemetry tl) {
        arm = new SimpleServo(hw, "arm", 0, 360);
        leftClaw = new SimpleServo(hw, "leftClaw", 0, 180);
        rightClaw = new SimpleServo(hw, "rightClaw", 0, 180);

        this.telemetry = tl;
    }

    public void liftArm() {
        arm.setPosition(0);
    }

    public void lowerArm() {
        arm.setPosition(0);
    }

    public void openClaw() {
        leftClaw.setPosition(0);
        rightClaw.setPosition(0);
    }
    public void closeClaw() {
        leftClaw.setPosition(0);
        rightClaw.setPosition(0);
    }

}
