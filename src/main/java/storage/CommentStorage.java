package storage;

import entity.CommentEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentStorage {

    private static final String ID = "id";
    private static final String TASK_ID = "task_id";
    private static final String AUTHOR = "nickname";
    private static final String TASK_COMMENT  = "task_comment";
    private static final String CREATED_AT = "created_at";

    private static final DatabaseConnection dbc = new DatabaseConnection();

    public List<CommentEntity> getAllComments() {
        List<CommentEntity> comments = new ArrayList<>();

        try(Connection connection = dbc.getConnection()){
            Statement statement = dbc.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT tc.id, tc.task_id, u.nickname, tc.task_comment, tc.created_at FROM task_distributor.task_comments AS tc " +
                    "JOIN task_distributor.users AS u ON tc.author = u.id ORDER BY id;");

            while (rs.next()) {
                String id = rs.getString(ID);
                String task_id = rs.getString(TASK_ID);
                String author = rs.getString(AUTHOR);
                String task_comment = rs.getString(TASK_COMMENT);
                String created_at = rs.getString(CREATED_AT);

                CommentEntity comment = new CommentEntity(id, task_id, author, task_comment, created_at);
                comments.add(comment);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return comments;
    }

    public CommentEntity getCommentByID (int id) {
        CommentEntity comment = null;

        try(Connection connection = dbc.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT tc.id, tc.task_id, u.nickname, tc.task_comment, tc.created_at FROM task_distributor.task_comments AS tc " +
                            "JOIN task_distributor.users AS u ON tc.author = u.id " +
                            "WHERE tc.id = ? ORDER BY id;");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                String task_id = rs.getString(TASK_ID);
                String author = rs.getString(AUTHOR);
                String task_comment = rs.getString(TASK_COMMENT);
                String created_at = rs.getString(CREATED_AT);

                comment = new CommentEntity(String.valueOf(id), task_id, author, task_comment, created_at);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return comment;
    }

    public CommentEntity getCommentByTaskID (int task_id) {
        CommentEntity comment = null;

        try(Connection connection = dbc.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT tc.id, tc.task_id, u.nickname, tc.task_comment, tc.created_at FROM task_distributor.task_comments AS tc " +
                            "JOIN task_distributor.users AS u ON tc.author = u.id " +
                            "WHERE tc.task_id = ? ORDER BY id;");
            statement.setInt(1, task_id);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                String id = rs.getString(ID);
                String author = rs.getString(AUTHOR);
                String task_comment = rs.getString(TASK_COMMENT);
                String created_at = rs.getString(CREATED_AT);

                comment = new CommentEntity(id, String.valueOf(task_id), author, task_comment, created_at);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return comment;
    }

    public List<CommentEntity> getCommentByAuthorAndTaskID (String author, int task_id) {
        List<CommentEntity> comments = new ArrayList<>();
        author = author + '%';

        try(Connection connection = dbc.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT tc.id, tc.task_id, u.nickname, tc.task_comment, tc.created_at FROM task_distributor.task_comments AS tc " +
                            "JOIN task_distributor.users AS u ON tc.author = u.id " +
                            "WHERE tc.task_id = ? AND u.nickname LIKE ? ORDER BY id;");
            statement.setInt(1, task_id);
            statement.setString(2, author);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                String id = rs.getString(ID);
                String task_comment = rs.getString(TASK_COMMENT);
                String created_at = rs.getString(CREATED_AT);

                CommentEntity comment = new CommentEntity(id, String.valueOf(task_id), author, task_comment, created_at);
                comments.add(comment);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return comments;
    }

    public int addComment(int task_id, String author, String task_comment) {
        int comment_id = 0;

        try(Connection connection = dbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO task_distributor.task_comments (task_id, author, task_comment) " +
                            "VALUES (?, (SELECT id FROM task_distributor.users WHERE nickname LIKE ?), ?) RETURNING id", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, task_id);
            statement.setString(2, author);
            statement.setString(3, task_comment);

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                comment_id = rs.getInt(1);
            }

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return comment_id;
    }

    public String correctComment (int id, String newCom) {

        try(Connection connection = dbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE task_distributor.task_comments SET task_comment = ? WHERE id = ?");

            statement.setString(1, newCom);
            statement.setInt(2, id);

            statement.executeUpdate();

            statement.close();
            return newCom;
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return "NULL";
    }

    public int deleteCommentById (int id) {
        int count = 0;

        try(Connection connection = dbc.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM task_distributor.task_comments WHERE id = ?;");

            statement.setInt(1, id);
            count = statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            SQLExceptionHandling.handling(e.getSQLState());
        }

        return count;
    }
}
