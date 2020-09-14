package Dao;

import java.sql.SQLException;
import java.util.List;

import Dto.BookDto;

public interface BooksDao {
	  
	List<BookDto> booksSelect() throws SQLException;
		
	BookDto booksSelectBybooksId(String booksId)throws SQLException;


	

	

}


