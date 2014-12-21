package ytvideoslist.mti.com.ytvideoslist.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import ytvideoslist.mti.com.ytvideoslist.R;


public class AboutActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window window = this.getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      window.setStatusBarColor(this.getResources().getColor(R.color.teal_600));
    }

    setContentView(R.layout.activity_about);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_about, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {

      // This is to avoid returning to our MainActivity even when called from ChannelActivity or DetailActivity, thus making navigation more consistent
      case android.R.id.home:
        onBackPressed();
        return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
