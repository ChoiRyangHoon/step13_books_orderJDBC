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
				MenuView.login(); // ����
				break;
			case 2 :
				MenuView.register();// �α���
				break;

			case 9 : 
				System.exit(0);
			}
		}
	}
	
	public static void Printmenu() {
		System.out.println("---------- Select Menu -----------");
		System.out.println("  | 1.�α��� | 2.ȸ������ | 0. ������ |");
		
	}
	
	/**�α���  */
	public static void login() {
		 System.out.print("���̵� : ");
		 String userId = sc.nextLine();
		 
		 System.out.print("��� : ");
		 String userPwd = sc.nextLine();
		 
		 UserController.login(userId, userPwd); 
	}
	
	/** �α׾ƿ�*/
	public static void logout(String userId) {
		UserSet us = UserSet.getInstance();
		User user = new User(userId);
		us.remove(user);	
	}
	
	/**ȸ������*/
	private static void register() {
		System.out.print("���� ���̵� : ");
		 String reuserId = sc.nextLine();
		 
		 System.out.print("��� : ");
		 int reuserPwd = Integer.parseInt(sc.nextLine());
		 
		 System.out.print("�̸� : ");
		 String reuserName = sc.nextLine();
		 
		 UserController.register(reuserId, reuserPwd, reuserName); 
			
	}
//////////////////////////////    ȸ�� �޴�        ///////////////////////////////////////
	
	public static void printUserMenu(String userId) {
		
		while(true) {
			UserSet userset = UserSet.getInstance();
			System.out.println(userset.getSet()); //Set��ü
			System.out.println("----------------  User Menu ------------------");
			System.out.println("------------------ " +userId+ " �α��� ��  ---------------");
			System.out.println("   | 1.����������        | 2.å��Ϻ���     | 3.�ֹ��ϱ�    | 4.����������  | ");
			System.out.println("   | 5.�ֹ���������  | 6.��ٱ��ϴ��  | 7.��ٱ��Ϻ���  |");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					printSubMenu(userId);
					return;	
				case 2 :
					BooksController.booksSelect();//��ü ��ǰ��ȸ
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
					System.out.println("��ȣ�� �°� �������ּ���");
					break;
				}
		}	
	}
	
	/** case : 1 */
	public static void printSubMenu(String userId) {
		System.out.println("| 1.�α׾ƿ�   |  2.����   | 3.Ż��  | 4.������  |");
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
			System.out.println("��ȣ�� �°� �������ּ���");
			break;	
		}
	}
	
	/** case : 1-2*/
	private static void Update(String userId) {
		System.out.println(" �ٲٽ� ������ �Է��ϼ��� ");
		System.out.println("�ȹٲٽô� ������ ���������� �Է��ϼ���");
		System.out.println(" ID = ");
		String id = sc.nextLine();
		System.out.println("PWD = ");
		String pwd = sc.nextLine();
		System.out.println("�̸�  = ");
		String name = sc.nextLine();
		
		UserDto ud = new UserDto(id, pwd, name, null, null);
		UserController.Update(ud, userId);
		
		
	}

	/** case : 3 */
	 public static void printInputOrder(String userId) {
	    	 System.out.print("�ֹ��� å ��ȣ : ");
			 String booksId = sc.nextLine();
			 
			 System.out.print("�ֹ����� : ");
			 int qty = Integer.parseInt(sc.nextLine());
			 
			 System.out.print("����ּ� : ");
			 String address = sc.nextLine();
			 
				 
			 Orders orders = new Orders(0, null, userId, address, 0);
			 OrderLine orderLine = new OrderLine(0, 0, booksId, 0, qty, 0);
			 orders.getOrderLineList().add(orderLine);
			 
			 OrderController.insertOrders(orders);	 
	    }
	 /** case : 4 */
		private static void printWantOrder(String userId) {
			System.out.println("---- ��� ���� ��� ----");
			System.out.println("������� ���� : ");
			String booksName = sc.nextLine();
			System.out.println("������ : ");
			String booksWriter = sc.nextLine();
			System.out.println("���ǻ� : ");
			String bookspublisher = sc.nextLine();
			
			RegBookDto want = new RegBookDto(booksName, booksWriter, bookspublisher);
			RegBookController.ResgisterBook(want);
			
			
			
		}
	 
	 
	 /**case : 5 */
	  public static void putCart(String id) {
		    System.out.println("--- ��ٱ��� ��� ---");
			System.out.print("��ǰ��ȣ : ");
			String booksId = sc.nextLine();
			System.out.print("���� : ");
			int qty = Integer.parseInt(sc.nextLine());
			
			CartController.putCart(id,booksId,qty);		
	 }
	  
	  /**case : 6 */
	  public static void viewCart(String id) {
			CartController.viewCart(id);			
		}

	 
///////////////////////////        ������ �޴�             //////////////////////////////////////
	  
	public static void printAdminMenu(String userId) {
		
		while(true) {
			UserSet userset = UserSet.getInstance();
			System.out.println(userset.getSet()); 
			System.out.println("--------------  Admin Menu --------------");
			System.out.println("  ------------   ������ ���     ------------");
			System.out.println("   |   1.�α׾ƿ�          |  2.ȸ����Ϻ���   |  ");
			System.out.println("   | 3.���������� ����  | 4.���ο�����  | 5.������� |");
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
					System.out.println("��ȣ�� �°� �������ּ���");
					break;
			
			}
		}
	}

	/** case : 4 */
	private static void PrintInputNewbook() {
		System.out.println("---- ��� ���� ��� ----");
		System.out.println("��ϵ����ڵ�: ");
		String newId = sc.nextLine();
		System.out.println("��ϵ��� ���� : ");
		String newName = sc.nextLine();
		System.out.println("��� ���� : ");
		int newPrice = Integer.parseInt(sc.nextLine());
		System.out.println("��� ���� : ");
		int newStock = Integer.parseInt(sc.nextLine());
		
		BookDto book = new BookDto(newId, newName, newPrice, newStock);
		AdminController.NewBook(book);	
	}
	/** case : 5 */
	private static void SalesManagement() {
		System.out.println("---- ������� �޴� ----");
		System.out.println(" | 1.������ ���� | 2.�Ⱓ�� ���� | 3.�� ����   | 4.���� ");
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
				System.out.println("��ȣ�� �°� �������ּ���");
				break;
		}
			
	}
	/** case : 5 -2 */
	private static void periodSales() {
		System.out.println("�������� ? ");
		String startdate = sc.nextLine();
		System.out.println("�������� ? ");
		String enddate = sc.nextLine();
		
		AdminController.periodSales(startdate,enddate);
		
		
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//MenuView
