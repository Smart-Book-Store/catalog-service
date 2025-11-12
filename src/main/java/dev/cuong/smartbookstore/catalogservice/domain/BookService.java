package dev.cuong.smartbookstore.catalogservice.domain;

import org.springframework.stereotype.Service;

/**
 * Service class that provides business logic for managing books in the catalog.
 * <p>
 * This class handles operations such as viewing the book list, viewing book details,
 * adding new books, editing existing books, and removing books from the catalog.
 * It interacts with the BookRepository to perform CRUD operations and enforces
 * domain-specific rules such as preventing duplicate ISBNs and handling not-found cases.
 */
@Service
public class BookService {
  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  /**
   * Retrieves the list of all books in the catalog.
   *
   * @return an Iterable containing all books
   */
  public Iterable<Book> viewBookList() {
    return bookRepository.findAll();
  }

  /**
   * Retrieves the details of a book by its ISBN.
   *
   * @param isbn the ISBN of the book to retrieve
   * @return the Book object with the specified ISBN
   * @throws BookNotFoundException if no book with the given ISBN exists
   */
  public Book viewBookDetails(String isbn) {
    return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
  }

  /**
   * Adds a new book to the catalog.
   *
   * @param book the Book object to add
   * @return the added Book object
   * @throws BookAlreadyExistsException if a book with the same ISBN already exists
   */
  public Book addBookToCatalog(Book book) {
    if (bookRepository.existsByIsbn(book.isbn()))
      throw new BookAlreadyExistsException(book.isbn());
    return bookRepository.save(book);
  }

  /**
   * Removes a book from the catalog by its ISBN.
   *
   * @param isbn the ISBN of the book to remove
   */
  public void removeBookFromCatalog(String isbn) {
    bookRepository.deleteByIsbn(isbn);
  }

  /**
   * Edits the details of an existing book or adds it if not present.
   *
   * @param isbn the ISBN of the book to edit
   * @param book the Book object containing updated details
   * @return the updated or newly added Book object
   */
  public Book editBookDetails(String isbn, Book book) {
    return bookRepository.findByIsbn(isbn)
        .map(existingBook -> {
          var bookToUpdate = new Book(
              existingBook.id(),
              existingBook.isbn(),
              book.title(),
              book.author(),
              book.price(),
              book.publisher(),
              existingBook.createdDate(),
              existingBook.lastModifiedDate(),
              existingBook.version()
          );
          return bookRepository.save(bookToUpdate);
        })
        .orElseGet(() -> addBookToCatalog(book));
  }
}
