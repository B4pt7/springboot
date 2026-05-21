package com.hesias.rest;

import com.hesias.dto.BookDTO;
import com.hesias.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> getBooks() {
        return bookService.getAllBooks().stream()
                .map(book -> {
                    BookDTO dto = new BookDTO();
                    dto.setId(book.getId());
                    dto.setTitle(book.getTitle());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}