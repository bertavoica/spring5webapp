package guru.springframework.spring5webapp.boostrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            PublisherRepository publisherRepository
    ){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher("Address Line One", "Romania", "Bucharest", 12344L);
        publisherRepository.save(publisher);
        Author author = new Author("Eric", "Evans");
        Book book = new Book("Domain Driven Design", "12345");

        author.getBooks().add(book);
        book.getAuthors().add(author);

        book.setPublisher(publisher);
        publisher.getBooks().add(book);


        authorRepository.save(author);
        bookRepository.save(book);
        publisherRepository.save(publisher);

        Author authorTwo = new Author("Bob", "Bobs");
        Book bookTwo = new Book("Test Driven Design", "12344");

        authorTwo.getBooks().add(bookTwo);
        bookTwo.getAuthors().add(authorTwo);

        bookTwo.setPublisher(publisher);
        publisher.getBooks().add(book);

        authorRepository.save(authorTwo);
        bookRepository.save(bookTwo);
        publisherRepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books" + publisher.getBooks().size());
    }
}
