package main.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.controller.MemberGetAction;
import product.db.ProductDao;
import product.db.ProductDto;

public class FrontController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		System.out.println(RequestURI);
		String contextPath = request.getContextPath();
		System.out.println(contextPath);
		String command = RequestURI.substring(contextPath.length());
		System.out.println(command);
		ActionForward forward = null;
		Action action = null;

		System.out.println("command : " + command);

		if (command.equals("/main.do")) {
			
			action = new MainAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}  else if(command.equals("/admin/main.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/admin_main.jsp");
			
		} else if(command.equals("/admin/memberMgr.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/admin_memberMgr.jsp");
		} else if(command.equals("/admin/memberUpdate.do")) {
			action = new MemberGetAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if (command.equals("/p_read.do")) {
			//상품 상세보기 페이지
			action = new ProductController();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/p_bestlist.do")) {
			//베트스 상품 모음전
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/product/p_bestlist.jsp");
			
		} else if (command.equals("/p_newlist.do")) {
			//신상품 모음전
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/product/p_newlist.jsp");
			
		}  else if (command.equals("/p_cate1.do")) {
			//상품 카테고리 1
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/product/p_cate1.jsp");
			
		}  else if (command.equals("/p_cate2.do")) {
			//상품 카테고리 2
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/product/p_cate2.jsp");

		}
		
		/*------------------Member controller Start------------------*/
		else if(command.equals("/member/MemberLoginAction.do")) {
			action = new MemberLoginAction();
			try {
				forward = action.execute(request, response);
				System.out.println("forward 성공");
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		
		else if(command.equals("/member/MemberRegisterAction.do")) {
			action= new MemberRegisterAction();
			try {
				forward = action.execute(request, response);
				System.out.println("forward 성공");
			}catch (Exception e) {
				e.printStackTrace();
			}
					
		}
		
		/*------------------Member controller End------------------*/

		//장바구니 추가
		else if (command.equals("/cart_proc.do")) {
			//베트스 상품 모음전
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/cart/cart_proc.jsp");
		}
		
		
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
}