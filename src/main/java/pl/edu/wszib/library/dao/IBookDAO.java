package pl.edu.wszib.library.dao;

import pl.edu.wszib.library.model.Book;

import java.util.List;

public interface IBookDAO {
    List<Book> getAllBooks();

    Book getBookById(int id);

    void updateBook(Book book);

}
