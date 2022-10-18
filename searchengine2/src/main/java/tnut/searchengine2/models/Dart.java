package tnut.searchengine2.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Immutable;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.mapping.model.Property;

import java.util.Properties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "dart", createIndex = true)
@Setting(settingPath = "elasticsearch/setting/setting.json")
@Mapping(mappingPath = "elasticsearch/mapping/dartMapping.json")
public class Dart {

    @Id
    private String id;

    private String keyword;

    private String stock_code;

    private Object data;

    private String item_id;

    private String text;

    private String publishDate;
}
