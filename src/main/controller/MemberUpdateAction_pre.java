package main.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import member.MemberDto;
import member.MemberDao;


public class MemberUpdateAction_pre implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		MemberDto memberdto = new MemberDto();
		MemberDao memberdao = new MemberDao();
		
		String id = request.getParameter("m_id"); 
				
		memberdto=memberdao.selectMemberById(id);
		
		if(memberdto!=null) {
			
			System.out.println("#####id : "+id);
			request.setAttribute("member",memberdto);
			
			forward.setRedirect(false);
			forward.setPath("/member/member_update.jsp");
			
			return forward;
			
		}else {
			response.setContentType("text/html; charset-euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert(\"로딩실\");");
			out.println("window.location.href='/main/main.jsp'");
			out.println("</script>");
			out.close();
			
		}
		return null;
	}
}