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
	
	/**회원정보*/
	public static void SelectUser() {
		try {
			List<UserDto> list = adminservice.SelectUser();
			AdminEndView.UserPrint(list);
		}catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
	}
	/**희망도서목록*/
	public static void SelectRegBook() {
		try {
			List<RegBookDto> list = adminservice.SelectRegBook();
			AdminEndView.RegBookPrint(list);
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		} 
		
	}
	/**신규도서등록*/
	public static void NewBook(BookDto book) {
		try {
			adminservice.NewBook(book);
			AdminEndView.printMessage("새로운 도서 등록 성공");
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
		
	}

	/**오늘의매출*/
	public static void todaySales() {
		try {
			List<Orders> list = adminservice.todaySales();
			AdminEndView.printSales(list);
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
		
	}
	/**총매출*/
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















