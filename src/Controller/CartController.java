package Controller;

import java.util.HashMap;
import java.util.Map;

import Dto.BookDto;
import Service.BooksService;
import User.User;
import User.UserSet;
import VIew.EndView;
import VIew.FailView;


public class CartController {
  private static BooksService booksService = new BooksService();
  
   public static void putCart(String id, String booksId, int quantity) {
		
		try {
			//��ǰ��ȣ�� �ش� ��ǰã��
			BookDto books = booksService.booksSelectBybooksId(booksId);
			/*if(goods.getStock() < quantity) {
				throw new SQLException("��� �������� ��ٱ��Ͽ� ������ �����ϴ�.");
			}*/
			//id�� �ش��ϴ� ����ã��
			UserSet us = UserSet.getInstance();
			User user = us.get(id);	
			
			//���ǿ��� ��ٱ��� ã��
			Map<BookDto, Integer> cart =(Map<BookDto,Integer>)user.getAttribute("cart"); //��ǰ , ���� ���� 
			
			//��ٱ��ϰ� ������ ��ٱ��� ����
			if(cart == null) { 
				cart = new HashMap<>(); 
				user.setAttribute("cart", cart);
			}
			
			//��ٱ��Ͽ��� ��ǰã��
			Integer oldQuantity = cart.get(books);
			if(oldQuantity != null) { //��ٱ��Ͽ� �̹� ��ǰ�� �ִٸ�
				quantity += oldQuantity; //������ ����
			}
			
			cart.put(books, quantity); //��ٱ��Ͽ� ��ǰ �ֱ�
			EndView.printMessage("��ٱ��Ͽ� ��ҽ��ϴ�");
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
   
   /**
    * ��ٱ��� ����
    * */
   public static void viewCart(String id) {
		UserSet us = UserSet.getInstance();
		User user = us.get(id);
		Map<BookDto,Integer> cart = (Map<BookDto, Integer>) user.getAttribute("cart");
		if(cart == null ) { // ��ٱ��ϰ� ���� ��
			FailView.errorMessage("��ٱ��ϰ� ������ϴ�");
		}else {
			EndView.printViewCart(id , cart);
		}
	}
}




