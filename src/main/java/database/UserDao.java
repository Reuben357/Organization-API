package database;

import config.Configs;
import models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Collections;
import java.util.List;

public class UserDao {
    List<User> user, showUsers;
    static final Logger UserDaoLogger = LoggerFactory.getLogger(User.class.getSimpleName());
    //public static Sql2o databaseConnection = new Sql2o(Configs.DB_URI, Configs.DB_USER, Configs.DB_PASS);

    private static final Connection databaseConnection = Database.databaseConnection.open();


    public UserDao() {
    }

    public User add(User user) {
        try (databaseConnection) {
            String insertUserQuery = "INSERT INTO users (username, address, phone, email, department_id, position, roles) VALUES (:username, :address, :phone, :email, :department_id, :position, :roles);";
            int id = (int) databaseConnection.createQuery(insertUserQuery, true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();

            user.setId(id);
        } catch (Exception exception) {
            UserDaoLogger.error("Could not save data, error{}", exception.getLocalizedMessage());
        }
        UserDaoLogger.info("User returned is {}", user.toString());
        return user;
    }

    public List<User> getAll() {
        try (databaseConnection) {
            return databaseConnection.createQuery("SELECT * FROM users")
                    .executeAndFetch(User.class);
        }
    }

    public List<User> getAllUsersByDepartment(int department_id) {
        String getAllUsersByDepartment = "SELECT * FROM users WHERE department_id=:department_id";
        try (Connection connection = Database.databaseConnection.open()) {
            return connection.createQuery(getAllUsersByDepartment)
                    .addParameter("department_id", department_id)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(User.class);
        }
    }

    public User findById(int id){
        String findById =  "SELECT * FROM users WHERE id=:id;";
        try(Connection connection = Database.databaseConnection.open()){
            return connection.createQuery(findById)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }
}
