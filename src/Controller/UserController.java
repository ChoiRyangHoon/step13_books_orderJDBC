package Controller;

import Dto.UserDto;
import Service.UserService;
import VIew.EndView;
import VIew.FailView;
import VIew.MenuView;

public class UserController {
	static UserService userService = new UserService();
	
	/**로그인*/
	public static void login(String userId, String userPwd) {
		try {
			UserDto user = userService.login(userId, userPwd);
			if(user.getGrade().equals("회원")) {
				System.out.println("접속을 환영합니다");
				MenuView.printUserMenu(userId);
			}
			else {
				System.out.println("관리자님 어서오세요");
				MenuView.printAdminMenu(userId);
			}
			
		}catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			
		}
	}
	
	/**회원가입*/
	public static void register(String reuserId, int reuserPwd,String reuserName) {
		try {
			userService.register(reuserId, reuserPwd, reuserName);
			EndView.messagePrint("회원가입을 축하합니다");
			}catch (Exception e) {
				FailView.errorMessage(e.getMessage());
			}
	
	}
	
	/**수정 */
	public static void Update(UserDto ud,String userId) {
		try {
			userService.Update(ud,userId);
			EndView.messagePrint("수정에 성공했습니다 다시 로그인 해주세요");
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
		
	}
	
	/**탈퇴*/
	public static void Delete(String userId) {
		try {
			userService.Delete(userId);
			EndView.messagePrint("탈퇴 성공");
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
		
	}

	

}
