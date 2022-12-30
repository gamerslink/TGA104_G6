<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="java.util.* ,com.group6.tibame104.orderlist.model.Product"%>

<%
Map<Integer, List<Product>> check = (Map<Integer, List<Product>>) session.getAttribute("check");
Integer count_num = (Integer) session.getAttribute("count_num");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>SYM</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="eCommerce HTML Template Free Download" name="keywords" />
<meta content="eCommerce HTML Template Free Download" name="description" />

<!-- Favicon -->
<link
	href="${pageContext.request.contextPath}/front-end/product_detail/img/logoSYM.jpg"
	rel="icon" />

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400|Source+Code+Pro:700,900&display=swap"
	rel="stylesheet" />

<!-- CSS Libraries -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/front-end/product_detail/lib/slick/slick.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/front-end/product_detail/lib/slick/slick-theme.css"
	rel="stylesheet" />

<!-- Template Stylesheet -->
<link
	href="${pageContext.request.contextPath}/front-end/product_detail/css/style.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/front-end/product_detail/css/woody.css"
	rel="stylesheet" />
</head>

<body>
	<!-- Top bar Start -->
	<!-- <div class="top-bar">
      <div class="container-fluid">
        <div class="row">
          <div class="col-sm-6">
            <i class="fa fa-envelope"></i>
            support@email.comh
          </div>
          <div class="col-sm-6">
            <i class="fa fa-phone-alt"></i>
            +012-345-6789
          </div>
        </div>
      </div>
    </div> -->
	<!-- Top bar End -->

	<!-- Nav Bar Start -->
	<div class="nav">
		<div class="container-fluid">
			<nav class="navbar navbar-expand-md bg-dark navbar-dark">
				<a href="#" class="navbar-brand">MENU</a>
				<button type="button" class="navbar-toggler" data-toggle="collapse"
					data-target="#navbarCollapse">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse justify-content-between"
					id="navbarCollapse">
					<div class="navbar-nav mr-auto">
						<a href="index.html" class="nav-item nav-link">首頁</a> <a
							href="product-list.html" class="nav-item nav-link">我的賣場</a>

						<div class="nav-item dropdown">
							<a href="#" class="nav-link " data-toggle="dropdown">客服中心</a>


						</div>
					</div>
				</div>
				<div class="navbar-nav ml-auto">
					<div class="nav-item dropdown">
						<!-- 登入前 -->
						<a href="#" class="nav-link dropdown-toggle"
							data-toggle="dropdown"> 登入/註冊</a>
						<div class="dropdown-menu">
							<a href="#" class="dropdown-item">登入</a> <a href="#"
								class="dropdown-item">註冊</a>
						</div>
						<!-- 登入後 -->
						<!-- <a
                    href="#"
                    class="nav-link dropdown-toggle"
                    data-toggle="dropdown"
                    >
                    <img
                        class="rounded-circle "
                        src="img/account.jpg"
                        alt=""
                        style="width: 40px; height: 40px"
                      />
                    帳號名稱</a
                  >
                  <div class="dropdown-menu">
                    <a href="#" class="dropdown-item">我的帳號</a>
                    <a href="#" class="dropdown-item">註冊</a>
                  </div> -->
					</div>
				</div>
		</div>
		</nav>
	</div>
	</div>
	<!-- Nav Bar End -->

	<!-- Bottom Bar Start -->
	<div class="bottom-bar">
		<div class="container-fluid">
			<div class="row align-items-center">
				<div class="col-md-3">
					<div class="logo">
						<a href="index.html"> <img
							src="${pageContext.request.contextPath}/front-end/product_detail/img/logoSYM.jpg"
							alt="Logo" />
						</a>
					</div>
				</div>
				<div class="col-md-6">
					<div class="search">
						<FORM METHOD="post"
							ACTION="${pageContext.request.contextPath}/SearchServlet">
							<input type="hidden" name="action" value="getAll_For_Display" />
							<input type="text" placeholder="商品搜尋" name="productName" />
							<button>
								<i class="fa fa-search"></i>
							</button>
						</FORM>
					</div>
				</div>
				<div class="col-md-3">
					<div class="user">
						<!-- <a href="wishlist.html" class="btn wishlist">
                  <i class="fa fa-heart"></i>
                  <span>(0)</span>
                </a> -->

						<a
							href="${pageContext.request.contextPath}/front-end/shop/Cart_new.jsp"
							class="btn cart"> <i class="fa fa-shopping-cart"></i> <span>(<%=(count_num == null) ? "0" : count_num%>)
						</span>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Bottom Bar End -->

	<!-- Breadcrumb Start -->
	<div class="breadcrumb-wrap">
		<div class="container-fluid">
			<ul class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">${categoryVO.productMainName}</a></li>
				<li class="breadcrumb-item"><a href="#">${categoryVO.productSecName}</a></li>
				<li class="breadcrumb-item active">${categoryVO.productName}</li>
			</ul>
		</div>
	</div>
	<!-- Breadcrumb End -->

	<!-- Product Detail Start -->
	<div class="product-detail">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-8">
					<div class="product-detail-top">
						<div class="row align-items-center">
							<div class="col-md-5">
								<div class="product-slider-single normal-slider">
									<img
										src="${pageContext.request.contextPath}/product/picServlet?productID=${categoryVO.productID}"
										alt="Product Image"> <img
										src="${pageContext.request.contextPath}/product/picServlet2?productID=${categoryVO.productID}"
										alt="Product Image"> <img
										src="${pageContext.request.contextPath}/product/picServlet3?productID=${categoryVO.productID}"
										alt="Product Image">
								</div>
								<div class="product-slider-single-nav normal-slider">
									<div class="slider-nav-img">
										<img
											src="${pageContext.request.contextPath}/product/picServlet?productID=${categoryVO.productID}"
											alt="Product Image">
									</div>
									<div class="slider-nav-img">
										<img
											src="${pageContext.request.contextPath}/product/picServlet2?productID=${categoryVO.productID}"
											alt="Product Image">
									</div>
									<div class="slider-nav-img">
										<img
											src="${pageContext.request.contextPath}/product/picServlet3?productID=${categoryVO.productID}"
											alt="Product Image">
									</div>

								</div>
							</div>
							<div class="col-md-7">
								<div class="product-content">
									<div class="title">
										<h2>${categoryVO.productName}</h2>
									</div>
									<p class="previewing" style='display: none'>${categoryVO.commentAvgStar}</p>
									<div class="ratting">
										<i class="fa fa-star" name="fa" style='display: none'></i> <i
											class="fa fa-star" name="fa" style='display: none'></i> <i
											class="fa fa-star" name="fa" style='display: none'></i> <i
											class="fa fa-star" name="fa" style='display: none'></i> <i
											class="fa fa-star" name="fa" style='display: none'></i>
									</div>
									<div class="price">
										<h4>價格:</h4>
										<p>${categoryVO.productPrice}</p>
									</div>


									<FORM METHOD="post"
										ACTION="${pageContext.request.contextPath}/front-end/shop/ShopServlet">

										<div class="quantity">
											<h4>數量:</h4>
											<div class="qty">
												<button type="button" class="btn-minus">
													<i class="fa fa-minus"></i>
												</button>
												<input type="text" id="quantity" name="quantity" value="1">
												<button type="button" class="btn-plus">
													<i class="fa fa-plus"></i>
												</button>
											</div>
										</div>
										<div class="quantity">
											<h4>庫存:</h4>
											<h4>${categoryVO.productStock}</h4>

										</div>
										<div class="action">





											<input type="hidden" name="name"
												value="${categoryVO.productName}" /> <input type="hidden"
												name="storeName" value="${categoryVO.storeName}" /> <input
												type="hidden" name="quantity"
												value="${categoryVO.productName}" /> <input type="hidden"
												name="price" value="${categoryVO.productPrice}" /> <input
												type="hidden" name="productID"
												value="${categoryVO.productID}" /> <input type="hidden"
												name="storeID" value="${categoryVO.storeID}" /> <input
												type="hidden" name="action" value="ADD" /> <input
												type="hidden" id="method" name="method" value="" />

											<button class="btn" id="bag" href="#">
												<i class="fa fa-shopping-bag"></i>直接購買
											</button>
											<button class="btn" id="cart" href="#">
												<i class="fa fa-shopping-cart"></i>加入購物車
											</button>
										</div>

									</FORM>
									<script>
var bag = document.getElementById('bag');
var cart = document.getElementById('cart');
var method = document.getElementById('method');


bag.addEventListener('click', (event) => {	
	  method.value ="bag";
  }
)
cart.addEventListener('click', (event) => {	
	  method.value ="cart";
  }
)
</script>

								</div>
							</div>
						</div>

						<div class="row product-detail-bottom">
							<div class="col-lg-12">
								<ul class="nav nav-pills nav-justified align-items-center"
									id="account-sv">
									<li class="nav-item col-lg-2"><a class="nav-link active1"
										data-toggle="pill" href="#description"> <img
											src="img/account.jpg" alt="沒惹" srcset="img/account.jpg 80w"
											sizes="(max-width: 100px) 50px, 1vw" />
									</a></li>
									<li class="nav-item col-lg-41"><a class="nav-link active1"
										data-toggle="pill" href="#description">${categoryVO.storeName}</a>
										<a class="btn" href="${pageContext.request.contextPath}
										 		/store/storeServlet?storeID=${categoryVO.storeID}&action=gotoShop">查看賣場</a></li>
									<li class="nav-item col-lg-21">賣場地址:${categoryVO.storeAddress}
									<br><br>聯絡電話:${categoryVO.phoneNumber}</li>

									<li class="nav-item col-lg-21"><a class="nav-link active1"
										data-toggle="pill" href="#specification">加入時間:${categoryVO.insertTime.toString().substring(0,10)}</a>
									</li>
								</ul>

							</div>
						</div>
						<div class="row product-detail-bottom">
							<div class="col-lg-12">
								<ul class="nav nav-pills nav-justified">
									<li class="nav-item"><a class="nav-link active"
										data-toggle="pill" href="#description">商品詳情</a></li>
									<li class="nav-item"><a class="nav-link"
										data-toggle="pill" href="#reviews">評論(${list.size()})</a></li>
								</ul>

								<div class="tab-content">
									<div id="description" class="container tab-pane active">
										<p>出貨地:${categoryVO.source}</p>
										<p>上架日期:${categoryVO.insertTime.toString().substring(0,10)}</p>
										<p>商品描述:${categoryVO.productDesc}</p>

									</div>
									<div id="reviews" class="container tab-pane fade">
										<c:forEach var="orderlistVO" items="${list}">




											<div class="reviewer">
												${orderlistVO.userAccount} &nbsp; <span>${orderlistVO.orderDate.toString().substring(0,19)}</span>
											</div>




											<p class="reviewing" style='display: none'>${orderlistVO.buyerReview}</p>
											<div class="ratting">
												<i class="fa fa-star" name="fa" style='display: none'></i> <i
													class="fa fa-star" name="fa" style='display: none'></i> <i
													class="fa fa-star" name="fa" style='display: none'></i> <i
													class="fa fa-star" name="fa" style='display: none'></i> <i
													class="fa fa-star" name="fa" style='display: none'></i>
											</div>
											<p>
												<img style="width: 200px; height: 160px;"
													src="${pageContext.request.contextPath}/CommentPicServlet?orderdetailid=${orderlistVO.orderDetailID}"
													alt="Product Image">
											</p>
											<p>${orderlistVO.buyerComment}</p>
											<hr>
										</c:forEach>
									</div>

								</div>

								<script>
									document.addEventListener("DOMContentLoaded", function(){
										var previewing = Number(document.getElementsByClassName('previewing').innerHTML);
										var fa = document.getElementsByName("fa");
										
										for(var i=0 ; i< ${list.size()} ; i++){
										if(document.getElementsByClassName('reviewing')[i].innerHTML == '1'){
											document.getElementsByName("fa")[i*5].style.display = 'inline';		
										}
										else if(document.getElementsByClassName('reviewing')[i].innerHTML == '2'){
											document.getElementsByName("fa")[i*5].style.display = 'inline';	
											document.getElementsByName("fa")[i*5+1].style.display = 'inline';	
										}
										else if(document.getElementsByClassName('reviewing')[i].innerHTML == '3'){
											document.getElementsByName("fa")[i*5].style.display = 'inline';	
											document.getElementsByName("fa")[i*5+1].style.display = 'inline';	
											document.getElementsByName("fa")[i*5+2].style.display = 'inline';	
										}
										else if(document.getElementsByClassName('reviewing')[i].innerHTML == '4'){
											document.getElementsByName("fa")[i*5].style.display = 'inline';	
											document.getElementsByName("fa")[i*5+1].style.display = 'inline';	
											document.getElementsByName("fa")[i*5+2].style.display = 'inline';	
											document.getElementsByName("fa")[i*5+3].style.display = 'inline';	
										}
										else if(document.getElementsByClassName('reviewing')[i].innerHTML == '5'){
											document.getElementsByName("fa")[i*5].style.display = 'inline';	
											document.getElementsByName("fa")[i*5+1].style.display = 'inline';	
											document.getElementsByName("fa")[i*5+2].style.display = 'inline';	
											document.getElementsByName("fa")[i*5+3].style.display = 'inline';	
											document.getElementsByName("fa")[i*5+4].style.display = 'inline';	
										}	
									}
										});
									
									if(previewing >= 1 && previewing < 2){
										fa[i*5].style.display = 'inline';		
									}
									else if(previewing >= 2 && previewing < 3){
										fa[i*5].style.display = 'inline';	
										fa[i*5+1].style.display = 'inline';	
									}
									else if(previewing >= 3 && previewing < 4){
										fa[i*5].style.display = 'inline';	
										fa[i*5+1].style.display = 'inline';	
										fa[i*5+2].style.display = 'inline';	
									}
									else if(previewing >= 4 && previewing <= 5){
										fa[i*5].style.display = 'inline';	
										fa[i*5+1].style.display = 'inline';	
										fa[i*5+2].style.display = 'inline';	
										fa[i*5+3].style.display = 'inline';	
									}
									
									
									</script>


							</div>
						</div>

						<div class="product">
							<div class="section-header">
								<h1>Related Products</h1>
							</div>

							<div
								class="row align-items-center product-slider product-slider-3">
								<div class="col-lg-3">
									<div class="product-item">
										<div class="product-title">
											<a href="#">Product Name</a>
											<div class="ratting">
												<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
													class="fa fa-star"></i> <i class="fa fa-star"></i> <i
													class="fa fa-star"></i>
											</div>
										</div>
										<div class="product-image">
											<a href="product-detail.html"> <img
												src="img/product-10.jpg" alt="Product Image">
											</a>
											<div class="product-action">
												<a href="#"><i class="fa fa-cart-plus"></i></a> <a href="#"><i
													class="fa fa-heart"></i></a> <a href="#"><i
													class="fa fa-search"></i></a>
											</div>
										</div>
										<div class="product-price">
											<h3>
												<span>$</span>99
											</h3>
											<a class="btn" href=""><i class="fa fa-shopping-cart"></i>Buy
												Now</a>
										</div>
									</div>
								</div>
								<div class="col-lg-3">
									<div class="product-item">
										<div class="product-title">
											<a href="#">Product Name</a>
											<div class="ratting">
												<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
													class="fa fa-star"></i> <i class="fa fa-star"></i> <i
													class="fa fa-star"></i>
											</div>
										</div>
										<div class="product-image">
											<a href="product-detail.html"> <img
												src="img/product-8.jpg" alt="Product Image">
											</a>
											<div class="product-action">
												<a href="#"><i class="fa fa-cart-plus"></i></a> <a href="#"><i
													class="fa fa-heart"></i></a> <a href="#"><i
													class="fa fa-search"></i></a>
											</div>
										</div>
										<div class="product-price">
											<h3>
												<span>$</span>99
											</h3>
											<a class="btn" href=""><i class="fa fa-shopping-cart"></i>Buy
												Now</a>
										</div>
									</div>
								</div>
								<div class="col-lg-3">
									<div class="product-item">
										<div class="product-title">
											<a href="#">Product Name</a>
											<div class="ratting">
												<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
													class="fa fa-star"></i> <i class="fa fa-star"></i> <i
													class="fa fa-star"></i>
											</div>
										</div>
										<div class="product-image">
											<a href="product-detail.html"> <img
												src="img/product-6.jpg" alt="Product Image">
											</a>
											<div class="product-action">
												<a href="#"><i class="fa fa-cart-plus"></i></a> <a href="#"><i
													class="fa fa-heart"></i></a> <a href="#"><i
													class="fa fa-search"></i></a>
											</div>
										</div>
										<div class="product-price">
											<h3>
												<span>$</span>99
											</h3>
											<a class="btn" href=""><i class="fa fa-shopping-cart"></i>Buy
												Now</a>
										</div>
									</div>
								</div>
								<div class="col-lg-3">
									<div class="product-item">
										<div class="product-title">
											<a href="#">Product Name</a>
											<div class="ratting">
												<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
													class="fa fa-star"></i> <i class="fa fa-star"></i> <i
													class="fa fa-star"></i>
											</div>
										</div>
										<div class="product-image">
											<a href="product-detail.html"> <img
												src="img/product-4.jpg" alt="Product Image">
											</a>
											<div class="product-action">
												<a href="#"><i class="fa fa-cart-plus"></i></a> <a href="#"><i
													class="fa fa-heart"></i></a> <a href="#"><i
													class="fa fa-search"></i></a>
											</div>
										</div>
										<div class="product-price">
											<h3>
												<span>$</span>99
											</h3>
											<a class="btn" href=""><i class="fa fa-shopping-cart"></i>Buy
												Now</a>
										</div>
									</div>
								</div>
								<div class="col-lg-3">
									<div class="product-item">
										<div class="product-title">
											<a href="#">Product Name</a>
											<div class="ratting">
												<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
													class="fa fa-star"></i> <i class="fa fa-star"></i> <i
													class="fa fa-star"></i>
											</div>
										</div>
										<div class="product-image">
											<a href="product-detail.html"> <img
												src="img/product-2.jpg" alt="Product Image">
											</a>
											<div class="product-action">
												<a href="#"><i class="fa fa-cart-plus"></i></a> <a href="#"><i
													class="fa fa-heart"></i></a> <a href="#"><i
													class="fa fa-search"></i></a>
											</div>
										</div>
										<div class="product-price">
											<h3>
												<span>$</span>99
											</h3>
											<a class="btn" href=""><i class="fa fa-shopping-cart"></i>Buy
												Now</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 sidebar">
					<div class="sidebar-widget category">
						<h2 class="title">Category</h2>
						<nav class="navbar bg-light">
							<ul class="navbar-nav">
								<li class="nav-item"><a class="nav-link"
									href="${pageContext.request.contextPath}/SearchServlet?productMainID=1&action=category"><i
										class="fa fa-home"></i>3C</a></li>
								<li class="nav-item"><a class="nav-link"
									href="${pageContext.request.contextPath}/SearchServlet?productMainID=2&action=category"><i
										class="fa fa-shopping-bag"></i>周邊</a></li>
								<li class="nav-item"><a class="nav-link"
									href="${pageContext.request.contextPath}/SearchServlet?productMainID=3&action=category"><i
										class="fa fa-plus-square"></i>精品</a></li>
							</ul>
						</nav>
					</div>

					<div class="sidebar-widget widget-slider">
						<div class="sidebar-slider normal-slider">
							<div class="product-item">
								<div class="product-title">
									<a href="#">Product Name</a>
									<div class="ratting">
										<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
											class="fa fa-star"></i> <i class="fa fa-star"></i> <i
											class="fa fa-star"></i>
									</div>
								</div>
								<div class="product-image">
									<a href="product-detail.html"> <img src="img/product-7.jpg"
										alt="Product Image" />
									</a>
									<div class="product-action">
										<a href="#"><i class="fa fa-cart-plus"></i></a> <a href="#"><i
											class="fa fa-heart"></i></a> <a href="#"><i
											class="fa fa-search"></i></a>
									</div>
								</div>
								<div class="product-price">
									<h3>
										<span>$</span>99
									</h3>
									<a class="btn" href=""><i class="fa fa-shopping-cart"></i>Buy
										Now</a>
								</div>
							</div>
							<div class="product-item">
								<div class="product-title">
									<a href="#">Product Name</a>
									<div class="ratting">
										<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
											class="fa fa-star"></i> <i class="fa fa-star"></i> <i
											class="fa fa-star"></i>
									</div>
								</div>
								<div class="product-image">
									<a href="product-detail.html"> <img src="img/product-8.jpg"
										alt="Product Image" />
									</a>
									<div class="product-action">
										<a href="#"><i class="fa fa-cart-plus"></i></a> <a href="#"><i
											class="fa fa-heart"></i></a> <a href="#"><i
											class="fa fa-search"></i></a>
									</div>
								</div>
								<div class="product-price">
									<h3>
										<span>$</span>99
									</h3>
									<a class="btn" href=""><i class="fa fa-shopping-cart"></i>Buy
										Now</a>
								</div>
							</div>
							<div class="product-item">
								<div class="product-title">
									<a href="#">Product Name</a>
									<div class="ratting">
										<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
											class="fa fa-star"></i> <i class="fa fa-star"></i> <i
											class="fa fa-star"></i>
									</div>
								</div>
								<div class="product-image">
									<a href="product-detail.html"> <img src="img/product-9.jpg"
										alt="Product Image" />
									</a>
									<div class="product-action">
										<a href="#"><i class="fa fa-cart-plus"></i></a> <a href="#"><i
											class="fa fa-heart"></i></a> <a href="#"><i
											class="fa fa-search"></i></a>
									</div>
								</div>
								<div class="product-price">
									<h3>
										<span>$</span>99
									</h3>
									<a class="btn" href=""><i class="fa fa-shopping-cart"></i>Buy
										Now</a>
								</div>
							</div>
						</div>
					</div>

					<div class="sidebar-widget brands">
						<h2 class="title">Our Brands</h2>
						<ul>
							<li><a href="#">Nulla </a><span>(45)</span></li>
							<li><a href="#">Curabitur </a><span>(34)</span></li>
							<li><a href="#">Nunc </a><span>(67)</span></li>
							<li><a href="#">Ullamcorper</a><span>(74)</span></li>
							<li><a href="#">Fusce </a><span>(89)</span></li>
							<li><a href="#">Sagittis</a><span>(28)</span></li>
						</ul>
					</div>

					<div class="sidebar-widget tag">
						<h2 class="title">Tags Cloud</h2>
						<a href="#">Lorem ipsum</a> <a href="#">Vivamus</a> <a href="#">Phasellus</a>
						<a href="#">pulvinar</a> <a href="#">Curabitur</a> <a href="#">Fusce</a>
						<a href="#">Sem quis</a> <a href="#">Mollis metus</a> <a href="#">Sit
							amet</a> <a href="#">Vel posuere</a> <a href="#">orci luctus</a> <a
							href="#">Nam lorem</a>
					</div>
				</div>
			</div>

			<!-- Product Detail End -->

			<!-- Brand Start -->
			<div class="brand">
				<div class="container-fluid">
					<div class="brand-slider">
						<div class="brand-item">
							<img src="img/brand-1.png" alt="">
						</div>
						<div class="brand-item">
							<img src="img/brand-2.png" alt="">
						</div>
						<div class="brand-item">
							<img src="img/brand-3.png" alt="">
						</div>
						<div class="brand-item">
							<img src="img/brand-4.png" alt="">
						</div>
						<div class="brand-item">
							<img src="img/brand-5.png" alt="">
						</div>
						<div class="brand-item">
							<img src="img/brand-6.png" alt="">
						</div>
					</div>
				</div>
			</div>
			<!-- Brand End -->

			<!-- Footer Start -->
			<div class="footer">
				<div class="container-fluid">
					<div class="row">
						<div class="col-lg-3 col-md-6">
							<div class="footer-widget">
								<h5>網站介紹</h5>
								<ul>
									<li><a href="#">關於SYM</a></li>
									<li><a href="#">SYM團隊成員介紹</a></li>
									<!-- <li><a href="#">Terms & Condition</a></li> -->
								</ul>
							</div>
						</div>

						<div class="col-lg-3 col-md-6">
							<div class="footer-widget">
								<h5>聯絡我們</h5>
								<div class="contact-info">
									<p>
										<i class="fa fa-map-marker"></i>台北商業大學
									</p>
									<!-- <p><i class="fa fa-envelope"></i>email@example.com</p>
              <p><i class="fa fa-phone"></i>+123-456-7890</p> -->
								</div>
							</div>
						</div>

						<!-- <div class="col-lg-3 col-md-6">
          <div class="footer-widget">
            <h2>Follow Us</h2>
            <div class="contact-info">
              <div class="social">
                <a href=""><i class="fab fa-twitter"></i></a>
                <a href=""><i class="fab fa-facebook-f"></i></a>
                <a href=""><i class="fab fa-linkedin-in"></i></a>
                <a href=""><i class="fab fa-instagram"></i></a>
                <a href=""><i class="fab fa-youtube"></i></a>
              </div>
            </div>
          </div>
        </div> -->

						<div class="col-lg-3 col-md-6">
							<div class="footer-widget">
								<h5>常見問題</h5>
								<ul>
									<li><a href="#">前往客服中心</a></li>
									<!-- <li><a href="#">Shipping Policy</a></li>
              <li><a href="#">Return Policy</a></li> -->
								</ul>
							</div>
						</div>
					</div>

					<!-- <div class="row payment align-items-center">
        <div class="col-md-6">
          <div class="payment-method">
            <h2>We Accept:</h2>
            <img src="img/payment-method.png" alt="Payment Method" />
          </div>
        </div>
        <div class="col-md-6">
          <div class="payment-security">
            <h2>Secured By:</h2>
            <img src="img/godaddy.svg" alt="Payment Security" />
            <img src="img/norton.svg" alt="Payment Security" />
            <img src="img/ssl.svg" alt="Payment Security" />
          </div>
        </div>
      </div> -->
				</div>
			</div>
			<!-- Footer End -->

			<!-- Footer Bottom Start -->
			<div class="footer-bottom">
				<div class="container">
					<div class="row">
						<div class="col-md-6 copyright">
							<p>
								Copyright &copy; <a href="#">SYM</a>. All Rights Reserved
							</p>
						</div>

						<div class="col-md-6 template-by">
							<!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
							<!-- <p>Designed By <a href="https://htmlcodex.com">HTML Codex</a></p> -->
						</div>
					</div>
				</div>
			</div>
			<!-- Footer End -->

			<!-- Back to Top -->
			<a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>

			<!-- JavaScript Libraries -->
			<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
			<script
				src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
			<script
				src="${pageContext.request.contextPath}/front-end/product_detail/lib/easing/easing.min.js"></script>
			<script
				src="${pageContext.request.contextPath}/front-end/product_detail/lib/slick/slick.min.js"></script>

			<!-- Template Javascript -->
			<script
				src="${pageContext.request.contextPath}/front-end/product_detail/js/main.js"></script>
</body>
</html>
