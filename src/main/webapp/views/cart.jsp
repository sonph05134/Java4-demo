<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/common.jsp"%>
<c:set var="pageTitle" value="Giỏ Hàng" />
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/views/common/head.jsp"%>
</head>
<body>
	<div class="container">
		<div class="homepage-slidebox">
			<img alt="Home page slide" src="<c:url value="/images/slide/slide.jpg" />">
		</div>
		<div class="grid_3 grid_5 w3ls">
			<br/>
       		<h3>Giỏ Hàng</h3>
        	<div class="alert alert-success" role="alert">
            <strong>Username: </strong><strong>${account.username}</strong>
        </div>
        <div class="alert alert-info" role="alert">
            <strong>Cart ID: </strong> <strong>${cart.cartId}</strong>
        </div>

    </div>

    <table class="table table-hover">
        <thead>
        <tr>
            <th>Product Name</th>
            <%-- <th>Product Image</th>--%>
            <th>Product Price</th>
            <th>Quantity</th>
            <th>SumTotal $</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="total" value="${0}"/>
        <c:forEach var="item" items="${listItems}">
            <c:set var="total" value="${total + item.detailPrice*item.detailQuantity}"/>
            <tr>
                <td>${item.detailName}</td>
                <td>${item.detailPrice}</td>
                <td>${item.detailQuantity}</td>
                <td>${item.detailPrice*item.detailQuantity} VND</td>
                <td>
                    <div class="cell float-lg-right">
                        <a href="<c:url value="/edit-cart?id=${item.productId}" />"
                           class="btn btn-warning btn-sm"><i
                                class="fa fa-edit" aria-hidden="true"> Edit </i></a>

                        <a href="<c:url value="/delete-cart?id=${item.productId}" />"
                           class="btn btn-danger btn-sm"><i
                                class="fa fa-trash-o" aria-hidden="true"> Delete </i></a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td><strong><c:out value="${total} VND"/></strong></td>
        </tr>
    </table>
		<jsp:include page="/views/common/footer.jsp"></jsp:include>
	</div>
</body>
</html>