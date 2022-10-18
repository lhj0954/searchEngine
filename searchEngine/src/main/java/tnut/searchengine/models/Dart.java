package tnut.searchengine.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setting(settingPath = "/static/settings/noriSetting.json")
@Mapping()
public class Dart {
    private String id;

    private String title;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;
}
