package com.example.customermanager.service.jdbc;

import com.example.customermanager.model.Product;
import com.example.customermanager.service.IProductService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceJDBC extends DatabaseContext implements IProductService {
    private static final String SELECT_ALL_CUSTOMER = "select * from product";
    private static final String INSERT_CUSTOMER = "INSERT INTO `product` (`id`,`name`, `price`, `amount`,`color`,`description`,`idcategory`) VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String UPDATE_CUSTOMER =
            "UPDATE `product` SET  `name` = ?, `price` = ?, `amount` = ?, `color` = ?, `description` = ? ,`iDcategory` = ? WHERE (`id` = ?)";

    private static final String FIND_BY_ID = "select * from product where id = ";

    private static final String FIND_BY_IDMAX = "SELECT max(id) as max FROM product";

    private static final String SP_GETALLCUSTOMER_BYIDCOUNTRY = "call spGetAllCustomerByIdCountry(?);";
    private static final String DELETE_CUSTOMER = "DELETE FROM `product` WHERE (`id` = ?);";
    private static final String SELECT_CUSTOMERS_BY_KW_IDCOUNTRY = "SELECT * FROM customer where idCountry = ? and name like ?;";
    private static final String SELECT_CUSTOMERS_BY_KW = "SELECT * FROM `product` where name like ? or price like ? or amount like ? or color like ? or description like ?;";
    private static final String SELECT_CUSTOMERS_BY_KW_ALLCOUNTRY_PAGGING = "SELECT SQL_CALC_FOUND_ROWS * FROM customer where name like ? limit ?, ?;";
    private static final String SELECT_CUSTOMERS_BY_KW_IDCOUNTRY_PAGGING = "SELECT SQL_CALC_FOUND_ROWS * FROM customer where idCountry = ? and name like ? limit ? , ? ";

    private int noOfRecords;

    @Override
    public List<Product> getAllCustomers() {
        List<Product> customers = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMER);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Product customer = getCustomerFromResulset(rs);
                customers.add(customer);
            }
            connection.close();
        } catch (SQLException exception) {
            printSQLException(exception);
        }
        return customers;
    }

    @Override
    public List<Product> getAllCustomersByKw(String kw) {
        List<Product> customers = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            Connection connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECT_CUSTOMERS_BY_KW);
            preparedStatement.setString(1, "%" + kw + "%");
            preparedStatement.setString(2, "%" + kw + "%");
            preparedStatement.setString(3, "%" + kw + "%");
            preparedStatement.setString(4, "%" + kw + "%");
            preparedStatement.setString(5, "%" + kw + "%");
            System.out.println(this.getClass() + " getAllCustomersByKwAndIdCountry: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product customer = getCustomerFromResulset(rs);
                customers.add(customer);
            }
            connection.close();
        } catch (SQLException sqlException) {
        }
        return customers;

    }

    private Product getCustomerFromResulset(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        String price = rs.getString("price");
        String amount = rs.getString("amount");
        String color = rs.getString("color");
        String description = rs.getString("description");
        int category = rs.getInt("idcategory");

        Product product = new Product(id, name, price, amount, color, description, category);
        return product;
    }


    @Override
    public void addCustomer(Product product) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER);
            preparedStatement.setLong(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getPrice());
            preparedStatement.setString(4, product.getAmount());
            preparedStatement.setString(5, product.getColor());
            preparedStatement.setString(6, product.getDescription());
            preparedStatement.setInt(7, product.getiDcategory());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }

    @Override
    public Product findCustomerById(long id) {

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String query = FIND_BY_ID + id;
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Product customer = getCustomerFromResulset(rs);
                return customer;
            }
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }

    public long findCustomerByIdMax() {

        try {
            Connection connection = getConnection();

            Statement statement = connection.createStatement();
            String query = FIND_BY_IDMAX;
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                return rs.getLong("max");
            }
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return -1;
    }

    @Override
    public void updateCustomer(Product product) {
        try {
            Connection connection = getConnection();

            //UPDATE `customer` SET  `name` = ?, `address` = ?, `idCountry` = ?, `picture` = ?  WHERE (`id` = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getPrice());
            preparedStatement.setString(3, product.getAmount());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getiDcategory());
            preparedStatement.setLong(7, product.getId());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }

    @Override
    public void deleteCustomer(long id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER);
            preparedStatement.setLong(1, id);

            System.out.println(this.getClass() + " deleteCustomer: " + preparedStatement);

            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }

    @Override
    public List<Product> getAllCustomerByIdCountry(long idCategory) {
        // ?,?
        List<Product> customers = new ArrayList<>();
        try {
            Connection connection = getConnection();
            CallableStatement callableStatement = connection.prepareCall(SP_GETALLCUSTOMER_BYIDCOUNTRY);
            callableStatement.setLong(1, idCategory);

            System.out.println(this.getClass() + " getAllCustomerByIdCountry: " + callableStatement);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                Product customer = getCustomerFromResulset(rs);
                customers.add(customer);
            }
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return customers;
    }

    @Override
    public int getNoOfRecords() {
        return this.noOfRecords;
    }


}
