package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dto.BookDto;
import Dto.OrderLine;
import Dto.Orders;
import Dto.RegBookDto;
import Dto.UserDto;
import Util.DbUtil;

public class AdminDaoImpl implements AdminDao {

	@Override
	public List<UserDto> SelectUser()throws Exception{
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		List<UserDto> list = new ArrayList<UserDto>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("select*from userlist where grade not in('관리자')");
			rs= ps.executeQuery();
			
			while(rs.next()) {
				list.add(new UserDto(rs.getString(1), rs.getString(2), rs.getString(3),
						   rs.getString(4), rs.getString(5)));	
			}
			
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

	@Override
	public List<RegBookDto> SelectRegBook() throws Exception {
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		List<RegBookDto> list = new ArrayList<RegBookDto>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("SELECT*FROM REGBOOK");
			rs= ps.executeQuery();
			
			while(rs.next()) {
				list.add(new RegBookDto(rs.getInt(1), rs.getString(2), rs.getString(3),
						   rs.getString(4), rs.getString(5)));	
			}
			
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
		
		
	}

	@Override
	public int NewBook(BookDto book) throws Exception {
		Connection con = null;
		PreparedStatement ps =null;
		int result =0;
		
		try {
			con = DbUtil.getConnection();
			con.getAutoCommit();
			ps = con.prepareStatement("insert into books values(?,?,?,?)");
			
			ps.setString(1, book.getBooksId());
			ps.setString(2, book.getBooksName());
			ps.setInt(3, book.getBooksPrice());
			ps.setInt(4, book.getStock());
			
			result= ps.executeUpdate();
			if(result==0) {
				   con.rollback();
				   throw new SQLException("등록 실패");
			   }
			else {
				int re = booksDelete(con, book.getBooksName());
				if(re==0) throw new Exception("등록 할수없습니다");	
			}
			
			con.commit();
			
	
		}finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	
	}
	
	
	private int booksDelete(Connection con, String booksName)throws Exception {
		PreparedStatement ps=null;
		int result =0;
		  
		 try {
		   ps= con.prepareStatement("delete regbook where reg_name =?");
		   ps.setString(1, booksName);
		   
		   result = ps.executeUpdate();
	      
		 }finally {
			 DbUtil.close(null, ps, null);
		 }
		 return result;				
	}

	
	@Override
	public List<Orders> todaySales() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Orders> list = new ArrayList<Orders>();
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("Select*from orders where order_date = sysdate");
			rs =ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Orders(rs.getInt(1), rs.getString(2), rs.getString(3),
									rs.getString(4), rs.getInt(5)));
			}
			
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

	@Override
	public int totalSales() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Orders or = null;
		int total =0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("Select*from orders");
			rs =ps.executeQuery();
			
			while(rs.next()) {
				
				or = new Orders(rs.getInt(1), rs.getString(2), rs.getString(3),
								rs.getString(4), rs.getInt(5));
				total += or.getTotalAmount();
			}
			
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return total;		
		
	}

	@Override
	public int periodSales(String startdate, String enddate) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Orders or = null;
		int total =0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("Select*from orders where order_date between ? and ?");
			ps.setString(1, startdate);
			ps.setString(2, enddate);
			
			rs =ps.executeQuery();
			
			while(rs.next()) {
				
				or = new Orders(rs.getInt(1), rs.getString(2), rs.getString(3),
								rs.getString(4), rs.getInt(5));
				total += or.getTotalAmount();
			}
			
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return total;
	}	
		
	
}










