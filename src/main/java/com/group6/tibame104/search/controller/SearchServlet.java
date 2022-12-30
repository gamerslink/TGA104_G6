package com.group6.tibame104.search.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.group6.tibame104.category.model.CategoryService;
import com.group6.tibame104.category.model.CategoryVO;
import com.group6.tibame104.orderlist.model.OrderlistService;
import com.group6.tibame104.orderlist.model.OrderlistVO;
import com.group6.tibame104.product.model.ProductVO;
import com.group6.tibame104.product.service.ProductService;

@Component
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private OrderlistService orderlistSvc;
	@Autowired
	private CategoryService categorySvc;
	
	
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req,res);
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		    req.setCharacterEncoding("UTF-8");
		    res.setContentType("text/html; charset=UTF-8");
		    
		    String action = req.getParameter("action");
		    HttpSession session = req.getSession();
		
		if("getAll_For_Display".equals(action)) {

		    Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*
			 * 商品名稱取值
			 */
			String productName = req.getParameter("productName");
			List<CategoryVO> categoryVOall;
			

			/*
			 * 搜尋 商品名稱 為空 全部 不為空 有條件
			 */
			if (productName.trim().length() == 0) {
				categoryVOall = categorySvc.getAll();
			} else {
				
				categoryVOall = categorySvc.getbyProductName(productName) ;
			}

			/*
			 * 如果報錯 轉去 addProduct 頁面
			 */
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/addProduct.jsp");
				try {
					failureView.forward(req, res);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
				return;
			}

			/*
			 * 轉去 listAllProduct 頁面
			 */
			session.removeAttribute("categoryVOall_forsort");
			session.setAttribute("categoryVOall", categoryVOall);
			String url = "/front-end/product_detail/productList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
		if("getOne_For_Display".equals(action)) {
			
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String str = req.getParameter("productID");
			
			Integer productID = Integer.valueOf(str);

			CategoryVO categoryVO = categorySvc.getbyProductID(productID);
			List<OrderlistVO> list = orderlistSvc.findByProductID(productID);
			/*
			 * 導向 listOneProduct頁面
			 */
			req.setAttribute("categoryVO", categoryVO);
			
			session.setAttribute("list", list);

			String url = "/front-end/product_detail/product_detail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("category".equals(action)) {

		    Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*
			 * 商品名稱取值
			 */
			Integer productMainID = Integer.valueOf(req.getParameter("productMainID"));
			
			
			List<CategoryVO> categoryVOall = categorySvc.getbyProductMainID(productMainID);
			List<CategoryVO> all = categorySvc.getAll();

		


			/*
			 * 轉去 listAllProduct 頁面
			 */
			session.removeAttribute("categoryVOall_forsort");
			session.setAttribute("all", all);
			session.setAttribute("categoryVOall", categoryVOall);
			String url = "/front-end/product_detail/productList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}


}
