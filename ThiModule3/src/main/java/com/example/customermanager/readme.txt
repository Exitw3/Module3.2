 static final String INSERT_CUSTOMER = "INSERT INTO `customer` (`id`,`name`, `address`, `idCountry`,`picture`) VALUES (?, ?, ?, ?, ?);";
--> Tham so cua dau hoi la bat dau tu 1

getCustomerFromResulset(ResultSet rs)
--> lấy dữ lieu từ resultset thì phải đúng label cột

 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER);
preparedStatement.setLong(1, customer.getId());

--> nhớ set tham số cho preparedStatement


System.out.println(INSERT_CUSTOMER);
--> nhớ in ra câu lệnh để dễ quan sát

switch (action) {
    case "create":
    break;
 --> nhớ break chỗ này

 ----------------CAT TEMPLATE
 Chen link js, css




