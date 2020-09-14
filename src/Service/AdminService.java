package Service;

import java.sql.SQLException;
import java.util.List;

import Dao.AdminDao;
import Dao.AdminDaoImpl;
import Dto.BookDto;
import Dto.OrderLine;
import Dto.Orders;
import Dto.RegBookDto;
import Dto.UserDto;

public class AdminService {
	AdminDao adminDao = new AdminDaoImpl();

	public List<UserDto> SelectUser()throws Exception {
		List<UserDto> list  = adminDao.SelectUser();
		if(list.size()==0) throw new SQLException("ȸ�������� �����ϴ�");
		return list;
	}

	public List<RegBookDto> SelectRegBook() throws Exception {
		List<RegBookDto> list  = adminDao.SelectRegBook();
		if(list.size()==0) throw new SQLException("������������ �����ϴ�");
		return list;
	}

	public int NewBook(BookDto book) throws Exception {
		int result = adminDao.NewBook(book);
		if(result ==0) throw new SQLException("��� ����");
		return result;
		
	}

	public List<Orders> todaySales()throws Exception {
		List<Orders> list = adminDao.todaySales();
		if(list.size()==0) throw new SQLException("���� �������� �����ϴ�");
		return list;
	}

	public int totalSales() throws Exception{
		int result = adminDao.totalSales();
		if(result ==0) throw new SQLException("�Ѹ����� �����ϴ�");
		return result;
	}

	public int periodSales(String startdate, String enddate)throws Exception {
		int result = adminDao.periodSales(startdate,enddate);
		if(result ==0) throw new SQLException("�Ⱓ���� ������ �����ϴ�");
		return result;
	}

}
