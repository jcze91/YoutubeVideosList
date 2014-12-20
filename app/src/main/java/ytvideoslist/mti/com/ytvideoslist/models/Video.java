package ytvideoslist.mti.com.ytvideoslist.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Video implements Parcelable {
  private String title;
  private String channel;
  private String description;
  private Date published;
  private String url;
  private String smallThumbnail;
  private String mediumThumbnail;
  private String largeThumbnail;

  public Video(String title, String channel,
               String description, Date published, String url,
               String smallThumbnail, String mediumThumbnail, String largeThumbnail) {
    this.title = title;
    this.channel = channel;
    this.description = description;
    this.published = published;
    this.url = url;
    this.smallThumbnail = smallThumbnail;
    this.mediumThumbnail = mediumThumbnail;
    this.largeThumbnail = largeThumbnail;
  }

  public Video(Parcel in) {
    this.title = in.readString();
    this.channel = in.readString();
    this.description = in.readString();
    try {
      this.published = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH).parse(in.readString());
    } catch (ParseException e) {
      // TODO Properly handle this
      e.printStackTrace();
    }
    this.url = in.readString();
    this.smallThumbnail = in.readString();
    this.mediumThumbnail = in.readString();
    this.largeThumbnail = in.readString();
  }

  public String getTitle() {
    return title;
  }

  public String getChannel() {
    return channel;
  }

  public String getDescription() {
    return description;
  }

  public Date getPublished() {
    return published;
  }

  public String getUrl() {
    return url;
  }

  public String getSmallThumbnail() {
    return smallThumbnail;
  }

  public String getMediumThumbnail() {
    return mediumThumbnail;
  }

  public String getLargeThumbnail() {
    return largeThumbnail;
  }

  private String formatDate() {
    return new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss").format(getPublished());
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(getTitle());
    dest.writeString(getChannel());
    dest.writeString(getDescription());
    dest.writeString(formatDate());
    dest.writeString(getUrl());
    dest.writeString(getSmallThumbnail());
    dest.writeString(getMediumThumbnail());
    dest.writeString(getLargeThumbnail());
  }

  public static final Parcelable.Creator<Video> CREATOR = new Parcelable.Creator<Video>() {

    @Override
    public Video createFromParcel(Parcel source) {
      return new Video(source);
    }

    @Override
    public Video[] newArray(int size) {
      return new Video[size];
    }
  };
}
