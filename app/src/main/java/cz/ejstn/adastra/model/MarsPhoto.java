package cz.ejstn.adastra.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import static android.R.attr.id;

@Parcel
public class MarsPhoto {

    int sol;

    @SerializedName("img_src")
    String imageUrl;

    @SerializedName("earth_date")
    String earthDate;

    Camera camera;

    Rover rover;

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