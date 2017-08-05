package cz.ejstn.adastra;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

    private static final String BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/";

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String API_KEY = "";

    private MarsPhotosAdapter mPhotosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpRecyclerView();


        NasaService service = RetrofitUtils.getNasaService(BASE_URL);

        Call<MarsPhotosResponse> call = service.getPhotos("curiosity",
                605, 1, API_KEY);


        call.enqueue(new Callback<MarsPhotosResponse>() {
            @Override
            public void onResponse(Call<MarsPhotosResponse> call, Response<MarsPhotosResponse> response) {
                Log.d(TAG, "onResponse: response code: " + response.code());
                MarsPhotosResponse photosList = response.body();
                Log.d(TAG, "onResponse: retrieved list: " + photosList.getMarsPhotosList().get(0));
                mPhotosAdapter.swapData(photosList.getMarsPhotosList());
            }

            @Override
            public void onFailure(Call<MarsPhotosResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: failure");
            }
        });

    }

    @Override
    public void onPhotoClick(MarsPhoto marsPhoto) {
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_photos);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        mPhotosAdapter = new MarsPhotosAdapter(null, this, this);

        recyclerView.setAdapter(mPhotosAdapter);

    }
}
