<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="req" value="${pageContext.request}" />
<html>
<head>
    <title>Product Management Application</title>
</head>
<body>
<center>
    <h1>Product Management</h1>
    <h2>
        <a href="${pageContext.request.contextPath}/products?action=products">List All Products</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Edit Product
                </h2>
            </caption>
            <c:if test="${product != null}">
                <input type="hidden" name="id" value="<c:out value='${product.id}' />"/>
            </c:if>
            <tr>
                <th>Product Name:</th>
                <td>
                    <input type="text" name="name" size="45"
                           value="<c:out value='${product.name}' required/>"
                    />
                </td>
            </tr>
            <tr>
                <th>Product Description:</th>
                <td>
                    <input type="text" name="description" size="45"
                           value="<c:out value='${product.description}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Product Price:</th>
                <td>
                    <input type="number" name="price" size="45"
                           value="<c:out value='${product.price}' required/>"
                    />
                </td>
            </tr>
            <tr>
                <th>Category:</th>
                <td>
                    <select name="category" id="category">
                    	<option value="${product.category}"><c:out value="${product.category}"></c:out></option>
						<c:forEach var="c" items="${listCategory}">
							<c:if test = "${c != product.category}">
					         	<option value="${c}"><c:out value="${c}"></c:out></option>
					      	</c:if>
	                	</c:forEach>
                	</select>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>