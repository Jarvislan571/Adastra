package cz.ejstn.adastra.retrofit;

import cz.ejstn.adastra.model.MarsPhotosResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Martin Soukup on 5.8.2017.
 */

public interface NasaService {

    // BASE URL: https://api.nasa.gov/mars-photos/api/v1/rovers/

    // curiosity/photos?sol=1000&page=1&api_key=DEMO_KEY

    @GET("{roverName}/photos")
    Call<MarsPhotosResponse> getPhotos(
            @Path("roverName") String roverName,
            @Query("sol") int sol,
            @Query("page") int page,
            @Query("api_key") String apiKey);

}
