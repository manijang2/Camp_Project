package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/* MemberDao
 * 생성자 추가됨
 * 필요한 메소드들 정의와 return 값만 입력해 놓음
 */

public class MemberDao {
	
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt = null;
	
	//MemberDao 생성자
	public MemberDao() {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("java:comp/env/jdbc/MariaDB");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<MemberDto> selectAll(){
		List<MemberDto> list = null;
		
		return list;
	}
	
	public MemberDto selectMemberById(String id){
		MemberDto  dto =null;
		
		return dto;
	}
	
	public boolean insertMember(MemberDto dto){
		boolean isInserted = false;
		
		return isInserted;

	}
	
	public boolean updateMember(MemberDto dto){
		boolean isUpdated = false;
		
		return isUpdated;

	}
	
	public boolean deleteMember(String[] ids){
		boolean isDeleted = false;
		
		return isDeleted;
		
	}
	
	public boolean checkLogin(MemberDto dto){
		boolean isLoginChecked = false;
		
		return isLoginChecked;

	}
	
	public boolean checkId(String id){
		boolean  isIdChecked = false;
		
		return isIdChecked;
	}
	
	

}
