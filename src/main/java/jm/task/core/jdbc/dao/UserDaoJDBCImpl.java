package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
public class UserDaoJDBCImpl implements UserDao {
   public UserDaoJDBCImpl() {

   }

   @Override
   public void createUsersTable() {
      try (Connection connection = Util.createConnection();PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users " +
              "(id INT NOT NULL AUTO_INCREMENT,  name VARCHAR(255), lastname VARCHAR(255), age INT, PRIMARY KEY (id))")) {
         statement.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   @Override
   public void dropUsersTable() {
      try (Connection connection = Util.createConnection();PreparedStatement statement = connection.prepareStatement("DROP TABLE IF EXISTS users")){
         statement.executeUpdate();
      } catch(SQLException e){
         e.printStackTrace();

      }
   }

   @Override
   public void saveUser(String name, String lastName, byte age) {
      try (Connection connection = Util.createConnection();PreparedStatement statement = connection.prepareStatement("INSERT INTO users(name, lastName, age) " +
              "VALUES('" + name + "', '" + lastName + "', '" + age + "')")){
         statement.executeUpdate();
         System.out.println("Пользователь " + name + " " + lastName + " " + age + " " + " добавлен");
         connection.commit();
      } catch (SQLException e) {
         e.printStackTrace();

      }
   }

   @Override
   public void removeUserById(long id) {
      try (Connection connection = Util.createConnection();PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
         statement.setLong(1, id);
         statement.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   @Override
   public List<User> getAllUsers() {
      List<User> users = new ArrayList<>();
      try (Connection connection = Util.createConnection();PreparedStatement statement = connection.prepareStatement("SELECT * FROM users where ID");
           ResultSet resultSet = statement.executeQuery()) {
         while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String lastName = resultSet.getString("lastName");
            byte age = resultSet.getByte("age");
            User user = new User(name, lastName, age);
            user.setId(id);
            users.add(user);
            connection.commit();
         }
         System.out.println(users);
      } catch (SQLException e) {
         e.printStackTrace();
      }
         return users;
      }

   @Override
   public void cleanUsersTable() {
      try (Connection connection = Util.createConnection();PreparedStatement statement = connection.prepareStatement("TRUNCATE TABLE users")){
         statement.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }

   }
}


