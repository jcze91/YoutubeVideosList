package ytvideoslist.mti.com.ytvideoslist.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import ytvideoslist.mti.com.ytvideoslist.R;
import ytvideoslist.mti.com.ytvideoslist.fragments.VideoDetailFragment;


public class DetailActivity extends ActionBarActivity {
  private static final String TAG = "DetailActivity";
  public static final String EXTRA_URL = "url";
  private String url;

    @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window window = this.getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      window.setStatusBarColor(this.getResources().getColor(R.color.teal_600));
    }

    setContentView(R.layout.activity_detail);

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      url = extras.getString(EXTRA_URL);
      VideoDetailFragment detailFragment = (VideoDetailFragment) getFragmentManager()
          .findFragmentById(R.id.detailFragment);
      detailFragment.setText(url);
    }
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_detail, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    switch (id) {
      case R.id.action_share:
        Log.d(TAG, "Sharing is caring");
          Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
          sharingIntent.setType("text/plain");
          String shareBody = "Hey check out this video : " + url;
          sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
          startActivity(Intent.createChooser(sharingIntent, "Share via"));
          return true;
      default:
        break;
    }

    return super.onOptionsItemSelected(item);
  }

    public void watchVideo(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + "v_UyVmITiYQ"));
        this.startActivity(intent);
    }
}
