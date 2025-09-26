package dev.cuong.smartbookstore.catalogservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  @GetMapping("/")
  public String greeting() {
    return "Welcome to the book catalog!";
  }

  @GetMapping("/about")
  public String about() {
    return "AI-powered online bookshop following cloud-native architecture.";
  }
}
