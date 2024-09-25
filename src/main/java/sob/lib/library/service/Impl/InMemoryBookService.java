package sob.lib.library.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sob.lib.library.model.Book;
import sob.lib.library.repository.InMemoryBookDAO;
import sob.lib.library.service.BooksService;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryBookService implements BooksService {
    private final InMemoryBookDAO bookDAO;

    @Override
    public List<Book> findAllBooks() {
        return bookDAO.findAllBooks();
    }

    @Override
    public Book saveBook(Book book) {
        return bookDAO.saveBook(book);
    }

    @Override
    public Book updateRating(String title, float newRating) {
        return bookDAO.updateRating(title, newRating);
    }

    @Override
    public void deleteBook(String title) {
        bookDAO.deleteBook(title);
    }

    @Override
    public List<Book> filter(String selection) {
        return bookDAO.filter(selection);
    }
}
