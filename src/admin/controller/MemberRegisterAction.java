package admin.controller;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.db.AdminDao;
import main.controller.Action;
import main.controller.ActionForward;
import member.MemberDto;
import member.MemberDao;


public class MemberRegisterAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ActionForward forward = new ActionForward();
		MemberDto memberdto = new MemberDto();
		AdminDao admindao = new AdminDao();
		
		request.setCharacterEncoding("utf-8");
		
		memberdto.setM_id(request.getParameter("m_id"));
		memberdto.setM_pwd(request.getParameter("m_pwd"));
		memberdto.setM_name(request.getParameter("m_name"));
		memberdto.setM_email(request.getParameter("m_email"));
		memberdto.setM_phone(request.getParameter("m_phone"));
		memberdto.setM_zipcode(request.getParameter("m_zipcode"));
		memberdto.setM_address(request.getParameter("m_address"));
		memberdto.setM_grade(request.getParameter("m_grade"));
		memberdto.setM_mileage(0);
		memberdto.setM_date(new Timestamp(System.currentTimeMillis()));
		
		boolean isInsert = admindao.insertMember(memberdto);
		if(isInsert) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입에 성공하였습니다.');");
			out.println("location.href='/admin/member_list.do';");
			out.println("</script>");
			out.close();
		} else {
			forward.setRedirect(true);
			forward.setPath("/admin/error.jsp");
		}
		
		return forward;
	}
}
