package Dao;

import java.sql.SQLException;

import Dto.UserDto;

public interface UserDao {
	  /**
	   * �α����ϱ�
	 * @throws Exception 
	   * */
		UserDto login(String userId, String userPwd)throws  Exception;

		/**ȸ������*/
		int register(String reuserId, int reuserPwd, String reuserName)throws SQLException;

		int Delete(String userId)throws Exception ;

		int Update(UserDto ud,String userId)throws Exception;
	}



