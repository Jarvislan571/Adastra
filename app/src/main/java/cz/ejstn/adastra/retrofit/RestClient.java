package cz.ejstn.adastra.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Martin Soukup on 5.8.2017.
 */

public class RestClient {

    private static final String BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/";

    private static RestClient instance;

    private NasaService nasaService;

    private RestClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        nasaService = retrofit.create(NasaService.class);

    }

    public static RestClient getInstance() {
        if (instance == null) {
            return new RestClient();
        } else
            return instance;
    }

    public NasaService getNasaService() {
        return nasaService;
    }
}
