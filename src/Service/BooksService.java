package Service;

import java.sql.SQLException;
import java.util.List;
import Dao.BooksDao;
import Dao.BooksDaoImpl;
import Dto.BookDto;

public class BooksService {
	BooksDao booksDao = new BooksDaoImpl();
	/**��ü å �˻�*/
	public List<BookDto> booksSelect() throws Exception {
		List<BookDto> list=booksDao.booksSelect();
		if(list.size()==0)throw new Exception("���� å��� �����ϴ�.");
		return list;	
	}
	
	/**å��ȣ�� �˻�*/
	public BookDto booksSelectBybooksId(String booksId) throws  SQLException{
		BookDto books=booksDao.booksSelectBybooksId(booksId);
		if(books==null)throw new SQLException(booksId + " ���� ��ǰ�� �����ϴ�.");
		return books;
	}


}
