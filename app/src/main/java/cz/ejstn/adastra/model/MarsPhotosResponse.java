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
        // choosing ArrayList because it will be used as data source in a RecyclerView
        // the adapter will do a lot of searching, from what I know ArrayList is faster for searching
        // than LinkedList
        marsPhotosList = new ArrayList<>();
    }

    public List<MarsPhoto> getMarsPhotosList() {
        return marsPhotosList;
    }
}
