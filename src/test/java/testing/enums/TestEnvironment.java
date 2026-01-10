package testing.enums;

public enum TestEnvironment {
  DEV(
      "https://dev.api.my-domain.sa",
      "https://kc-dev.my-domain.sa/realms/myrealm/protocol/openid-connect/token",
      "test-client-id",
      "test-client-secret"),
  STG(
      "https://stg.api.my-domain.sa",
      "https://kc-stg.my-domain.sa/realms/myrealm/protocol/openid-connect/token",
      "stg-client-id",
      "stg-client-secret"),
  PPD(
      "https://ppd.api.my-domain.sa",
      "https://kc-ppd.my-domain.sa/realms/myrealm/protocol/openid-connect/token",
      "ppd-client-id",
      "ppd-client-secret");

  public final String baseUrl;
  public final String tokenUrl;
  public final String clientId;
  public final String clientSecret;

  TestEnvironment(String baseUrl, String tokenUrl, String clientId, String clientSecret) {
    this.baseUrl = baseUrl;
    this.tokenUrl = tokenUrl;
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }

  public static TestEnvironment fromProperty() {
    String env = System.getProperty("test.env", "DEV").toUpperCase();
    return rmun.technical.testing.enums.TestEnvironment.valueOf(env);
  }
}
