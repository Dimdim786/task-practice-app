package storage;

import entity.TaskEntity;
import status.TaskStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage {

    private static final String ID = "id";
    private static final String PROJECT = "project_name";
    private static final String TASK_DESCRIPTION = "task_description";
    private static final String CREATION_DATE = "creation_date";
    private static final String DEADLINE = "deadline";
    private static final String STATUS = "status";
    private static final String IMPLEMENTER = "nickname";

    private static final DatabaseConnection dbc = new DatabaseConnection();

    public List<TaskEntity> getAllTasks() {
        List<TaskEntity> tasks = new ArrayList<>();

        try(Connection connection = dbc.getConnection()){
            Statement statement = dbc.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT task.id, pro.project_name, task.task_description, task.creation_date, task.deadline, task.status, u.nickname FROM task_distributor.tasks AS task " +
                            "JOIN task_distributor.projects AS pro ON task.project = pro.id " +
                            "JOIN task_distributor.users AS u ON task.implementer = u.id ORDER BY id;");

            while (rs.next()) {
                String id = rs.getString(ID);
                String project_name = rs.getString(PROJECT);
                String task_description = rs.getString(TASK_DESCRIPTION);
                String creation_date = rs.getString(CREATION_DATE);
                String deadline = rs.getString(DEADLINE);
                String status = rs.getString(STATUS);
                String implementer = rs.getString(IMPLEMENTER);

                TaskEntity task = new TaskEntity(id, project_name, task_description, creation_date, deadline, status, implementer);
                tasks.add(task);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return tasks;
    }

    public TaskEntity getTaskByID (int id) {
        TaskEntity task = null;

        try(Connection connection = dbc.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT task.id, pro.project_name, task.task_description, task.creation_date, task.deadline, task.status, u.nickname FROM task_distributor.tasks AS task " +
                            "JOIN task_distributor.projects AS pro ON task.project = pro.id " +
                            "JOIN task_distributor.users AS u ON task.implementer = u.id " +
                            "WHERE task.id = ? ORDER BY id;");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                String project_name = rs.getString(PROJECT);
                String task_description = rs.getString(TASK_DESCRIPTION);
                String creation_date = rs.getString(CREATION_DATE);
                String deadline = rs.getString(DEADLINE);
                String status = rs.getString(STATUS);
                String implementer = rs.getString(IMPLEMENTER);

                task = new TaskEntity(String.valueOf(id), project_name, task_description, creation_date, deadline, status, implementer);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return task;
    }

    public List<TaskEntity> getTasksByImplementer (String implementer) {
        List<TaskEntity> tasks = new ArrayList<>();
        implementer = implementer + '%';

        try(Connection connection = dbc.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT task.id, pro.project_name, task.task_description, task.creation_date, task.deadline, task.status, u.nickname FROM task_distributor.tasks AS task " +
                            "JOIN task_distributor.projects AS pro ON task.project = pro.id " +
                            "JOIN task_distributor.users AS u ON task.implementer = u.id " +
                            "WHERE u.nickname LIKE ? ORDER BY id;");
            statement.setString(1, implementer);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                String id = rs.getString(ID);
                String project_name = rs.getString(PROJECT);
                String task_description = rs.getString(TASK_DESCRIPTION);
                String creation_date = rs.getString(CREATION_DATE);
                String deadline = rs.getString(DEADLINE);
                String status = rs.getString(STATUS);

                TaskEntity task = new TaskEntity(id, project_name, task_description, creation_date, deadline, status, implementer);
                tasks.add(task);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return tasks;
    }

    public List<TaskEntity> getTasksByStatus (TaskStatus status) {
        List<TaskEntity> tasks = new ArrayList<>();

        try(Connection connection = dbc.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT task.id, pro.project_name, task.task_description, task.creation_date, task.deadline, task.status, u.nickname FROM task_distributor.tasks AS task " +
                            "JOIN task_distributor.projects AS pro ON task.project = pro.id " +
                            "JOIN task_distributor.users AS u ON task.implementer = u.id " +
                            "WHERE task.status LIKE ? ORDER BY id;");
            statement.setString(1, status.name());
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                String id = rs.getString(ID);
                String project_name = rs.getString(PROJECT);
                String task_description = rs.getString(TASK_DESCRIPTION);
                String creation_date = rs.getString(CREATION_DATE);
                String deadline = rs.getString(DEADLINE);
                String implementer = rs.getString(IMPLEMENTER);

                TaskEntity task = new TaskEntity(id, project_name, task_description, creation_date, deadline, String.valueOf(status), implementer);
                tasks.add(task);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return tasks;
    }

    public int addTask(String project, String task_description, Date deadline, String implementer) {
        int task_id = 0;

        try(Connection connection = dbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO task_distributor.tasks (project, task_description, deadline, implementer) " +
                            "VALUES ((SELECT id FROM task_distributor.projects WHERE project_name LIKE ?), ?, ?, (SELECT id FROM task_distributor.users WHERE nickname LIKE ?)) RETURNING id", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, project);
            statement.setString(2, task_description);
            statement.setDate(3, deadline);
            statement.setString(4, implementer);

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                task_id = rs.getInt(1);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return task_id;
    }

    public String correctDescription (int id, String newDescrip) {

        try(Connection connection = dbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE task_distributor.tasks SET task_description = ? WHERE id = ?;");

            statement.setString(1, newDescrip);
            statement.setInt(2, id);

            statement.executeUpdate();

            statement.close();
            return newDescrip;
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return "NULL";
    }

    public String correctImplementer (int id, String newImpl) {

        try(Connection connection = dbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE task_distributor.tasks SET implementer = (SELECT id FROM task_distributor.users WHERE nickname LIKE ?) " +
                            "WHERE id = ?;");

            statement.setString(1, newImpl);
            statement.setInt(2,id);

            statement.executeUpdate();

            statement.close();
            return newImpl;
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return "NULL";
    }

    public String changeTaskStatus (String id, TaskStatus status) {

        try(Connection connection = dbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE task_distributor.tasks SET status = ? WHERE id = ?");

            statement.setString(1, status.name());
            statement.setString(2, id);

            statement.executeUpdate();

            statement.close();
            return status.name();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return "NULL";
    }

    public int deleteTaskById (int id) {
        int count = 0;

        try(Connection connection = dbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM task_distributor.tasks WHERE id = ?;");

            statement.setInt(1, id);
            count = statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return count;
    }
}
