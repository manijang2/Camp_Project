package main.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.db.ProductDao;
import product.db.ProductDto;

public class ProductController implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
			request.setCharacterEncoding("euc-kr");

			ProductDto productdto = new ProductDto();
			ProductDao productdao = new ProductDao();
			
			//상품 번호값 받아옴
			int p_code = Integer.parseInt(request.getParameter("p_code"));
			System.out.println(p_code);	
			ActionForward forward = new ActionForward();
			System.out.println(forward);
			forward.setRedirect(true);
			forward.setPath("/Camp_Project/product/p_read.jsp?p_code="+p_code);

			return forward;
	}

}