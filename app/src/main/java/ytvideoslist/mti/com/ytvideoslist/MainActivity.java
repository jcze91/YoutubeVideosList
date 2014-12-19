package ytvideoslist.mti.com.ytvideoslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.cards.material.MaterialLargeImageCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;


public class MainActivity extends ActionBarActivity {

  // TODO Remove possible useless debug logs prior to submission
  private static final String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .add(R.id.container, new VideoListFragment())
          .commit();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    switch (id) {
      case R.id.action_share:
        Log.d(TAG, "Sharing is caring");
        return true;
      case R.id.action_about:
        navigateToAbout();
        return true;
      default:
        break;
    }

    return super.onOptionsItemSelected(item);
  }

  private void navigateToAbout() {
    Intent intent = new Intent(this, AboutActivity.class);
    startActivity(intent);
  }

  // TODO Maybe move the fragment out to a dedicated class/file

  /**
   * The fragment containing the video list.
   */
  public static class VideoListFragment extends Fragment {

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

      // TODO Fetch data from the API and populate cards based on Video Model
      for (int i = 0; i < 5; ++i) {
        MaterialLargeImageCard card =
            MaterialLargeImageCard.with(getActivity())
                .setTextOverImage("")
                .setTitle("Google unveils Google Glass Explorer Edition at I/O - CNET News")
                .setSubTitle("CNETTV")
                .useDrawableId(R.drawable.video_thumb)
                .build();
        card.setOnClickListener(new Card.OnCardClickListener() {
          @Override
          public void onClick(Card card, View view) {
            // TODO Open detailed view for the current card
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
  }
}
