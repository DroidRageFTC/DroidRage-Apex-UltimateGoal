package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.dashboard.config.Config;

public class VisionConstants {
    @Config
    public static class RED_LEFT_VISION {
        public static double WIDTH = 0.97;
        public static double BOTTOM_HEIGHT = 0.89;
        public static double TOP_HEIGHT = 0.73;
    }

    @Config
    public static class RED_RIGHT_VISION {

        public static double WIDTH = 0.04;
        public static double BOTTOM_HEIGHT = 0.87;
        public static double TOP_HEIGHT = 0.71;
    }

    @Config
    public static class BLUE_RIGHT_VISION {
        public static double WIDTH = 0.04;
        public static double BOTTOM_HEIGHT = 0.58;
        public static double TOP_HEIGHT = 0.44;
    }

    @Config
    public static class BLUE_LEFT_VISION {
        public static double WIDTH = 0.96;
        public static double BOTTOM_HEIGHT = 0.50;
        public static double TOP_HEIGHT = 0.36;
    }
}
