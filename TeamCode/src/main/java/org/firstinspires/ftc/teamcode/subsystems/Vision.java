package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.pipelines.CompHGPipeline;
import org.firstinspires.ftc.teamcode.pipelines.RingPipelineEx;
import org.firstinspires.ftc.teamcode.pipelines.UGBasicHighGoalPipeline;

import org.firstinspires.ftc.teamcode.Util;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.logging.Level;
@Config
public class Vision extends SubsystemBase {

    public static double TOP_PERCENT = 0.39;
    public static double BOTTOM_PERCENT = 0.52;
    public static double WIDTH_PERCENT = 0.9;
    OpenCvCamera ringCamera;

    private Telemetry telemetry;
    private RingPipelineEx ringPipeline;
    private RingPipelineEx.Stack currentStack;

    public Vision(HardwareMap hw, Telemetry tl, double top, double bottom, double width) {
        this.telemetry = tl;


        int cameraMonitorViewId = hw.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hw.appContext.getPackageName());
        ringCamera = OpenCvCameraFactory.getInstance().createWebcam(hw.get(WebcamName.class, "webcam1"), cameraMonitorViewId);
        ringCamera.showFpsMeterOnViewport(false);

        ringPipeline = new RingPipelineEx();
        ringPipeline.setBottomRectangle(width, bottom);
        ringPipeline.setTopRectangle(width, top);
        ringPipeline.setRectangleSize(15, 15);

        ringCamera.setPipeline(ringPipeline);

        ringCamera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                ringCamera.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
            }

        });
        ringCamera.openCameraDevice();

    }
    public Vision(HardwareMap hw, Telemetry tl, double top, double bottom, double width, UGBasicHighGoalPipeline.Mode color) {
        this(hw, tl, top, bottom, width);
    }


    @Override
    public void periodic() {
        currentStack = ringPipeline.getStack();
        Util.logger(this, telemetry, Level.INFO, "Current Stack", currentStack);
        Util.logger(this, telemetry, Level.INFO, "Bottom", ringPipeline.getBottomAverage());
        Util.logger(this, telemetry, Level.INFO, "Top", ringPipeline.getTopAverage());
    }

    public void setBottomPercent(double bottomPercent, double width) { ringPipeline.setBottomRectangle(width, bottomPercent);
    }

    public void setTopPercent(double topPercent, double width) {
        ringPipeline.setTopRectangle(width, topPercent);
    }
    public double getTopAverage() {
        return ringPipeline.getTopAverage();
    }
    public double getBottomAverage() {
        return ringPipeline.getTopAverage();
    }


    public RingPipelineEx.Stack getCurrentStack() {
        return ringPipeline.getStack();
    }

}
