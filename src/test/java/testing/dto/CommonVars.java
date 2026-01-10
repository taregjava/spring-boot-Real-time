package testing.dto;

import java.util.Map;

public class CommonVars {
  private String defaultEnv;
  private Map<String, String> common;
  private Map<String, Map<String, String>> environments;

  public String getDefaultEnv() {
    return defaultEnv;
  }

  public void setDefaultEnv(String defaultEnv) {
    this.defaultEnv = defaultEnv;
  }

  public Map<String, String> getCommon() {
    return common;
  }

  public void setCommon(Map<String, String> common) {
    this.common = common;
  }

  public Map<String, Map<String, String>> getEnvironments() {
    return environments;
  }

  public void setEnvironments(Map<String, Map<String, String>> environments) {
    this.environments = environments;
  }
}
