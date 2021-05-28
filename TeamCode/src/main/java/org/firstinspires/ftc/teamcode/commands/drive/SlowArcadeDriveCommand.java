package org.firstinspires.ftc.teamcode.commands.drive;

import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.apache.commons.math3.geometry.spherical.oned.Arc;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

public class SlowArcadeDriveCommand extends ArcadeDriveCommand {
    public SlowArcadeDriveCommand(Drivetrain drive, GamepadEx driverGamepad) {
        super(drive, driverGamepad);
        this.multiplier = 0.5;
    }
}
