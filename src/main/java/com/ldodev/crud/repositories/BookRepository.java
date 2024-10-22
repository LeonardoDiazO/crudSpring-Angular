package com.ldodev.crud.repositories;

import com.ldodev.crud.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
