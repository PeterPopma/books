package nl.sidn.demo.books;

import org.springframework.data.repository.CrudRepository;

public interface BooksRepository extends CrudRepository<BookCountEntity, String> {
}