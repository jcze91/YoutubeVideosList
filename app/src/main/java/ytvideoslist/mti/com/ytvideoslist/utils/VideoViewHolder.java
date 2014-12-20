package ytvideoslist.mti.com.ytvideoslist.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ytvideoslist.mti.com.ytvideoslist.R;
import ytvideoslist.mti.com.ytvideoslist.activities.MainActivity;

public class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
  protected ImageView thumbnail;
  protected TextView title;
  protected TextView channel;
  protected TextView share;
  protected TextView details;
  public IVideoViewHolderClicks listener;

  public VideoViewHolder(View itemView, IVideoViewHolderClicks listener) {
    super(itemView);

    this.listener = listener;

    thumbnail = (ImageView) itemView.findViewById(R.id.videoThumbnail);
    thumbnail.setOnClickListener(this);

    title = (TextView) itemView.findViewById(R.id.videoTitle);
    title.setTypeface(MainActivity.ROBOTO_SLAB);

    channel = (TextView) itemView.findViewById(R.id.channelTitle);
    channel.setOnClickListener(this);

    share = (TextView) itemView.findViewById(R.id.shareAction);
    share.setTypeface(MainActivity.ROBOTO_MEDIUM);
    share.setOnClickListener(this);

    details = (TextView) itemView.findViewById(R.id.detailsAction);
    details.setTypeface(MainActivity.ROBOTO_MEDIUM);
    details.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    if (v instanceof ImageView || v.getId() == R.id.detailsAction) {
      listener.onThumbnail(getPosition());
    } else if (v.getId() == R.id.channelTitle) {
      listener.onChannel(getPosition());
    } else if (v.getId() == R.id.shareAction) {
      listener.onShare(getPosition());
    }
  }

  public static interface IVideoViewHolderClicks {
    public void onChannel(int position);

    public void onShare(int position);

    public void onThumbnail(int position);
  }
}
