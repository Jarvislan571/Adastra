package cz.ejstn.adastra.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Utility class for setting up Retrofit client. <br><br>
 * use {@link #getNasaService(String)} to get {@link NasaService} instance
 */
public class RetrofitUtils {

    private static RetrofitUtils instance;

    private NasaService nasaService;

    /**
     * use {@link #getNasaService(String)} instaed of this
     */
    private RetrofitUtils(String baseUrl) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        nasaService = retrofit.create(NasaService.class);

    }

    /**
     * use {@link #getNasaService(String)} intead of this
     */
    private static RetrofitUtils getInstance(String baseUrl) {

        if (instance == null) {
            instance = new RetrofitUtils(baseUrl);
        }

        return instance;
    }

    /**
     * Prepares Retrofit client with passed in base URL. <br>
     * Under the hood uses Singleton pattern to prevent multiple Retrofit instances creation <br>
     * Retrofit is said to be more efficient if there is only one instance because it can
     * leverage some stuff like shared OkHttp client or something
     *
     * @param baseUrl base url
     * @return prepared service ready to use
     */
    public static NasaService getNasaService(String baseUrl) {
        return getInstance(baseUrl).nasaService;
    }
}
