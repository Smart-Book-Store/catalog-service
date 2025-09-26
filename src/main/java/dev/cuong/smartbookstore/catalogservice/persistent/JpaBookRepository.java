package dev.cuong.smartbookstore.catalogservice.persistent;

import dev.cuong.smartbookstore.catalogservice.domain.Book;
import dev.cuong.smartbookstore.catalogservice.domain.BookRepository;

import java.util.Optional;

public class JpaBookRepository implements BookRepository {
  @Override
  public Iterable<Book> findAll() {
    return null;
  }

  @Override
  public Optional<Book> findByIsbn(String isbn) {
    return Optional.empty();
  }

  @Override
  public boolean existsByIsbn(String isbn) {
    return false;
  }

  @Override
  public Book save(Book book) {
    return null;
  }

  @Override
  public void deleteByIsbn(String isbn) {

  }
}
