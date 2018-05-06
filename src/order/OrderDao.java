package order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import cart.CartDto;


public class OrderDao {
	
	boolean b= false;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 주문 생성
	public boolean orderOne(OrderDto dto) throws SQLException{
		
		int o_pcode = dto.getO_pcode();
		int o_quantity = dto.getO_quantity();
		String o_id = dto.getO_id();
		String o_mname = dto.getO_mname();
		String o_phone = dto.getO_phone();
		String o_zipcode = dto.getO_zipcode();
		String o_address = dto.getO_address();
		int o_usemileage = dto.getO_usemileage();
		int o_pay = dto.getO_pay();
		int o_shoppingfee = dto.getO_shippingfee();
		String o_message = dto.getO_message();
		
			
		String sql ="insert into orders(o_pcode,o_quantity,o_date,o_id,o_mname,o_phone,o_zipcode,o_address,o_usemileage,o_pay,o_shippingfee,o_message) "
				+ "values(?,?,now(),?,?,?,?,?,?,?,?,?)";
		try {
			
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MariaDB");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,o_pcode);
			pstmt.setInt(2,o_quantity);
			pstmt.setString(3,o_id);
			pstmt.setString(4,o_mname);
			pstmt.setString(5,o_phone);
			pstmt.setString(6,o_zipcode);
			pstmt.setString(7,o_address);
			pstmt.setInt(8,o_usemileage);
			pstmt.setInt(9,o_pay);
			pstmt.setInt(10,o_shoppingfee);
			pstmt.setString(11,o_message);
			
			

			if(pstmt.executeUpdate() > 0) {
				b = true;	
				System.out.println("동기화 성공");
				conn.commit();
			}
			else {
				b= false;
				System.out.println("동기화 실패");
				conn.rollback();
			}
			
		} catch (Exception e) {
			System.out.println("insertorder err : " + e);
			
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
	
	
	public OrderDto selectMember(String c_id) throws SQLException{

		OrderDto dto= new OrderDto();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("c_id 값 테스트:"+c_id);
		
		
		String sql = "select * from member where m_id = (select distinct c_id from cart where c_id= ?)";
	
		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MariaDB");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				dto.setM_id(rs.getString("m_id"));
				/*dto.setM_pwd(rs.getString("m_pwd"));*/
				dto.setM_name(rs.getString("m_name"));
				dto.setM_email(rs.getString("m_email"));
				dto.setM_phone(rs.getString("m_phone"));
				dto.setM_zipcode(rs.getString("m_zipcode"));
				dto.setM_address(rs.getString("m_address"));

			}
			
		} catch (Exception e) {
			System.out.println("selectMember err : " + e);
			
		}  finally{
			
			try{
				if(rs!=null)rs.close();	
				if(pstmt!=null)pstmt.close();
				if(conn!=null) conn.close();
				
				}catch(Exception ex) {
					
				}
			}
		return dto;
	}
	
	public List<OrderDto> orderAll(String o_id){
		
		List<OrderDto> list =  new ArrayList<OrderDto>();
		System.out.println(o_id);
		String sql = "select * from orders left outer join orderstatus on o_status=os_num left outer join product on o_pcode=p_code where o_id=? order by o_num desc";
		
		
		try {
			
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MariaDB");

			if(o_id !=null) {
				
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, o_id);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					OrderDto dto = new OrderDto();
					
					dto.setO_num(rs.getInt("o_num"));
					dto.setO_pcode(rs.getInt("o_pcode"));
					dto.setO_quantity(rs.getInt("o_quantity"));
					dto.setO_date(rs.getString("o_date"));
					dto.setO_id(rs.getString("o_id"));
					dto.setO_mname(rs.getString("o_mname"));
					dto.setO_phone(rs.getString("o_phone"));
					dto.setO_zipcode(rs.getString("o_zipcode"));
			        dto.setO_address(rs.getString("o_address"));
			        dto.setO_pay(rs.getInt("o_pay"));
			        dto.setO_shippingfee(rs.getInt("o_shippingfee"));
			        dto.setOs_status(rs.getString("os_status"));
			        dto.setO_message(rs.getString("o_message"));
			        dto.setP_image1(rs.getString("p_image1"));
			        dto.setP_mileagerate(rs.getFloat("p_mileagerate"));
					dto.setP_price(rs.getInt("p_price"));
					dto.setO_quantity(rs.getInt("o_quantity"));
					dto.setO_usemileage(rs.getInt("o_usemileage"));
					dto.setP_name(rs.getString("p_name"));
					System.out.println("=======================");
					System.out.println(rs.getFloat("p_mileagerate"));
					System.out.println(rs.getInt("p_price"));
					System.out.println(rs.getInt("o_quantity"));
					System.out.println(rs.getInt("o_usemileage"));
					System.out.println("=======================");
					
					list.add(dto);
					
					
					
				}
			}
		} catch (Exception e) {
			
			System.out.println("orderAll err : " + e);
			
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
	
	//전체상품 보기
	
	
	//주문시 상품수량 변경
	public boolean updateProduct(OrderDto dto) throws SQLException{
		boolean b= false;
		
		int c_quantity = dto.getC_quantity();
		int o_pcode = dto.getO_pcode();
		System.out.println("c_quantity:"+c_quantity + "o_pcode:"+o_pcode);
		String sql = "update product set p_stock=(p_stock-?), p_sales=? where p_code=?";
	
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MariaDB");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_quantity);
			pstmt.setInt(2, c_quantity);
			pstmt.setInt(3, o_pcode);
		
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
			System.out.println("updateProduct err : " + e);
	
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
	
	//멤버 적립금 차감
	public boolean updateMileage(OrderDto dto){
		
		boolean b = false; 
		
		int o_usemileage = dto.getO_usemileage();
		String o_id = dto.getO_id();
		System.out.println("o_usemileage:"+o_usemileage + "o_id:"+o_id);
		String sql = "update member set m_mileage=(m_mileage-?) where m_id=?";
		
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MariaDB");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, o_usemileage);
			pstmt.setString(2, o_id);
		
		
			if(pstmt.executeUpdate() > 0) {
				b = true;	
				System.out.println("마일리지 업데이트 성공");
				conn.commit();
			}
			else {
				b= false;
				System.out.println("마일리지 업데이트 실패");
				conn.rollback();
			}		
		} catch (Exception e) {
			System.out.println("updateMileage() err:" + e);
			
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


	// 카트 선택상품 주문하기 - 미완료
			public boolean selectOrder(String[] nums){
				
				//List<OrderDto> list = new ArrayList<OrderDto>();
				
				//checkbox값 -> c_id 값 배열로 불러옴
				System.out.println(Arrays.toString(nums));
				
				
				System.out.println(Arrays.toString(nums).substring(1, Arrays.toString(nums).indexOf(']')));
				String CheckNums = Arrays.toString(nums).substring(1, Arrays.toString(nums).indexOf(']'));
		
				
				boolean b= false;
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				OrderDto dto = new OrderDto();
				
				
				String sql =  "select o_pcode from orders where o_pcode in (select p_code from product where p_code in (select c_pcode from cart where c_num in ("+CheckNums +")));";
				String sql2 = "insert into orders(o_pcode,o_quantity,o_date,o_id,o_mname,o_phone,o_zipcode,o_address,o_usemileage,o_pay,o_shippingfee,o_message) values(select o_pcode from orders where o_pcode in (select p_code from product where p_code in (select c_pcode from cart where c_num in (\"+CheckNums +\"))),?,now(),?,?,?,?,?,?,?,?,?)";
				
				try {
				
					Context init = new InitialContext();
					DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MariaDB");
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt = conn.prepareStatement(sql2);
					
					/*pstmt.setInt(1,o_pcode);
					pstmt.setInt(2,o_quantity);
					pstmt.setString(3,o_id);
					pstmt.setString(4,o_mname);
					pstmt.setString(5,o_phone);
					pstmt.setString(6,o_zipcode);
					pstmt.setString(7,o_address);
					pstmt.setInt(8,o_usemileage);
					pstmt.setInt(9,o_pay);
					pstmt.setInt(10,o_shoppingfee);
					pstmt.setString(11,o_message);*/
					 
					
				if(pstmt.executeUpdate() > 0) {
					b = true;	
					System.out.println("Test");
					System.out.println("선택된 상품 결제");
					conn.commit();
				} else {
					b = false;
					System.out.println("선택된 상품 결제 실패");
					conn.rollback();
				}
				
				} catch (Exception e) {
					System.out.println("selectOrder() err:" + e);
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
	/*//[admin] 주문 등록 시 신규번호 
	public int getNewOrderNum(){
		int newNum = 0;
		SqlSession session  = factory.openSession();
		try {
			OrderMapper inter = (OrderMapper)session.getMapper(OrderMapper.class);
			newNum = inter.selectMaxNum();
		} catch (Exception e) {
			System.out.println("getNewOrderNum() err:" + e);
		}finally {
			if(session != null)	session.close();
		}		
		return newNum + 1;
	}
	
	
	//[admin]전체주문번호 보기
	public String[] selectOrderNum(){
		String[] orders = null;	
		SqlSession sqlSession = factory.openSession();
		try {
			OrderMapper inter = (OrderMapper)sqlSession.getMapper(OrderMapper.class);		
			orders = inter.selectOrderNum();	
		} catch (Exception e) {
			System.out.println("selectOrderNum() err : " + e);
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return orders;
	}

	//[admin]주문 정보 보기
	public OrderDto selectOrder(String o_num){
		OrderDto dto = null;
		SqlSession session = factory.openSession();
		try {
			OrderMapper inter = (OrderMapper)session.getMapper(OrderMapper.class);		
			dto = inter.selectOrder(o_num);			
		} catch (Exception e) {
			System.out.println("selectOrder() err:" + e);
		}finally {
			if(session != null) session.close();
		}		
		return dto;
	}
	
	//[admin]주문 삭제
	public boolean deleteOrder(String[] orders){
		boolean isDeleted = false;
		SqlSession session = factory.openSession();
		try {
			OrderMapper inter = (OrderMapper)session.getMapper(OrderMapper.class);
			isDeleted = (inter.deleteOrders(orders) > 0) ? true : false;			
			session.commit();
		} catch (Exception e) {
			System.out.println("deleteOrder() err:" + e);
			session.rollback();
		}finally {
			if(session != null) session.close();
		}
		return isDeleted;
	}
	
	//[admin]주문 삭제
	public boolean updateOrder(OrderDto dto){
		boolean isUpdated = false;
		SqlSession session = factory.openSession();
		try {
			System.out.println(dto.toString());
			OrderMapper inter = (OrderMapper)session.getMapper(OrderMapper.class);
			isUpdated = (inter.updateOrder(dto) > 0) ? true : false;			
			session.commit();
		} catch (Exception e) {
			System.out.println("updateOrder() err:" + e);
			session.rollback();
		}finally {
			if(session != null) session.close();
		}
		return isUpdated;
	}
	
	//[admin] 주문 등록 시 신규번호 
	public int getMonthlyPay(String y_month){
		int monthlySum = 0;
		String sum = null; 
		SqlSession session  = factory.openSession();
		try {
			OrderMapper inter = (OrderMapper)session.getMapper(OrderMapper.class);
			String date = "%" + y_month.substring(0,2) + "-" + y_month.substring(3,5) + "%"; 
			sum = inter.selectSumByMonth(date);
			if(sum != null) monthlySum = Integer.parseInt(sum); 
		} catch (Exception e) {
			System.out.println("getMonthlyPay() err:" + e);
		}finally {
			if(session != null)	session.close();
		}		
		return monthlySum;
	}*/
}
