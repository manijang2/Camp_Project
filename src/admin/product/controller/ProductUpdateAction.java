package admin.product.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.db.AdminDao;
import main.controller.Action;
import main.controller.ActionForward;
import member.MemberDto;
import product.db.ProductDto;


public class ProductUpdateAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		AdminDao admindao = new AdminDao();
		ProductDto productDto = new ProductDto();
		
		request.setCharacterEncoding("utf-8");

		productDto.setP_name(request.getParameter("p_name"));
		productDto.setP_price(Integer.parseInt(request.getParameter("p_price")));
		productDto.setP_stock(Integer.parseInt(request.getParameter("p_stock")));
		productDto.setP_brand(request.getParameter("p_brand"));
		productDto.setP_origin(request.getParameter("p_origin"));
		productDto.setP_mileagerate(Float.parseFloat(request.getParameter("p_mileagerate")));
		productDto.setP_date(request.getParameter("p_date"));
		productDto.setP_shippingfee(Integer.parseInt(request.getParameter("p_shippingfee")));
		productDto.setP_sales(Integer.parseInt(request.getParameter("p_sales")));
		productDto.setP_image1(request.getParameter("p_image1"));
		productDto.setP_image2(request.getParameter("p_image2"));
		productDto.setP_image3(request.getParameter("p_image3"));
		productDto.setP_image4(request.getParameter("p_image4"));
		productDto.setP_info(request.getParameter("p_info"));
		productDto.setP_code(Integer.parseInt(request.getParameter("p_code")));
		
		boolean isUpdateOk = admindao.updateProduct(productDto);
		if(isUpdateOk){ 
			forward.setRedirect(true);
			forward.setPath("/Camp_Project/admin/productList.do");
		} else {
			forward.setRedirect(false);
			forward.setPath("/admin/error.jsp");
		}
		
		return forward;
	}
}
