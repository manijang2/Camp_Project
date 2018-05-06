package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.db.AdminDao;
import main.controller.Action;
import main.controller.ActionForward;
import member.MemberDao;
import member.MemberDto;

public class MemberCheckIDAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ActionForward forward = new ActionForward();
		AdminDao admindao = new AdminDao();
		
		String m_id = request.getParameter("m_id");
		System.out.println("m_id : " + m_id);
		boolean check=admindao.checkId(m_id);
		System.out.println("check : " + check);
		
		request.setAttribute("id", m_id);
		request.setAttribute("check", check);
		forward.setPath("/admin/member_id_check.jsp");
		
		return forward;
	}
}

