package board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.db.noticeDao;
import main.controller.*;


public class BoardListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		//공지사항은 로그인 정보 안받아와도 될 것 같음
		
		noticeDao noticedao = new noticeDao();
		List boardlist = new ArrayList();
		
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page")!= null) {
			page = Integer.parseInt(request.getParameter("page"));
			
		}
		
		int listcount = noticedao.getListCount();
		boardlist = noticedao.selectDataAll(page, limit);
		
		int maxpage = (int)((double)listcount/limit+0.95);
		int startpage = (((int) ((double) page / 10 + 0.9))-1)*10 +1 ;
		int endpage = startpage + 10 -1;
		
		if(endpage > startpage+10-1) endpage = maxpage;
		
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("boardlist", boardlist);

		forward.setRedirect(false);
		forward.setPath("./board/board_noticelist.jsp");
		return forward;
		}
	

}
