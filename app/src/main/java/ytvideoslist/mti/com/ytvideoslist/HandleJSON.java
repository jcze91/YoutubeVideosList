package ytvideoslist.mti.com.ytvideoslist;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ytvideoslist.mti.com.ytvideoslist.models.Video;

/**
 * Created by Yayap on 19/12/14.
 */
public class HandleJSON extends AsyncTask<Void, Void, Void>{

    private String urlString;
    private List<Video> listVideos;

    public HandleJSON (String url)
    {
        this.urlString = url;
        this.listVideos = new ArrayList<Video>();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            JSONObject reader = new JSONObject(urlString);

            JSONArray items = reader.getJSONArray("items");

            for (int i = 0; i < items.length(); ++i)
            {
                JSONObject item = items.getJSONObject(i);


                JSONObject snippet = item.getJSONObject("snippet");
                String date = snippet.getString("publishedAt");
                String channel = snippet.getString("channelTitle");
                String title = snippet.getString("title");
                String description = snippet.getString("description");

                JSONObject thumbnails = item.getJSONObject("thumbnails");
                String smallTumbnail = thumbnails.getString("default");
                String mediumThumbnail = thumbnails.getString("medium");
                String largeTumbnail = thumbnails.getString("high");

                Video video = new Video(title,
                        channel,
                        description,
                        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH).parse(date),
                        smallTumbnail,
                        mediumThumbnail,
                        largeTumbnail);

                this.listVideos.add(video);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
