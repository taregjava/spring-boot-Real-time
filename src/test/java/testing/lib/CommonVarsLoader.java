package testing.lib;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import rmun.technical.testing.dto.CommonVars;

public final class CommonVarsLoader {

  private static final String FILE_NAME = "common-vars.yml";
  private static CommonVars cached;

  private CommonVarsLoader() {}

  private static CommonVars loadRaw() {
    if (cached != null) return cached;

    try (InputStream is =
        Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_NAME)) {
      if (is == null) {
        throw new IllegalStateException("Could not find " + FILE_NAME + " on classpath");
      }
      Yaml yaml = new Yaml();
      cached = yaml.loadAs(is, CommonVars.class);
      return cached;
    } catch (Exception e) {
      throw new RuntimeException("Failed to load " + FILE_NAME, e);
    }
  }

  /**
   * envName can be: - explicit from suite (suite.env) - null -> will use system property or
   * defaultEnv in YAML
   */
  public static Map<String, String> loadForEnv(String envNameOverride) {
    CommonVars cv = loadRaw();

    // precedence:
    // 1) system property test.env
    // 2) suite env (envNameOverride)
    // 3) defaultEnv from YAML
    String envName = System.getProperty("test.env");
    if (envName == null || envName.isBlank()) {
      envName = envNameOverride;
    }
    if (envName == null || envName.isBlank()) {
      envName = cv.getDefaultEnv();
    }
    if (envName == null || envName.isBlank()) {
      throw new IllegalStateException("No env specified and no defaultEnv in common-vars.yml");
    }

    Map<String, String> result = new HashMap<>();
    if (cv.getCommon() != null) {
      result.putAll(cv.getCommon());
    }

    Map<String, String> envVars =
        cv.getEnvironments() != null ? cv.getEnvironments().get(envName) : null;

    if (envVars == null) {
      throw new IllegalArgumentException(
          "Environment '" + envName + "' not found in common-vars.yml");
    }

    result.putAll(envVars);
    result.put("env", envName); // useful inside tests / logs

    return result;
  }
}
