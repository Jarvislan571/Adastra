package cz.ejstn.adastra;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.ejstn.adastra.model.MarsPhoto;

public class DetailActivity extends AppCompatActivity {

    public static final String INTENT_PHOTO_EXTRA_KEY = "photo";

    private static final String TAG = DetailActivity.class.getSimpleName();

    @BindView(R.id.tv_rover_name)
    TextView mRoverNameTextView;
    @BindView(R.id.tv_camera_name)
    TextView mCameraTextView;
    @BindView(R.id.tv_earth_date)
    TextView mEarthDateTextView;
    @BindView(R.id.tv_sol)
    TextView mSolTextView;
    @BindView(R.id.iv_detail_photo)
    ImageView mDetailPhotoImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        // works allright through configuration changes
        MarsPhoto mMarsPhoto = Parcels.unwrap(getIntent().getParcelableExtra(INTENT_PHOTO_EXTRA_KEY));
        Log.d(TAG, "onCreate: received MarsPhoto: " + mMarsPhoto);

        fillUiWithData(mMarsPhoto);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return true;
    }

    private void fillUiWithData(MarsPhoto marsPhoto) {
        String imageUrl = marsPhoto.getImageUrl();
        Picasso.with(this).load(imageUrl).into(mDetailPhotoImageView);

        String roverName = marsPhoto.getRoverName();
        String sol = String.valueOf(marsPhoto.getSol());
        String earthDate = marsPhoto.getEarthDate();
        String camera = marsPhoto.getCameraName();

        mRoverNameTextView.setText(roverName);
        mSolTextView.setText(sol);
        mEarthDateTextView.setText(earthDate);
        mCameraTextView.setText(camera);

    }
}
