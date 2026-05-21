package com.hesias;

import com.hesias.entity.Book;
import com.hesias.entity.Library;
import com.hesias.service.BookService;
import com.hesias.service.LibraryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private final BookService bookService;
    private final LibraryService libraryService;

    public Main(BookService bookService, LibraryService libraryService) {
        this.bookService = bookService;
        this.libraryService = libraryService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        var book1 = new Book();
        book1.setTitle("Harry Potter à l'école des sorciers");

        var book2 = new Book();
        book2.setTitle("Harry Potter et la Chambre des secrets");

        var library = new Library();
        library.setName("Bibliothèque de Poudlard");
        library.setBooks(List.of(book1, book2));

        Library saved = libraryService.createLibrary(library);

        System.out.println("Toutes les bibliothèques : " + libraryService.getAllLibraries());
        System.out.println("Tous les livres :     " + bookService.getAllBooks());
        System.out.println("Livres dans la bibliothèque : " + libraryService.getBooksByLibrary(saved.getId()));

        bookService.deleteBook(book2.getId());
        System.out.println("Après la suppression du livre2 : " + bookService.getAllBooks());
    }
}
