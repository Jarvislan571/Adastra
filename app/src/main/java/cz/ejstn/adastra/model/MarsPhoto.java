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
    @SerializedName("name")
    private String roverName;

    @Expose
    @SerializedName("full_name")
    private String cameraName;

    @Expose
    @SerializedName("img_src")
    private String imageUrl;

    @Expose
    @SerializedName("earth_date")
    private String earthDate;

    public int getId() {
        return id;
    }

    public int getSol() {
        return sol;
    }

    public String getRoverName() {
        return roverName;
    }

    public String getCameraName() {
        return cameraName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getEarthDate() {
        return earthDate;
    }
}
