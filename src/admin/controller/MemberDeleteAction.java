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
		
		
		request.setCharacterEncoding("utf-8");
		
		if(request.getParameter("m_id") != null) {
			MemberDto memberDto = new MemberDto();
			memberDto.setM_id(request.getParameter("m_id"));
			
			boolean isDeleteOk = adminrdao.deleteMember(memberDto);
			if(isDeleteOk){ 
				forward.setPath("/admin/member_list.jsp");
			} else {
				forward.setPath("/admin/error.jsp");
			}
		} else if(request.getParameterValues("check") != null) {
			String[] values = request.getParameterValues("check");
			
			boolean isDeleteOk = false;
			for (String id : values) {
				MemberDto memberDto = new MemberDto();
				memberDto.setM_id(id);
				
				isDeleteOk = adminrdao.deleteMember(memberDto);
				if(isDeleteOk == false){ 
					break;
				}
			}
			
			if(isDeleteOk) {
				forward.setPath("/admin/member_list.jsp");
			} else {
				forward.setPath("/admin/error.jsp");
			}
		}
		
		
		return forward;
	}
}
