package cz.ejstn.adastra;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cz.ejstn.adastra.model.MarsPhotosResponse;
import cz.ejstn.adastra.retrofit.NasaService;
import cz.ejstn.adastra.retrofit.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/";

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        NasaService service = RetrofitUtils.getNasaService(BASE_URL);

        Call<MarsPhotosResponse> call = service.getPhotos("curiosity",
                605, 1, "DEMO_KEY");


        call.enqueue(new Callback<MarsPhotosResponse>() {
            @Override
            public void onResponse(Call<MarsPhotosResponse> call, Response<MarsPhotosResponse> response) {
                Log.d(TAG, "onResponse: success");
                MarsPhotosResponse photosList = response.body();
                Log.d(TAG, "onResponse: retrieved list: " + photosList.getMarsPhotosList().get(0));
            }

            @Override
            public void onFailure(Call<MarsPhotosResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: failure");
            }
        });

    }
}
