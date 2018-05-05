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
import order.db.OrderDto;
import product.db.ProductDto;

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
	
	//전체상품 보기
	public List<ProductDto> productAll() throws NamingException, SQLException{
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
		con=ds.getConnection();
		String sql="select * from product";
		
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		
		List<ProductDto> list = new ArrayList<ProductDto>();
		while(rs.next()) {
			ProductDto product=new ProductDto();
			product.setP_code(Integer.parseInt(rs.getString("p_code")));
			product.setP_name(rs.getString("p_name"));
			product.setP_price(Integer.parseInt(rs.getString("p_price")));
			product.setP_brand(rs.getString("p_brand"));
			product.setP_origin(rs.getString("p_origin"));
			product.setP_stock(Integer.parseInt(rs.getString("p_stock")));
			product.setP_sales(Integer.parseInt(rs.getString("p_sales")));
			product.setP_mileagerate(Float.parseFloat(rs.getString("p_mileagerate")));
			product.setP_shippingfee(Integer.parseInt(rs.getString("p_shippingfee")));
			product.setP_date(rs.getString("p_date"));
			product.setP_cnum(Integer.parseInt(rs.getString("p_cnum")));
			product.setP_image1(rs.getString("p_image1"));
			product.setP_image2(rs.getString("p_image2"));
			product.setP_image3(rs.getString("p_image3"));
			product.setP_image4(rs.getString("p_image4"));
			
			list.add(product);
		}
		
		rs.close();
		pstmt.close();
		con.close();
				
		return list;
	}
	
	//[admin] 상품 등록 시 신규번호 
	public int getNewCode() throws NamingException, SQLException{
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
		con=ds.getConnection();
		
		int newCode = -1;
		pstmt=con.prepareStatement("select max(p_code) from product");
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			newCode = rs.getInt(1);
		}

		rs.close();
		pstmt.close();
		con.close();

		return newCode + 1;
	}
	
	//[admin] 상품 수정 
	public boolean updateProduct(ProductDto dto) throws SQLException, NamingException{
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
		con=ds.getConnection();
		
		// @Update("update product set p_name=#{p_name},p_price=#{p_price},p_stock=#{p_stock},p_brand=#{p_brand},p_origin=#{p_origin},p_mileagerate=#{p_mileagerate},p_date=#{p_date},p_shippingfee=#{p_shippingfee},p_sales=#{p_sales},p_cnum=#{p_cnum},p_image1=#{p_image1},p_image2=#{p_image2},p_image3=#{p_image3},p_image4=#{p_image4},p_info=#{p_info} where p_code=#{p_code}")
		String sql="update product set "
				+ "p_name=?,p_price=?,p_stock=?,p_brand=?,p_origin=?,p_mileagerate=?,p_date=?,p_shippingfee=?,p_sales=?,"
				+ "p_cnum=?,p_image1=?,p_image2=?,p_image3=?,p_image4=?,p_info=?,p_cnum=? where p_code=?";
			
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dto.getP_name());
		pstmt.setInt(2, dto.getP_price());
		pstmt.setInt(3, dto.getP_stock());
		pstmt.setString(4, dto.getP_brand());
		pstmt.setString(5, dto.getP_origin());
		pstmt.setFloat(6, dto.getP_mileagerate());
		pstmt.setString(7, dto.getP_date());
		pstmt.setInt(8, dto.getP_shippingfee());
		pstmt.setInt(9, dto.getP_sales());
		pstmt.setInt(10, dto.getP_cnum());
		pstmt.setString(11, dto.getP_image1());
		pstmt.setString(12, dto.getP_image2());
		pstmt.setString(13, dto.getP_image3());
		pstmt.setString(14, dto.getP_image4());
		pstmt.setString(15, dto.getP_info());
		pstmt.setInt(16, dto.getCt_num());
		pstmt.setInt(17, dto.getP_code());
		

		int updateCnt = pstmt.executeUpdate();
						
		pstmt.close();
		con.close();
				
		return (0 < updateCnt) ? true : false;
	}
	
	public boolean deleteProduct(ProductDto dto)throws SQLException, NamingException{
		
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
		con=ds.getConnection();
		
		pstmt=con.prepareStatement("DELETE FROM product WHERE p_code=?");
		pstmt.setInt(1, dto.getP_code());
		int deleteCnt = pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
				
		return (0 < deleteCnt) ? true : false;
	}
	
	//[admin] 상품 등록
	public boolean insertProduct(ProductDto dto) throws NamingException, SQLException{
		
		System.out.println(dto);

		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
		con=ds.getConnection();

		String sql="insert into product(p_code,p_name,p_price,p_stock,p_brand,p_origin,p_image1,p_image2,p_image3,p_image4,"
				+ "p_info,p_mileagerate,p_shippingfee,p_sales,p_cnum,p_date) " + 
		"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, dto.getP_code());
		pstmt.setString(2, dto.getP_name());
		pstmt.setInt(3, dto.getP_price());
		pstmt.setInt(4, dto.getP_stock());
		pstmt.setString(5, dto.getP_brand());
		pstmt.setString(6, dto.getP_origin());
		pstmt.setString(7, dto.getP_image1());
		pstmt.setString(8, dto.getP_image2());
		pstmt.setString(9, dto.getP_image3());
		pstmt.setString(10, dto.getP_image4());
		pstmt.setString(11, dto.getP_info());
		pstmt.setFloat(12, dto.getP_mileagerate());
		pstmt.setFloat(13, dto.getP_shippingfee());
		pstmt.setInt(14, dto.getP_sales());
		pstmt.setInt(15, dto.getP_cnum());
		pstmt.setString(16, dto.getP_date());
		
		int cnt = pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
		
		return (cnt > 0) ? true:false;
	}
	
	//[admin]전체주문번호 보기		@Select("select o_num from orders")
	public List<Integer> selectOrderNum() throws NamingException, SQLException{
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
		con=ds.getConnection();
		
		List<Integer> list = new ArrayList<Integer>();
		int num = -1;
		pstmt=con.prepareStatement("select o_num from orders");
		rs=pstmt.executeQuery();
		
		while(rs.next()) {
			list.add(rs.getInt(1));
		}

		rs.close();
		pstmt.close();
		con.close();

		return list;
	}
	
	//[admin] 주문 등록 시 신규번호
	public int getNewOrderNum() throws NamingException, SQLException{
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
		con=ds.getConnection();
		
		int newCode = -1;
		pstmt=con.prepareStatement("select max(p_code) from product");
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			newCode = rs.getInt(1);
		}

		rs.close();
		pstmt.close();
		con.close();

		return newCode + 1;
	}
	
	//[admin]주문 정보 보기
	//[admin]주문정보	
	// @Select("select * from orders left outer join orderstatus on o_status=os_num left outer join product on o_pcode=p_code left outer join member on o_id=m_id where o_num=#{o_num}")
	public OrderDto selectOrder(int o_num) throws SQLException, NamingException{
		
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
		con=ds.getConnection();
		
		OrderDto dto = new OrderDto();
		int num = -1;
		pstmt=con.prepareStatement("select * from orders left outer join orderstatus on o_status=os_num left outer join product on o_pcode=p_code left outer join member on o_id=m_id where o_num=?");
		pstmt.setInt(1, o_num);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			dto.setO_num(Integer.parseInt(rs.getString("o_num")));
			dto.setO_pcode(Integer.parseInt(rs.getString("o_pcode")));
			dto.setP_name(rs.getString("p_name"));
			dto.setP_price((Integer.parseInt(rs.getString("p_price"))));
			dto.setO_quantity(Integer.parseInt(rs.getString("o_quantity")));
			dto.setO_usemileage(Integer.parseInt(rs.getString("o_usemileage")));
			dto.setO_shippingfee(Integer.parseInt(rs.getString("o_shippingfee")));
			dto.setO_pay(Integer.parseInt(rs.getString("o_pay")));
			
			dto.setO_date(rs.getString("o_date"));
			dto.setO_id(rs.getString("o_id"));
			dto.setO_mname(rs.getString("o_mname"));
			dto.setO_phone(rs.getString("o_phone"));
			dto.setO_zipcode(rs.getString("o_zipcode"));
			dto.setO_address(rs.getString("o_address"));
			dto.setO_message(rs.getString("o_message"));
		}

		rs.close();
		pstmt.close();
		con.close();

		return dto;
	}
	
	//[admin]주문 삭제
	/*
	@Update("update orders set o_pcode=#{o_pcode},o_quantity=#{o_quantity},o_date=#{o_date},o_status=#{o_status}, "
			+ "o_id=#{o_id},o_mname=#{o_mname},o_phone=#{o_phone},o_zipcode=#{o_zipcode},o_address=#{o_address},"
			+ "o_usemileage=#{o_usemileage},o_pay=#{o_pay},o_shippingfee=#{o_shippingfee},o_message=#{o_message} "
			+ "where o_num = #{o_num}")
	*/
	public boolean updateOrder(OrderDto dto) throws NamingException, SQLException{
		
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
		con=ds.getConnection();
		
		String sql = "update orders set o_pcode=?,o_quantity=?,o_date=?,o_status=?,"
		+ "o_id=?,o_mname=?,o_phone=?,o_zipcode=?,o_address=?,"
		+ "o_usemileage=?,o_pay=?,o_shippingfee=?,o_message=? "
		+ "where o_num = ?";
		
		/*
		@Update("update orders set o_pcode=#{o_pcode},o_quantity=#{o_quantity},o_date=#{o_date},o_status=#{o_status}, "
				+ "o_id=#{o_id},o_mname=#{o_mname},o_phone=#{o_phone},o_zipcode=#{o_zipcode},o_address=#{o_address},"
				+ "o_usemileage=#{o_usemileage},o_pay=#{o_pay},o_shippingfee=#{o_shippingfee},o_message=#{o_message} "
				+ "where o_num = #{o_num}")
		*/
		
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, dto.getO_pcode());
		pstmt.setInt(2, dto.getO_quantity());
		pstmt.setString(3, dto.getO_date());
		pstmt.setString(4, dto.getO_status());
		pstmt.setString(5, dto.getO_id());
		pstmt.setString(6, dto.getO_mname());
		pstmt.setString(7, dto.getO_phone());
		pstmt.setString(8, dto.getO_zipcode());
		pstmt.setString(9, dto.getO_address());
		pstmt.setInt(10, dto.getO_usemileage());
		pstmt.setInt(11, dto.getO_pay());
		pstmt.setInt(12, dto.getO_shippingfee());
		pstmt.setString(13, dto.getO_message());
		pstmt.setInt(14, dto.getO_num());

		int updateCnt = pstmt.executeUpdate();
						
		pstmt.close();
		con.close();
				
		return (0 < updateCnt) ? true : false;
	}
}
