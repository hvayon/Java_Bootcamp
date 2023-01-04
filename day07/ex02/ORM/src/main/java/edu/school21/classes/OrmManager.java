package edu.school21.classes;

import com.zaxxer.hikari.HikariDataSource;
import org.reflections.Reflections;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrmManager {

    public static final String VALUES = ") VALUES (";
    public static final String STRING = "string";
    public static final String STR2 = "'";
    public static final String UPDATE_ = "UPDATE ";
    public static final String SET = " SET ";
    public static final String STR4 = "=";
    public static final String WHERE_ID = " WHERE id=";
    public static final String STR5 = ";";
    public static final String SELECT_FROM = "SELECT * FROM ";
    public static final String WHERE_ID1 = " WHERE id=";
    public static final String BIGINT = " BIGINT";
    public static final String BOOLEAN = " boolean";
    public static final String BOOLEAN1 = "boolean";
    public static final String LONG = "long";
    public static final String STR8 = ");";
    Connection connection;
    private String tableName;
    private final DataSource dataSource;
    public OrmManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void init() throws ClassNotFoundException, SQLException {
        Connection connection = dataSource.getConnection();
        Reflections reflections;
        reflections = new Reflections("edu.school21.classes");
        Set<Class<?>> set = reflections.getTypesAnnotatedWith(OrmEntity.class);

        List<String> classes = set.stream()
                .map(Class::getCanonicalName)
                .collect(Collectors.toList());

        for (String aClass : classes) {
            Class<?> cls = Class.forName(aClass);
            OrmEntity ormEntity = cls.getAnnotation(OrmEntity.class);

            String tableName = ormEntity.table();
            this.tableName = tableName;
            try {
                Statement statement = connection.createStatement();
                String request = ("drop table if exists " + tableName + " cascade;");
                statement.execute(request);
                System.out.println(request);

                Field[] fields = cls.getDeclaredFields();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
                stringBuilder.append(tableName);
                stringBuilder.append(" (");

                for (int i = 0; i < fields.length; i++) {
                    if (fields[i].isAnnotationPresent(OrmColumnId.class)){
                        stringBuilder.append(fields[i].getName());
                        stringBuilder.append(" SERIAL PRIMARY KEY");
                    }
                    if (fields[i].isAnnotationPresent(OrmColumn.class)){
                        OrmColumn ormColumn = fields[i].getAnnotation(OrmColumn.class);
                        stringBuilder.append(ormColumn.name());
                        if (fields[i].getType().getSimpleName().equalsIgnoreCase("string")){
                            stringBuilder.append(" varchar(").append(ormColumn.length()).append(")");
                        } else if (fields[i].getType().getSimpleName().equalsIgnoreCase("integer")){
                            stringBuilder.append(" INTEGER");
                        } else if (fields[i].getType().getSimpleName().equalsIgnoreCase(LONG)){
                            stringBuilder.append(BIGINT);
                        } else if (fields[i].getType().getSimpleName().equalsIgnoreCase(BOOLEAN1)){
                            stringBuilder.append(BOOLEAN);
                        }
                    }
                    if (i != fields.length - 1){
                        stringBuilder.append(", ");
                    }
                }
                stringBuilder.append(STR8);
                statement.execute(stringBuilder.toString());
                System.out.println(stringBuilder);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void save(Object entity){
        Class<?> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (!clazz.isAnnotationPresent(OrmEntity.class)){
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ");
        stringBuilder.append(this.tableName);
        stringBuilder.append(" (");

        for (int i = 1; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(OrmColumn.class)){
                OrmColumn ormColumn = fields[i].getAnnotation(OrmColumn.class);
                stringBuilder.append(ormColumn.name());
            }

            if (i != fields.length - 1){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(VALUES);

        for (int i = 1; i < fields.length; i++){

            try {
                fields[i].setAccessible(true);
                Object object = fields[i].get(entity);

                if (fields[i].getType().getSimpleName().equalsIgnoreCase(STRING)){
                    stringBuilder.append(STR2);
                }

                stringBuilder.append(object);

                if (fields[i].getType().getSimpleName().equalsIgnoreCase(STRING)){
                    stringBuilder.append(STR2);
                }

                if (i != fields.length - 1){
                    stringBuilder.append(", ");
                }
                fields[i].setAccessible(false);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        stringBuilder.append(");");
        System.out.println(stringBuilder);

        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(stringBuilder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(Object entity){
        Class<?> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();

        if (!clazz.isAnnotationPresent(OrmEntity.class)){
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(UPDATE_).append(tableName).append(SET);
        for (int i = 1; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(OrmColumn.class)) {
                OrmColumn ormColumn = fields[i].getAnnotation(OrmColumn.class);
                stringBuilder.append(ormColumn.name());
                stringBuilder.append(STR4);
            }
            try {
                fields[i].setAccessible(true);
                Object object = fields[i].get(entity);
                if (fields[i].getType().getSimpleName().equalsIgnoreCase(STRING)){
                    stringBuilder.append(STR2);
                }

                stringBuilder.append(object);

                if (fields[i].getType().getSimpleName().equalsIgnoreCase("string")){
                    stringBuilder.append(STR2);
                }

                if (i != fields.length - 1){
                    stringBuilder.append(", ");
                }
                fields[i].setAccessible(false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        try {
            fields[0].setAccessible(true);
            Object object = fields[0].get(entity);
            stringBuilder.append(WHERE_ID);
            stringBuilder.append(object).append(STR5);
            fields[0].setAccessible(false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println(stringBuilder);
        Statement statement = null;

        try (Connection connection = dataSource.getConnection()){
            statement = connection.createStatement();
            statement.execute(stringBuilder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T> T findById(Long id, Class<T> aClass){
        if (!aClass.isAnnotationPresent(OrmEntity.class)){
            return null;
        }

        OrmEntity ormEntity = aClass.getAnnotation(OrmEntity.class);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SELECT_FROM);
        stringBuilder.append(ormEntity.table());
        stringBuilder.append(WHERE_ID1);
        stringBuilder.append(id);
        stringBuilder.append(STR5);
        ResultSet tableSet = null;
        T object = null;

        try (Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            System.out.println(stringBuilder);

            tableSet = statement.executeQuery(stringBuilder.toString());
            object = aClass.newInstance();
            tableSet.next();

            Field[] fields = aClass.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(OrmColumnId.class)){
                    field.set(object, tableSet.getLong("id"));
                } else if (field.isAnnotationPresent(OrmColumn.class)){
                    OrmColumn ormColumn = field.getAnnotation(OrmColumn.class);
                    field.set(object, tableSet.getObject(ormColumn.name()));
                }

            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }
}
