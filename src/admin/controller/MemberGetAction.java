package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.db.AdminDao;
import main.controller.Action;
import main.controller.ActionForward;
import member.MemberDto;


public class MemberGetAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ActionForward forward = new ActionForward();
		AdminDao admindao = new AdminDao();
		
		request.setCharacterEncoding("utf-8");
		
		MemberDto dto = admindao.selectMemberById(request.getParameter("m_id"));
		if(dto == null) {
			forward.setRedirect(false);
			forward.setPath("/admin/error.jsp");
		} else {
			request.setAttribute("dto", dto);
			forward.setRedirect(false);
			forward.setPath("/admin/member_update.jsp");
		}
		
		return forward;
	}
}
