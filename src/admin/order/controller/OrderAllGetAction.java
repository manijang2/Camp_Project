package admin.order.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.db.AdminDao;
import main.controller.Action;
import main.controller.ActionForward;
import member.MemberDto;
import order.db.OrderDto;
import product.db.ProductDto;


public class OrderAllGetAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ActionForward forward = new ActionForward();
		AdminDao admindao = new AdminDao();
		
		request.setCharacterEncoding("utf-8");
		
		List<Integer> orderNums = admindao.selectOrderNum();
		List<OrderDto> list = new ArrayList<OrderDto>();
		for(Integer n : orderNums) {
			OrderDto dto = (OrderDto)admindao.selectOrder(n);
			System.out.println(dto);
			list.add(dto);
		}
		
		request.setAttribute("orderNums", orderNums);
		request.setAttribute("list", list);

		forward.setRedirect(false);
		forward.setPath("/admin/admin_orderMgr.jsp");
		
		return forward;
	}
}
