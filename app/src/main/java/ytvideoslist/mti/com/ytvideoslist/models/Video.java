package ytvideoslist.mti.com.ytvideoslist.models;

import java.util.Date;

public class Video {
  private String title;
  private String channel;
  private String description;
  private Date published;
  private String smallThumbnail;
  private String mediumThumbnail;
  private String largeThumbnail;

  public Video(String title, String channel,
               String description, Date published,
               String smallThumbnail, String mediumThumbnail, String largeThumbnail) {
    this.title = title;
    this.channel = channel;
    this.description = description;
    this.published = published;
    this.smallThumbnail = smallThumbnail;
    this.mediumThumbnail = mediumThumbnail;
    this.largeThumbnail = largeThumbnail;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getPublished() {
    return published;
  }

  public void setPublished(Date published) {
    this.published = published;
  }

  public String getSmallThumbnail() {
    return smallThumbnail;
  }

  public void setSmallThumbnail(String smallThumbnail) {
    this.smallThumbnail = smallThumbnail;
  }

  public String getMediumThumbnail() {
    return mediumThumbnail;
  }

  public void setMediumThumbnail(String mediumThumbnail) {
    this.mediumThumbnail = mediumThumbnail;
  }

  public String getLargeThumbnail() {
    return largeThumbnail;
  }

  public void setLargeThumbnail(String largeThumbnail) {
    this.largeThumbnail = largeThumbnail;
  }
}
