package ytvideoslist.mti.com.ytvideoslist.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import it.gmariotti.cardslib.library.cards.material.MaterialLargeImageCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;
import ytvideoslist.mti.com.ytvideoslist.HandleJSON;
import ytvideoslist.mti.com.ytvideoslist.R;
import ytvideoslist.mti.com.ytvideoslist.models.Video;

public class VideoListFragment extends Fragment {
  private OnItemSelectedListener listener;
  private ArrayList<Video> videoList;


  public void setVideoList(ArrayList<Video> videoList) {
      this.videoList = videoList;
  }

  public VideoListFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_main, container, false);

    return rootView;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    ArrayList<Card> cards = new ArrayList<>();

      try {
        HandleJSON hj = new HandleJSON("http://tutos-android.com/projet/youtube_api_search.json");
        hj.execute();
        this.videoList = hj.get();
      } catch (Exception e) {
          e.printStackTrace();
      }

      for (int i = 0; i < videoList.size(); ++i) {
      Video current = videoList.get(i);
      MaterialLargeImageCard card =
          MaterialLargeImageCard.with(getActivity())
              .setTextOverImage("")
              .setTitle(current.getTitle())
              .setSubTitle(current.getChannel())
              .useDrawableUrl(current.getMediumThumbnail())
              .build();
      card.setOnClickListener(new Card.OnCardClickListener() {
        @Override
        public void onClick(Card card, View view) {
          updateDetail(card.getTitle());
        }
      });

      cards.add(card);
    }

    CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(), cards);

    CardListView listView = (CardListView) getActivity().findViewById(R.id.videoList);
    if (listView != null) {
      listView.setAdapter(mCardArrayAdapter);
    }
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof OnItemSelectedListener) {
      listener = (OnItemSelectedListener) activity;
    } else {
      throw new ClassCastException(activity.toString()
          + " must implement VideoListFragment.OnItemSelectedListener");
    }
  }

  // TODO Replace the String with more complete video data
  public void updateDetail(String title) {
    listener.onItemSelected(title);
  }

  public static interface OnItemSelectedListener {
    public void onItemSelected(String link);
  }
}