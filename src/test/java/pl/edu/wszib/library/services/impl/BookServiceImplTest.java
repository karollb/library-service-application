package pl.edu.wszib.library.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.library.configuration.TestConfiguration;
import pl.edu.wszib.library.dao.IBookDAO;
import pl.edu.wszib.library.model.Book;
import pl.edu.wszib.library.services.IBookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class BookServiceImplTest {

    @Autowired
    IBookDAO bookDAO;

    @Autowired
    IBookService bookService;

    @Test
    public void addNewBookTest() {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Ogniem i Mieczem");
        book.setAuthor("H. Sienkiewicz");
        book.setIsbn("12343-23453-235-32");

        this.bookService.addNewBook(book);

        Assert.assertEquals(book.getStatus(), Book.Status.AVAILABLE);

    }


}
