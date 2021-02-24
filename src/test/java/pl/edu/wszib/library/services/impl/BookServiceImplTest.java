package pl.edu.wszib.library.services.impl;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.wszib.library.configuration.AppConfiguration;
import pl.edu.wszib.library.services.IBookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class})
public class BookServiceImplTest {

    @Autowired
    IBookService bookService;
}
