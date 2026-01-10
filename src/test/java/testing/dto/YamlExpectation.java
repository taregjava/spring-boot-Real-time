package testing.dto;

import java.util.Map;

import lombok.Data;

@Data
public class YamlExpectation {
  private int status;
  // json path -> expected value ("notNull", "COMPLETED", etc.)
  private Map<String, String> json;

  // getters/setters
}
