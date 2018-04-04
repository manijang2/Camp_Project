package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Timestamp;


/* MemberDao
 * 생성자 추가됨
 * 필요한 메소드들 정의와 return 값만 입력해 놓음
 */


public class MemberDao {
	
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
		
		
	public List<MemberDto> selectAll()throws SQLException{
		String sql=null;
		List<MemberDto> list = null;
		
		try {
			con=ds.getConnection();
			sql="select * from member";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				MemberDto member=new MemberDto();
				member.setM_address(rs.getString("m_address"));
				member.setM_date(rs.getTimestamp("m_date"));
				member.setM_email(rs.getString("m_email"));
				member.setM_grade(rs.getString("m_grade"));
				member.setM_id(rs.getString("m_id"));
				member.setM_mileage(rs.getInt("m_mileage"));
				member.setM_name(rs.getString("m_name"));
				member.setM_phone(rs.getString("m_phone"));
				member.setM_pwd(rs.getString("m_pwd"));
				member.setM_zipcode(rs.getString("m_zipcode"));
				
				list.add(member);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}	finally{
			try{if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(con!=null) con.close();
			}catch(Exception ex) {}
		}
				
		return list;
	}
	
	public MemberDto selectMemberById(String id)throws SQLException{
		String sql=null;
		MemberDto member=new MemberDto();

		
		try {
			con=ds.getConnection();
			sql="select * from member where m_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				member.setM_address(rs.getString("m_address"));
				member.setM_date(rs.getTimestamp("m_date"));
				member.setM_email(rs.getString("m_email"));
				member.setM_grade(rs.getString("m_grade"));
				member.setM_id(rs.getString("m_id"));
				member.setM_mileage(rs.getInt("m_mileage"));
				member.setM_name(rs.getString("m_name"));
				member.setM_phone(rs.getString("m_phone"));
				member.setM_pwd(rs.getString("m_pwd"));
				member.setM_zipcode(rs.getString("m_zipcode"));
								
			}
			}catch(Exception e) {
				e.printStackTrace();
			}	finally{
				try{if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null) con.close();
				}catch(Exception ex) {}
			}
								
		
		return member;
	}
	
	public boolean insertMember(MemberDto dto)throws SQLException{
		boolean isInserted = false;
		String sql=null;
		
		try {
			con=ds.getConnection();
			sql="insert into member values" + 
			"(?,?,?,?,?,?,?,?,?,?)";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getM_id());
			pstmt.setString(2, dto.getM_pwd());
			pstmt.setString(3, dto.getM_name());
			pstmt.setString(4, dto.getM_email());
			pstmt.setString(5, dto.getM_phone());
			pstmt.setString(6, dto.getM_zipcode());
			pstmt.setString(7, dto.getM_address());
			pstmt.setInt(8, dto.getM_mileage());
			pstmt.setString(9, dto.getM_grade());
			pstmt.setTimestamp(10, dto.getM_date());
			
			pstmt.executeUpdate();
			
			isInserted=true;
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}	finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception ex) {}
		}
		
		return isInserted;

	}
	
	public boolean updateMember(MemberDto dto)throws SQLException{
		boolean isUpdated = false;
		String sql=null;
		
		try {
			con=ds.getConnection();
			sql="update member set m_pwd=?,m_name=?,m_email=?,m_phone=?,"+
			"m_zipcode=?,m_address=?,m_grade=?,m_mileage=? where m_id=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getM_pwd());
			pstmt.setString(2, dto.getM_name());
			pstmt.setString(3, dto.getM_email());
			pstmt.setString(4, dto.getM_phone());
			pstmt.setString(5, dto.getM_zipcode());
			pstmt.setString(6, dto.getM_address());
			pstmt.setInt(7, dto.getM_mileage());
			pstmt.setString(8, dto.getM_grade());
			pstmt.setTimestamp(9, dto.getM_date());
			pstmt.setString(10, dto.getM_id());

			pstmt.executeUpdate();
			isUpdated=true;
						
		}catch(Exception e) {
			e.printStackTrace();
		}	finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception ex) {}
		}
				
		return isUpdated;

	}

	/*나중에 수정해야할거같음 -원본은 동적 sql사용 String ids[]*/
	public boolean deleteMember(String id)throws SQLException{
		boolean isDeleted = false;
		String sql=null;
		
		try {
			con=ds.getConnection();
			sql="delete from member where m_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			isDeleted=true;
		}catch(Exception e) {
			e.printStackTrace();
		}	finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception ex) {}
		}
				
		return isDeleted;
		
	}
	
	/*기존방식과 다르게 하였음*/ 
	public boolean checkLogin(String id, String pw){
		boolean isLoginChecked = false;
		String sql=null;
		System.out.println("id/pw : memberdao "+id + " "+ pw);
		
		try {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
			con=ds.getConnection();
			sql="select * from member where m_id=? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			System.out.println(sql);

			
			if(rs.next()) {
				String memberpw = rs.getString("m_pwd");
				System.out.println(memberpw);

				if(memberpw.equals(pw)) {
					isLoginChecked=true;
				}
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}	finally{
			try{if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(con!=null) con.close();
			}catch(Exception ex) {}
		}
			
		
		return isLoginChecked;

	}

	/*true : 아이디 존재함 */
	
	public boolean checkId(String id){
		boolean  isIdChecked = false;
		String sql=null;
		
		try {
			con=ds.getConnection();
			sql="select * from member where m_id=? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				isIdChecked=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}	finally{
			try{if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(con!=null) con.close();
			}catch(Exception ex) {}
		}
			
		return isIdChecked;
	}
	
	

}
