package entity;

public class ProjectEntity {

    private String id;
    private String project_name;
    private String status;

    public ProjectEntity(String id, String project_name, String status) {
        setId(id);
        setProject_name(project_name);
        setStatus(status);
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

    public String getProject_name() {
        return project_name;
    }

    private void setProject_name(String project_name) {
        if (project_name == null) {
            this.project_name = "NULL";
        }
        else{
            this.project_name = project_name;
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

    @Override
    public String toString() {
        return getId() + " | " + getProject_name() + " | " + getStatus();
    }
}
