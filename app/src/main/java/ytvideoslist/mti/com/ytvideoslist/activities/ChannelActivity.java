package ytvideoslist.mti.com.ytvideoslist.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import ytvideoslist.mti.com.ytvideoslist.R;
import ytvideoslist.mti.com.ytvideoslist.fragments.VideoListFragment;
import ytvideoslist.mti.com.ytvideoslist.models.Video;
import ytvideoslist.mti.com.ytvideoslist.utils.ChannelAdapter;
import ytvideoslist.mti.com.ytvideoslist.utils.ChannelViewHolder;

public class ChannelActivity extends ActionBarActivity implements ChannelViewHolder.IChannelViewHolderClicks {
  public static ArrayList<Video> videoList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_channel);

    videoList = new ArrayList<>();

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window window = this.getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      window.setStatusBarColor(this.getResources().getColor(R.color.teal_600));
    }

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      String title = extras.getString("channel");
      getSupportActionBar().setTitle(title);

      for (Video vid : VideoListFragment.videoList) {
        if (vid.getChannel().equals(title)) {
          videoList.add(vid);
        }
      }

      RecyclerView recList = (RecyclerView) findViewById(R.id.videoList);
      recList.setHasFixedSize(true);
      LinearLayoutManager llm = new LinearLayoutManager(this);
      llm.setOrientation(LinearLayoutManager.VERTICAL);
      recList.setLayoutManager(llm);

      ChannelAdapter ca = new ChannelAdapter(this, videoList);
      recList.setAdapter(ca);
    }
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_channel, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_about) {
      navigateToAbout();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void navigateToAbout() {
    Intent intent = new Intent(this, AboutActivity.class);
    startActivity(intent);
  }

  @Override
  public void onItem(int position) {
    Video current = videoList.get(position);

    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
    intent.putExtra("video", current);
    intent.putExtra("source", getClass().getCanonicalName());
    startActivity(intent);
  }
}
