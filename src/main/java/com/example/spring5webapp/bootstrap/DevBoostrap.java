package com.example.spring5webapp.bootstrap;

import com.example.spring5webapp.model.Author;
import com.example.spring5webapp.model.Book;
import com.example.spring5webapp.model.Publisher;
import com.example.spring5webapp.repositories.AuthorRepository;
import com.example.spring5webapp.repositories.BookRepository;
import com.example.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBoostrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBoostrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

        private void initData(){

        Publisher publisher = new Publisher();
        publisher.setName("foo");

        publisherRepository.save(publisher);

        //Eric
        Author eric = new Author("Eric", "Evans");
        Book bookEric = new Book("Domain Driven Design", "1234", publisher);
        eric.getBooks().add(bookEric);
        bookEric.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(bookEric);

        //Rod
        Author rod = new Author("Rod", "Johnson");
        Book bookRod = new Book("J2EE Development", "4444", publisher);
        rod.getBooks().add(bookRod);
        bookRod.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(bookRod);

    }
}
