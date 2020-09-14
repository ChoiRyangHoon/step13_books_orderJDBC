package VIew;

import java.util.List;

import Dto.OrderLine;
import Dto.Orders;
import Dto.RegBookDto;
import Dto.UserDto;

public class AdminEndView {

	public static void UserPrint(List<UserDto> list) {
		System.out.println("-----√— »∏ø¯ : "+ list.size() +"∏Ì -------------");
		for(UserDto user : list) {
			System.out.println(user);
		}
		System.out.println();
		
	}

	public static void RegBookPrint(List<RegBookDto> list) {
		System.out.println("-----»Ò∏¡µµº≠∏Ò∑œ : "+ list.size() +"∞≥ -------------");
		for(RegBookDto regbook : list) {
			System.out.println(regbook);
		}
		System.out.println();
		
		
	}

	public static void printMessage(String message) {
		System.out.println(message);
		
	}

	public static void printSales(List<Orders> list) {
		System.out.println("--------------ø¿¥√¿« ∏≈√‚«•-------------");
		for(Orders or : list) {
			System.out.println(or);
		}
		System.out.println();
		
	}

	public static void totalSales(int result) {
		System.out.println("√—∏≈√‚ ±›æ◊ = "+result);
	
	}

	public static void periodSales(String startdate, String enddate, int result) {
		System.out.println(startdate + "~"+enddate+" ±Ó¡ˆ √—∏≈√‚ ="+result);
		
	}

}









