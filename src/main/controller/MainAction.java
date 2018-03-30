package main.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.ProductDao;
import product.ProductDto;

public class MainAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductDao productDao = new ProductDao();
		ArrayList<ProductDto> list = (ArrayList) productDao.productAll();

		request.setAttribute("list", list);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/main/main.jsp");
		return forward;
	}
}