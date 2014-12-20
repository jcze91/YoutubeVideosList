package ytvideoslist.mti.com.ytvideoslist;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

            //JSONObject result = reader.getJSONObject("resultat");

            JSONArray items = reader.getJSONArray("items");

            for (int i = 0; i < items.length(); ++i)
            {
                Video video = new Video();
                JSONObject item = items.getJSONObject(i);

                video.setTag(item.getString("etag"));

                JSONObject id = item.getJSONObject("id");
                video.setIdKind(id.getString("kind"));
                video.setId(id.getString("videoId"));

                JSONObject snippet = item.getJSONObject("snippet");
                video.setPublishedAt(snippet.getString("publishedAt"));
                video.setChannelId(snippet.getString("channelId"));
                video.setChanelTitle(snippet.getString("channelTitle"));
                video.setTitle(snippet.getString("title"));
                video.setDescription(snippet.getString("description"));

                JSONObject thumbnails = item.getJSONObject("thumbnails");
                video.setThumbnailDefault(thumbnails.getString("default"));
                video.setThumbnailMedium(thumbnails.getString("medium"));
                video.setThumbnailHigh(thumbnails.getString("high"));



                this.listVideos.add(video);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
