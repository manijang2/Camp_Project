package admin.order.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.db.AdminDao;
import main.controller.Action;
import main.controller.ActionForward;
import member.MemberDto;
import order.db.OrderDto;
import product.db.ProductDto;


public class OrderUpdateAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		AdminDao admindao = new AdminDao();
		OrderDto orderDto = new OrderDto();
		
		request.setCharacterEncoding("utf-8");
		
		orderDto.setO_num(Integer.parseInt(request.getParameter("o_num")));
		orderDto.setO_pcode(Integer.parseInt(request.getParameter("o_pcode")));
		orderDto.setP_name(request.getParameter("p_name"));
		orderDto.setP_price(Integer.parseInt(request.getParameter("p_price")));
		orderDto.setO_quantity(Integer.parseInt(request.getParameter("o_quantity")));
		orderDto.setO_usemileage(Integer.parseInt(request.getParameter("o_usemileage")));
		orderDto.setO_shippingfee(Integer.parseInt(request.getParameter("o_shippingfee")));
		orderDto.setO_pay(Integer.parseInt(request.getParameter("o_pay")));
		orderDto.setO_status(request.getParameter("o_status"));
		orderDto.setO_date(request.getParameter("o_date"));
		orderDto.setO_id(request.getParameter("o_id"));
		orderDto.setO_mname(request.getParameter("o_mname"));
		orderDto.setO_phone(request.getParameter("o_phone"));
		orderDto.setO_zipcode(request.getParameter("o_zipcode"));
		orderDto.setO_address(request.getParameter("o_address"));
		orderDto.setO_message(request.getParameter("o_message"));
		
		System.out.println(orderDto);
		
		boolean isUpdateOk = admindao.updateOrder(orderDto);
		if(isUpdateOk){ 
			forward.setRedirect(true);
			forward.setPath("/Camp_Project/admin/orderList.do");
		} else {
			forward.setRedirect(false);
			forward.setPath("/admin/error.jsp");
		}
		
		return forward;
	}
}
