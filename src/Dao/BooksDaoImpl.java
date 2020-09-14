package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Dto.BookDto;
import Util.DbUtil;

public class BooksDaoImpl implements BooksDao {
	/**전체책검색*/
	@Override
	public List<BookDto> booksSelect() throws SQLException {
		Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  List<BookDto> list = new ArrayList<>();
		 try {
		   con = DbUtil.getConnection();
		   ps= con.prepareStatement("select * from books order by books_id");
	       rs = ps.executeQuery(); 
	        
	        while(rs.next()) {
	        	BookDto books  = new BookDto(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
	        	list.add(books);
	        }
    }finally {
    	DbUtil.close(con, ps, rs);
    }
		return list;				
	}
	
	/**책번호로 검색*/
	@Override
	public BookDto booksSelectBybooksId(String booksId) throws SQLException {
		Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  BookDto books =null;
		 try {
		   con = DbUtil.getConnection();
		   ps= con.prepareStatement("select * from books where books_id=?");
		   ps.setString(1, booksId);
	       rs = ps.executeQuery(); 
	        
	        if(rs.next()) {
	        	 books  = new BookDto(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
	        	
	        }
		 }finally {
			 DbUtil.close(con, ps, rs);
		 }
		 return books;
	}

}//class







