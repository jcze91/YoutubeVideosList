package ytvideoslist.mti.com.ytvideoslist.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import ytvideoslist.mti.com.ytvideoslist.R;
import ytvideoslist.mti.com.ytvideoslist.fragments.VideoDetailFragment;
import ytvideoslist.mti.com.ytvideoslist.fragments.VideoListFragment;
import ytvideoslist.mti.com.ytvideoslist.models.Video;
import ytvideoslist.mti.com.ytvideoslist.utils.ViewPagerAdapter;


public class DetailActivity extends ActionBarActivity {
  private Video video;
  private ViewPager mViewPager;
  private ViewPagerAdapter mAdapter;
  public static ArrayList<Video> videoList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

      VideoDetailFragment detailFragment = (VideoDetailFragment) getSupportFragmentManager()
          .findFragmentById(R.id.detailFragment);
      detailFragment.setTitle(video.getTitle());
      detailFragment.setImage(video.getLargeThumbnail());
      detailFragment.setChannel(video.getChannel());
      detailFragment.setDate(video.getPublished());
      detailFragment.setDescription(video.getDescription());
    }

    mViewPager = (ViewPager) findViewById(R.id.viewpager);

    if (extras != null && extras.getString("source").equals(ChannelActivity.class.getCanonicalName())) {
      DetailActivity.videoList = ChannelActivity.videoList;
    } else
      DetailActivity.videoList = VideoListFragment.videoList;

    mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), DetailActivity.videoList);
    mViewPager.setAdapter(mAdapter);
    mViewPager.setCurrentItem(this.getVideoIndex(this.video));
    mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
      @Override
      public void onPageSelected(int position) {
        video = DetailActivity.videoList.get(mViewPager.getCurrentItem());
      }
    });
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
      case R.id.action_about:
        navigateToAbout();
        return true;
      default:
        break;
    }

    return super.onOptionsItemSelected(item);
  }

  public void goToChannel(View view) {
    Intent intent = new Intent(this, ChannelActivity.class);
    intent.putExtra("channel", video.getChannel());
    startActivity(intent);
  }

  public void watchVideo(View view) {
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(video.getUrl()));
    this.startActivity(intent);
  }

  private void navigateToAbout() {
    Intent intent = new Intent(this, AboutActivity.class);
    startActivity(intent);
  }

  private int getVideoIndex(Video video) {
    for (int i = 0; i < DetailActivity.videoList.size(); ++i) {
      if (video.getTitle().equals(DetailActivity.videoList.get(i).getTitle()))
        return i;
    }
    return 0;
  }
}
