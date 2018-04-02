package product.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class ProductDao {
		
	//상품 상세보기
	public ProductDto selectProductData(int p_code) throws Exception{
		System.out.println("p_code값:" +p_code);
		Connection conn = null;
		String sql = "select * from product inner join category on p_cnum=ct_num where p_code=" +p_code;
		ProductDto dto = null;
		System.out.println("aaaaa");
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
			conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new ProductDto();
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
				dto.setP_date(rs.getString("p_date"));
				dto.setP_shippingfee(rs.getInt("p_shippingfee"));
				dto.setP_sales(rs.getInt("p_sales"));
				dto.setP_cnum(rs.getInt("p_cnum"));
				dto.setCt_num(rs.getInt("ct_num"));
				dto.setCt_name1(rs.getString("ct_name1"));
				dto.setCt_name2(rs.getString("ct_name2"));
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectProductData 에러:");
		}
		
		return dto;
	}
	
	//베스트상품 모음전 db연동
	public List<ProductDto> productBest() throws Exception{
		
		Connection conn = null;
		String sql = "select * from product order by p_sales desc";
		List<ProductDto> list = new ArrayList<ProductDto>();
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
			conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDto dto = new ProductDto();
				dto.setP_code(rs.getInt("p_code"));
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
				dto.setP_date(rs.getString("p_date"));
				dto.setP_shippingfee(rs.getInt("p_shippingfee"));
				dto.setP_sales(rs.getInt("p_sales"));
				dto.setP_cnum(rs.getInt("p_cnum"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("productBest 에러:");
		} 
		return list;
	}
	
	//신상품 카테고리
	public List<ProductDto> selectNewProduct() throws SQLException{
		Connection conn = null;
		String sql = "select * from product order by p_date desc";
		List<ProductDto> list = new ArrayList<ProductDto>();
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
			conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDto dto = new ProductDto();
				dto.setP_code(rs.getInt("p_code"));
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
				dto.setP_date(rs.getString("p_date"));
				dto.setP_shippingfee(rs.getInt("p_shippingfee"));
				dto.setP_sales(rs.getInt("p_sales"));
				dto.setP_cnum(rs.getInt("p_cnum"));
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("selectNewProduct err : " + e);
		} 
		
		
		return list;
		
	}
	
	
	//전체 카테고리 리스트
	public List<ProductDto> cateList(){
		Connection conn = null;
		String sql = "select * from category";
		List<ProductDto> list = new ArrayList<ProductDto>();
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
			conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDto dto = new ProductDto();
				dto.setCt_num(rs.getInt("ct_num"));
				dto.setCt_name1(rs.getString("ct_name1"));
				dto.setCt_name2(rs.getString("ct_name2"));
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			System.out.println("cateList err : " + e);
		}
		return list;
	}
	
	//대분류 카테고리 분류
	public List<ProductDto> cate1Data(String ct_name1) throws SQLException{
		System.out.println(ct_name1);
		Connection conn = null;
		String sql = "select * from product inner join category on p_cnum=ct_num where ct_name1=?";
		List<ProductDto> list = new ArrayList<ProductDto>();
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
			
			if(ct_name1 !=null) {
				conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ct_name1);
				ResultSet rs = pstmt.executeQuery();
				System.out.println(rs);
			
				
				while(rs.next()) {
				ProductDto dto = new ProductDto();
				dto.setP_code(rs.getInt("p_code"));
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
				dto.setP_shippingfee(rs.getInt("p_shippingfee"));
				dto.setP_sales(rs.getInt("p_sales"));
				dto.setP_cnum(rs.getInt("p_cnum"));
				dto.setCt_num(rs.getInt("ct_num"));
				dto.setCt_name1(rs.getString("ct_name1"));
				dto.setCt_name2(rs.getString("ct_name2"));
				list.add(dto);
				
			}
		}
			
		} catch (Exception e) {
			System.out.println("cate1Data err : " + e);
		} 
		
		return list;
	}
	
	    //소분류 카테고리 분류
		public List<ProductDto> cate2Data(String ct_name2) throws SQLException{
			System.out.println(ct_name2);
			Connection conn = null;
			String sql = "select * from product inner join category on p_cnum=ct_num where ct_name2=?";
			List<ProductDto> list = new ArrayList<ProductDto>();
			
			try {
				Context init = new InitialContext();
				DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");

				if(ct_name2 !=null) {
					conn = ds.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, ct_name2);
					ResultSet rs = pstmt.executeQuery();
					System.out.println(rs);
				
				while(rs.next()) {
					ProductDto dto = new ProductDto();
					dto.setP_code(rs.getInt("p_code"));
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
					dto.setP_shippingfee(rs.getInt("p_shippingfee"));
					dto.setP_sales(rs.getInt("p_sales"));
					dto.setP_cnum(rs.getInt("p_cnum"));
					dto.setCt_num(rs.getInt("ct_num"));
					dto.setCt_name1(rs.getString("ct_name1"));
					dto.setCt_name2(rs.getString("ct_name2"));
					list.add(dto);
				}
				
			  }
			} catch (Exception e) {
				System.out.println("cate2Data err : " + e);
			} 
			
			return list;
		}
		
		
	/*
	//검색 서제스트 
	public List<ProductDto> selectSerchName(String p_name) throws SQLException{
		SqlSession sqlSession = factory.openSession();
		List<ProductDto> list = null;
		try {
			ProductMapper inter = (ProductMapper)sqlSession.getMapper(ProductMapper.class);		
			list = inter.selectSerchName(p_name);
		} catch (Exception e) {
			System.out.println("selectSerchName err : " + e);
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return list;
	}
	

	
	//[admin] 상품 읽기
	public ProductDto selectProductByCode(int p_code) {
		ProductDto dto =null;
		SqlSession session = factory.openSession();
		try {
			ProductMapper inter = (ProductMapper)session.getMapper(ProductMapper.class);		
			dto = inter.selectProductByCode(p_code);
		} catch (Exception e) {
			System.out.println("selectProductByCode() err : " + e);
		} finally {
			if(session != null) session.close();
		}
		return dto;
	}
	//[admin] 상품 등록 시 신규번호 
	public int getNewCode(){
		int newCode = 0;
		SqlSession session  = factory.openSession();
		try {
			ProductMapper inter = (ProductMapper)session.getMapper(ProductMapper.class);
			newCode = inter.selectMaxCode();
		} catch (Exception e) {
			System.out.println("getNewCode() err:" + e);
		}finally {
			if(session != null)	session.close();
		}		
		return newCode + 1;
	}
	
	//[admin] 상품 등록
	public boolean insertProduct(ProductDto dto){
		boolean isInserted = false;
		SqlSession session = factory.openSession();
		try {
			ProductMapper inter = (ProductMapper)session.getMapper(ProductMapper.class);			
			isInserted = (inter.insertProduct(dto) > 0) ? true : false;
			session.commit();
		} catch (Exception e) {
			System.out.println("insertProduct() err:" + e);
			session.rollback();
		}finally {
			if(session != null) session.close();
		}
		return isInserted;
	}
	
	
	//[admin] 상품 수정 
	public boolean updateProduct(ProductDto dto){
		boolean isUpdated = false;
		SqlSession session = factory.openSession();
		try {
			System.out.println("input : "  + dto.toString());
			ProductMapper inter = (ProductMapper)session.getMapper(ProductMapper.class);		
			isUpdated = (inter.updateProduct(dto) > 0) ? true : false;
			System.out.println("output : "  + isUpdated);
			session.commit();			
		} catch (Exception e) {
			System.out.println("updateProduct() err:" + e);
			session.rollback ();
		}finally {
			if(session != null) session.close();
		}
		return isUpdated;
	}
	
	//[admin] 상품 삭제
	public boolean deleteProduct(String[] codes){
		boolean isDeleted = false;
		SqlSession session = factory.openSession();
		try {
			ProductMapper inter = (ProductMapper)session.getMapper(ProductMapper.class);
			isDeleted = (inter.deleteProduct(codes) > 0) ? true : false;			
			session.commit();
		} catch (Exception e) {
			System.out.println("deleteProduct() err:" + e);
			session.rollback();
		}finally {
			if(session != null) session.close();
		}
		return isDeleted;
	}
	*/
}
