package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryTest {

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1L, "prod1", 111.0),
            new Product(2L, "prod2", 222.0),
            new Product(3L, "prod3", 333.0),
            new Product(4L, "prod4", 444.0),
            new Product(5L, "prod5", 555.0)
    );
    private final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(2L, "prod2", 222.0);
    private final Product EXPECTED_SAVED_PRODUCT = new Product(10L, "saved prod", 999.0);
    private final Product EXPECTED_UPDATED_PRODUCT = new Product(3L, "updated prod", 888.0);

    private DataSource ds;
    ProductsRepository repository;

    @BeforeEach
    void init() {

        ds = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();

        repository = new ProductsRepositoryJdbcImpl(ds);

    }

    @Test
    void findAllCheck() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, repository.findAll());
    }

    @Test
    void findByIdCheck() throws SQLException {
        Assertions.assertEquals(Optional.of(EXPECTED_FIND_BY_ID_PRODUCT), repository.findById(2L));
    }

    @Test
    void saveCheck() throws SQLException {
        repository.save(EXPECTED_SAVED_PRODUCT);
        Assertions.assertEquals(EXPECTED_SAVED_PRODUCT, repository.findById(10L).get());
    }

    @Test
    void updateCheck() throws SQLException {
        repository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, repository.findById(EXPECTED_UPDATED_PRODUCT.getId()).get());
    }

    @Test
    void deleteCheck() throws SQLException {
        repository.delete(1L);
        Assertions.assertThrowsExactly(RuntimeException.class, () -> repository.findById(1L));
    }
}
