package Service;

import Dao.RegBookDao;
import Dao.RegBookDaoImpl;
import Dto.RegBookDto;

public class RegBookService {
	RegBookDao regbook = new RegBookDaoImpl();
/**����������*/
	public void ResgisterBook(RegBookDto want)throws Exception {
		int result =regbook.ResgisterBook(want);
		if(result ==0) throw new Exception("��ϵ��� �ʾҽ��ϴ�");	
	}

}
