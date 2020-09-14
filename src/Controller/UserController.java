package Controller;

import Dto.UserDto;
import Service.UserService;
import VIew.EndView;
import VIew.FailView;
import VIew.MenuView;

public class UserController {
	static UserService userService = new UserService();
	
	/**�α���*/
	public static void login(String userId, String userPwd) {
		try {
			UserDto user = userService.login(userId, userPwd);
			if(user.getGrade().equals("ȸ��")) {
				System.out.println("������ ȯ���մϴ�");
				MenuView.printUserMenu(userId);
			}
			else {
				System.out.println("�����ڴ� �������");
				MenuView.printAdminMenu(userId);
			}
			
		}catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			
		}
	}
	
	/**ȸ������*/
	public static void register(String reuserId, int reuserPwd,String reuserName) {
		try {
			userService.register(reuserId, reuserPwd, reuserName);
			EndView.messagePrint("ȸ�������� �����մϴ�");
			}catch (Exception e) {
				FailView.errorMessage(e.getMessage());
			}
	
	}
	
	/**���� */
	public static void Update(UserDto ud,String userId) {
		try {
			userService.Update(ud,userId);
			EndView.messagePrint("������ �����߽��ϴ� �ٽ� �α��� ���ּ���");
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
		
	}
	
	/**Ż��*/
	public static void Delete(String userId) {
		try {
			userService.Delete(userId);
			EndView.messagePrint("Ż�� ����");
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
		
	}

	

}
