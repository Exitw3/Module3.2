<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Product</title>
  <jsp:include page="/WEB-INF/admin/layout/headcss.jsp"></jsp:include>
</head>
<body>
<!-- Body content -->
<!-- End of nav bar -->
<div class="container">
  <h1>Create customer</h1>
  <c:if test="${requestScope.errors!=null}">
    <div class="alert alert-danger" role="alert">
      <ul>
        <c:forEach items="${requestScope.errors}" var="error">
          <li>${error}</li>
        </c:forEach>
      </ul>
    </div>
  </c:if>

  <c:if test="${requestScope.message != null}">
    <div class="alert alert-success">
      <h3>${requestScope.message}</h3>
    </div>
  </c:if>


  <form method="post">
    <label for="idName">Name: </label>
    <input type="text" id="idName" name="name" class="form-control" required>

    <label for="idprice">price: </label>
    <input type="number" id="idprice" name="price" class="form-control" required>

    <label for="idamount">amount: </label>
    <input type="number" id="idamount" name="amount" class="form-control" required>

    <label for="idcolor">color: </label>
    <input type="text" id="idcolor" name="color" class="form-control" required>

    <label for="iddescription">description: </label>
    <input type="text" id="iddescription" name="description" class="form-control" required>

    <label for="ididCategory" style="margin: 15px 0px">Category: </label>
    <select id="ididCategory" name="idCategory">
      <c:forEach items="${applicationScope.Categorys}" var="Category">
        <option value="${Category.getId()}"
                <c:if test="${Category.getId() == product.getiDcategory() }"> selected</c:if>
        >${Category.getName()}</option>
      </c:forEach>
    </select> <br>
    <button class="btn btn-success">Create</button>
  </form>
    <a href="/customers"><button style="margin-top: 10px" class="btn btn-success" >Back</button></a>

</div>


</body>
</html>