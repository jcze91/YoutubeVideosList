package ytvideoslist.mti.com.ytvideoslist.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ytvideoslist.mti.com.ytvideoslist.R;
import ytvideoslist.mti.com.ytvideoslist.models.Video;
import ytvideoslist.mti.com.ytvideoslist.utils.CardAdapter;
import ytvideoslist.mti.com.ytvideoslist.utils.HandleJSON;
import ytvideoslist.mti.com.ytvideoslist.utils.VideoViewHolder;


public class VideoListFragment extends Fragment {

  private final String API_URL = "http://tutos-android.com/projet/youtube_api_search.json";
  public static ArrayList<Video> videoList;

  public VideoListFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    return inflater.inflate(R.layout.fragment_main, container, false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    videoList = new ArrayList<>();

    try {
      HandleJSON hj = new HandleJSON(API_URL);
      hj.execute();
      videoList = hj.get();
    } catch (Exception e) {
      e.printStackTrace();
    }

    RecyclerView recList = (RecyclerView) getActivity().findViewById(R.id.cardList);
    recList.setHasFixedSize(true);
    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
    llm.setOrientation(LinearLayoutManager.VERTICAL);
    recList.setLayoutManager(llm);

    CardAdapter ca = new CardAdapter(videoList);
    recList.setAdapter(ca);
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof VideoViewHolder.IVideoViewHolderClicks) {
    } else {
      throw new ClassCastException(activity.toString()
          + " must implement VideoViewHolder.IVideoViewHolderClicks");
    }
  }
}