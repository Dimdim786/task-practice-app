package storage;

import entity.UserEntity;
import status.UserStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserStorage {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PATRONYMIC = "patronymic";
    private static final String SURNAME = "surname";
    private static final String NICKNAME = "nickname";
    private static final String STATUS = "status";
    private static final String USER_ROLE = "user_role";

    private static final DatabaseConnection dbc = new DatabaseConnection();

    public List<UserEntity> getAllUsers() {
        List<UserEntity> users = new ArrayList<>();

        try(Connection connection = dbc.getConnection()){
            Statement statement = dbc.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM task_distributor.users ORDER BY id;");

            while (rs.next()) {
                String id = rs.getString(ID);
                String name = rs.getString(NAME);
                String patronymic = rs.getString(PATRONYMIC);
                String surname = rs.getString(SURNAME);
                String nickname = rs.getString(NICKNAME);
                String status = rs.getString(STATUS);
                String user_role = rs.getString(USER_ROLE);

                UserEntity user = new UserEntity(id, name, patronymic, surname, nickname, status, user_role);
                users.add(user);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return users;
    }

    public UserEntity getUserByID (int id) {
        UserEntity user = null;

        try(Connection connection = dbc.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM task_distributor.users WHERE id = ? ORDER BY id;");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                String name = rs.getString(NAME);
                String patronymic = rs.getString(PATRONYMIC);
                String surname = rs.getString(SURNAME);
                String nickname = rs.getString(NICKNAME);
                String status = rs.getString(STATUS);
                String user_role = rs.getString(USER_ROLE);

                user = new UserEntity(String.valueOf(id), name, patronymic, surname, nickname, status, user_role);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return user;
    }

    public UserEntity getUserByNickname (String nickname){
        UserEntity user = null;
        nickname = nickname + '%';

        try(Connection connection = dbc.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM task_distributor.users WHERE nickname LIKE ? ORDER BY id;");
            statement.setString(1, nickname);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String id = rs.getString(ID);
                String name = rs.getString(NAME);
                String patronymic = rs.getString(PATRONYMIC);
                String surname = rs.getString(SURNAME);
                String status = rs.getString(STATUS);
                String user_role = rs.getString(USER_ROLE);

                user = new UserEntity(id, name, patronymic, surname, nickname, status, user_role);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return user;
    }

    public List<UserEntity> getUsersByStatus(UserStatus status) {
        List<UserEntity> users = new ArrayList<>();

        try(Connection connection = dbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
              "SELECT * FROM task_distributor.users WHERE status LIKE ? ORDER BY id;"
            );
            statement.setString(1, status.name());
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                String id = rs.getString(ID);
                String name = rs.getString(NAME);
                String patronymic = rs.getString(PATRONYMIC);
                String surname = rs.getString(SURNAME);
                String nickname = rs.getString(NICKNAME);
                String user_role = rs.getString(USER_ROLE);

                UserEntity user = new UserEntity(id, name, patronymic, surname, nickname, String.valueOf(status), user_role);
                users.add(user);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return users;
    }

    public List<UserEntity> getUsersByUserRole(String user_role) {
        List<UserEntity> users = new ArrayList<>();

        try(Connection connection = dbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM task_distributor.users WHERE user_role LIKE ? ORDER BY id;"
            );
            statement.setString(1, user_role);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                String id = rs.getString(ID);
                String name = rs.getString(NAME);
                String patronymic = rs.getString(PATRONYMIC);
                String surname = rs.getString(SURNAME);
                String nickname = rs.getString(NICKNAME);
                String status = rs.getString(STATUS);

                UserEntity user = new UserEntity(id, name, patronymic, surname, nickname, status, user_role);
                users.add(user);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return users;
    }

    public int addUser(String name, String patronymic, String surname, String nickname, String user_role) {
        int user_id = 0;

        try(Connection connection = dbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO task_distributor.users (name, patronymic, surname, nickname, user_role) " +
                     "VALUES (?, ?, ?, ?, ?) RETURNING id", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setString(2, patronymic);
            statement.setString(3, surname);
            statement.setString(4, nickname);
            statement.setString(5, user_role);

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                user_id = rs.getInt(1);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return user_id;
    }

    public String correctNickname (String oldNick, String newNick) {

        try(Connection connection = dbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE task_distributor.users SET nickname = ? WHERE nickname = ?");

            statement.setString(1, newNick);
            statement.setString(2, oldNick);

            statement.executeUpdate();

            statement.close();
            return newNick;
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return oldNick;
    }

    public String changeUserStatus (String nickname, UserStatus status) {

        try(Connection connection = dbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE task_distributor.users SET status = ? WHERE nickname = ?");

            statement.setString(1, status.name());
            statement.setString(2, nickname);

            statement.executeUpdate();

            statement.close();
            return status.name();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return "NULL";
    }

    public String correctUserRole (String nickname, String newRole) {

        try(Connection connection = dbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE task_distributor.users SET user_role = ? WHERE nickname = ?");

            statement.setString(1, newRole);
            statement.setString(2, nickname);

            statement.executeUpdate();

            statement.close();
            return newRole;
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return "NULL";
    }

    public int deleteUserByNickname (String nickname) {
        int count = 0;

        try(Connection connection = dbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM task_distributor.users WHERE nickname = ?;");

            statement.setString(1, nickname);
            count = statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }
        return count;
    }

}
