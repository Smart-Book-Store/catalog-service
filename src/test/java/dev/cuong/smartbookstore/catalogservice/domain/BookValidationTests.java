package dev.cuong.smartbookstore.catalogservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BookValidationTests {
  private static Validator validator;

  @BeforeAll
  static void setup() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Test
  void testWhenAllFieldsCorrectThenValidationSucceeds() {
    var book = Book.of("1234567890", "Title", "Author", 9.90, "Polar Sophia");
    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    assertThat(violations).isEmpty();
  }

  @Test
  void testWhenIsbnDefinedButIncorrectThenValidationFails() {
    var book = Book.of("123456789a", "Title", "Author", 9.90, "Polar Sophia");
    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    assertThat(violations).hasSize(1);
    assertThat(violations.iterator().next().getMessage()).isEqualTo("The ISBN format must be valid.");
  }
}