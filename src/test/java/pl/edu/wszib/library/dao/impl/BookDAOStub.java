package pl.edu.wszib.library.dao.impl;

import pl.edu.wszib.library.dao.IBookDAO;
import pl.edu.wszib.library.model.Book;

import java.util.List;

public class BookDAOStub implements IBookDAO {

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public Book getBookById(int id) {
        return null;
    }

    @Override
    public void updateBook(Book book) {

    }
}
