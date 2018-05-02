package admin.product.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.db.AdminDao;
import main.controller.Action;
import main.controller.ActionForward;
import member.MemberDto;
import product.db.ProductDto;

public class ProductDeleteAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ActionForward forward = new ActionForward();		
		AdminDao admindao = new AdminDao();
		request.setCharacterEncoding("utf-8");
		
		boolean isDeleteOk = false;
		
		if(request.getParameter("p_code") != null) {
			ProductDto dto = new ProductDto();
			dto.setP_code(Integer.parseInt(request.getParameter("p_code")));
			
			isDeleteOk = admindao.deleteProduct(dto);
			
		}/* else if(request.getParameterValues("check") != null) {
			String[] values = request.getParameterValues("check");
			
			for (String id : values) {
				MemberDto memberDto = new MemberDto();
				memberDto.setM_id(id);
				
				isDeleteOk = admindao.deleteMember(memberDto);
				if(isDeleteOk == false){ 
					break;
				}
			}
		}*/
		

		if(isDeleteOk) {
			forward.setRedirect(true);
			forward.setPath("/Camp_Project/admin/productList.do");
		} else {
			forward.setRedirect(false);
			forward.setPath("/admin/error.jsp");
		}
		
		return forward;
	}
}
