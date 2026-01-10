package testing.lib;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TestContext {
  private final Map<String, String> vars = new HashMap<>();

  public TestContext() {
    vars.put("randomUuid", UUID.randomUUID().toString());
  }

  public void put(String key, String value) {
    if (value != null) {
      vars.put(key, value);
    }
  }

  public String get(String key) {
    return vars.get(key);
  }

  public String resolvePlaceholders(String text) {
    if (text == null) return null;
    String result = text;
    for (Map.Entry<String, String> e : vars.entrySet()) {
      result = result.replace("${" + e.getKey() + "}", e.getValue());
    }
    return result;
  }

  public Map<String, String> resolveMap(Map<String, String> input) {
    if (input == null) return null;
    Map<String, String> out = new HashMap<>();
    input.forEach((k, v) -> out.put(k, resolvePlaceholders(v)));
    return out;
  }

  public void putAll(Map<String, String> map) {
    if (map == null) return;
    map.forEach(this::put);
  }
}
