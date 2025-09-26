package dev.cuong.smartbookstore.catalogservice.domain;

public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException(String isbn) {
    super("The book with ISBN " + isbn + " not found");
  }
}
