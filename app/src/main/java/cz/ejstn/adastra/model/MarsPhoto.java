package cz.ejstn.adastra.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Martin Soukup on 5.8.2017.
 */

public class MarsPhoto {

    private int id;

    private int sol;

    @SerializedName("img_src")
    private String imageUrl;

    @SerializedName("earth_date")
    private String earthDate;

    private Camera camera;

    private Rover rover;

    @Override
    public String toString() {
        return "MarsPhoto{" +
                "id=" + id +
                ", sol=" + sol +
                ", imageUrl='" + imageUrl + '\'' +
                ", earthDate='" + earthDate + '\'' +
                ", camera=" + camera +
                ", rover=" + rover +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getSol() {
        return sol;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getEarthDate() {
        return earthDate;
    }

    public String getRoverName() {
        return rover.getName();
    }

    public String getCameraName() {
        return camera.getName();
    }

}