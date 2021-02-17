package pl.edu.wszib.library.services;

import pl.edu.wszib.library.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();

    Book getBookById(int id);

    void updateBook(Book book);

    void addNewBook(Book book);

}
