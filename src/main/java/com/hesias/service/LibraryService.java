package com.hesias.service;

import com.hesias.entity.Book;
import com.hesias.entity.Library;
import com.hesias.repository.BookRepository;
import com.hesias.repository.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;

    public LibraryService(LibraryRepository libraryRepository, BookRepository bookRepository) {
        this.libraryRepository = libraryRepository;
        this.bookRepository = bookRepository;
    }

    public List<Library> getAllLibraries() {
        return libraryRepository.findAll();
    }

    public Optional<Library> getLibraryById(Long id) {
        return libraryRepository.findById(id);
    }

    public Library createLibrary(Library library) {
        return libraryRepository.save(library);
    }

    public List<Book> getBooksByLibrary(Long libraryId) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new RuntimeException("Library not found with id: " + libraryId));
        return library.getBooks();
    }

    public Library addBooksToLibrary(Long libraryId, List<Book> books) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new RuntimeException("Library not found with id: " + libraryId));
        for (Book book : books) {
            book.setLibrary(library);
        }
        bookRepository.saveAll(books);
        library.getBooks().addAll(books);
        return library;
    }

    public void removeBookFromLibrary(Long libraryId, Long bookId) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new RuntimeException("Library not found with id: " + libraryId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
        library.getBooks().remove(book);
        book.setLibrary(null);
        bookRepository.save(book);
    }

    public void deleteLibrary(Long id) {
        libraryRepository.deleteById(id);
    }
}
