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

    //ArrayList<Video> videolist = new ArrayList<>();

    /* I'm ugly and I know it
      TODO Fetch data from the API */
    /*try {
      videolist.add(new Video("Project Ara Prototype shown at Google I/O 2014",
          "qualitypointtech",
          "Paul Eremenko, the head of Project Ara showed the prototype of Project Ara at Google I/O 2014. Though the phone failed to fully load, Audience cheered on see.",
          new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH).parse("2014-06-27T10:31:41.000Z"),
          "https://i.ytimg.com/vi/0He3Jr-fZh0/default.jpg",
          "https://i.ytimg.com/vi/0He3Jr-fZh0/mqdefault.jpg",
          "https://i.ytimg.com/vi/0He3Jr-fZh0/hqdefault.jpg"));

      videolist.add(new Video("LG G Watch Hands-on Impressions!",
          "marquesbrownlee",
          "LG's Android Wear Smartwatch. More I/O 2014 coverage coming! LG G Watch: https://play.google.com/store/devices/details?id=lg_g_watch_black Props for Lew ...",
          new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH).parse("2014-06-26T06:52:49.000Z"),
          "https://i.ytimg.com/vi/yBWNZTYPbzA/default.jpg",
          "https://i.ytimg.com/vi/yBWNZTYPbzA/mqdefault.jpg",
          "https://i.ytimg.com/vi/yBWNZTYPbzA/hqdefault.jpg"));

      videolist.add(new Video("Google I/O 2013: Keynote",
          "GoogleDevelopers",
          "Google I/O keynote. To check out, \\\"Here's to your imagination,\\\" the opening video to the conference, visit http://www.youtube.com/watch?v=Dr3STRBtTp0 ...",
          new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH).parse("2013-05-15T20:34:15.000Z"),
          "https://i.ytimg.com/vi/9pmPa_KxsAM/default.jpg",
          "https://i.ytimg.com/vi/9pmPa_KxsAM/mqdefault.jpg",
          "https://i.ytimg.com/vi/9pmPa_KxsAM/hqdefault.jpg"));

      videolist.add(new Video("Google unveils Google Glass Explorer Edition at I/O - CNET News",
          "CNETTV",
          "http://cnet.co/LtDHse Google's Sergey Brin reveals video-capturing, augmented reality glasses at Google I/O in San Francisco. The Google Glass Explorer Editi...",
          new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH).parse("2012-06-27T20:26:45.000Z"),
          "https://i.ytimg.com/vi/OLn0cSZfl6c/default.jpg",
          "https://i.ytimg.com/vi/OLn0cSZfl6c/mqdefault.jpg",
          "https://i.ytimg.com/vi/OLn0cSZfl6c/mqdefault.jpg"));

      videolist.add(new Video("Google I/O 2012 - Keynote Day 1",
          "GoogleDevelopers",
          "Video footage from Day 1 keynote at Google I/O 2012.",
          new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH).parse("2012-06-28T21:41:01.000Z"),
          "https://i.ytimg.com/vi/VuC0i4xTyrI/default.jpg",
          "https://i.ytimg.com/vi/VuC0i4xTyrI/mqdefault.jpg",
          "https://i.ytimg.com/vi/VuC0i4xTyrI/hqdefault.jpg"));

      videolist.add(new Video("Unreal Engine 4 \"Rivalry\" Demo -- Google I/O 2014",
          "UnrealDevelopmentKit",
          "Epic's Unreal Engine 4 'Rivalry' project demonstrated at Google I/O shows what's possible when PC-class gaming technologies and performance are brought to ...",
          new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH).parse("2014-06-25T21:51:11.000Z"),
          "https://i.ytimg.com/vi/X-tAZtbDZ8E/default.jpg",
          "https://i.ytimg.com/vi/X-tAZtbDZ8E/mqdefault.jpg",
          "https://i.ytimg.com/vi/X-tAZtbDZ8E/hqdefault.jpg"));
    } catch (ParseException e) {
      Log.d("PARSE ERROR", e.getMessage());
      e.printStackTrace();
    }*/

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