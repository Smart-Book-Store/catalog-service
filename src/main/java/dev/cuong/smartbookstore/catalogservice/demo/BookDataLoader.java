package dev.cuong.smartbookstore.catalogservice.demo;

import dev.cuong.smartbookstore.catalogservice.domain.Book;
import dev.cuong.smartbookstore.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata")
public class BookDataLoader {
  private final BookRepository repository;

  public BookDataLoader(BookRepository repository) {
    this.repository = repository;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void run() {
    // Delete all existing books if any to start from an empty db
    repository.deleteAll();
    var book1 = Book.of("1234567890", "Book 1", "Author 1", 10.9, "Polar Sophia");
    var book2 = Book.of("1234567891", "Book 2", "Author 2", 9.9, "Polar Sophia");
    repository.saveAll(List.of(book1, book2));
  }
}
