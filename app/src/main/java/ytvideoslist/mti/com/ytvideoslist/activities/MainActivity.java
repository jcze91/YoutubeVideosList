package ytvideoslist.mti.com.ytvideoslist.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import ytvideoslist.mti.com.ytvideoslist.R;
import ytvideoslist.mti.com.ytvideoslist.fragments.VideoDetailFragment;
import ytvideoslist.mti.com.ytvideoslist.fragments.VideoListFragment;
import ytvideoslist.mti.com.ytvideoslist.models.Video;
import ytvideoslist.mti.com.ytvideoslist.utils.VideoViewHolder;


public class MainActivity extends ActionBarActivity implements VideoViewHolder.IVideoViewHolderClicks {

  // TODO Remove possible useless debug logs prior to submission
  private static final String TAG = "MainActivity";
  public static Typeface ROBOTO_SLAB;
  // This is to follow Material Design guidelines even on pre-Lollipop devices
  public static Typeface ROBOTO_MEDIUM;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ROBOTO_SLAB = Typeface.createFromAsset(this.getAssets(), "fonts/RobotoSlab-Regular.ttf");
    ROBOTO_MEDIUM = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Medium.ttf");

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window window = this.getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      window.setStatusBarColor(this.getResources().getColor(R.color.teal_600));
    }

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

  @Override
  public void onThumbnail(int position) {
    VideoDetailFragment fragment = (VideoDetailFragment) getFragmentManager()
        .findFragmentById(R.id.detailFragment);
    Video current = VideoListFragment.videoList.get(position);

    if (fragment != null && fragment.isInLayout()) {
      fragment.setText(current.getTitle());
    } else {
      Intent intent = new Intent(getApplicationContext(),
          DetailActivity.class);
      intent.putExtra("video", current);
      startActivity(intent);
    }
  }

  @Override
  public void onChannel(int position) {
    StringBuilder sb = new StringBuilder();
    Video current = VideoListFragment.videoList.get(position);
    Log.d(TAG, sb.append("Go to ").append(current.getChannel()).append("channel's").toString());
  }

  @Override
  public void onShare(int position) {
    StringBuilder sb = new StringBuilder();
    Video current = VideoListFragment.videoList.get(position);
    Log.d(TAG, sb.append("Sharing ").append(current.getTitle()).toString());
  }
}
