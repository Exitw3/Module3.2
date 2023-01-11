<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Product management</title>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<h1>Thêm mới sản phẩm</h1>
<h3><a href="/product">Quay lại danh sách</a></h3>
<h4 style="color: red">${mess}</h4>
<form action="/product?action=create" method="post">
  <input name="id" placeholder="nhập id sản phẩm"><br>
  <input name="name" placeholder="nhập tên sản phẩm"><br>
  <input name="price" placeholder="nhập giá sản phẩm"><br>
  <button type="submit">Save</button>
</form>
</body>
</html>
