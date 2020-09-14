package Service;

import java.sql.SQLException;
import java.util.List;
import Dao.BooksDao;
import Dao.BooksDaoImpl;
import Dto.BookDto;

public class BooksService {
	BooksDao booksDao = new BooksDaoImpl();
	/**전체 책 검색*/
	public List<BookDto> booksSelect() throws Exception {
		List<BookDto> list=booksDao.booksSelect();
		if(list.size()==0)throw new Exception("현재 책재고가 없습니다.");
		return list;	
	}
	
	/**책번호로 검색*/
	public BookDto booksSelectBybooksId(String booksId) throws  SQLException{
		BookDto books=booksDao.booksSelectBybooksId(booksId);
		if(books==null)throw new SQLException(booksId + " 현재 상품이 없습니다.");
		return books;
	}


}
