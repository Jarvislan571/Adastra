package cz.ejstn.adastra.retrofit;

import cz.ejstn.adastra.model.NasaApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Retrofit service
 */
public interface NasaService {

    /**
     * Queries the NASA Mars rover photos API for photos and associated details
     * <p>
     * query example:
     * BASE_URL/curiosity/photos?sol=1000&page=1&api_key=DEMO_KEY
     *
     * @param roverName name of the rover which photos to query
     * @param sol       day of the Mars mission for the particular rover I think
     * @param page      which page to show, each page is 25 photos
     * @param apiKey    api key
     * @return Nasa API response
     */
    @GET("{roverName}/photos")
    Call<NasaApiResponse> getPhotos(
            @Path("roverName") String roverName,
            @Query("sol") int sol,
            @Query("page") int page,
            @Query("api_key") String apiKey);

}
