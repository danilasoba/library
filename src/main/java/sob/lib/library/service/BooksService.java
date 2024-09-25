package sob.lib.library.service;
import sob.lib.library.model.Book;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface BooksService {
    List<Book> findAllBooks();
    Book saveBook(Book book);
    Book updateRating(String title, float newRating);
    void deleteBook(String title);
    List<Book> filter(String selection);
}
