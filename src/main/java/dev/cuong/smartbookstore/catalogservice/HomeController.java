package dev.cuong.smartbookstore.catalogservice;

import dev.cuong.smartbookstore.catalogservice.config.StoreProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  private final StoreProperties storeProperties;

  public HomeController(StoreProperties storeProperties) {
    this.storeProperties = storeProperties;
  }

  @GetMapping("/")
  public String greeting() {
    return storeProperties.greeting();
  }

  @GetMapping("/about")
  public String about() {
    return storeProperties.about();
  }
}
