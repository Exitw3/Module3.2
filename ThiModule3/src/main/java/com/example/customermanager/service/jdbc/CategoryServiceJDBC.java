package com.example.customermanager.service.jdbc;

import com.example.customermanager.model.Category;
import com.example.customermanager.service.ICategoryService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceJDBC extends DatabaseContext implements ICategoryService {
    private static final String SELECT_ALL_COUNTRY = "select * from category;";
    private static final String FIND_COUNTRY_BY_ID = "select * from category where id = ";
    private static final String SP_INSERTCOUNTRY = "call spInsertcategory(?, ?);";


    @Override
    public List<Category> getAllCategory() {
        List<Category> countries = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COUNTRY);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Category country = getCountryFromResulSet(rs);
                countries.add(country);
            }
            connection.close();
        } catch (SQLException exception) {
            printSQLException(exception);
        }
        return countries;
    }

    private Category getCountryFromResulSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");


        Category country = new Category(id, name);
        return country;
    }

    @Override
    public Category findCountryById(long id) {
        try{
            Connection connection = getConnection();

            Statement statement = connection.createStatement();
            String query = FIND_COUNTRY_BY_ID + id;
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Category country = getCountryFromResulSet(rs);
                return country;
            }
            connection.close();
        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return null;
    }

    @Override
    public boolean insertCountryBySP(String nameCountry) {
        boolean check = false;
        try {
            // spInsertCountry(?, ?);
            Connection connection = getConnection();
            CallableStatement callableStatement = connection.prepareCall(SP_INSERTCOUNTRY);
            callableStatement.setString(1, nameCountry);
            callableStatement.registerOutParameter(2, Types.BOOLEAN);

            System.out.println(this.getClass() + " insertCountryBySP: " + callableStatement);
            callableStatement.execute();

            check = callableStatement.getBoolean(2);

        } catch (SQLException sqlException) {
            printSQLException(sqlException);


        }
        return check;
    }

    @Override
    public boolean testProcedure() {

        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `country` (`id`, `name`) VALUES ('7', 'Anh123')");
            preparedStatement.executeUpdate();

            connection.prepareStatement("INSERT INTO `country` (`id`, `name`) VALUES ('7', 'Phap')");
            preparedStatement.executeUpdate();

            connection.commit();
            connection.close();
        } catch (SQLException sqlException) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            printSQLException(sqlException);

        }
        return false;
    }

}
