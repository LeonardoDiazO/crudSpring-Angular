package com.ldodev.crud.controllers;

import com.ldodev.crud.entities.Book;
import com.ldodev.crud.services.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "http://localhost:4200/")
public class BookController {

    @Autowired
    BookServiceImpl bookServiceImpl;

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestPart("book") Book book, @RequestPart("file") MultipartFile file) throws IOException {
        try {
            Book saveBook = bookServiceImpl.saveBook(book, file);
            return new ResponseEntity<>(saveBook, HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<Book> updateBookImg(@PathVariable Long id, @RequestPart("file")MultipartFile file) throws IOException {
        Optional<Book> book = bookServiceImpl.getBookById(id);

        if(book.isPresent()){
            Book updatedBook = bookServiceImpl.updateBookImg(file, book.get());
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        try {
            Book updateBook = bookServiceImpl.updateBook(book);
            return new ResponseEntity<>(updateBook, HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(){
        return  new ResponseEntity<>(bookServiceImpl.getBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookServiceImpl.getBookById(id);
        return book
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) throws IOException {
        Optional<Book> book = bookServiceImpl.getBookById(id);
        if (book.isPresent()){
            bookServiceImpl.deleteBook(book.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }




}
