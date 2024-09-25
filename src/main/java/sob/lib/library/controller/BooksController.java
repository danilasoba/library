package sob.lib.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sob.lib.library.model.Book;
import sob.lib.library.service.BooksService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor // Generates the required constructor
public class BooksController {

    private final BooksService service;

    @GetMapping
    public List<Book> findAllBooks() {
        return service.findAllBooks();
    }

    @PostMapping("/save_book")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        Book savedBook = service.saveBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping("/{title}/add_rating")
    public ResponseEntity<Book> addRating(@PathVariable String title, @RequestBody Float newRating) {
        Book updatedBook = service.updateRating(title, newRating);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/delete_book/{title}")
    public ResponseEntity<Void> deleteBook(@PathVariable String title) {
        service.deleteBook(title);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/filter/{selection}")
    public List<Book> filter(@PathVariable String selection) {
        return service.filter(selection);
    }
}
