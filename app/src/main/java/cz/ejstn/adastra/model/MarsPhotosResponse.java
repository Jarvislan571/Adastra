package cz.ejstn.adastra.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin Soukup on 5.8.2017.
 */

public class MarsPhotosResponse {

    @SerializedName("photos")
    private List<MarsPhoto> marsPhotosList;

    public MarsPhotosResponse() {
        marsPhotosList = new ArrayList<>();
    }
}
