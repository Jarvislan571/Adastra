package cz.ejstn.adastra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.parceler.Parcels;

import cz.ejstn.adastra.adapter.MarsPhotosAdapter;
import cz.ejstn.adastra.adapter.OnPhotoClickListener;
import cz.ejstn.adastra.model.MarsPhoto;
import cz.ejstn.adastra.model.MarsPhotosResponse;
import cz.ejstn.adastra.retrofit.NasaService;
import cz.ejstn.adastra.retrofit.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnPhotoClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/";

    private static final String API_KEY = "B1K30QnXEAdDDNjMzkLZ6VbsBoTtqtFhwe2VFWNI";

    private static final String ROVER_NAME = "curiosity";
    private static final int SOL = 605;

    private MarsPhotosAdapter mPhotosAdapter;

    private Call<MarsPhotosResponse> mApiCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpRecyclerView();

        setUpRetrofitService();

        kickOffApiCall();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mApiCall.cancel();
    }

    @Override
    public void onPhotoClick(MarsPhoto marsPhoto) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("photo", Parcels.wrap(marsPhoto));
        startActivity(intent);
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_photos);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        mPhotosAdapter = new MarsPhotosAdapter(this, this);

        recyclerView.setAdapter(mPhotosAdapter);

    }

    private void setUpRetrofitService() {
        NasaService service = RetrofitUtils.getNasaService(BASE_URL);
        mApiCall = service.getPhotos(ROVER_NAME, SOL, 1, API_KEY);
    }

    private void kickOffApiCall() {

        mApiCall.enqueue(new Callback<MarsPhotosResponse>() {
            @Override
            public void onResponse(Call<MarsPhotosResponse> call, Response<MarsPhotosResponse> response) {
                Log.d(TAG, "onResponse: response code: " + response.code());

                MarsPhotosResponse photos = response.body();

                if (photos != null) {
                    mPhotosAdapter.swapData(photos.getMarsPhotosList());
                } else
                    Toast.makeText(MainActivity.this, R.string.error, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<MarsPhotosResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: failure");
                Log.d(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(MainActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
            }
        });
    }

}