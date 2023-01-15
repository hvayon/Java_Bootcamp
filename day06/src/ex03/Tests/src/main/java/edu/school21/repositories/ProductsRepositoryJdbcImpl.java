package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private final DataSource ds;

    public ProductsRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<Product> findAll() throws SQLException {

        List<Product> list = new ArrayList<>();

        Connection connection = ds.getConnection();

        String query = "SELECT * FROM product";
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            list.add(new Product(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price")));
        }

        statement.close();
        connection.close();

        return list;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {

        Optional<Product> optionalProduct;

        Connection connection = ds.getConnection();

        String query = "SELECT * FROM product WHERE id=" + id;
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        if (!resultSet.next()) {
            throw new RuntimeException("Object not found");
        }

        optionalProduct = Optional.of(new Product(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getDouble("price")));

        statement.close();
        connection.close();

        return optionalProduct;

    }

    @Override
    public void update(Product product) throws SQLException {

        Connection connection = ds.getConnection();

        String query = "UPDATE product SET name = ?, price = ? WHERE id = ?;";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, product.getName());
        statement.setDouble(2, product.getPrice());
        statement.setLong(3, product.getId());

        statement.execute();

        statement.close();
        connection.close();

    }

    @Override
    public void save(Product product) throws SQLException {

        Connection connection = ds.getConnection();

        String query = "INSERT INTO product VALUES (?, ?, ?);";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setLong(1, product.getId());
        statement.setString(2, product.getName());
        statement.setDouble(3, product.getPrice());

        statement.execute();

        statement.close();
        connection.close();

    }

    @Override
    public void delete(Long id) throws SQLException {

        Connection connection = ds.getConnection();

        String query = "DELETE FROM product WHERE id=?";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setLong(1, id);

        statement.execute();

        statement.close();
        connection.close();

    }

}
