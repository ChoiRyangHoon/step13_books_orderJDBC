package Controller;

import java.sql.SQLException;
import java.util.List;

import Dto.Orders;
import Service.OrderService;
import VIew.EndView;
import VIew.FailView;

public class OrderController {
	private static OrderService orderService = new OrderService();
		
	/**주문*/
	public static void insertOrders(Orders order) {
		try {
			 orderService.insertOrders(order);
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());		
		}
	}
		
	/**주문내역*/	
	public static void selectOrdersByUserId(String userId) {
		try {
			 List<Orders> orderList = orderService.selectOrdersByUserId(userId);
	         EndView.printOrderByUserId(orderList);
		}catch (SQLException e) {
			FailView.errorMessage(e.getMessage());		
		}
	}
		
}
