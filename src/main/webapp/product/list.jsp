<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="req" value="${pageContext.request}" />
<html>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
        </tr>
        <c:forEach var="product" items="${listProduct}" varStatus="count">
            <tr>
                <td><c:out value="${count.index+1}"/></td>
                <td><c:out value="${product.name}"/></td>
                <td><c:out value="${product.description}"/></td>
                <td><c:out value="${product.price}"/></td>
                <td><c:out value="${product.category}"></c:out></td>
                <td>
                    <a href="${pageContext.request.contextPath}/products?action=edit&id=${product.id}">Edit</a>
                    <a href="${pageContext.request.contextPath}/products?action=delete&id=${product.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    
    <div class="pagination">
  <a href="#">«</a>
  <c:forEach var="i" begin="1" end="${paging }">
  		 <a href="${pageContext.request.contextPath}/products?page=${i}"><c:out value="${i}"></c:out></a>
  </c:forEach>
  <a href="#">»</a>
</div>
</div>
</body>
</html>