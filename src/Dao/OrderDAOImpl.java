package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Dto.BookDto;
import Dto.OrderLine;
import Dto.Orders;
import Util.DbUtil;

public class OrderDAOImpl implements OrderDAO {
	
	BooksDao booksDao = new BooksDaoImpl();
	
   /**�ֹ��ϱ�*/
	@Override
	public int orderInsert(Orders order)throws SQLException {
		 Connection con=null;
		  PreparedStatement ps=null;
		  String sql="INSERT INTO ORDERS(ORDER_ID, ORDER_DATE,USER_ID, ADDRESS, TOTAL_AMOUNT)" + 
		  		"  VALUES(ORDER_ID_SEQ.NEXTVAL ,sysdate,?,?,?)";
		  int result=0;
		 try {
			
		   con = DbUtil.getConnection();
		   con.setAutoCommit(false);
		   
		   ps = con.prepareStatement(sql);
		   ps.setString(1, order.getUserId());
		   ps.setString(2, order.getAddress());
		   ps.setInt(3, getTotalAmount(order));//�ѱ��űݾױ��ϴ� �޼ҵ� ȣ��
		   
		   result = ps.executeUpdate();
		   if(result==0) {
			   con.rollback();
			   throw new SQLException("�ֹ� ����");
		   }
		   else {
			   int re [] = orderLineInsert(con, order); //�ֹ��� ����ϱ� 
			   for(int i : re) {
				   if(i !=Statement.SUCCESS_NO_INFO) {//-2
					   con.rollback();
					   throw new SQLException("�ֹ� �Ҽ� �����ϴ�");
				   }
			   }
			   
			   //�ֹ�������ŭ ��� �����ϱ�
			   decrementStock(con, order.getOrderLineList());
			   con.commit();
		   }
		   
      }finally {
    	  con.commit();
      	DbUtil.close(con, ps , null);
      }
		
		return result;
	}
	
	/**�ֹ���*/
	public int[] orderLineInsert(Connection con  , Orders order) throws SQLException{
		
		  PreparedStatement ps=null;
		  String sql="insert into order_line(order_line_id,order_id, books_id,unit_price, qty, amount)" + 
		  		"  values(ORDER_LINE_ID_SEQ.nextval ,ORDER_ID_SEQ.currval , ?,?,?,? )";
		  int result [] =null;
		 try {
			 ps = con.prepareStatement(sql);
		  for( OrderLine orderline : order.getOrderLineList() ) {
			 BookDto books = booksDao.booksSelectBybooksId(orderline.getBooksId());
			  
			   ps.setString(1, orderline.getBooksId());
			   ps.setInt(2, books.getBooksPrice());//����
			   ps.setInt(3, orderline.getQty());//�ѱ��Ű���
			   ps.setInt(4,  books.getBooksPrice()*orderline.getQty());//�ѱ��űݾ�
			   ps.addBatch(); //�ϰ�ó���� �۾��� �߰�
			   ps.clearParameters();
			   
		  }
		  result = ps.executeBatch();//�ϰ�ó��
		  System.out.println("�ֹ��Ϸ�");
		  
		   
    }finally {
    	DbUtil.close(null, ps , null);
    }
		
		return result;
		
	}
	
	/**������*/
	public int[] decrementStock(Connection con , List<OrderLine> orderLineList)throws SQLException {
		 PreparedStatement ps=null;
		  String sql="update books set stock = stock-? where books_id=?";
		  int result [] =null;
		  try {
			  ps = con.prepareStatement(sql);
			  for( OrderLine orderline : orderLineList ) {
				  ps.setInt(1, orderline.getQty());
				  ps.setString(2, orderline.getBooksId());
			   
				  ps.addBatch(); //�ϰ�ó���� �۾��� �߰�
				  ps.clearParameters();
			  }
		  
		  result = ps.executeBatch();//�ϰ�ó��
		  
		  }finally {
			  DbUtil.close(null, ps, null);
		  }
		  
		
		return result;
	}
	
	/**TOTAL AMOUNT*/
	public int getTotalAmount(Orders order) throws SQLException {
		List<OrderLine> orderLineList= order.getOrderLineList();
	    int total=0;
		for(OrderLine line : orderLineList) {
			BookDto books =booksDao.booksSelectBybooksId(line.getBooksId());
			if(books==null)throw new SQLException("��ǰ��ȣ �����Դϴ�.... �ֹ� ����..");
	    	total += line.getQty() * books.getBooksPrice() ;
	    }
		return total;
	}
	
	/**�ֹ�����*/
	public List<Orders> selectOrdersByUserId(String userId)throws SQLException{
		Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  List<Orders> list = new ArrayList<>();
		 try {
		   con = DbUtil.getConnection();
		   ps= con.prepareStatement("select * from orders where user_id=?");
		   ps.setString(1, userId);
	       rs = ps.executeQuery(); 
	        
	        while(rs.next()) {
	        	Orders orders  = new Orders(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
	        	
	        	List<OrderLine> orderLineList = selectOrderLine(orders.getOrderId());
	        	orders.setOrderLineList(orderLineList);
	        	list.add(orders);
	        }
    }finally {
    	DbUtil.close(con, ps, rs);
    }
		return list;
		
	}
	
	/** �ֹ���ȣ�� �ش��ϴ� �ֹ��� ��������*/
	public List<OrderLine> selectOrderLine(int orderId)throws SQLException{
		Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  List<OrderLine> list = new ArrayList<>();
		 try {
		   con = DbUtil.getConnection();
		   ps= con.prepareStatement("select * from order_line where  order_id=?");
		   ps.setInt(1, orderId);
	       rs = ps.executeQuery(); 
	        
	        while(rs.next()) {
	        	OrderLine orderLine  = new OrderLine(rs.getInt(1), rs.getInt(2), 
	        			rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
	        	list.add(orderLine);
	        }
    }finally {
    	DbUtil.close(con, ps, rs);
    }
		return list;
		
	}
}







