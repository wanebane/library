package com.rivaldy.shki.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rivaldy.shki.domain.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, String>{

		List<Book> findByTitle(String title);
		List<Book> findByAuthor(String author);
		List<Book> findByTitleAndAuthor(String title, String author);
}
