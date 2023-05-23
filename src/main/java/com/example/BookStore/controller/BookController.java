package com.example.BookStore.controller;

import com.example.BookStore.entity.Book;
import com.example.BookStore.services.BookService;
import com.example.BookStore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showAllBooks(Model model){
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books",books);
        return "book/list";
    }
    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book",new Book());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "book/add";
    }
    @PostMapping("/add")
    public String addBook(@ModelAttribute("book")Book book){
        bookService.addBook(book);
        return "redirect:/books";
    }
    @GetMapping("/search")
    public String searchBooks(@RequestParam("keyword") String keyword, Model model) {
        List<Book> books = bookService.searchBooks(keyword);
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        return "book/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        bookService.deleteBook(id);
        return "redirect:/books";
    }
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model){
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("categories",categoryService.getAllCategories());
        return "book/edit";
    }
    @PostMapping("edit/{id}")
    public String editBooks(@PathVariable("id") Long id, @ModelAttribute("book") Book editBook){
        Book book = bookService.getBookById(id);
        book.setTitle(editBook.getTitle());
        book.setAuthor(editBook.getAuthor());
        book.setPrice(editBook.getPrice());
        book.setCategory(editBook.getCategory());
        bookService.updateBook(book);
        return "redirect:/books";
    }

}
