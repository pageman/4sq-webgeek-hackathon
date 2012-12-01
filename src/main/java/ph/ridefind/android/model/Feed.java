package ph.ridefind.android.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class Feed {
    private Long id;

    private String desc;

    @JsonProperty("image_url")
    private String imageUrl;

    private String link;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    public static Feed buildFeed(String desc) {
        Feed feed = new Feed();
        feed.setDesc(desc);
        feed.setCreatedAt(new Date());
        feed.setUpdatedAt(new Date());
        return feed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
