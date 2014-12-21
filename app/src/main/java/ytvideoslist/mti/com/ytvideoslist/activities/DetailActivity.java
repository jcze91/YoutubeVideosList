package ytvideoslist.mti.com.ytvideoslist.activities;

import android.content.Intent;
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
import ytvideoslist.mti.com.ytvideoslist.models.Video;


public class DetailActivity extends ActionBarActivity {
  private static final String TAG = "DetailActivity";
  private Video video;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Window window = this.getWindow();

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
    }

    setContentView(R.layout.activity_detail);

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      findViewById(R.id.novideoPlaceholder).setVisibility(View.GONE);

      this.video = extras.getParcelable("video");

      VideoDetailFragment detailFragment = (VideoDetailFragment) getFragmentManager()
          .findFragmentById(R.id.detailFragment);
      detailFragment.setTitle(video.getTitle());
      detailFragment.setImage(video.getLargeThumbnail());
      detailFragment.setChannel(video.getChannel());
      detailFragment.setDate(video.getPublished());
      detailFragment.setDescription(video.getDescription());
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_detail, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    switch (id) {
      case R.id.action_share:
        Video.shareVideo(this, video);
        return true;
      default:
        break;
    }

    return super.onOptionsItemSelected(item);
  }

  public void watchVideo(View view) {
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(video.getUrl()));
    this.startActivity(intent);
  }
}
