package storage;

import entity.ProjectEntity;
import status.ProjectStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectStorage {

    private static final String ID = "id";
    private static final String PROJECT_NAME = "project_name";
    private static final String STATUS = "status";

    private static final DatabaseConnection dbc = new DatabaseConnection();

    public List<ProjectEntity> getAllProjects() {
        List<ProjectEntity> projects = new ArrayList<>();

        try(Connection connection = dbc.getConnection()){
            Statement statement = dbc.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM task_distributor.projects ORDER BY id;");

            while (rs.next()) {
                String id = rs.getString(ID);
                String project_name = rs.getString(PROJECT_NAME);
                String status = rs.getString(STATUS);

                ProjectEntity project = new ProjectEntity(id, project_name, status);
                projects.add(project);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return projects;
    }

    public ProjectEntity getProjectByID (int id) {
        ProjectEntity project = null;

        try(Connection connection = dbc.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM task_distributor.projects WHERE id = ? ORDER BY id;");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                String project_name = rs.getString(PROJECT_NAME);
                String status = rs.getString(STATUS);

                project = new ProjectEntity(String.valueOf(id), project_name, status);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return project;
    }

    public ProjectEntity getProjectByName (String project_name) {
        ProjectEntity project = null;

        try(Connection connection = dbc.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM task_distributor.projects WHERE project_name LIKE ? ORDER BY id;");
            statement.setString(1, project_name);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                String id = rs.getString(ID);
                String status = rs.getString(STATUS);

                project = new ProjectEntity(id, project_name, status);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return project;
    }

    public List<ProjectEntity> getProjectByStatus (ProjectStatus status) {
        List<ProjectEntity> projects = new ArrayList<>();

        try(Connection connection = dbc.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM task_distributor.projects WHERE status LIKE ? ORDER BY id;");
            statement.setString(1, status.name());
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                String id = rs.getString(ID);
                String project_name = rs.getString(PROJECT_NAME);

                ProjectEntity project = new ProjectEntity(id, project_name, String.valueOf(status));
                projects.add(project);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return projects;
    }

    public int addProject(String project_name) {
        int project_id = 0;

        try(Connection connection = dbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO task_distributor.projects (project_name) " +
                            "VALUES (?) RETURNING id", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, project_name);

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                project_id = rs.getInt(1);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return project_id;
    }

    public String correctName (String oldName, String newName) {

        try(Connection connection = dbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE task_distributor.projects SET project_name = ? WHERE project_name = ?");

            statement.setString(1, newName);
            statement.setString(2, oldName);

            statement.executeUpdate();

            statement.close();
            return newName;
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return oldName;
    }

    public String changeProjectStatus (String project_name, ProjectStatus status) {

        try(Connection connection = dbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE task_distributor.projects SET status = ? WHERE project_name = ?");

            statement.setString(1, status.name());
            statement.setString(2, project_name);

            statement.executeUpdate();

            statement.close();
            return status.name();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return "NULL";
    }

    public int deleteProjectByName (String project_name) {
        int count = 0;

        try(Connection connection = dbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM task_distributor.projects WHERE project_name LIKE ?;");

            statement.setString(1, project_name);
            count = statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return count;
    }
}
