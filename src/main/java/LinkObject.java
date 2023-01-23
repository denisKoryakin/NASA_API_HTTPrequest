import com.fasterxml.jackson.annotation.JsonProperty;

public class LinkObject {
    private final String date;
    private final String explanation;
    private final String hdurl;
    private final String media_type;
    private final String service_version;
    private final String title;
    private final String url;
    private final String path;


    LinkObject(
            @JsonProperty("date") String date,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") String hdurl,
            @JsonProperty("media_type") String media_type,
            @JsonProperty("service_version") String service_version,
            @JsonProperty("title") String title,
            @JsonProperty("url") String url
    ) {
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.media_type = media_type;
        this.service_version = service_version;
        this.title = title;
        this.url = url;
        path = getWords(this.hdurl);
    }

    private String getWords(String hdurl) {
        String[] words = hdurl.split("/");
        return words[words.length - 1];
    }

    public String getTitle() {
        return title;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getPath() {
        return path;
    }
}
