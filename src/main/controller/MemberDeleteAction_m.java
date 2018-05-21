package main.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import member.MemberDto;
import member.MemberDao;


public class MemberDeleteAction_m implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		MemberDto memberdto = new MemberDto();
		MemberDao memberdao = new MemberDao();
		
		String id = request.getParameter("m_id"); 
				
		boolean isDeleteOk = memberdao.deleteMember(id);
		System.out.println("isDeleteOk :" + isDeleteOk);

		if(isDeleteOk) {
			
			session.removeAttribute("idKey"); 
			session.removeAttribute("gradeKey");
			

			forward.setRedirect(true);
			forward.setPath("../main.do");
			return forward;
			
		}else {
			response.setContentType("text/html; charset-euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert(\"탈퇴실패\");");
			out.println("window.location.href='/main/main.jsp'");
			out.println("</script>");
			out.close();
			
		}
		return null;
	}
}
