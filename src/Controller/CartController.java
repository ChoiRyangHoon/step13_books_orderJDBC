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
			//상품번호에 해당 상품찾기
			BookDto books = booksService.booksSelectBybooksId(booksId);
			/*if(goods.getStock() < quantity) {
				throw new SQLException("재고량 부족으로 장바구니에 담을수 없습니다.");
			}*/
			//id에 해당하는 세션찾기
			UserSet us = UserSet.getInstance();
			User user = us.get(id);	
			
			//세션에서 장바구니 찾기
			Map<BookDto, Integer> cart =(Map<BookDto,Integer>)user.getAttribute("cart"); //상품 , 수량 저장 
			
			//장바구니가 없으면 장바구니 생성
			if(cart == null) { 
				cart = new HashMap<>(); 
				user.setAttribute("cart", cart);
			}
			
			//장바구니에서 상품찾기
			Integer oldQuantity = cart.get(books);
			if(oldQuantity != null) { //장바구니에 이미 상품이 있다면
				quantity += oldQuantity; //수량을 누적
			}
			
			cart.put(books, quantity); //장바구니에 상품 넣기
			EndView.printMessage("장바구니에 담았습니다");
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
   
   /**
    * 장바구니 보기
    * */
   public static void viewCart(String id) {
		UserSet us = UserSet.getInstance();
		User user = us.get(id);
		Map<BookDto,Integer> cart = (Map<BookDto, Integer>) user.getAttribute("cart");
		if(cart == null ) { // 장바구니가 없는 고객
			FailView.errorMessage("장바구니가 비었습니다");
		}else {
			EndView.printViewCart(id , cart);
		}
	}
}




