package ytvideoslist.mti.com.ytvideoslist.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ytvideoslist.mti.com.ytvideoslist.R;
import ytvideoslist.mti.com.ytvideoslist.models.Video;


public class CardAdapter extends RecyclerView.Adapter<VideoViewHolder> {
  private ArrayList<Video> videoList;
  private Context ctx;

  public CardAdapter(ArrayList<Video> videoList) {
    this.videoList = videoList;
  }

  @Override
  public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.
        from(parent.getContext()).
        inflate(R.layout.video_card, parent, false);

    this.ctx = parent.getContext();

    return new VideoViewHolder(itemView, (VideoViewHolder.IVideoViewHolderClicks) this.ctx);
  }

  @Override
  public void onBindViewHolder(VideoViewHolder holder, int position) {
    Video vid = videoList.get(position);
    Picasso.with(ctx).load(vid.getLargeThumbnail())
        .placeholder(R.drawable.thumbnail_placeholder)
        .error(R.drawable.thumbnail_placeholder)
        .into(holder.thumbnail);
    holder.title.setText(vid.getTitle());
    holder.channel.setText(vid.getChannel());
  }

  @Override
  public int getItemCount() {
    return videoList.size();
  }
}
