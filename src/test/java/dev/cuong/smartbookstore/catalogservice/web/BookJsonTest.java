package dev.cuong.smartbookstore.catalogservice.web;

import dev.cuong.smartbookstore.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class BookJsonTest {
  @Autowired
  private JacksonTester<Book> json;

  @Test
  void testSerialize() throws IOException {
    var book = new Book("1234567890", "Title", "Author", 9.90);
    var content = json.write(book);
    assertThat(content).hasJsonPathStringValue("@.isbn");
    assertThat(content).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
  }

  @Test
  void testDeserialize() throws IOException {
    var content = """
        {
          "isbn": "1234567890",
          "title": "Title",
          "author": "Author",
          "price": 9.90
        }
        """;
    var book = json.parse(content);
    assertThat(book)
        .usingRecursiveAssertion()
        .isEqualTo(new Book("1234567890", "Title", "Author", 9.90));
  }
}