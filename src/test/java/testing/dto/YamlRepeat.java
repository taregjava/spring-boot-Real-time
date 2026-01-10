package testing.dto;

import lombok.Data;

@Data
public class YamlRepeat {
  private int timeoutSeconds;
  private int intervalSeconds;
  private YamlExpectation until; // reuse same structure (json expectations)

  // getters/setters
}
