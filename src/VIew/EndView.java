package VIew;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Controller.OrderController;
import Dao.UserDao;
import Dto.BookDto;
import Dto.OrderLine;
import Dto.Orders;
import User.User;
import User.UserSet;

public class EndView {
	/**printbooklist*/
	public static void printBooksList(List<BookDto> list) {
		System.out.println("-----å "+ list.size() +"�� -------------");
		for(BookDto books : list) {
			System.out.println(books);
		}
		System.out.println();
	}
	
	/**printmessage*/
	public static void messagePrint(String message) {
		System.out.println(message);
	}
	
	////////////////////////////////////////////////////////////////////
	
	/**��ٱ��� ����*/
	public static void printViewCart(String id , Map<BookDto,Integer> cart) {
		System.out.println("��ٱ��ϳ���....");
		
		for(BookDto Books: cart.keySet()) {
			String goodsId = Books.getBooksId();//��ǰ��ȣ
			String name = Books.getBooksName();//��ǰ�̸�
			int price = Books.getBooksPrice();//��ǰ����
			int quantity = cart.get(Books);//
			System.out.println(goodsId+" : "+name+" : "+price+" \t "+quantity);
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("1.�ֹ��ϱ�  |  9.������");
		switch(Integer.parseInt(sc.nextLine())) {
		case 1:
			
			 System.out.print("����ּ� : ");
			 String address = sc.nextLine();
			
			 Orders orders = new Orders(0, null, id, address, 0);
			 List<OrderLine> orderLineList = orders.getOrderLineList();
			 for(BookDto bookssKey : cart.keySet()) {
				 int qty = cart.get(bookssKey);
				 OrderLine orderLine = new OrderLine(0, 0, bookssKey.getBooksId() , 0, qty, 0);
				 orderLineList.add(orderLine);
			 }
			 System.out.println("orderLineList ���� : " + orderLineList.size());
			 OrderController.insertOrders(orders);
			 
			 //��ٱ��Ϻ���
			 UserSet ss = UserSet.getInstance();
			 User session = ss.get(id);
			 session.removeAttribute("cart");
			break;
			
		case 9:
			break;
		}
		
	
	}
 
	/**�ֹ���*/
	public static void printOrderByUserId(List<Orders> orderList) {
	   for(Orders order : orderList) {
		   System.out.println(order.getOrderId()+ " | " + order.getOrderDate() +" | " + order.getTotalAmount() +" | " + order.getAddress());
		   for(OrderLine orderLine : order.getOrderLineList()) {
			   System.out.println("  �� "+orderLine);
		   }
		   System.out.println();
	   }
		
	}

	
	public static void printMessage(String message) {
		 System.out.println(message);
		
	}	
	
}












