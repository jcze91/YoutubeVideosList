package ytvideoslist.mti.com.ytvideoslist.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

  private Video current;

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
        if (current != null)
          Video.shareVideo(this, current);
        else {
          new AlertDialog.Builder(this)
              .setTitle(getResources().getString(R.string.shareAlertTitle))
              .setMessage(getResources().getString(R.string.shareAlertMsg))
              .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
              })
              .show();
        }
        return true;
      case R.id.action_about:
        navigateToAbout();
        return true;
      default:
        break;
    }

    return super.onOptionsItemSelected(item);
  }

  public void watchVideo(View view) {
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(current.getUrl()));
    startActivity(intent);
  }

  private void navigateToAbout() {
    Intent intent = new Intent(this, AboutActivity.class);
    startActivity(intent);
  }

  @Override
  public void onThumbnail(int position) {
    VideoDetailFragment detailFragment = (VideoDetailFragment) getSupportFragmentManager()
        .findFragmentById(R.id.detailFragment);
    current = VideoListFragment.videoList.get(position);

    if (detailFragment != null && detailFragment.isInLayout()) {
      detailFragment.getActivity().findViewById(R.id.novideoPlaceholder).setVisibility(View.GONE);

      detailFragment.setTitle(current.getTitle());
      detailFragment.setImage(current.getLargeThumbnail());
      detailFragment.setChannel(current.getChannel());
      detailFragment.setDate(current.getPublished());
      detailFragment.setDescription(current.getDescription());
    } else {
      Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
      intent.putExtra("video", current);
      startActivity(intent);
    }
  }

  @Override
  public void onChannel(int position) {
    Video current = VideoListFragment.videoList.get(position);
    Intent intent = new Intent(this, ChannelActivity.class);
    intent.putExtra("channel", current.getChannel());
    startActivity(intent);
  }

  @Override
  public void onShare(int position) {
    Video current = VideoListFragment.videoList.get(position);
    Video.shareVideo(this, current);
  }
}
