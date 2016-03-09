package com.adi.ho.jackie.versa_news.GSONClasses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

/**
 * Created by Rob on 3/7/16.
 */
public class ViceItemsClass {

    private String title;
    private String preview;
    private String[] tags; // Array of search tags
    private String body;
    private String id; // Article id
    private String url; // Direct URL for article
    private String author; // Author name only (no detailed info)
    private String pubDate;
    private String category; // Category of the article (music, etc)
    private String thumb; // Article image thumbnail
    private String image; // Article image

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getArticleID(){
        String data = "";
        Gson gson = new Gson();
        ViceSearchResultsClass results = gson.fromJson(data, ViceSearchResultsClass.class);

        String articleID = results.getData().getItems().get(0).getId();
        return articleID;
    }

}
