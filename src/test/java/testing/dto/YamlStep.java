package testing.dto;

import java.util.Map;

import lombok.Data;

@Data
public class YamlStep {
  private String name;
  private String method;
  private String path;
  private Map<String, String> headers;
  private Map<String, String> formParams;
  private String body;
  private YamlExpectation expect;
  private Map<String, String> extract;
  private YamlRepeat repeat;
}
