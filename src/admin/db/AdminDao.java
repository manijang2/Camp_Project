package admin.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import member.MemberDto;

import java.sql.Timestamp;


public class AdminDao {
	
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
		
		
	public List<MemberDto> selectAll()throws SQLException, NamingException{
		List<MemberDto> list = new ArrayList<MemberDto>();
		
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
		con=ds.getConnection();
		String sql="select * from member";
		
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		
		while(rs.next()) {
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
		
		rs.close();
		pstmt.close();
		con.close();
				
		return list;
	}
	
	public MemberDto selectMemberById(String id)throws SQLException, NamingException{
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
		con=ds.getConnection();
		
		String sql="select * from member where m_id=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		rs=pstmt.executeQuery();
		
		MemberDto member = null;
		if(rs.next()) {
			member = new MemberDto();
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

		rs.close();
		pstmt.close();
		con.close();
				
		return member;
	}
	
	
	public boolean updateMember(MemberDto dto)throws SQLException, NamingException{
		
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
		con=ds.getConnection();
		
		String sql="update member set m_pwd=?,m_name=?,m_email=?,m_phone=?,"+
			"m_zipcode=?,m_address=? where m_id=?";
			
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dto.getM_pwd());
		pstmt.setString(2, dto.getM_name());
		pstmt.setString(3, dto.getM_email());
		pstmt.setString(4, dto.getM_phone());
		pstmt.setString(5, dto.getM_zipcode());
		pstmt.setString(6, dto.getM_address());
		pstmt.setString(7, dto.getM_id());

		int updateCnt = pstmt.executeUpdate();
						
		pstmt.close();
		con.close();
				
		return (0 < updateCnt) ? true : false;
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
