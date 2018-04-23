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


public class MemberGetAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ActionForward forward = new ActionForward();
		MemberDao memberdao = new MemberDao();
		
		request.setCharacterEncoding("utf-8");
		
		MemberDto dto = new MemberDto();
		AdminDao dao = new AdminDao();

		dto.setM_pwd(request.getParameter("m_pwd"));
		dto.setM_name(request.getParameter("m_name"));
		dto.setM_email(request.getParameter("m_email"));
		dto.setM_phone(request.getParameter("m_phone"));
		dto.setM_zipcode(request.getParameter("m_zipcode"));
		dto.setM_address(request.getParameter("m_address"));
		//dto.setM_mileage(Integer.parseInt(request.getParameter("m_mileage")));
		dto.setM_grade(request.getParameter("m_grade"));
		//dto.setM_date(new Timestamp(Integer.parseInt(request.getParameter("m_date"))));
		dto.setM_id(request.getParameter("m_id"));
		
		String url = "/main.do"; 
		//관리자일 경우 관리자 페이지로 이동
		
		HttpSession session = request.getSession();
		
		String grade = (String)session.getAttribute("gradeKey"); 
		if(grade.equals("admin")) { //관리자 
			url = "/admin/memberUpdate.do"; 
		}
		
		forward.setRedirect(true);
		forward.setPath("/Camp_Project/admin/admin_member_update.jsp");
		
		/*
		//boolean isUpdateOk = dao.updateMember(dto);
		if(isUpdateOk) {
			forward.setRedirect(false);
			forward.setPath("/admin/admin_member_update_ok.jsp");
		} else {
			forward.setRedirect(false);
			forward.setPath("/admin/admin_member_update_no.jsp");
		}*/
		
		return forward;
	}
}
