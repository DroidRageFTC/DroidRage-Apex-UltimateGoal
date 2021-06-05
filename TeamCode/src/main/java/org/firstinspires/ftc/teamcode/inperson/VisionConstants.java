package org.firstinspires.ftc.teamcode.inperson;

import com.acmerobotics.dashboard.config.Config;

public class VisionConstants {
    @Config
    public static class RED_LEFT_VISION {
        public static double WIDTH = 0.98;
        public static double BOTTOM_HEIGHT = 0.86;
        public static double TOP_HEIGHT = 0.72;
    }

    @Config
    public static class RED_RIGHT_VISION {
        public static double WIDTH = 0.0099;
        public static double BOTTOM_HEIGHT = 0.85;
        public static double TOP_HEIGHT = 0.72;
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
