package Controller;

import java.util.List;

import Dto.BookDto;
import Dto.OrderLine;
import Dto.Orders;
import Dto.RegBookDto;
import Dto.UserDto;
import Service.AdminService;
import VIew.AdminEndView;
import VIew.FailView;

public class AdminController {
	static AdminService adminservice = new AdminService();
	
	/**ȸ������*/
	public static void SelectUser() {
		try {
			List<UserDto> list = adminservice.SelectUser();
			AdminEndView.UserPrint(list);
		}catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
	}
	/**����������*/
	public static void SelectRegBook() {
		try {
			List<RegBookDto> list = adminservice.SelectRegBook();
			AdminEndView.RegBookPrint(list);
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		} 
		
	}
	/**�űԵ������*/
	public static void NewBook(BookDto book) {
		try {
			adminservice.NewBook(book);
			AdminEndView.printMessage("���ο� ���� ��� ����");
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
		
	}

	/**�����Ǹ���*/
	public static void todaySales() {
		try {
			List<Orders> list = adminservice.todaySales();
			AdminEndView.printSales(list);
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
		
	}
	/**�Ѹ���*/
	public static void totalSales() {
		try {
			int result  = adminservice.totalSales();
			AdminEndView.totalSales(result);
			
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
	public static void periodSales(String startdate, String enddate) {
		try {
			int result = adminservice.periodSales(startdate,enddate);
			AdminEndView.periodSales(startdate,enddate,result);
			}catch(Exception e){
			FailView.errorMessage(e.getMessage());
		}
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//class















