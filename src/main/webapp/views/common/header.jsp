<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header>
	<nav class="navbar navbar-toggleable-md navbar-light bg-faded">
		<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<a class="navbar-brand" href="<c:url value="/" />">WEBSITE MOBILE SHOP</a>
	
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class='nav-item <c:if test="${empty categoryId}">active</c:if>'>
					<a class="nav-link" href="<c:url value="/" />">Home</a>
				</li>
				<li class="nav-item dropdown">
       				 <a class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          				Danh mục
        			</a>
        			<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
       				 	<c:forEach var="category" items="${listCategories}">
         				 	<a class="nav-link" href="<c:url value="/list?cid=${category.id}" />">${category.name}</a> 
          			 	</c:forEach>
       		 		</div>
     			 </li>
			
				  <li class='nav-item <c:if test="${empty productId}">active</c:if>'>
						<a class="nav-link" href="<c:url value="/list?cid=${product.id}" />">Sản Phẩm</a>	
	 			</li>
			</ul>
			<div class="my-2 my-lg-0">
				<c:if test="${not empty sessionScope.account}">
					<p>${sessionScope.account.username} | <a href="<c:url value="/cart" />">Cart</a> | <a href="<c:url value="/logout" />">Logout</a></p>
				</c:if>
				<c:if test="${empty sessionScope.account}">
					<p><a href="<c:url value="/createAccount" />">Create Account</a> | <a href="<c:url value="/login" />">Login</a> </p>
				</c:if>
			</div>
		</div>
	</nav>
</header>