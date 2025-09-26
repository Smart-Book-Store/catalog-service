package dev.cuong.smartbookstore.catalogservice.domain;

public class BookAlreadyExistsException extends RuntimeException {
  public BookAlreadyExistsException(String isbn) {
    super("The book with ISBN " + isbn + " already exists");
  }
}
