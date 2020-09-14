package Service;

import Dao.RegBookDao;
import Dao.RegBookDaoImpl;
import Dto.RegBookDto;

public class RegBookService {
	RegBookDao regbook = new RegBookDaoImpl();
/**희망도서등록*/
	public void ResgisterBook(RegBookDto want)throws Exception {
		int result =regbook.ResgisterBook(want);
		if(result ==0) throw new Exception("등록되지 않았습니다");	
	}

}
