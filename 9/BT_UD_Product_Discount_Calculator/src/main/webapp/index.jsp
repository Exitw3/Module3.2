<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Discount Calculator</title>
</head>
<body>
<form action="/calculate_discount" method="post">
    <p>Nhập mô tả sản phẩm</p>
    <input type="text" name="ProductDescription"><br>
    <p>Nhập giá sản phẩm</p>
    <input type="text" name="ListPrice"><br>
    <p>Nhập tỉ lệ chiết khấu của sản phẩm</p>
    <input type="text" name="DiscountPercent"><br><br>
    <button type="submit">Calculate Discount</button>
</form>
</body>
</html>