package nl.sidn.demo.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class BooksController {
    @Autowired
    BooksRepository booksRepository;

    @GetMapping(value = "/books")
    public List<BookCountEntity> getIssueCount() {

        List<BookCountEntity> booksList = StreamSupport
                .stream(booksRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return booksList;
    }
}
