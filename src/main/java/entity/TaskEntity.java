package entity;

public class TaskEntity {

    private String id;
    private String project;
    private String task_description;
    private String creation_date;
    private String deadline;
    private String status;
    private String implementer;

    public TaskEntity(String id, String project, String task_description, String creation_date, String deadline, String status, String implementer) {
        setId(id);
        setProject(project);
        setTask_description(task_description);
        setCreation_date(creation_date);
        setDeadline(deadline);
        setStatus(status);
        setImplementer(implementer);
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

    public String getProject() {
        return project;
    }

    private void setProject(String project) {
        if (project == null) {
            this.project = "NULL";
        }
        else{
            this.project = project;
        }
    }

    public String getTask_description() {
        return task_description;
    }

    private void setTask_description(String task_description) {
        if (task_description == null) {
            this.task_description = "NULL";
        }
        else{
            this.task_description = task_description;
        }
    }

    public String getCreation_date() {
        return creation_date;
    }

    private void setCreation_date(String creation_date) {
        if (creation_date == null) {
            this.creation_date = "NULL";
        }
        else{
            this.creation_date = creation_date;
        }
    }

    public String getDeadline() {
        return deadline;
    }

    private void setDeadline(String deadline) {
        if (deadline == null) {
            this.deadline = "NULL";
        }
        else{
            this.deadline = deadline;
        }
    }

    public String getStatus() {
        return status;
    }

    private void setStatus(String status) {
        if (status == null) {
            this.status = "NULL";
        }
        else{
            this.status = status;
        }
    }

    public String getImplementer() {
        return implementer;
    }

    private void setImplementer(String implementer) {
        if (implementer == null) {
            this.implementer = "NULL";
        }
        else{
            this.implementer = implementer;
        }
    }

    @Override
    public String toString(){
        return getId() + " | " + getProject() + " | " + getTask_description() + " | "
                + getCreation_date() + " | " + getDeadline() + " | " + getStatus() + " | "
                + getImplementer();
    }
}
