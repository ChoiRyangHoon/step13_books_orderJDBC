package Dao;

import java.sql.SQLException;

import Dto.UserDto;

public interface UserDao {
	  /**
	   * 로그인하기
	 * @throws Exception 
	   * */
		UserDto login(String userId, String userPwd)throws  Exception;

		/**회원가입*/
		int register(String reuserId, int reuserPwd, String reuserName)throws SQLException;

		int Delete(String userId)throws Exception ;

		int Update(UserDto ud,String userId)throws Exception;
	}



