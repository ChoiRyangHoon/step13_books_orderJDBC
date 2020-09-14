package Controller;

import Dto.RegBookDto;
import Service.RegBookService;
import VIew.EndView;
import VIew.FailView;

public class RegBookController {
	static RegBookService service = new RegBookService();

	public static void ResgisterBook(RegBookDto want) {
		try {
			service.ResgisterBook(want);
			EndView.messagePrint("등록 성공");
			
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
		
	}

}
