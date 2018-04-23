package admin.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.db.AdminDao;
import main.controller.Action;
import main.controller.ActionForward;
import member.MemberDto;


public class MemberDeleteAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		AdminDao adminrdao = new AdminDao();
		MemberDto memberDto = new MemberDto();
		
		request.setCharacterEncoding("utf-8");
		
		memberDto.setM_id(request.getParameter("m_id"));
		
		boolean isDeleteOk = adminrdao.deleteMember(memberDto);
		if(isDeleteOk){ 
			forward.setPath("/admin/member_list.jsp");
		} else {
			forward.setPath("/admin/error.jsp");
		}
		
		return forward;
	}
}
