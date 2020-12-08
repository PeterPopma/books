package nl.sidn.demo.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ImportService {

    Integer numItems;

    List<String> listBooks = new ArrayList<>(
            List.of("The Little Prince ",
                    "The Hobbit",
                    "Harry Potter and the Philosopher's Stone",
                    "And Then There Were None",
                    "Dream of the Red Chamber",
                    "The Lion, the Witch and the Wardrobe",
                    "She: A History of Adventure",
                    "The Adventures of Pinocchio ",
                    "The Da Vinci Code",
                    "Harry Potter and the Chamber of Secrets",
                    "Harry Potter and the Prisoner of Azkaban",
                    "Harry Potter and the Goblet of Fire",
                    "Harry Potter and the Order of the Phoenix",
                    "Harry Potter and the Half-Blood Prince ",
                    "Harry Potter and the Deathly Hallows",
                    "The Alchemist",
                    "The Catcher in the Rye",
                    "The Bridges of Madison County",
                    "Ben-Hur: A Tale of the Christ",
                    "You Can Heal Your Life",
                    "One Hundred Years of Solitude",
                    "Lolita",
                    "Heidi",
                    "The Common Sense Book of Baby and Child Care",
                    "Anne of Green Gables",
                    "Black Beauty",
                    "The Name of the Rose",
                    "The Eagle Has Landed",
                    "Watership Down",
                    "The Hite Report",
                    "Charlotte's Web",
                    "The Ginger Man"));

    @Autowired
    BooksRepository booksRepository;

 //   @Scheduled(fixedRate = 1000)
    public void importJob() {
        Random rand = new Random();
        int currentMinute = Instant.now().atZone(ZoneOffset.UTC).getMinute() % 5;
        numItems = rand.nextInt(20) + currentMinute * 20;
        for( int i=0; i<numItems; i++ ) {
            String borrowedBook = listBooks.get(rand.nextInt(listBooks.size()));
            Optional<BookCountEntity> book = booksRepository.findById(borrowedBook);
            if(book.isPresent()) {
                book.get().count++;
                booksRepository.save(book.get());
            } else {
                BookCountEntity newItem = new BookCountEntity(borrowedBook, 1);
                booksRepository.save(newItem);
            }
        }
    }
}
