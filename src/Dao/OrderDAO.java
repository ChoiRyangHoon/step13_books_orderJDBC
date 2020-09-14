package Dao;

import java.sql.SQLException;
import java.util.List;

import Dto.Orders;

public interface OrderDAO {
  /**
   * �ֹ��ϱ�
   *  1) orders���̺��� insert
   *  2) order_line���̺��� insert
   *  3) �����(stock)���� ��Ű��(update)
   * */
	int orderInsert(Orders order)throws SQLException;
	
	/**
	 * userId�� �ش��ϴ� �ֹ� ��� 
	 * */
	
	
	/**
	 * �ֹ���������
	 * */
	List<Orders> selectOrdersByUserId(String userId)throws SQLException;
}