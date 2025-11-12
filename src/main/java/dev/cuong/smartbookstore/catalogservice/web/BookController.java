package dev.cuong.smartbookstore.catalogservice.web;

import dev.cuong.smartbookstore.catalogservice.domain.Book;
import dev.cuong.smartbookstore.catalogservice.domain.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * An API to provide CRUD operations on books.
 *
 * @author cuongtran
 */
@RestController
@RequestMapping("books")
public class BookController {
  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  /**
   * Get all the books in the catalog
   *
   * @return List of all books in the catalog
   */
  @GetMapping
  public ResponseEntity<Iterable<Book>> get() {
    return ResponseEntity.ok(bookService.viewBookList());
  }

  /**
   * Get the book by given ISBN
   *
   * @param isbn The ISBN to get the book
   * @return The book with details if found, otherwise HTTP status 404 not found
   */
  @GetMapping("/{isbn}")
  public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
    return ResponseEntity.ok(bookService.viewBookDetails(isbn));
  }

  /**
   * Add a new book to the catalog
   *
   * @param book The book with details to be added to the catalog
   * @return Newly added book with details
   */
  @PostMapping
  public ResponseEntity<Book> post(@Valid @RequestBody Book book) {
    var createdBook = bookService.addBookToCatalog(book);
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{isbn}")
        .buildAndExpand(createdBook.isbn())
        .toUri();
    return ResponseEntity.created(location).body(createdBook);
  }

  /**
   * Delete the book by given ISBN
   *
   * @param isbn The ISBN of book to be deleted
   * @return HTTP status 204 if deleted
   */
  @DeleteMapping("/{isbn}")
  public ResponseEntity<Void> delete(@PathVariable String isbn) {
    bookService.removeBookFromCatalog(isbn);
    return ResponseEntity.noContent().build();
  }

  /**
   * Update the book by given ISBN.
   * <ul>
   *   <li>While changing the details for a book not in the catalog, create a new book.</li>
   * </ul>
   *
   * @param isbn The book's ISBN to be updated
   * @param book The book details to be updated
   * @return The book with updated details
   */
  @PutMapping("/{isbn}")
  public ResponseEntity<Book> put(@PathVariable String isbn, @Valid @RequestBody Book book) {
    return ResponseEntity.ok(bookService.editBookDetails(isbn, book));
  }
}
