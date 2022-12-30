package com.group6.tibame104.groupdiscount.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group6.tibame104.groupdiscount.model.GroupdiscountService;
import com.group6.tibame104.groupdiscount.model.GroupdiscountVO;
@WebServlet("/front-end/groupdiscount/Groupdiscount.do")
public class GroupdiscountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GroupdiscountServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("countTableID");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入折扣表編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupdiscount/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer countTableID = null;
			try {
				countTableID = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("折扣表格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupdiscount/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			GroupdiscountService groupdiscountSvc = new GroupdiscountService();
			GroupdiscountVO groupdiscountVO = groupdiscountSvc.getByPK(countTableID);

			if (groupdiscountVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/grouporder/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("groupdiscountVO", groupdiscountVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/groupdiscount/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
		
		//用groupBuyID查所有折扣表
		if ("getGroup_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("groupBuyID");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入折扣表編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupdiscount/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer groupBuyID = null;
			try {
				groupBuyID = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("折扣表格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupdiscount/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			GroupdiscountService groupdiscountSvc = new GroupdiscountService();
			List<GroupdiscountVO> groupdiscountVO = groupdiscountSvc.getAllByGroupBuyID(groupBuyID);

			if (groupdiscountVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/grouporder/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("groupdiscountVO", groupdiscountVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/groupdiscount/listAllGroup.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer countTableID = Integer.valueOf(req.getParameter("countTableID"));

			/*************************** 2.開始查詢資料 ****************************************/
			GroupdiscountService groupdiscountSvc = new GroupdiscountService();
			GroupdiscountVO groupdiscountVO = groupdiscountSvc.getByPK(countTableID);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("groupdiscountVO", groupdiscountVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/groupdiscount/update_discount.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer countTableID = Integer.valueOf(req.getParameter("countTableID").trim());
			Integer groupBuyID = Integer.valueOf(req.getParameter("groupBuyID").trim());
			Integer groupBuyProductOrderTotal = Integer.valueOf(req.getParameter("groupBuyProductOrderTotal").trim());
			Double groupBuyCount = Double.valueOf(req.getParameter("groupBuyCount").trim());
			
			GroupdiscountVO groupdiscountVO = new GroupdiscountVO();
						
			groupdiscountVO.setCountTableID(countTableID);
			groupdiscountVO.setGroupBuyID(groupBuyID);
			groupdiscountVO.setGroupBuyProductOrderTotal(groupBuyProductOrderTotal);
			groupdiscountVO.setGroupBuyCount(groupBuyCount);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("groupdiscountVO", groupdiscountVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/update_emp_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			GroupdiscountService groupdiscountSvc = new GroupdiscountService();
			groupdiscountVO = groupdiscountSvc.updateGroupdiscount(countTableID, groupBuyID, groupBuyProductOrderTotal,groupBuyCount);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("groupdiscountVO", groupdiscountVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/groupdiscount/listAllGroupDiscount.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		
			if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

				
				Integer groupBuyID = Integer.valueOf(req.getParameter("groupBuyID").trim());
				Integer groupBuyProductOrderTotal = Integer.valueOf(req.getParameter("groupBuyProductOrderTotal").trim());
				Double groupBuyCount = Double.valueOf(req.getParameter("groupBuyCount").trim());
				

				GroupdiscountVO groupdiscountVO = new GroupdiscountVO();
				
				
				groupdiscountVO.setGroupBuyID(groupBuyID);
				groupdiscountVO.setGroupBuyProductOrderTotal(groupBuyProductOrderTotal);
				groupdiscountVO.setGroupBuyCount(groupBuyCount);
				
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
	req.setAttribute("groupdiscountVO", groupdiscountVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/groupdiscount/addEmp.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					GroupdiscountService groupdiscountSvc = new GroupdiscountService();
					groupdiscountVO = groupdiscountSvc.addGroupdiscount(groupBuyID, groupBuyProductOrderTotal,groupBuyCount);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/back-end/groupdiscount/listAllGroupDiscount.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
			}
			if ("delete".equals(action)) { // 來自listAllEmp.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
		
					/***************************1.接收請求參數***************************************/
					Integer countTableID = Integer.valueOf(req.getParameter("countTableID"));
					
					/***************************2.開始刪除資料***************************************/
					GroupdiscountService groupdiscountSvc = new GroupdiscountService();
					groupdiscountSvc.deleteGroupdiscount(countTableID);
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
					String url = "/back-end/groupdiscount/listAllGroupDiscount.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
			}
	}
	

}
