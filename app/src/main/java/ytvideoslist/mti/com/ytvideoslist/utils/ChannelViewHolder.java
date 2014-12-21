package ytvideoslist.mti.com.ytvideoslist.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ytvideoslist.mti.com.ytvideoslist.R;

public class ChannelViewHolder extends RecyclerView.ViewHolder {
  protected ImageView thumbnail;
  protected TextView title;
  protected TextView date;

  public ChannelViewHolder(View itemView) {
    super(itemView);

    thumbnail = (ImageView) itemView.findViewById(R.id.chlVideoThumbnail);
    title = (TextView) itemView.findViewById(R.id.chlVideoTitle);
    date = (TextView) itemView.findViewById(R.id.chlVideoDate);
  }
}
