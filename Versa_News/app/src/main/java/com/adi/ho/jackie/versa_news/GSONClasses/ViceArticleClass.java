package com.adi.ho.jackie.versa_news.GSONClasses;

/**
 * Created by JHADI on 3/9/16.
 */
public class ViceArticleClass {

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

    public String getPreview() {
        return preview;
    }

    public String[] getTags() {
        return tags;
    }

    public String getBody() {
        return body;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthor() {
        return author;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getCategory() {
        return category;
    }

    public String getThumb() {
        return thumb;
    }

    public String getImage() {
        return image;
    }
}
