package cz.ejstn.adastra.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.ejstn.adastra.R;
import cz.ejstn.adastra.model.MarsPhoto;

/**
 * Created by Martin Soukup on 5.8.2017.
 */

public class MarsPhotosAdapter extends RecyclerView.Adapter<MarsPhotosAdapter.MarsPhotosViewHolder> {

    private List<MarsPhoto> mPhotoList;

    private OnPhotoClickListener mItemClickListener;

    public MarsPhotosAdapter(List<MarsPhoto> photosList, OnPhotoClickListener itemClickListener) {
        this.mPhotoList = photosList;
        this.mItemClickListener = itemClickListener;
    }

    @Override
    public MarsPhotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mars_photo, parent, false);

        return new MarsPhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MarsPhotosViewHolder holder, int position) {

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
        ImageView itemImagaView;

        MarsPhotosViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
