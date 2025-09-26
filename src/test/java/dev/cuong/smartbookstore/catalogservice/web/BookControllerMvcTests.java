package dev.cuong.smartbookstore.catalogservice.web;

import dev.cuong.smartbookstore.catalogservice.domain.BookNotFoundException;
import dev.cuong.smartbookstore.catalogservice.domain.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerMvcTests {
  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private BookService bookService;

  @Test
  void testWhenGetBookNotExistThenShouldReturn404() throws Exception {
    var isbn = "1212123456";
    given(bookService.viewBookDetails(isbn)).willThrow(BookNotFoundException.class);
    mockMvc.perform(get("/books/" + isbn)).andExpect(status().isNotFound());
  }
}