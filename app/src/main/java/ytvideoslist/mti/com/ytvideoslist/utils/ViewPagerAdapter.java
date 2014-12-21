package ytvideoslist.mti.com.ytvideoslist.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import ytvideoslist.mti.com.ytvideoslist.fragments.VideoDetailFragment;
import ytvideoslist.mti.com.ytvideoslist.models.Video;

public class ViewPagerAdapter extends FragmentPagerAdapter {
  ArrayList<Video> videoList;

  public ViewPagerAdapter(FragmentManager fm, ArrayList<Video> videoList) {
    super(fm);
    this.videoList = videoList;
  }

  @Override
  public Fragment getItem(int position) {
    Video v = videoList.get(position);
    return VideoDetailFragment.newInstance(v);
  }

  @Override
  public int getCount() {
    return this.videoList.size();
  }
}
