package dev.cuong.smartbookstore.catalogservice;

import dev.cuong.smartbookstore.catalogservice.config.StoreProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StoreProperties.class)
public class CatalogServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CatalogServiceApplication.class, args);
  }

}
