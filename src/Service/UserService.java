package Service;

import Dao.UserDao;
import Dao.UserDaoImpl;
import Dto.UserDto;
import User.User;
import User.UserSet;


public class UserService {
	UserDao userdao = new UserDaoImpl();
	/**�α���*/
	public  UserDto login(String userId, String userPwd)throws Exception {
		UserDto user= userdao.login(userId, userPwd);
		if(user==null) {
			throw new Exception("������ �ٽ� Ȯ�����ּ���.");
		}
		
		//�α��� �� ���� �����ϱ�
		
		User user2 = new User(userId);
		UserSet userSet = UserSet.getInstance();
		userSet.add(user2);
		
		return user;
		
	}
	/**ȸ������*/
	public  void register(String reuserId, int reuserPwd, String reuserName)throws Exception {
		int result = userdao.register(reuserId,reuserPwd,reuserName);
		if(result ==0) throw new Exception("ȸ�������� �Ұ��մϴ� �ٽ� �õ����ּ���");
		
	}
	
	/**����*/
	public void Update(UserDto ud,String userId)throws Exception {
		int result = userdao.Update(ud,userId);
		if(result ==0) throw new Exception("������ �Ұ����մϴ� �ٽ� �õ����ּ���");
		
	}
	
	/**Ż��*/
	public void Delete(String userId) throws Exception{
		int result = userdao.Delete(userId);
		if(result ==0) throw new Exception("Ż�� �Ұ����մϴ� �ٽ� �õ����ּ���");
		
	}


}
