package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Dto.UserDto;
import Util.DbUtil;

public class UserDaoImpl implements UserDao {
	
	/**로그인*/
	@Override
	public UserDto login(String userId, String userPwd) throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		UserDto userdto=null;
		try {
		   con = DbUtil.getConnection();
		   ps= con.prepareStatement("select * from userlist where user_id=? and user_pwd=?");
		   ps.setString(1, userId);
		   ps.setString(2, userPwd);
		   
	       rs = ps.executeQuery(); 
	        
	       if(rs.next()) {
	        userdto = new UserDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5));
	       }
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return userdto;
	}
	
	/**회원가입*/
	@Override
	public int register(String reuserId, int reuserPwd, String reuserName) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("insert into  userlist values(?,?,?,sysdate,default)");
			
			ps.setString(1	, reuserId);
			ps.setInt(2, reuserPwd);
			ps.setString(3, reuserName);
			
		    result = ps.executeUpdate();
		    	
		}finally {
			DbUtil.close(con, ps, null);
		}
		
		return result;
	}
	
	/**수정*/
	@Override
	public int Update(UserDto ud,String userId) throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("update userlist set user_id =?, user_pwd=?, user_name=? where user_id =?");
			
			ps.setString(1, ud.getUserId());
			ps.setString(2, ud.getUserPwd());
			ps.setString(3, ud.getUserName());
			ps.setString(4, userId);
		    result = ps.executeUpdate();
		    	
		}finally {
			DbUtil.close(con, ps, null);
		}
		
		return result;
	
	}
	
	/**탈퇴*/
	@Override
	public int Delete(String userId) throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("delete userlist where user_id = ?");
			
			ps.setString(1, userId);
		    result = ps.executeUpdate();
		    	
		}finally {
			DbUtil.close(con, ps, null);
		}
		
		return result;
		
	}

	
}
		

