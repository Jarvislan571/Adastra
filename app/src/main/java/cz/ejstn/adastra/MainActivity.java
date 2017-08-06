package cz.ejstn.adastra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.parceler.Parcels;

import cz.ejstn.adastra.adapter.MarsPhotosAdapter;
import cz.ejstn.adastra.adapter.OnPhotoClickListener;
import cz.ejstn.adastra.model.MarsPhoto;
import cz.ejstn.adastra.model.NasaApiResponse;
import cz.ejstn.adastra.retrofit.NasaService;
import cz.ejstn.adastra.retrofit.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activity that shows user list of mars rover photos
 */
public class MainActivity extends AppCompatActivity implements OnPhotoClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String BUNDLE_RESPONSE_KEY = "mars_photos";

    private static final String BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/";
    private static final String API_KEY = "B1K30QnXEAdDDNjMzkLZ6VbsBoTtqtFhwe2VFWNI";
    private static final String ROVER_NAME = "curiosity";
    private static final int SOL = 605;

    private MarsPhotosAdapter mPhotosAdapter;

    private Call<NasaApiResponse> mApiCall;

    private NasaApiResponse mNasaApiResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpRecyclerView();

        // I try not to query the API again everytime user rotates the screen
        if (savedInstanceState != null && savedInstanceState.containsKey(BUNDLE_RESPONSE_KEY)) {
            Log.d(TAG, "onCreate: reusing data");
            mNasaApiResponse = Parcels.unwrap(savedInstanceState.getParcelable(BUNDLE_RESPONSE_KEY));
            mPhotosAdapter.swapData(mNasaApiResponse.getMarsPhotosList());
            return;
        }

        // application is started the first time or there is no data that can be reused
        setUpRetrofitService();
        kickOffApiCall();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mApiCall != null)
            mApiCall.cancel();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mNasaApiResponse != null)
            outState.putParcelable(BUNDLE_RESPONSE_KEY, Parcels.wrap(mNasaApiResponse));
        super.onSaveInstanceState(outState);
    }

    /**
     * Callback for recyclerview item clicks
     */
    @Override
    public void onItemPhotoListClick(MarsPhoto clickedMarsPhoto) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.INTENT_PHOTO_EXTRA_KEY, Parcels.wrap(clickedMarsPhoto));
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

        mApiCall.enqueue(new Callback<NasaApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<NasaApiResponse> call,
                                   @NonNull Response<NasaApiResponse> response) {
                Log.d(TAG, "onResponse: response code: " + response.code());

                NasaApiResponse photos = response.body();

                if (response.code() == 200 && photos != null) {
                    mNasaApiResponse = photos;
                    mPhotosAdapter.swapData(mNasaApiResponse.getMarsPhotosList());
                } else
                    showErrorToast();

            }

            @Override
            public void onFailure(@NonNull Call<NasaApiResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: failure");
                Log.d(TAG, "onFailure: " + t.getMessage());
                showErrorToast();
            }
        });
    }

    /**
     * shows a toast with generic error message
     */
    private void showErrorToast() {
        Toast.makeText(MainActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
    }

}