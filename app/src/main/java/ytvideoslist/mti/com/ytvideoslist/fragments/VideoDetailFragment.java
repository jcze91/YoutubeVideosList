package ytvideoslist.mti.com.ytvideoslist.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ytvideoslist.mti.com.ytvideoslist.R;
import ytvideoslist.mti.com.ytvideoslist.activities.MainActivity;
import ytvideoslist.mti.com.ytvideoslist.models.Video;

public class VideoDetailFragment extends Fragment {

  private String url;
  private String title;
  private String channel;
  private String date;
  private String desc;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_video_detail, container, false);
    return view;
  }

  @Override
  public void onCreate(Bundle saveInstanceState) {
    super.onCreate(saveInstanceState);
    if (getArguments() != null) {
      Video v = getArguments().getParcelable("video");
      url = v.getLargeThumbnail();
      title = v.getTitle();
      channel = v.getChannel();
      date = v.getPublished();
      desc = v.getDescription();
    }
  }

  private void hidePlaceholder() {
    TextView placeholder = (TextView) getView().findViewById(R.id.novideoPlaceholder);
    placeholder.setVisibility(View.GONE);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
    int screenSize = getResources().getConfiguration().screenLayout &
        Configuration.SCREENLAYOUT_SIZE_MASK;

    switch (screenSize) {
      case Configuration.SCREENLAYOUT_SIZE_XLARGE:
        if (getActivity().getClass() != MainActivity.class) {
          hidePlaceholder();
        }
        break;
      case Configuration.SCREENLAYOUT_SIZE_LARGE:
        if (getActivity().getClass() != MainActivity.class) {
          hidePlaceholder();
        }
        break;
      case Configuration.SCREENLAYOUT_SIZE_NORMAL:
        hidePlaceholder();
      case Configuration.SCREENLAYOUT_SIZE_SMALL:
        hidePlaceholder();
      default:
        break;
    }

    super.onViewCreated(view, saveInstanceState);
    setImage(url);
    setTitle(title);
    setChannel(channel);
    setDate(date);
    setDescription(desc);
  }

  public static Fragment newInstance(Video video) {
    VideoDetailFragment vdf = new VideoDetailFragment();

    Bundle b = new Bundle(1);
    b.putParcelable("video", video);
    vdf.setArguments(b);
    return vdf;
  }

  public void setImage(String url) {
    ImageView img = (ImageView) getView().findViewById(R.id.detailImage);
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
