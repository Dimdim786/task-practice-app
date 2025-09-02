package entity;

public class CommentEntity {

    private String id;
    private String task_id;
    private String author;
    private String task_comment;
    private String created_at;

    public CommentEntity(String id, String task_id, String author, String task_comment, String created_at) {
        setId(id);
        setTask_id(task_id);
        setAuthor(author);
        setTask_comment(task_comment);
        setCreated_at(created_at);
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        if (id == null) {
            this.id = "NULL";
        }
        else{
            this.id = id;
        }
    }

    public String getTask_id() {
        return task_id;
    }

    private void setTask_id(String task_id) {
        if (task_id == null) {
            this.task_id = "NULL";
        }
        else{
            this.task_id = task_id;
        }
    }

    public String getAuthor() {
        return author;
    }

    private void setAuthor(String author) {
        if (author == null) {
            this.author = "NULL";
        }
        else{
            this.author = author;
        }
    }

    public String getTask_comment() {
        return task_comment;
    }

    private void setTask_comment(String task_comment) {
        if (task_comment == null) {
            this.task_comment = "NULL";
        }
        else{
            this.task_comment = task_comment;
        }
    }

    public String getCreated_at() {
        return created_at;
    }

    private void setCreated_at(String created_at) {
        if (created_at == null) {
            this.created_at = "NULL";
        }
        else{
            this.created_at = created_at;
        }
    }

    @Override
    public String toString(){
        return getId() + " | " + getTask_id() + " | " + getAuthor() + " | "
                + getTask_comment() + " | " + getCreated_at();
    }
}
