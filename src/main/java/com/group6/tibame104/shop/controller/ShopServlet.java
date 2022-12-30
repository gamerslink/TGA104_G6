package com.group6.tibame104.shop.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.group6.tibame104.order.model.OrderService;
import com.group6.tibame104.order.model.OrderVO;
import com.group6.tibame104.orderlist.model.OrderlistVO;
import com.group6.tibame104.orderlist.model.Product;
import com.group6.tibame104.product.model.ProductVO;
import com.group6.tibame104.product.service.ProductService;

//import redis.clients.jedis.Jedis;

//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;

//import redis.clients.jedis.JedisPool;

//@WebServlet("/front-end/shop/ShopServlet")
@Component
@WebServlet("/front-end/shop/ShopServlet")
public class ShopServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Autowired
	OrderService ordsvc;
	@Autowired
	CategoryService categorySvc;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		@SuppressWarnings("unchecked")
//		List<Product> buylist = (Vector<Product>) session.getAttribute("check");
		Map<Integer, List<Product>> check = (Map<Integer, List<Product>>)session.getAttribute("check");
		
		String action = req.getParameter("action");
		Integer count_string = (Integer) session.getAttribute("count_num");
		int count_num;
		if(count_string == null)
			count_num = 0;
		else
			count_num = count_string;

		if (!action.equals("CHECKOUT")) {
			
			// 新增書籍至購物車中
			 if (action.equals("ADD")) {

				// 取得後來新增的書籍
				Product product = getProduct(req);
				Integer storeID = product.getStoreID();
				


				if (check==null) {
					
					List list = new ArrayList();
					list.add(product);
					
					check = new HashMap<Integer, List<Product>>();
					check.put(storeID, list);
					count_num++;

				} else {
					if(check.get(storeID)==null) {
						List<Product> buylist = new ArrayList<Product>();
						buylist.add(product);
						check.put(storeID, buylist);
						count_num++;
					} else {
						
					List<Product> buylist = check.get(storeID);
					
					if (buylist.contains(product)) {

						Product innerProduct = buylist.get(buylist.indexOf(product));
						innerProduct.setQuantity(innerProduct.getQuantity() + product.getQuantity());

					} else {
						buylist.add(product);
						check.put(storeID, buylist);
						count_num++;
				
					}
				  }
				}

			
			}
	
			session.setAttribute("check", check);
			session.setAttribute("count_num", count_num);
			CategoryVO categoryVO =categorySvc.getbyProductID(Integer.parseInt(req.getParameter("productID")));		
			req.setAttribute("categoryVO", categoryVO);
			
			if(req.getParameter("method").equals("bag")) {
				String url = "/front-end/shop/Cart_new.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
			}
			if(req.getParameter("method").equals("cart")) {

				
				String url = "/front-end/product_detail/product_detail.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
			}
			
		}
		

		// 結帳，計算購物車書籍價錢總數
		if (action.equals("CHECKOUT")) {
			
//			List<OrderVO> orderVO_list = new ArrayList();
			Map<OrderVO,List<OrderlistVO>> orderVO_list = new HashMap<OrderVO,List<OrderlistVO>>();
			
			Set<Integer> set = check.keySet();
			Iterator<Integer> it = set.iterator();
			
			while (it.hasNext()) {
			int total = 0;
			
			List<OrderlistVO> orderlist = new ArrayList<OrderlistVO>();
			Integer storeID = it.next();
			List<Product> buylist = check.get(storeID);
			
			
			Map<String, String[]> map = req.getParameterMap();
			String[] valuelist = map.get("check"+storeID);
			System.out.println(valuelist[0]);
			
			
			for (int i = 0; i < valuelist.length; i++) {
				if(valuelist[i].equals("1")) {
					Product order = buylist.get(i);
					String name = order.getName();
					Integer price = order.getPrice();
					String str = req.getParameter("product"+order.getStoreID()+i);
					Integer quantity = Integer.parseInt(str);
					Integer productID = order.getProductID();
					String userAccount = req.getParameter("userAccount");
				
					total += (price * quantity);
				
//				double total1 = buylist.stream()
//								      .mapToDouble(b -> b.getPrice()*b.getQuantity())
//								      .sum();

				
					OrderlistVO list = new OrderlistVO(); //產生訂單明細物件

					list.setProductID(productID);
					list.setProductName(name);
					list.setPrice(price);
					list.setQuantity(quantity);
					list.setSubTotal(price*quantity);
					list.setUserAccount(userAccount);


					orderlist.add(list);
				}
		
			 }
			
			
			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);

			OrderVO ordervo = new OrderVO(); //產生訂單物件
			
			Integer memberID = Integer.parseInt( req.getParameter("memberID") );
			
			String str = req.getParameter("storeID"+storeID.toString());
			Integer storeid = Integer.parseInt(str);
			String storeName = req.getParameter("storeName"+str);
			String receiver = req.getParameter("receiver"+str);
			String phone = req.getParameter("phone"+str);
			String address = req.getParameter("address"+str);
			String paytype = req.getParameter("paytype"+str);

			Integer useShoppingGold = Integer.valueOf(req.getParameter("useShoppingGold"+str));
			Double count = Double.valueOf(req.getParameter("couponID"+str));
			

	      

			ordervo.setMemberID(memberID);
			ordervo.setStoreID(storeid);
			ordervo.setStoreName(storeName);
			ordervo.setCreditcardNumber("10");
			ordervo.setPayType(paytype);
			ordervo.setReceiver(receiver);
			ordervo.setOrderStatus(2);
			ordervo.setPhone(phone);
			ordervo.setAddress(address);
			
			ordervo.setOriginalTotal(total);
			ordervo.setCouponID(1);
			ordervo.setUseCouponGold((int) (total * (1 - count)));
			ordervo.setUseShoppingGold(useShoppingGold);
			ordervo.setFinalTotal((int) (total * count - useShoppingGold));
			
			int orderID = ordsvc.addOrder(ordervo, orderlist);
			OrderVO order =ordsvc.getOrder(orderID);
		
			
			orderVO_list.put(order, orderlist);


			
			
		
			
//			session.setAttribute("orderVO"+str, ordervo);
			
			
			
			}
			
			session.setAttribute("orderVO_list", orderVO_list);
			
			
//			OrderlistService orderlistService = new OrderlistService();
//			orderlistService.addOrderlist(orderlist);
		
			
			

			String url = "/front-end/shop/Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);

			session.removeAttribute("check");
			session.removeAttribute("count_num");

//			receiver:input, phone:input, address:input
//			creditcard:信用卡, atm: atm轉帳, arrive:貨到付款, coupon:85 75, 

//			ordervo.setCreditcardNumber(paytype);

//			ordervo.setMemberID(null);

//			ordervo.setOriginalTotal(null);
//			ordervo.setUseShoppingGold(null);
//			ordervo.setCouponGold();
//			ordervo.setFinalTotal(null);

		}
	}

	private Product getProduct(HttpServletRequest req) {
//		req.getAttribute(name);
//		String memberID = req.getParameter("memberID");
		String quantity = req.getParameter("quantity");
		String name = req.getParameter("name");
		String price = req.getParameter("price");
		String productID = req.getParameter("productID");
		String storeID = req.getParameter("storeID");
		String storeName = req.getParameter("storeName");
//		String storeID = req.getParameter("storeID");

		Product product = new Product();

		product.setProductID(Integer.parseInt(productID));
		product.setName(name);
		product.setPrice(new Integer(price));
		product.setQuantity((new Integer(quantity)).intValue());
		product.setStoreID(Integer.parseInt(storeID));
		product.setStoreName(storeName);
		return product;
	}
	

//	static void addCart(Long userID, Long productID, int num) {
//		Jedis jedis = new Jedis("localhost",6379);
//		String product = jedis.hget("cart" + userID, String.valueOf(productID));
//		if(product!=null || !"".equals(product)) {
//			jedis.hincrBy("cart:"+userID, String.valueOf(productID), num);
//		} else {
//			jedis.hset("cart:"+userID, String.valueOf(productID), String.valueOf(num));
//		}
//		
//		jedis.close();
//	}

}
