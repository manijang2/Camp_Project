package admin.product.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.db.AdminDao;
import main.controller.Action;
import main.controller.ActionForward;
import member.MemberDto;
import product.db.ProductDto;


public class ProductAllGetAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ActionForward forward = new ActionForward();
		AdminDao admindao = new AdminDao();
		
		request.setCharacterEncoding("utf-8");
		
		List<ProductDto> list = admindao.productAll();
		request.setAttribute("list", list);
		if(request.getParameter("newInput").equals("y")) {
			int newCode = admindao.getNewCode();
			request.setAttribute("newCode", newCode);
		}
		forward.setRedirect(false);
		forward.setPath("/admin/product_list.jsp");
		
		return forward;
	}
}
