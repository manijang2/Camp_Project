package main.controller;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import member.MemberDto;
import member.MemberDao;


public class MemberUpdateAction_m  implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		MemberDto memberdto = new MemberDto();
		MemberDao memberdao = new MemberDao();
		boolean isPwd =false;
		
		request.setCharacterEncoding("utf-8");
		
		memberdto.setM_id(request.getParameter("m_id"));
		memberdto.setM_pwd(request.getParameter("m_pwd"));
		memberdto.setM_name(request.getParameter("m_name"));
		memberdto.setM_email(request.getParameter("m_email"));
		memberdto.setM_phone(request.getParameter("m_phone"));
		memberdto.setM_zipcode(request.getParameter("m_zipcode"));
		memberdto.setM_address(request.getParameter("m_address"));
		
		isPwd = memberdao.checkLogin(request.getParameter("m_id"), request.getParameter("m_pwd"));
		
		
		memberdao.updateMember(memberdto);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('회원정보수정에 성공하였습니다.');");
		out.println("location.href='../main.do';");
		out.println("</script>");
		out.close();
		
		

		return forward;
	}

}
