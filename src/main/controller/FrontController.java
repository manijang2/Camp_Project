package main.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.controller.MemberAllGetAction;
import admin.controller.MemberCheckIDAction;
import admin.controller.MemberDeleteAction;
import admin.controller.MemberGetAction;
import admin.controller.MemberUpdateAction;
import admin.product.controller.ProductAllGetAction;
import admin.product.controller.ProductDeleteAction;
import admin.product.controller.ProductRegisterAction;
import admin.product.controller.ProductUpdateAction;
import board.controller.*;

public class FrontController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
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
			forward.setPath("/admin/main.jsp");	
		} else if(command.equals("/admin/memberList.do")) {
			action = new MemberAllGetAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
		} else if(command.equals("/admin/memberUpdate.do") && request.getMethod().equals("GET")) {
			action = new MemberGetAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/admin/memberUpdate.do") && request.getMethod().equals("POST")) {
			action = new MemberUpdateAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/admin/memberDelete.do") && request.getMethod().equals("POST")) {
			action = new MemberDeleteAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/admin/member_register.do") && request.getMethod().equals("GET")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin/admin_memberMgr.jsp");
			
		} else if(command.equals("/admin/memberUpdate.do")) {
			action = new MemberGetAction();
			forward.setPath("/admin/member_register.jsp");
			
			
		} else if(command.equals("/admin/member_register.do") && request.getMethod().equals("POST")) {
			action = new admin.controller.MemberRegisterAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/admin/member_id_check.do") && request.getMethod().equals("GET")) {
			action = new MemberCheckIDAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/admin/productList.do") && request.getMethod().equals("GET")) {
			action = new ProductAllGetAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/admin/productUpdate.do") && request.getMethod().equals("POST")) {
			action = new ProductUpdateAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/admin/productDelete.do") && request.getMethod().equals("POST")) {
			action = new ProductDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			  }
			} else if(command.equals("/admin/memberUpdate_Proc.do")) {
				action = new MemberUpdateAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/admin/productInsert.do") && request.getMethod().equals("POST")) {
			action = new ProductRegisterAction();
			
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
		
		else if(command.equals("/member/MemberRegisterValidIdAction.do")) {
			action=new MemberRegisterValidIdAction();
			try {
				forward = action.execute(request, response);
				System.out.println("forward 성공");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/member/MemberUpdateAction_m.do")) {
			action= new MemberUpdateAction_m();
			try {
				forward = action.execute(request, response);
				System.out.println("forward 성공");
			}catch (Exception e) {
				e.printStackTrace();
			}
					
		}else if(command.equals("/member/MemberDeleteAction_m.do")) {
			action= new MemberDeleteAction_m();
			try {
				forward = action.execute(request, response);
				System.out.println("forward 성공");
			}catch (Exception e) {
				e.printStackTrace();
			}
					
		}else if(command.equals("/member/MemberUpdateAction_pre.do")) {
			action= new MemberUpdateAction_pre();
			try {
				forward = action.execute(request, response);
				System.out.println("forward 성공");
			}catch (Exception e) {
				e.printStackTrace();
			}
					
		}
		
		
		/*------------------Member controller End------------------*/
		
		
		/*------------------Board controller Start----------------*/
		else if (command.equals("/BoardList.do")) {
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		/*------------------Board controller End----------------*/

		//장바구니 추가
		else if (command.equals("/cart_proc.do")) {
			//장바구니 상품 insert
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/cart/cart_proc.jsp");
			
		} else if (command.equals("/cart_delete.do")) {
			//장바구니 품목 삭제
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/cart/cart_delete.jsp");
			
		} else if (command.equals("/cart_update.do")) {
			//카트 - 주문 수량 변경버튼
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/cart/cart_update.jsp");
			
		} else if (command.equals("/cart_select_del.do")) {
			//장바구니 -카트 선택품목 삭제 하기 
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/cart/cart_select_del.jsp");
		
		} else if (command.equals("/order_one.do")) {
			//결제하기
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/order/order_one.jsp");
		
		} else if (command.equals("/order_proc.do")) {
			//결제하기
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/order/order_proc.jsp");
		
			/*(선택결제 미완료)*/
		} else if (command.equals("/cart_select_order.do")) {
			//선택결제하기
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/order/order_select.jsp");
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