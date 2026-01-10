package testing.lib;

import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;

import rmun.technical.testing.dto.YamlTestSuite;

public class YamlTestLoader {

  public static YamlTestSuite loadSuite(InputStream is) {
    Yaml yaml = new Yaml();
    return yaml.loadAs(is, YamlTestSuite.class);
  }
}
