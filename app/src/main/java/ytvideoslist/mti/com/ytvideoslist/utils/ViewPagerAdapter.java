package ytvideoslist.mti.com.ytvideoslist.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import ytvideoslist.mti.com.ytvideoslist.fragments.VideoDetailFragment;
import ytvideoslist.mti.com.ytvideoslist.fragments.VideoListFragment;
import ytvideoslist.mti.com.ytvideoslist.models.Video;

public class ViewPagerAdapter extends FragmentPagerAdapter {

  public ViewPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override
  public Fragment getItem(int position) {
    Video v = VideoListFragment.videoList.get(position);
    return VideoDetailFragment.newInstance(v);
  }

  @Override
  public int getCount() {
    return VideoListFragment.videoList.size();
  }
}
