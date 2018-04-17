package cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;




public class CartDao {
	
	// 카트 리스트 출력
	public List<CartDto> cartRead(String c_id) throws SQLException{
		Connection conn = null;
		String sql = "select * from cart inner join product on p_code=c_pcode inner join member on c_id=m_id where c_id=?";
		System.out.println("bbbb");
		List<CartDto> list = new ArrayList<CartDto>();
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
			
			if(c_id != null) {
				conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, c_id);
				ResultSet rs = pstmt.executeQuery();
				System.out.println(rs);
				
			while(rs.next()) {
				CartDto dto = new CartDto();
				dto.getC_num();
				dto.getC_pcode();
				dto.getC_quantity();
				dto.getC_id();
				dto.getC_pcode();
				dto.getP_name();
				dto.getP_price();
				
			}
			}
			
			conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
		} catch (Exception e) {
			System.out.println("cartRead err : " + e);
		}
		
		return list;
	}


}
