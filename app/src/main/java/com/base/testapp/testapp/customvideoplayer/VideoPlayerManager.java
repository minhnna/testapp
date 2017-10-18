package com.base.testapp.testapp.customvideoplayer;

public class VideoPlayerManager {

    public static VideoPlayer FIRST_FLOOR_VD;
    public static VideoPlayer SECOND_FLOOR_VD;

    public static VideoPlayer getFirstFloor() {
        return FIRST_FLOOR_VD;
    }

    public static void setFirstFloor(VideoPlayer videoPlayer) {
        FIRST_FLOOR_VD = videoPlayer;
    }

    public static VideoPlayer getSecondFloor() {
        return SECOND_FLOOR_VD;
    }

    public static void setSecondFloor(VideoPlayer videoPlayer) {
        SECOND_FLOOR_VD = videoPlayer;
    }

    public static VideoPlayer getCurrentVd() {
        if (getSecondFloor() != null) {
            return getSecondFloor();
        }
        return getFirstFloor();
    }

    public static void completeAll() {
        if (SECOND_FLOOR_VD != null) {
            SECOND_FLOOR_VD.onCompletion();
            SECOND_FLOOR_VD = null;
        }
        if (FIRST_FLOOR_VD != null) {
            FIRST_FLOOR_VD.onCompletion();
            FIRST_FLOOR_VD = null;
        }
    }
}
