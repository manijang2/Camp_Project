package main.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import member.MemberDto;
import member.MemberDao;


public class MemberLoginAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		MemberDto memberdto = new MemberDto();
		MemberDao memberdao = new MemberDao();
		
		String id = request.getParameter("m_id"); 
		String pwd = request.getParameter("m_pwd"); 
		
		System.out.println("id, pwd :" + id + " " + pwd);
		
		boolean isLoginOk = memberdao.checkLogin(id, pwd);
		
		System.out.println("isLoginOk :" + isLoginOk);

		if(isLoginOk) {
			memberdto = memberdao.selectMemberById(id);
			
			session.setAttribute("idKey", memberdto.getM_id()); 
			session.setAttribute("gradeKey", memberdto.getM_grade());
			
			System.out.println("id :" + session.getAttribute("idKey"));

			forward.setRedirect(true);
			forward.setPath("../main.do");
			return forward;
			
		}else {
			response.setContentType("text/html; charset-euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert(\"pwd or id is not correct\");");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
		}
		return null;
	}
}
