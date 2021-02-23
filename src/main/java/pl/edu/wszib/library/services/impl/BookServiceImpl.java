package pl.edu.wszib.library.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.library.dao.IBookDAO;
import pl.edu.wszib.library.model.Book;
import pl.edu.wszib.library.services.IBookService;

import java.util.List;

@Service
public class BookServiceImpl  implements IBookService {

    @Autowired
    IBookDAO bookDAO;

    @Override
    public List<Book> getAllBooks() {
        return this.bookDAO.getAllBooks();
    }

    @Override
    public Book getBookById(int id) {
        return this.bookDAO.getBookById(id);
    }

    //TODO UpdateBook Controler
    @Override
    public void updateBook(Book book) {
        Book bookFromDB = this.bookDAO.getBookById(book.getId());
        bookFromDB.setTitle(book.getTitle());
        bookFromDB.setAuthor(book.getAuthor());
        bookFromDB.setIsbn(book.getIsbn());

        this.bookDAO.updateBook(bookFromDB);

    }

    @Override
    public void addNewBook(Book book) {
        book.setStatus(Book.Status.AVAILABLE);
        this.bookDAO.updateBook(book);
    }
}
