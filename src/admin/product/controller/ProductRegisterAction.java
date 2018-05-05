package admin.product.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.db.AdminDao;
import main.controller.Action;
import main.controller.ActionForward;
import product.db.ProductDto;


public class ProductRegisterAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ActionForward forward = new ActionForward();
		ProductDto productdto = new ProductDto();
		AdminDao admindao = new AdminDao();
		
		request.setCharacterEncoding("utf-8");
		
		/*
		 * 
			<td class="td2"><input type="text" name="p_code" value="${newCode}" size="15" class="form-control input-sm" readonly/></td>
			<td class="td3"><input type="text" id="newName" name="p_name" size="60" class="form-control input-sm"/></td>
			<td class="td4"><input type="text" name="p_price" size="35" class="form-control input-sm"/></td>
			<td class="td4"><input type="text" name="p_brand" size="40" class="form-control input-sm"/></td>
			<td class="td4"><input type="text" name="p_origin" size="30" class="form-control input-sm"/></td>
			<td class="th2"><input type="text" name="p_stock" size="25" class="form-control input-sm"/></td>
			<td class="th2"><input type="text" name="p_sales" size="25" class="form-control input-sm"/></td>
			<td class="th2"><input type="text" name="p_mileagerate" size="25" class="form-control input-sm"/></td>
			<td class="td4"><input type="text" name="p_shippingfee" size="35" class="form-control input-sm"/></td>
			<td class="td4"><input type="text" name="p_date" size="35" class="form-control input-sm"/></td>		
			<td class="td4"><input type="text" name="p_cnum" size="25" class="form-control input-sm"/></td>
				<input type="hidden" name="p_image1" />
				<input type="hidden" name="p_image2" />
				<input type="hidden" name="p_image3" />
				<input type="hidden" name="p_image4" />
				<input type="hidden" name="p_info" />
		 * 
		 * */
		
		productdto.setP_code(Integer.parseInt(request.getParameter("p_code")));
		productdto.setP_name(request.getParameter("p_name"));
		productdto.setP_price(Integer.parseInt(request.getParameter("p_price")));
		productdto.setP_brand(request.getParameter("p_brand"));
		productdto.setP_origin(request.getParameter("p_origin"));
		productdto.setP_stock(Integer.parseInt(request.getParameter("p_stock")));
		productdto.setP_sales(Integer.parseInt(request.getParameter("p_sales")));
		productdto.setP_mileagerate(Float.parseFloat(request.getParameter("p_mileagerate")));
		productdto.setP_shippingfee(Integer.parseInt(request.getParameter("p_shippingfee")));
		productdto.setP_date(request.getParameter("p_date"));
		productdto.setP_cnum(Integer.parseInt(request.getParameter("p_cnum")));
		
		boolean isInsert = admindao.insertProduct(productdto);
		if(isInsert) {
			forward.setRedirect(true);
			forward.setPath("/Camp_Project/admin/productList.do");
		} else {
			forward.setRedirect(false);
			forward.setPath("/admin/error.jsp");
		}
		
		return forward;
	}
}
