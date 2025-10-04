package com.training.database.repositories;

import com.training.database.TestDataUtil;
import com.training.database.domain.Author;
import com.training.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTests {

    private BookRepository underTest;
    private AuthorRepository authorRepository;

    @Autowired
    public void BookDaoImplIntegrationTest(BookRepository underTest, AuthorRepository authorRepository) {

        this.underTest = underTest;
        this.authorRepository = authorRepository;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthorA();
        Book book = TestDataUtil.createTestBookA(author);
        authorRepository.save(author);
        underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        // Create author constraints
        Author authorA = TestDataUtil.createTestAuthorA();
        authorRepository.save(authorA);

        // Create Books
        Book bookA = TestDataUtil.createTestBookA(authorA);
        underTest.save(bookA);

        Book bookB = TestDataUtil.createTestBookB(authorA);
        underTest.save(bookB);

        Book bookC = TestDataUtil.createTestBookC(authorA);
        underTest.save(bookC);

        Iterable<Book> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(bookA, bookB, bookC);
    }

    @Test
    public void testThatBookCanBeUpdated() {
        Author author = TestDataUtil.createTestAuthorA();
        authorRepository.save(author);
        Book book = TestDataUtil.createTestBookA(author);
        underTest.save(book);

        book.setTitle("UPDATED");
        underTest.save(book);

        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        Author author = TestDataUtil.createTestAuthorA();
        authorRepository.save(author);

        Book book = TestDataUtil.createTestBookA(author);
        underTest.save(book);

        underTest.delete(book);

        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isEmpty();
    }
}
