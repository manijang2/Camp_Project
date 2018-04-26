package cart;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



import com.sun.javafx.geom.transform.GeneralTransform3D;

import member.MemberDto;
import product.db.ProductDto;
import cart.CartDto;


public class CartDao {

	private Hashtable hCart = new Hashtable<>();

	// 카트 삽입
	public boolean insertCart(CartDto dto) throws SQLException {

		boolean b = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int c_pcode = dto.getC_pcode();
		int quantity = dto.getC_quantity();
		String c_id =  dto.getC_id();
		System.out.println("c_pcode 값 테스트:"+c_pcode);	
		System.out.println("quantity 값 테스트:"+quantity);
		System.out.println("c_id 값 테스트:"+c_id);
		
		try {
			//수량이 0보다 클경우 key로 c_pcode를 찾아옴
			//containsKey은 HashMap에 지정된 키(Key)가 포함되어 있는지 알려준다.
			if (quantity > 0) {
				if (hCart.containsKey(c_pcode)) {
					//get을 이용해 c_pcode 값  temp에 저장
					CartDto temp = (CartDto) hCart.get(c_pcode);
					//수량을 얻어와서 quantity의 총계를 구함
					quantity += temp.getC_quantity();
					//총계를 setter를 이용해 저장
					temp.setC_quantity(quantity);
					//hashtable에 담음
					hCart.put(c_pcode, temp);
				}
			} else {
				//수량이 0 일경우 
				hCart.put(c_pcode, dto);
			}

			System.out.println("카트 삽입 시도 test");
			String sql = "insert into cart(c_pcode,c_quantity,c_id) values(?,?,?)";
			System.out.println("카트 삽입 sql 적용 test");
			
			
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MariaDB");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_pcode);
			pstmt.setInt(2, quantity);
			pstmt.setString(3, c_id);
			
			if(pstmt.executeUpdate() > 0) {
				b = true;	
				System.out.println("수량 업데이트 성공");
				conn.commit();
			}
			else {
				b= false;
				System.out.println("수량 업데이트 실패");
				conn.rollback();
			}
	
		} catch (Exception e) {
			System.out.println("insertCart err : " + e);

		} finally{
			try{
			if(rs!=null)rs.close();	
			if(pstmt!=null)pstmt.close();
			if(conn!=null) conn.close();
			
			}catch(Exception ex) {
				
			}
		}
		
		return b;
	}

	// 카트 리스트 출력
	public List<CartDto> cartRead(String c_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println(c_id);
		String sql = "select * from cart inner join product on p_code=c_pcode inner join member on c_id=m_id where c_id=?";
		
		// CartDto list선언
		List<CartDto> list = new ArrayList<CartDto>();
		System.out.println("c_id값 출력:" + c_id);
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MariaDB");

			if (c_id != null) {
				conn = ds.getConnection();
				System.out.println(conn);

				pstmt = conn.prepareStatement(sql);
				System.out.println(pstmt);
				pstmt.setString(1, c_id);
				rs = pstmt.executeQuery();
				System.out.println(rs);

				while (rs.next()) {
					
					CartDto dto = new CartDto();
					
					dto.setC_num(rs.getInt("c_num"));
					dto.setC_pcode(rs.getInt("c_pcode"));
					dto.setC_quantity(rs.getInt("c_quantity"));
					dto.setC_id(rs.getString("c_id"));
					dto.setC_pcode(rs.getInt("c_pcode"));
					dto.setP_name(rs.getString("p_name"));
					dto.setP_price(rs.getInt("p_price"));
					dto.setP_stock(rs.getInt("p_stock"));
					dto.setP_brand(rs.getString("p_brand"));
					dto.setP_origin(rs.getString("p_origin"));
					dto.setP_image1(rs.getString("p_image1"));
					dto.setP_image2(rs.getString("p_image2"));
					dto.setP_image3(rs.getString("p_image3"));
					dto.setP_image4(rs.getString("p_image4"));
					dto.setP_info(rs.getString("p_info"));
					dto.setP_mileagerate(rs.getFloat("p_mileagerate"));
					dto.setP_date(rs.getString("m_date"));
					dto.setP_shippingfee(rs.getInt("p_shippingfee"));
					dto.setP_sales(rs.getInt("p_sales"));
					dto.setP_cnum(rs.getInt("c_num"));
					dto.setM_id(rs.getString("m_id"));
					dto.setM_pwd(rs.getString("m_pwd"));
					dto.setM_name(rs.getString("m_name"));
					dto.setM_email(rs.getString("m_email"));
					dto.setM_phone(rs.getString("m_phone"));
					dto.setM_zipcode(rs.getString("m_zipcode"));
					dto.setM_address(rs.getString("m_address"));
					dto.setM_mileage(rs.getInt("m_mileage"));
					dto.setM_grade(rs.getString("m_grade"));
					dto.setM_date(rs.getTimestamp("m_date"));
					
					list.add(dto);

				}
			}

		} catch (Exception e) {
			System.out.println("cartRead err : " + e);
		} finally{
			try{
			if(rs!=null)rs.close();	
			if(pstmt!=null)pstmt.close();
			if(conn!=null) conn.close();
			
			}catch(Exception ex) {
				
			}
		}

		return list;
	}
	
	
	// 카트 삭제
		public boolean deleteCart(int c_num){
			boolean b = false;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			
			try {
				String sql = "delete from cart where c_num=?";
				Context init = new InitialContext();
				DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MariaDB");
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, c_num);
	
			System.out.println(pstmt.executeUpdate());
			
			if(pstmt.executeUpdate() > 0) {
				b = true;		
				conn.commit();
			} else {
				b = false;
				conn.rollback();
			}
				
				
			} catch (Exception e) {
				System.out.println("deleteCart err : " + e);
			
				
			}  finally{
				try{
					if(rs!=null)rs.close();	
					if(pstmt!=null)pstmt.close();
					if(conn!=null) conn.close();
					
					}catch(Exception ex) {
						
					}
				}
				
			
			return b;
		}
		
		// 카트 수량 수정
		public boolean updateCart(CartDto dto) throws SQLException{
			boolean b= false;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int c_num = dto.getC_num();
			int c_quantity = dto.getC_quantity();
			
		    System.out.println("c_num:"+c_num + "c_quantity:"+c_quantity);
			String sql = "update cart set c_quantity=? where c_num=?";
			
		    try {
		    	Context init = new InitialContext();
				DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MariaDB");
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, c_quantity);
				pstmt.setInt(2, c_num);
				
				if(pstmt.executeUpdate() > 0) {
					b = true;	
					System.out.println("수량 업데이트 성공");
					conn.commit();
				}
				else {
					b= false;
					System.out.println("수량 업데이트 실패");
					conn.rollback();
				}
			} catch (Exception e) {
				System.out.println("updateCart err : " + e);

			} finally{
				
				try{
					if(rs!=null)rs.close();	
					if(pstmt!=null)pstmt.close();
					if(conn!=null) conn.close();
					
					}catch(Exception ex) {
						
					}
				}
			
			return b;
		}
		
		// 카트 선택상품 삭제
		public boolean selectDelete(String[] nums){
			System.out.println(nums);
			boolean b= false;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "<script> DELETE FROM CART WHERE C_NUM in <foreach item='c_num' collection='array' open='(' separator=',' close=')'> ? </foreach></script>";
			
			
			try {
			
				Context init = new InitialContext();
				DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MariaDB");
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				//pstmt.setS(1, nums);
	
			System.out.println(pstmt.executeUpdate());
			
			if(pstmt.executeUpdate() > 0) {
				b = true;		
				conn.commit();
			} else {
				b = false;
				conn.rollback();
			}
			
			} catch (Exception e) {
				System.out.println("selectDelete() err:" + e);
			} finally{
				
				try{
					if(rs!=null)rs.close();	
					if(pstmt!=null)pstmt.close();
					if(conn!=null) conn.close();
					
					}catch(Exception ex) {
						
					}
				}
			
			return b;
		}
		
		
	

}
