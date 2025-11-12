package dev.cuong.smartbookstore.catalogservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * A record holding custom properties of the book store.
 *
 * @param greeting A message to welcome users
 * @param about A brief about the store
 * @param testData Test data configuration
 */
@ConfigurationProperties(prefix = "store")
public record StoreProperties(
    String greeting,
    String about,
    TestData testData) {
  /**
   * @param enabled Whether to enable test data
   */
  public record TestData(boolean enabled) {
  }
}
