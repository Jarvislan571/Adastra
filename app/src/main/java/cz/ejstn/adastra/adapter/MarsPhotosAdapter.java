package cz.ejstn.adastra.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.ejstn.adastra.R;
import cz.ejstn.adastra.model.MarsPhoto;

/**
 * Created by Martin Soukup on 5.8.2017.
 */

public class MarsPhotosAdapter extends RecyclerView.Adapter<MarsPhotosAdapter.MarsPhotosViewHolder> {

    private static final int VIEW_TYPE_IMAGE_RIGHT = 420;
    private static final int VIEW_TYPE_IMAGE_LEFT = 360;

    private List<MarsPhoto> mPhotoList;

    private OnPhotoClickListener mItemClickListeningActivity;
    private Context mContext;


    public MarsPhotosAdapter(OnPhotoClickListener itemClickListener, Context context) {
        this.mItemClickListeningActivity = itemClickListener;
        this.mContext = context;
    }

    @Override
    public MarsPhotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutIdToInflate = R.layout.item_mars_photo_left;

        switch (viewType) {
            case VIEW_TYPE_IMAGE_LEFT:
                layoutIdToInflate = R.layout.item_mars_photo_left;
                break;
            case VIEW_TYPE_IMAGE_RIGHT:
                layoutIdToInflate = R.layout.item_mars_photo_right;
                break;
        }

        View view = LayoutInflater.from(parent.getContext())
                .inflate(layoutIdToInflate, parent, false);

        return new MarsPhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MarsPhotosViewHolder holder, int position) {
        MarsPhoto currentPhoto = mPhotoList.get(position);

        String photoUrl = currentPhoto.getImageUrl();
        Picasso.with(mContext).load(photoUrl).into(holder.itemImageView);

        String roverName = currentPhoto.getRoverName();
        int sol = currentPhoto.getSol();
        String cameraName = currentPhoto.getCameraName();
        String roverLabel = mContext.getString(R.string.rover);
        String solLabel = mContext.getString(R.string.sol);

        String newLine = "\n\n";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(roverLabel)
                .append(" ")
                .append(roverName)
                .append(newLine)
                .append(solLabel)
                .append(" ")
                .append(sol)
                .append(newLine)
                .append(cameraName);

        holder.itemInfoTextView.setText(stringBuilder);

        holder.itemView.setTag(position);

    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 1)
            return VIEW_TYPE_IMAGE_LEFT;
        else
            return VIEW_TYPE_IMAGE_RIGHT;
    }

    @Override
    public int getItemCount() {
        if (mPhotoList == null)
            return 0;
        else
            return mPhotoList.size();
    }

    public void swapData(List<MarsPhoto> newPhotos) {
        mPhotoList = newPhotos;
        notifyDataSetChanged();
    }

    class MarsPhotosViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_photo_info)
        TextView itemInfoTextView;

        @BindView(R.id.iv_item_image)
        ImageView itemImageView;

        MarsPhotosViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListeningActivity.onPhotoClick(mPhotoList.get((Integer) itemView.getTag()));
                }
            });
        }
    }
}
