package cz.ejstn.adastra.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Martin Soukup on 5.8.2017.
 */

@Parcel
public class Rover {

    @SerializedName("name")
    private String name;

    @Override
    public String toString() {
        return "Rover{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }
}
