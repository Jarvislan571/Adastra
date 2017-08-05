package cz.ejstn.adastra;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import org.parceler.Parcels;

import cz.ejstn.adastra.model.MarsPhoto;

public class DetailActivity extends AppCompatActivity {

    public static final String INTENT_PHOTO_EXTRA_KEY = "photo";

    private static final String TAG = DetailActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

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

    }
}
