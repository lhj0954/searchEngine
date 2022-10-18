package tnut.searchengine.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class News {

    private String id;

    private String title;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String publishDate;
}
