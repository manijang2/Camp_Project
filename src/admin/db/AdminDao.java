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

public class AdminDao {
	
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
		
		
	public List<MemberDto> selectAll()throws SQLException, NamingException{

		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
		con=ds.getConnection();
		String sql="select * from member";
		
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		
		List<MemberDto> list = new ArrayList<MemberDto>();
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
	public boolean deleteMember(MemberDto dto)throws SQLException, NamingException{
		
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
		con=ds.getConnection();
		
		pstmt=con.prepareStatement("delete from member where m_id=?");
		pstmt.setString(1, dto.getM_id());
		int deleteCnt = pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
				
		return (0 < deleteCnt) ? true : false;
	}
	

	/*true : 아이디 존재함 */
	
	public boolean checkId(String id) throws NamingException, SQLException{
		boolean  isIdChecked = false;
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
		con=ds.getConnection();
		
		pstmt=con.prepareStatement("select * from member where m_id=?");
		pstmt.setString(1, id);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			isIdChecked=true;
		}

		rs.close();
		pstmt.close();
		con.close();

		return isIdChecked;
	}
	
	public boolean insertMember(MemberDto dto)throws SQLException, NamingException{
		boolean isInserted = false;
		String sql=null;
		
		System.out.println(dto);
		
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
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
		
		pstmt.close();
		con.close();
		
		return isInserted;

	}
}
