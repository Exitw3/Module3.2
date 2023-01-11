<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 1/3/2023
  Time: 10:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Product management</title>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<h1>Danh sách sản phẩm</h1>
<h2><a href="/product?action=create">Thêm mới sản phẩm</a></h2>
<table class="table table-dark">
  <tr>
    <td>Stt</td>
    <td>Id</td>
    <td>Name</td>
    <td>Price</td>
  </tr>
  <c:forEach var="product" items="${productList}" varStatus="status">
    <tr>
      <td>${status.count}</td>
      <td>${product.id}</td>
      <td>${product.name}</td>
      <td>${product.price}</td>
    </tr>
  </c:forEach>
</table>

</body>
</html>