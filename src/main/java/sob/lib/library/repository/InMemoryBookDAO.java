package sob.lib.library.repository;

import org.springframework.stereotype.Repository;
import sob.lib.library.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryBookDAO {
    private final List<Book> BOOKS = new ArrayList<>();

    public List<Book> findAllBooks() {
        return BOOKS;
    }

    public Book saveBook(Book book) {
        BOOKS.add(book);
        return book;
    }

    public Book updateRating(String title, float newRating) {
        if (newRating > 10 || newRating < 1) {
            throw new IllegalArgumentException("Rating must be between 1 and 10");
        }
        for (Book b : BOOKS) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                // Initialize ratings list if it is null
                if (b.getRatings() == null) {
                    b.setRatings(new ArrayList<>());
                }
                b.getRatings().add(newRating); 
                return b;
            }
        }
        throw new IllegalArgumentException("Book not found: " + title);
    }


    public void deleteBook(String title) {
        BOOKS.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }

    public List<Book> filter(String selection) {
        return switch (selection.toLowerCase()) {
            case "title" -> BOOKS.stream()
                    .sorted((b1, b2) -> b1.getTitle().compareTo(b2.getTitle()))
                    .collect(Collectors.toList());
            case "year" -> BOOKS.stream()
                    .sorted((b1, b2) -> Integer.compare(b1.getYear(), b2.getYear()))
                    .collect(Collectors.toList());
            case "author" -> BOOKS.stream()
                    .sorted((b1, b2) -> b1.getAuthor().compareTo(b2.getAuthor()))
                    .collect(Collectors.toList());
            case "rating" -> BOOKS.stream()
                    .sorted((b1, b2) -> Double.compare(b1.getAverageRating(), b2.getAverageRating()))
                    .collect(Collectors.toList());
            default -> throw new IllegalArgumentException("Invalid selection: " + selection);
        };
    }
}
