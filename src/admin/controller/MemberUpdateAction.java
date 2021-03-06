package admin.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.db.AdminDao;
import main.controller.Action;
import main.controller.ActionForward;
import member.MemberDto;


public class MemberUpdateAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		AdminDao adminrdao = new AdminDao();
		MemberDto memberDto = new MemberDto();
		
		request.setCharacterEncoding("utf-8");
		memberDto.setM_pwd(request.getParameter("m_pwd"));
		memberDto.setM_name(request.getParameter("m_name"));
		memberDto.setM_email(request.getParameter("m_email"));
		memberDto.setM_phone(request.getParameter("m_phone"));
		memberDto.setM_zipcode(request.getParameter("m_zipcode"));
		memberDto.setM_address(request.getParameter("m_address"));
		memberDto.setM_id(request.getParameter("m_id"));
		
		
		boolean isUpdateOk = adminrdao.updateMember(memberDto);
		if(isUpdateOk){ 
			forward.setRedirect(true);
			forward.setPath("/Camp_Project/admin/memberList.do");
		} else {
			forward.setRedirect(false);
			forward.setPath("/admin/error.jsp");
		}
		
		return forward;
	}
}
