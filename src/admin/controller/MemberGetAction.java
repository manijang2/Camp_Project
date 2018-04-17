package admin.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.controller.Action;
import main.controller.ActionForward;
import member.MemberDto;
import member.MemberDao;


public class MemberGetAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ActionForward forward = new ActionForward();
		MemberDao memberdao = new MemberDao();
		
		String id = request.getParameter("m_id"); 
		MemberDto dto = memberdao.selectMemberById(id);
		System.out.println(dto);
		if(dto != null) {
			forward.setRedirect(false);
			forward.setPath("/admin/admin_member_update.jsp");
			request.setAttribute("dto", dto);
			return forward;
		}	
		
		return null;
	}
}
