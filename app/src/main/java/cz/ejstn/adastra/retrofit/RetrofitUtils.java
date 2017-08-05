package cz.ejstn.adastra.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitUtils {

    private static RetrofitUtils instance;

    private NasaService nasaService;

    private RetrofitUtils(String baseUrl) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        nasaService = retrofit.create(NasaService.class);

    }


    private static RetrofitUtils getInstance(String baseUrl) {

        if (instance == null) {
            instance = new RetrofitUtils(baseUrl);
        }

        return instance;
    }

    public static NasaService getNasaService(String baseUrl) {
        return getInstance(baseUrl).nasaService;
    }
}
