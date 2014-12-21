package ytvideoslist.mti.com.ytvideoslist.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ytvideoslist.mti.com.ytvideoslist.R;

public class VideoDetailFragment extends Fragment {
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_video_detail, container, false);
  }

  public void setImage(String url) {
    ImageView img = (ImageView) getActivity().findViewById(R.id.detailImage);
    Picasso.with(getActivity()).load(url)
        .placeholder(R.drawable.thumbnail_placeholder)
        .error(R.drawable.thumbnail_placeholder)
        .into(img);
  }

  public void setTitle(String title) {
    TextView view = (TextView) getView().findViewById(R.id.detailTitle);
    view.setText(title);
  }

  public void setChannel(String channel) {
    TextView view = (TextView) getView().findViewById(R.id.detailChannel);
    view.setText(channel);
  }

  public void setDate(String date) {
    TextView view = (TextView) getView().findViewById(R.id.detailDate);
    view.setText(date);
  }

  public void setDescription(String desc) {
    TextView view = (TextView) getView().findViewById(R.id.detailDescription);
    view.setText(desc);
  }
}
