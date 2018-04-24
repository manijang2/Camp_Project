package main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDao;
import member.MemberDto;

public class MemberRegisterValidIdAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ActionForward forward = new ActionForward();
		MemberDao memberdao = new MemberDao();
		
		String m_id = request.getParameter("m_id");
		boolean check=memberdao.checkId(m_id);
		request.setAttribute("id", m_id);
		request.setAttribute("check", check);
		forward.setPath("member_registerValidId.jsp");
			
		
		
		return forward;
	}
	}

