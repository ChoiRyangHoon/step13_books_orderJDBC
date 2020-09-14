package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Dto.RegBookDto;
import Util.DbUtil;

public class RegBookDaoImpl implements RegBookDao {

	@Override
	public int ResgisterBook(RegBookDto want) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		int result =0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("INSERT INTO REGBOOK VALUES(REG_NO_SEQ.NEXTVAL,?,?,?,DEFAULT)");
			ps.setString(1, want.getBookName());
			ps.setString(2, want.getBookWriter());
			ps.setString(3, want.getBookpublisher());
			
			result = ps.executeUpdate();
			
		}finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}

}
