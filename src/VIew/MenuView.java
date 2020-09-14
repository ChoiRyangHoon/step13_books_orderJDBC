package VIew;

import java.util.Scanner;

import Controller.AdminController;
import Controller.BooksController;
import Controller.CartController;
import Controller.OrderController;
import Controller.RegBookController;
import Controller.UserController;
import Dao.UserDao;
import Dto.BookDto;
import Dto.OrderLine;
import Dto.Orders;
import Dto.RegBookDto;
import Dto.UserDto;
import User.User;
import User.UserSet;


//////////////////////////////////        Login        ////////////////////////////////////
public class MenuView {
	private static Scanner sc = new Scanner(System.in);
	
	public static void menu() {
		while(true) {
			UserSet ss = UserSet.getInstance();
			System.out.println(ss.getSet());
			
			MenuView.Printmenu();
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				MenuView.login(); // 가입
				break;
			case 2 :
				MenuView.register();// 로그인
				break;

			case 9 : 
				System.exit(0);
			}
		}
	}
	
	public static void Printmenu() {
		System.out.println("---------- Select Menu -----------");
		System.out.println("  | 1.로그인 | 2.회원가입 | 0. 나가기 |");
		
	}
	
	/**로그인  */
	public static void login() {
		 System.out.print("아이디 : ");
		 String userId = sc.nextLine();
		 
		 System.out.print("비번 : ");
		 String userPwd = sc.nextLine();
		 
		 UserController.login(userId, userPwd); 
	}
	
	/** 로그아웃*/
	public static void logout(String userId) {
		UserSet us = UserSet.getInstance();
		User user = new User(userId);
		us.remove(user);	
	}
	
	/**회원가입*/
	private static void register() {
		System.out.print("가입 아이디 : ");
		 String reuserId = sc.nextLine();
		 
		 System.out.print("비번 : ");
		 int reuserPwd = Integer.parseInt(sc.nextLine());
		 
		 System.out.print("이름 : ");
		 String reuserName = sc.nextLine();
		 
		 UserController.register(reuserId, reuserPwd, reuserName); 
			
	}
//////////////////////////////    회원 메뉴        ///////////////////////////////////////
	
	public static void printUserMenu(String userId) {
		
		while(true) {
			UserSet userset = UserSet.getInstance();
			System.out.println(userset.getSet()); //Set객체
			System.out.println("----------------  User Menu ------------------");
			System.out.println("------------------ " +userId+ " 로그인 중  ---------------");
			System.out.println("   | 1.마이페이지        | 2.책목록보기     | 3.주문하기    | 4.희망도서등록  | ");
			System.out.println("   | 5.주문내역보기  | 6.장바구니담기  | 7.장바구니보기  |");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					printSubMenu(userId);
					return;	
				case 2 :
					BooksController.booksSelect();//전체 상품조회
					break;
				case 3 :
					printInputOrder(userId);
					break;
				case 4 :
					printWantOrder(userId);
					break;
				case 5 :
					OrderController.selectOrdersByUserId(userId);
					break;
				case 6 :
					MenuView.putCart(userId);// 
					break;	
				case 7 : 
					viewCart(userId);
					break;
				default :
					System.out.println("번호에 맞게 선택해주세요");
					break;
				}
		}	
	}
	
	/** case : 1 */
	public static void printSubMenu(String userId) {
		System.out.println("| 1.로그아웃   |  2.수정   | 3.탈퇴  | 4.나가기  |");
		int menu =Integer.parseInt( sc.nextLine());
		switch(menu) {
		case 1 :
			logout(userId);
			break;
		case 2 :
			Update(userId);
			break;
		case 3 :
			UserController.Delete(userId);
			break;
		case 4 :
			return;
		default :
			System.out.println("번호에 맞게 선택해주세요");
			break;	
		}
	}
	
	/** case : 1-2*/
	private static void Update(String userId) {
		System.out.println(" 바꾸실 정보를 입력하세요 ");
		System.out.println("안바꾸시는 정보는 원래정보를 입력하세요");
		System.out.println(" ID = ");
		String id = sc.nextLine();
		System.out.println("PWD = ");
		String pwd = sc.nextLine();
		System.out.println("이름  = ");
		String name = sc.nextLine();
		
		UserDto ud = new UserDto(id, pwd, name, null, null);
		UserController.Update(ud, userId);
		
		
	}

	/** case : 3 */
	 public static void printInputOrder(String userId) {
	    	 System.out.print("주문할 책 번호 : ");
			 String booksId = sc.nextLine();
			 
			 System.out.print("주문수량 : ");
			 int qty = Integer.parseInt(sc.nextLine());
			 
			 System.out.print("배송주소 : ");
			 String address = sc.nextLine();
			 
				 
			 Orders orders = new Orders(0, null, userId, address, 0);
			 OrderLine orderLine = new OrderLine(0, 0, booksId, 0, qty, 0);
			 orders.getOrderLineList().add(orderLine);
			 
			 OrderController.insertOrders(orders);	 
	    }
	 /** case : 4 */
		private static void printWantOrder(String userId) {
			System.out.println("---- 희망 도서 등록 ----");
			System.out.println("희망도서 제목 : ");
			String booksName = sc.nextLine();
			System.out.println("지은이 : ");
			String booksWriter = sc.nextLine();
			System.out.println("출판사 : ");
			String bookspublisher = sc.nextLine();
			
			RegBookDto want = new RegBookDto(booksName, booksWriter, bookspublisher);
			RegBookController.ResgisterBook(want);
			
			
			
		}
	 
	 
	 /**case : 5 */
	  public static void putCart(String id) {
		    System.out.println("--- 장바구니 담기 ---");
			System.out.print("상품번호 : ");
			String booksId = sc.nextLine();
			System.out.print("수량 : ");
			int qty = Integer.parseInt(sc.nextLine());
			
			CartController.putCart(id,booksId,qty);		
	 }
	  
	  /**case : 6 */
	  public static void viewCart(String id) {
			CartController.viewCart(id);			
		}

	 
///////////////////////////        관리자 메뉴             //////////////////////////////////////
	  
	public static void printAdminMenu(String userId) {
		
		while(true) {
			UserSet userset = UserSet.getInstance();
			System.out.println(userset.getSet()); 
			System.out.println("--------------  Admin Menu --------------");
			System.out.println("  ------------   관리자 모드     ------------");
			System.out.println("   |   1.로그아웃          |  2.회원목록보기   |  ");
			System.out.println("   | 3.희망도서목록 보기  | 4.새로운도서등록  | 5.매출관리 |");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					logout(userId);
					return;	
				case 2 :
					AdminController.SelectUser();
					break;
				case 3:
					AdminController.SelectRegBook();
					break;
				case 4:
					PrintInputNewbook();
					break;
				case 5 : 
					SalesManagement();
					break;
				default :
					System.out.println("번호에 맞게 선택해주세요");
					break;
			
			}
		}
	}

	/** case : 4 */
	private static void PrintInputNewbook() {
		System.out.println("---- 희망 도서 등록 ----");
		System.out.println("등록도서코드: ");
		String newId = sc.nextLine();
		System.out.println("등록도서 제목 : ");
		String newName = sc.nextLine();
		System.out.println("등록 가격 : ");
		int newPrice = Integer.parseInt(sc.nextLine());
		System.out.println("등록 개수 : ");
		int newStock = Integer.parseInt(sc.nextLine());
		
		BookDto book = new BookDto(newId, newName, newPrice, newStock);
		AdminController.NewBook(book);	
	}
	/** case : 5 */
	private static void SalesManagement() {
		System.out.println("---- 매출관리 메뉴 ----");
		System.out.println(" | 1.오늘의 매출 | 2.기간별 매출 | 3.총 매출   | 4.종료 ");
		int menu =Integer.parseInt( sc.nextLine());
		switch(menu) {
			case 1 :
				AdminController.todaySales();
				break;	
			case 2 :
				periodSales();
				break;
			case 3 :
				AdminController.totalSales();
				break;
			case 4 :
				break;
			default :
				System.out.println("번호에 맞게 선택해주세요");
				break;
		}
			
	}
	/** case : 5 -2 */
	private static void periodSales() {
		System.out.println("언제부터 ? ");
		String startdate = sc.nextLine();
		System.out.println("언제까지 ? ");
		String enddate = sc.nextLine();
		
		AdminController.periodSales(startdate,enddate);
		
		
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//MenuView
