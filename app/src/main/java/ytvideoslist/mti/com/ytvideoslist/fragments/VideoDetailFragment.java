package ytvideoslist.mti.com.ytvideoslist.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ytvideoslist.mti.com.ytvideoslist.R;

public class VideoDetailFragment extends Fragment {
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_video_detail,
        container, false);
  }

  public void setText(String item) {
    // TODO Check NullPointerException
    TextView view = (TextView) getView().findViewById(R.id.detailsText);
    view.setText(item);
  }
}
