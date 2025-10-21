package org.firstinspires.ftc.teamcode.subsystems.vision;

import org.firstinspires.ftc.teamcode.utilities.MotifColor;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

// Thanks to Kalipso-Robotics
//https://github.com/Kalipso-Robotics/FtcRobotController/tree/master/TeamCode

public class MotifDetection {
    private final MotifCamera motifCamera;
    private long startTime;
    protected boolean isDone = false;

    public MotifDetection(MotifCamera motifCamera) {
        this.motifCamera = motifCamera;
    }
    public void startSearching() {
        this.startTime = System.currentTimeMillis();
    }
    public MotifCamera getMotifCamera() {
        return motifCamera;
    }

    private int getObeliskID() {
        List<AprilTagDetection> detections = motifCamera.getAprilTagProcessor().getDetections();

        return detections.get(0).id;
    }

    public boolean hasTimedOut() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;

        return elapsedTime >= 5000 && !MotifVisible();
    }
    public MotifCamera.MotifPattern getMotifPattern() {
        if (getObeliskID() == 21) {
            return new MotifCamera.MotifPattern(MotifColor.GREEN, MotifColor.PURPLE, MotifColor.PURPLE);
        }
        if (getObeliskID() == 22) {
            return new MotifCamera.MotifPattern(MotifColor.PURPLE, MotifColor.GREEN, MotifColor.PURPLE);
        }
        if (getObeliskID() == 23) {
            return new MotifCamera.MotifPattern(MotifColor.PURPLE, MotifColor.PURPLE, MotifColor.GREEN);
        }
        else { //nothing seen
            return new MotifCamera.MotifPattern(MotifColor.NONE, MotifColor.NONE, MotifColor.NONE);
        }
    }
    private boolean MotifVisible() {
        return !motifCamera.getAprilTagProcessor().getDetections().isEmpty();
    }
    public void resumeStreaming() {
        motifCamera.getVisionPortal().resumeStreaming();
    }
    public void stopStreaming() {
        motifCamera.getVisionPortal().stopStreaming();
    }
    public void close() {
        motifCamera.getVisionPortal().close();
    }

    public boolean isUpdateDone() {
        isDone = (!getMotifPattern().equals(MotifColor.NONE, MotifColor.NONE, MotifColor.NONE));
        return isDone;
    }

    public boolean updateCheckDone() {
        if (isDone) {
            return true;
        } //if done never update
        update();

        return isUpdateDone();
    }

    public boolean getIsDone() {
        return isDone;
    }



    //TODO think about auto implementation + what to do if no pattern detected
    //TODO timeout if nothing detected


    public void update() {
        getObeliskID();
        isDone = true;
    }

}
