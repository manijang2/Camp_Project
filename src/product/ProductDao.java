package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductDao {
	//전체상품 보기
		public List<ProductDto> productAll(){
			Connection conn = null;
			String sql = "select * from product";
			List<ProductDto> list = new ArrayList<ProductDto>();
			
			try {
				Context init = new InitialContext();
				DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
				conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					ProductDto dto = new ProductDto();
					dto.setP_image1(rs.getString("p_image1"));
					dto.setP_price(rs.getInt("p_price"));
					dto.setP_brand(rs.getString("p_brand"));
					dto.setP_origin(rs.getString("p_origin"));
					dto.setP_code(rs.getInt("p_code"));
					list.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return list;
		}
}
