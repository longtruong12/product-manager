<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>User Info</title>
   </head>
   <body>

     <jsp:include page="../_menu.jsp"></jsp:include>  

      <h3>Hello: ${loginedUser.email}</h3>

     	Email: <b>${loginedUser.email}</b>
      <br />


   </body>
</html>