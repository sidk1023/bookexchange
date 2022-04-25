package com.example.demo.bookRequest;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRequestRepository extends JpaRepository<BookRequest,Long> {
	
	@Query("SELECT s FROM BookRequest s WHERE s.status = ?1")
	List <BookRequest> findBookRequestByStatus(String status);
	
    @Query("SELECT s FROM BookRequest s WHERE s.lender.id = ?1")
    List <BookRequest> findBookRequestByLenderId(Long lenderId);
	
    @Query("SELECT s FROM BookRequest s WHERE s.borrower.id = ?1")
    List <BookRequest> findBookRequestByBorrowerId(Long borrowerId);
    
    @Query("SELECT s FROM BookRequest s WHERE s.borrower.id = ?1 AND s.lender.id = ?2")
    List <BookRequest> findBookRequestByBorrowerAndLenderId(Long borrowerId, Long lenderId );
    
    @Query("SELECT s FROM BookRequest s WHERE s.borrower.id = ?1 AND s.status = ?2")
    List <BookRequest> findBookRequestByBorrowerIdAndStatus(Long borrowerId, String status );
    
    @Query("SELECT s FROM BookRequest s WHERE s.lender.id = ?1 AND s.status = ?2")
    List <BookRequest> findBookRequestByLenderIdAndStatus(Long lenderId, String status );
        
    @Query("SELECT s FROM BookRequest s WHERE s.book.id = ?1")
    List <BookRequest> findBookRequestByBookId(Long bookId);
}
