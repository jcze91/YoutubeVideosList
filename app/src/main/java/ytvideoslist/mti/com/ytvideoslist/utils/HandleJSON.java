package ytvideoslist.mti.com.ytvideoslist.utils;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import ytvideoslist.mti.com.ytvideoslist.models.Video;

/**
 * Created by Yayap on 19/12/14.
 */
public class HandleJSON extends AsyncTask<Void, Void, ArrayList<Video>> {

  private String urlString;
  private ArrayList<Video> videoList;

  public HandleJSON(String url) {
    this.urlString = url;
    this.videoList = new ArrayList<Video>();
  }

  @Override
  protected ArrayList<Video> doInBackground(Void... params) {
    try {
      // http client
      DefaultHttpClient httpClient = new DefaultHttpClient();
      HttpEntity httpEntity = null;
      HttpResponse httpResponse = null;
      HttpGet httpGet = new HttpGet(this.urlString);
      httpResponse = httpClient.execute(httpGet);
      httpEntity = httpResponse.getEntity();
      String response = EntityUtils.toString(httpEntity);

      StringBuilder sb;

      JSONObject reader = new JSONObject(response);
      JSONArray items = reader.getJSONArray("items");

      for (int i = 0; i < items.length(); ++i) {
        JSONObject item = items.getJSONObject(i);

        String id = item.getJSONObject("id").getString("videoId");

        sb = new StringBuilder();
        String url = sb.append("http://youtu.be/").append(id).toString();

        JSONObject snippet = item.getJSONObject("snippet");
        String date = snippet.getString("publishedAt");
        String channel = snippet.getString("channelTitle");
        String title = snippet.getString("title");
        String description = snippet.getString("description");

        JSONObject thumbnails = snippet.getJSONObject("thumbnails");
        String smallTumbnail = thumbnails.getJSONObject("default").getString("url");
        String mediumThumbnail = thumbnails.getJSONObject("medium").getString("url");
        String largeTumbnail = thumbnails.getJSONObject("high").getString("url");

        Date published = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH).parse(date);

        Video video = new Video(title,
            channel,
            description,
            new SimpleDateFormat("yyyy/MM/dd - hh:mm").format(published),
            url,
            smallTumbnail,
            mediumThumbnail,
            largeTumbnail);

        this.videoList.add(video);
      }

    } catch (Exception e) {
      // TODO Properly handle this
      e.printStackTrace();
    }
    return videoList;
  }
}
