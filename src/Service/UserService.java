package Service;

import Dao.UserDao;
import Dao.UserDaoImpl;
import Dto.UserDto;
import User.User;
import User.UserSet;


public class UserService {
	UserDao userdao = new UserDaoImpl();
	/**로그인*/
	public  UserDto login(String userId, String userPwd)throws Exception {
		UserDto user= userdao.login(userId, userPwd);
		if(user==null) {
			throw new Exception("정보를 다시 확인해주세요.");
		}
		
		//로그인 된 정보 저장하기
		
		User user2 = new User(userId);
		UserSet userSet = UserSet.getInstance();
		userSet.add(user2);
		
		return user;
		
	}
	/**회원가입*/
	public  void register(String reuserId, int reuserPwd, String reuserName)throws Exception {
		int result = userdao.register(reuserId,reuserPwd,reuserName);
		if(result ==0) throw new Exception("회원가입이 불가합니다 다시 시도해주세요");
		
	}
	
	/**수정*/
	public void Update(UserDto ud,String userId)throws Exception {
		int result = userdao.Update(ud,userId);
		if(result ==0) throw new Exception("수정이 불가능합니다 다시 시도해주세요");
		
	}
	
	/**탈퇴*/
	public void Delete(String userId) throws Exception{
		int result = userdao.Delete(userId);
		if(result ==0) throw new Exception("탈퇴가 불가능합니다 다시 시도해주세요");
		
	}


}
