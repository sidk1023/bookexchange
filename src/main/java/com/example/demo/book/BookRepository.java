package com.example.demo.book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
//import com.example.demo.user.Users;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
 
	
	@Query("SELECT s FROM Book s WHERE s.status = ?1")
    List <Book> findBookByStatus(String status);
	
	
	@Query("SELECT s FROM Book s WHERE s.lender.id = ?1")
	List <Book> findBookByUser(Long lenderId);
	
	@Query("SELECT s FROM Book s WHERE s.author = ?1")
	List <Book> findBookByAuthor(String author);
	
	@Query("SELECT s FROM Book s WHERE s.title = ?1")
	List <Book> findBookByTitle(String title);
	
}
