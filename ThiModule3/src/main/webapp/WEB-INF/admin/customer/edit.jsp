<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: Admin--%>
<%--  Date: 12/12/2022--%>
<%--  Time: 8:36 AM--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--  <meta charset="utf-8">--%>
<%--  <meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
<%--  <title>Title</title>--%>
<%--  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">--%>
<%--  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />--%>
<%--  <jsp:include page="/WEB-INF/admin/layout/headcss.jsp"></jsp:include>--%>
<%--</head>--%>
<%--<body>--%>
<%--<jsp:include page="/WEB-INF/admin/layout/topnav.jsp"></jsp:include>--%>
<%--<div class="container">--%>
<%--  <h1>Edit product</h1>--%>
<%--  <form method="post">--%>
<%--    <input type="hidden" name="id" value="${requestScope.product.getId()}">--%>

<%--    <label for="idName">Name: </label>--%>
<%--    <input type="text" id="idName" name="name" class="form-control" value="${requestScope.product.getName()}">--%>

<%--    <label for="idAdress">Address: </label>--%>
<%--    <input type="text" id="idAdress" name="address" class="form-control" value="${requestScope.product.getAddress()}">--%>

<%--    <label for="idCountry">Country: </label>--%>
<%--    <select id="idCountry" name="idCountry">--%>
<%--      <c:forEach items="${applicationScope.countries}" var="country">--%>
<%--        <option value="${country.getId()}"--%>
<%--                <c:if test="${country.getId() == product.getIdCountry() }"> selected</c:if>--%>
<%--        >${country.getName()}</option>--%>
<%--      </c:forEach>--%>
<%--    </select> <br>--%>
<%--    <label for="idPicture">Picture: </label>--%>
<%--    <input type="text" id="idPicture" name="picture" class="form-control" value="${requestScope.product.getPicture()}">--%>

<%--    <button>Save</button>--%>
<%--  </form>--%>

<%--</div>--%>
<%--<jsp:include page="/WEB-INF/admin/layout/footer.jsp"></jsp:include>--%>
<%--</body>--%>
<%--</html>--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Product</title>
    <jsp:include page="/WEB-INF/admin/layout/headcss.jsp"></jsp:include>
    <style>
        #idCountry {
            margin-top: 15px;
            margin-right: 5px;
        }

        button {
            margin-top: 15px;
            background: #05AE0E;
            color: black;
            border: 2px solid black;
            padding: 1px 20px;
        }
    </style>
</head>
<body>
<!-- Body content -->
<!-- End of nav bar -->
<div class="container">
    <h1>Edit product</h1>
    <c:if test="${requestScope.errors!=null}">
        <div class="alert alert-danger" role="alert">
            <ul>
                <c:forEach items="${requestScope.errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <form method="post">

        <input type="hidden" name="id" value="${requestScope.product.getId()}">

        <label for="idName">Name: </label>
        <input type="text" id="idName" name="name" class="form-control" value="${requestScope.product.getName()}" required>

        <label for="idprice">Price: </label>
        <input type="number" id="idprice" name="price" class="form-control" value="${requestScope.product.getPrice()}" required>

        <label for="idamount">Amount: </label>
        <input type="number" id="idamount" name="amount" class="form-control" value="${requestScope.product.getAmount()}" required>

        <label for="idcolor">Color: </label>
        <input type="text" id="idcolor" name="color" class="form-control" value="${requestScope.product.getColor()}"required>

        <label for="iddescription">Description: </label>
        <input type="text" id="iddescription" name="description" class="form-control" value="${requestScope.product.getDescription()}" required>

        <label for="ididCategory" style="margin: 15px 0px">Category: </label>
        <select id="ididCategory" name="idCategory">
            <c:forEach items="${applicationScope.Categorys}" var="Category">
                <option value="${Category.getId()}"
                        <c:if test="${Category.getId() == product.getiDcategory() }"> selected</c:if>
                >${Category.getName()}</option>
            </c:forEach>
        </select> <br>
        <button class="btn btn-success">Save</button>
    </form>
    <a href="/customers"><button class="btn btn-success">Back</button></a>

</div>


</body>
</html>