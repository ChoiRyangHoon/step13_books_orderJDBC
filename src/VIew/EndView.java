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
		System.out.println("-----책 "+ list.size() +"개 -------------");
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
	
	/**장바구니 보기*/
	public static void printViewCart(String id , Map<BookDto,Integer> cart) {
		System.out.println("장바구니내용....");
		
		for(BookDto Books: cart.keySet()) {
			String goodsId = Books.getBooksId();//상품번호
			String name = Books.getBooksName();//상품이름
			int price = Books.getBooksPrice();//상품가격
			int quantity = cart.get(Books);//
			System.out.println(goodsId+" : "+name+" : "+price+" \t "+quantity);
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("1.주문하기  |  9.나가기");
		switch(Integer.parseInt(sc.nextLine())) {
		case 1:
			
			 System.out.print("배송주소 : ");
			 String address = sc.nextLine();
			
			 Orders orders = new Orders(0, null, id, address, 0);
			 List<OrderLine> orderLineList = orders.getOrderLineList();
			 for(BookDto bookssKey : cart.keySet()) {
				 int qty = cart.get(bookssKey);
				 OrderLine orderLine = new OrderLine(0, 0, bookssKey.getBooksId() , 0, qty, 0);
				 orderLineList.add(orderLine);
			 }
			 System.out.println("orderLineList 개수 : " + orderLineList.size());
			 OrderController.insertOrders(orders);
			 
			 //장바구니비우기
			 UserSet ss = UserSet.getInstance();
			 User session = ss.get(id);
			 session.removeAttribute("cart");
			break;
			
		case 9:
			break;
		}
		
	
	}
 
	/**주문상세*/
	public static void printOrderByUserId(List<Orders> orderList) {
	   for(Orders order : orderList) {
		   System.out.println(order.getOrderId()+ " | " + order.getOrderDate() +" | " + order.getTotalAmount() +" | " + order.getAddress());
		   for(OrderLine orderLine : order.getOrderLineList()) {
			   System.out.println("  ▶ "+orderLine);
		   }
		   System.out.println();
	   }
		
	}

	
	public static void printMessage(String message) {
		 System.out.println(message);
		
	}	
	
}












