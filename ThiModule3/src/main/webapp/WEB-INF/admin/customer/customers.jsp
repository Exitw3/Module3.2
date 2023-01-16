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
    <style>
        .search-form {
        display: flex;
        border: none;
        margin-right: 20px;
        }

        .search-form>input {
            border: 1px solid;
            padding: 10px;
            margin-right: 10px;
        }

        .row-search-form {
            justify-content: space-between;
            display: flex;
            padding: 0px 20px;
        }

        .addproductbtn {
            margin-top: 25px;
            margin-right: auto;
        }

        .addproductbtn button {
            font-size: 24px;
            border: 2px red;
        }
        /*tbody tr:nth-child(odd) {*/
        /*background-color: #bde0d9;*/
        /*}*/
        /*tbody tr:hover {*/
        /*    background-color: #b9b598;*/
        /*}*/
        /*tbody {*/
        /*    color: black;*/
        /*}*/


    </style>
</head>
<body>
<!-- Body content -->
<!-- End of nav bar -->
<div class="row">
    <div class="col-sm-12">
        <div class="demo-box p-2">
            <div class="row row-search-form">
                <div class="addproductbtn"><a href="/customers?action=create"><button style="font-size:24px; border: 2px red" >add Product</button></a>
                </div>
                <div>
                    <form method="get" action="/customers" class="search-form">
                        <input type="text" name="kw" class="mr-1" value="${requestScope.kw}">
                        <button>Search</button>
                    </form>
                </div>
            </div>

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

            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Price</th>
                        <th scope="col">Amount</th>
                        <th scope="col">Color</th>
                        <th scope="col">description</th>
                        <th scope="col">Category</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.products}" var="product">
                        <tr>
                            <th scope="row">${product.getId()}</th>
                            <td>${product.getName()}</td>
                            <td>${product.getPrice()}</td>
                            <td>${product.getAmount()}</td>
                            <td>${product.getColor()}</td>
                            <td>${product.getDescription()}</td>
                            <c:forEach items="${applicationScope.Categorys}" var="Category">
                                <c:if test="${Category.getId() == product.getiDcategory()}">
                                    <td>${Category.getName()}</td>
                                </c:if>
                            </c:forEach>
                            <td>
                                <a href="/customers?action=edit&id=${product.getId()}"><i class="fa fa-edit" style="font-size:24px"></i> </a>
                                <a href="/customers?action=remove&id=${product.getId()}"><i class="fa fa-remove" style="font-size:24px; color: red;"></i> </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


</body>
</html>