package Controller;

import java.util.List;

import Dto.BookDto;
import Service.BooksService;
import VIew.EndView;
import VIew.FailView;



public class BooksController {
	static BooksService booksService = new BooksService();
	
	public static void booksSelect() {
		/**전체책 검색*/
		try {
			List<BookDto> list = booksService.booksSelect();
			EndView.printBooksList(list);
		}catch (Exception e) {
				FailView.errorMessage(e.getMessage());
		}
	}
		

}
