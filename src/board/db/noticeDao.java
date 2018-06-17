package board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class noticeDao {
	
	Connection con=null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	DataSource ds;
	
	public noticeDao() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MariaDB");
		}catch(Exception ex) {
			System.out.println("Db연결 실패"+ex);
			return;
		}
	}
	
	//공지사항 글 갯수 구하기
	public int getListCount() {
		int x=0;
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from boardnotice");
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				x=rs.getInt(1);
			}
		}catch(Exception ex) {
			System.out.println("글 갯수 구하기 에러"+ex);
		}finally{
			try{if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(con!=null) con.close();
			}catch(Exception ex) {}
		}
		
		return x;
		
	}
	//전체 공지사항 보기
	public List selectDataAll(int page, int limit){
		
		String board_list_sql ="select rownum rnum,bn_num,bn_title,bn_id,bn_date,bn_views,bn_content from boardnotice order by bn_num desc"+
								"where rnum>=? and rnum<=?";
		
		List list = new ArrayList();
		
		int startrow=(page-1)*10+1;
		int endrow = startrow+limit-1;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				noticeDto notice = new noticeDto();
				notice.setBn_num(rs.getInt("bn_num"));
				notice.setBn_title(rs.getString("bn_title"));
				notice.setBn_id(rs.getString("bn_id"));
				notice.setBn_date(rs.getTimestamp("bn_date"));
				notice.setBn_views(rs.getInt("bn_views"));
				notice.setBn_content(rs.getString("bn_content"));
				
				list.add(notice);
				
			}
			
			return list;
		}catch(Exception ex) {
			System.out.println("글목록 보기"+ex);
		}finally{
			try{if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(con!=null) con.close();
			}catch(Exception ex) {}
		}
		return null;
	}
	
	public noticeDto getDetail(int num) throws Exception{
		noticeDto notice = null;
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement("select * from boardnotice where bn_num =?");
			pstmt.setInt(1, num);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				notice = new noticeDto();
				notice.setBn_num(rs.getInt("bn_num"));
				notice.setBn_title(rs.getString("bn_title"));
				notice.setBn_id(rs.getString("bn_id"));
				notice.setBn_date(rs.getTimestamp("bn_date"));
				notice.setBn_views(rs.getInt("bn_views"));
				notice.setBn_content(rs.getString("bn_content"));
				
			}
			
			return notice;
		}catch(Exception ex) {
			System.out.println("글 내용 보기"+ex);
		}finally{
			try{if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(con!=null) con.close();
			}catch(Exception ex) {}
		}
		return null;
		
	}
	
	public void setViewsUpdate (int num) throws Exception{
		String sql = "update boardnotice set bn_views = bn+views+1 where bn_num = " + num;
		
		try { con=ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("글 조회수 증가"+ex);
		}
	}
	}

	
	
	/*
	//현재 페이지
	public int currentGetNum() {
		SqlSession sqlSession = factory.openSession();
		int maxCount = 0;
		try {
			noticeMapper inter = (noticeMapper)sqlSession.getMapper(noticeMapper.class);
			int a = inter.totalList();
			if(a > 0) maxCount = a;
		} catch (Exception e) {
			System.out.println("currentGetNum err : " + e);
		} finally {
			if (sqlSession != null) sqlSession.close();
		}
		
		return maxCount;
	}

	// 페이지 수 얻기
	public int getPageCount() {
		SqlSession sqlSession = factory.openSession();
		pageCount = 0; 
		totalPage = 0;
		try {
			//1. 전체 레코드 수 구하기 
			noticeMapper inter = (noticeMapper)sqlSession.getMapper(noticeMapper.class);
			totalPage = inter.totalList(); // 전체 레코드 수 
			pageCount = totalPage / pList;
			if(totalPage % pList > 0) pageCount++;
		} catch (Exception e) {
			System.out.println("totalList err : " + e);
		} finally {
			if (sqlSession != null) sqlSession.close();
		}
		return pageCount;
	}

	
	
	//공지사항 추가
	public boolean boardnoticeinsert(noticeBean bean){
		boolean b =false;
		SqlSession sqlSession = factory.openSession();
		try {
			noticeMapper inter = (noticeMapper)sqlSession.getMapper(noticeMapper.class);			
			
			if(inter.boardnoticeinsert(bean) > 0) b = true;
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println("boardnoticeinsert err : " + e);			
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return b;
	}
	
	//공지사항 상세보기
	public noticeDto getData(String num){
		SqlSession sqlSession = factory.openSession();
		noticeDto dto = null;
		
		try {
			noticeMapper inter = (noticeMapper)sqlSession.getMapper(noticeMapper.class);
			dto = inter.getData(num);
		} catch (Exception e) {
			System.out.println("getData err : " + e);
		} finally {
			if (sqlSession != null) sqlSession.close();
		}
		return dto;
	}
	
	//수정
	public boolean boardnoticeupdateData(noticeBean bean){
		SqlSession sqlSession = factory.openSession();
		boolean b = false;
		
		try {
			noticeMapper inter = (noticeMapper)sqlSession.getMapper(noticeMapper.class);
			if(inter.boardnoticeupdateData(bean) > 0) b = true;
			sqlSession.commit();
			
		} catch (Exception e) {
			System.out.println("updateData err : " + e);
			sqlSession.rollback();
		} finally {
			if (sqlSession != null) sqlSession.close();
		}
		
		return b;
	}
	//삭제
	public boolean deleteData(String num){
		boolean b = false;
		SqlSession sqlSession = factory.openSession();
		
		try {
			noticeMapper inter = (noticeMapper)sqlSession.getMapper(noticeMapper.class);
			int del = inter.deleteData(num);
			if(del > 0) b = true;
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println("deleteData err : " + e);
			sqlSession.rollback();
		} finally {
			if (sqlSession != null) sqlSession.close();
		}
		
		return b;
	}
	
	//검색
	public List<noticeDto> selectSerchtitle(String selword){
		SqlSession sqlSession = factory.openSession();
		List<noticeDto> list = null;
		try {
			noticeMapper inter = (noticeMapper)sqlSession.getMapper(noticeMapper.class);		
			String searchdata = "%" + selword + "%";
			list = inter.selectSerchtitle(searchdata);
		} catch (Exception e) {
			System.out.println("selectSerchtitle err : " + e);
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return list;
	}
	
	//조회수 증가
	public boolean updateViews(String num){
		SqlSession sqlSession = factory.openSession();
		boolean b = false; 
		try {
			noticeMapper inter = (noticeMapper)sqlSession.getMapper(noticeMapper.class);			
			int view = inter.updateViews(num);			
			if(view > 0) b = true;
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println("updateViews err : " + e);
			sqlSession.rollback();
		} finally {
			if (sqlSession != null) sqlSession.close();
		}
		return b;
	}
}*/