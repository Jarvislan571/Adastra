package cz.ejstn.adastra.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Martin Soukup on 5.8.2017.
 */

public class MarsPhoto {

    @Expose
    private int id;

    @Expose
    private int sol;

    @Expose
    @SerializedName("img_src")
    private String imageUrl;

    @Expose
    @SerializedName("earth_date")
    private String earthDate;

    @Expose
    @SerializedName("camera")
    private Camera camera;

    @Expose
    @SerializedName("rover")
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
}
