package admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.db.AdminDao;
import main.controller.Action;
import main.controller.ActionForward;
import member.MemberDto;


public class MemberAllGetAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ActionForward forward = new ActionForward();
		AdminDao admindao = new AdminDao();
		
		request.setCharacterEncoding("utf-8");
		
		List<MemberDto> list = admindao.selectAll();
		request.setAttribute("list", list);
		forward.setRedirect(false);
		forward.setPath("/admin/member_list.jsp");
		
		
		return forward;
	}
}
