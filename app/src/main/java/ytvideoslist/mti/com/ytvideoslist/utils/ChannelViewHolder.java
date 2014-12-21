package ytvideoslist.mti.com.ytvideoslist.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ytvideoslist.mti.com.ytvideoslist.R;

public class ChannelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
  protected ImageView thumbnail;
  protected TextView title;
  protected TextView date;
  public IChannelViewHolderClicks listener;

  public ChannelViewHolder(View itemView, IChannelViewHolderClicks listener) {
    super(itemView);

    this.listener = listener;

    thumbnail = (ImageView) itemView.findViewById(R.id.chlVideoThumbnail);
    title = (TextView) itemView.findViewById(R.id.chlVideoTitle);
    date = (TextView) itemView.findViewById(R.id.chlVideoDate);

    itemView.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    listener.onItem(getPosition());
  }

  public static interface IChannelViewHolderClicks {
    public void onItem(int position);
  }
}
