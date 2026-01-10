package testing.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class YamlTestSuite {
  private String name;
  private String env;
  private Map<String, String> variables;
  private List<YamlStep> steps;
}
