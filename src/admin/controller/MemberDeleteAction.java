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
		AdminDao adminrdao = new AdminDao();
		request.setCharacterEncoding("utf-8");
		
		boolean isDeleteOk = false;
		
		if(request.getParameter("m_id") != null) {
			MemberDto memberDto = new MemberDto();
			memberDto.setM_id(request.getParameter("m_id"));
			
			isDeleteOk = adminrdao.deleteMember(memberDto);
			
		} else if(request.getParameterValues("check") != null) {
			String[] values = request.getParameterValues("check");
			
			for (String id : values) {
				MemberDto memberDto = new MemberDto();
				memberDto.setM_id(id);
				
				isDeleteOk = adminrdao.deleteMember(memberDto);
				if(isDeleteOk == false){ 
					break;
				}
			}
		}
		

		if(isDeleteOk) {
			forward.setRedirect(true);
			forward.setPath("/Camp_Project/admin/memberList.do");
		} else {
			forward.setRedirect(false);
			forward.setPath("/admin/error.jsp");
		}
		
		return forward;
	}
}
