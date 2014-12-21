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

import ytvideoslist.mti.com.ytvideoslist.R;
import ytvideoslist.mti.com.ytvideoslist.fragments.VideoDetailFragment;
import ytvideoslist.mti.com.ytvideoslist.fragments.VideoListFragment;
import ytvideoslist.mti.com.ytvideoslist.models.Video;
import ytvideoslist.mti.com.ytvideoslist.utils.ViewPagerAdapter;


public class DetailActivity extends ActionBarActivity {
  private static final String TAG = "DetailActivity";
  private Video video;
  private ViewPager mViewPager;
  private ViewPagerAdapter mAdapter;

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
    mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    mViewPager.setAdapter(mAdapter);
    mViewPager.setCurrentItem(this.getVideoIndex(this.video));
    mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
          video = VideoListFragment.videoList.get(mViewPager.getCurrentItem());
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

  public void watchVideo(View view) {
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(video.getUrl()));
    this.startActivity(intent);
  }

  private void navigateToAbout() {
    Intent intent = new Intent(this, AboutActivity.class);
    startActivity(intent);
  }

  private int getVideoIndex(Video video)
  {
      for (int i = 0; i < VideoListFragment.videoList.size(); ++i){
        if (video.getTitle().equals(VideoListFragment.videoList.get(i).getTitle()))
            return i;
      }
      return 0;
  }
}
