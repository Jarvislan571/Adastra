package cz.ejstn.adastra.adapter;

import cz.ejstn.adastra.model.MarsPhoto;

/**
 * object/(activity) that implements this interface will be notified of {@link MarsPhotosAdapter} item clicks
 */
public interface OnPhotoClickListener {

    void onItemPhotoListClick(MarsPhoto clickedMarsPhoto);

}
