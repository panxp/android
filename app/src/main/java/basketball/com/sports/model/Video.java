package basketball.com.sports.model;

import java.net.URI;
import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
public class Video {
    public String title;
    private String cover;
    private String length;
    private String url;

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCover() {
        return cover;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLength() {

        return length;
    }
}
