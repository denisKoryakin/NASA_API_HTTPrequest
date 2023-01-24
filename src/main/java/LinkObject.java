import com.fasterxml.jackson.annotation.JsonProperty;

public class LinkObject {
    private final String date;
    private final String explanation;
    private final String hdurl;
    private final String mediaType;
    private final String serviceVersion;
    private final String title;
    private final String url;
    private final String path;


    LinkObject(
            @JsonProperty("date") String date,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") String hdurl,
            @JsonProperty("media_type") String mediaType,
            @JsonProperty("service_version") String serviceVersion,
            @JsonProperty("title") String title,
            @JsonProperty("url") String url
    ) {
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.mediaType = mediaType;
        this.serviceVersion = serviceVersion;
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
