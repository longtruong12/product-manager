<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="req" value="${pageContext.request}" />
<html>
<head>
    <title>Product Management Application</title>
    <script src="/js/app.js"></script>
    <link rel="stylesheet" href="/css/app.css">
</head>
<body>
<center>
    <h1 class="header">Product Management</h1>
    <h2>
        <a href="${pageContext.request.contextPath}/products?action=create">Add New Product</a>
    </h2>
    
    <form method="get" ">
	    <input type ="text" placeholder="search..." name = "q" value="${q }"/>
	    <select name="category">
 			<option value="-1">All</option>
 			<c:forEach var="j" items="${listCategory }" >
 				<c:choose>
 					<c:when test="${product== j.getId()}">
 						<option value="${j.getId() }" selected>${j.name }</option>
 					</c:when>
 					<c:otherwise>
 						<option value="${j.getId() }">${j.name }</option>
 					</c:otherwise>
 				</c:choose>
 				
 			</c:forEach>
 		</select>
	    <button >Search</button>
 		
    </form>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Products</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Category</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="product" items="${listProduct}">
            <tr>
                <td><c:out value="${product.id}"/></td>
                <td><c:out value="${product.name}"/></td>
                <td><c:out value="${product.description}"/></td>
 				<td><c:out value="${product.price}"/></td>
                <td>
                	<c:forEach var="c" items="${listCategory}">
                		<c:if test="${product.getCountry() == c.getId() }">
                			<c:out value="${c.name }"/>
                		</c:if>
                	</c:forEach>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/products?action=edit&id=${product.id}">Edit</a>
                    <a href="${pageContext.request.contextPath}/products?action=delete&id=${product.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    
    <div class = "pagination">
    	<c:if test="${currentPage!=1 }">
    		 <a href="${pageContext.request.contextPath}/products?page=${currentPage-1 }&q=${q}&category=${category}">«</a>
    	</c:if>
    	<c:forEach begin="1" end="${noOfPages }" var = "i">
    		<c:choose>
    			<c:when test="${currentPage eq i }">
    				<a href="#" class="active">${i }</a>
    			</c:when>
    			<c:otherwise>
    				<a href="${pageContext.request.contextPath}/products?page=${i }&q=${q}&category=${category}">${i }</a>
    			</c:otherwise>
    		</c:choose>
    	
    	</c:forEach>
    	
    	<c:if test="${currentPage lt noOfPages }">
    		<a href="${pageContext.request.contextPath}/products?page=${currentPage +1 }&q=${q}&category=${category}">»</a>
    	</c:if>
    </div>
    
</div>
</body>
</html>