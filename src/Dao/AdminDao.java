package Dao;

import java.util.List;

import Dto.BookDto;
import Dto.OrderLine;
import Dto.Orders;
import Dto.RegBookDto;
import Dto.UserDto;

public interface AdminDao {

	List<UserDto> SelectUser() throws Exception;

	List<RegBookDto> SelectRegBook()throws Exception;

	int NewBook(BookDto book) throws Exception;

	List<Orders> todaySales()throws Exception;

	int totalSales()throws Exception;

	int periodSales(String startdate, String enddate)throws Exception;



}
