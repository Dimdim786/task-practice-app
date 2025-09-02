package entity;

public class UserEntity {

    private String id;
    private String name;
    private String patronymic;
    private String surname;
    private String nickname;
    private String status;
    private String user_role;

    public UserEntity(String id, String name, String patronymic, String surname, String nickname, String status, String user_role) {
        setId(id);
        setName(name);
        setPatronymic(patronymic);
        setSurname(surname);
        setNickname(nickname);
        setStatus(status);
        setUser_role(user_role);
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

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null) {
            this.name = "NULL";
        }
        else{
            this.name = name;
        }
    }

    public String getPatronymic() {
        return patronymic;
    }

    private void setPatronymic(String patronymic) {
        if (patronymic == null) {
            this.patronymic = "NULL";
        }
        else{
            this.patronymic = patronymic;
        }
    }

    public String getSurname() {
        return surname;
    }

    private void setSurname(String surname) {
        if (surname == null){
            this.surname = "NULL";
        }
        else{
            this.surname = surname;
        }
    }

    public String getNickname() {
        return nickname;
    }

    private void setNickname(String nickname) {
        if (nickname == null){
            this.nickname = "NULL";
        }
        else{
            this.nickname = nickname;
        }
    }

    public String getStatus() {
        return status;
    }

    private void setStatus(String status) {
        if (status == null){
            this.status = "NULL";
        }
        else{
            this.status = status;
        }
    }

    public String getUser_role() {
        return user_role;
    }

    private void setUser_role(String user_role) {
        if (user_role == null){
            this.user_role = "NULL";
        }
        else{
            this.user_role = user_role;
        }
    }

    @Override
    public String toString(){
        return getId() + " | " + getName() + " | " + getPatronymic() + " | "
                + getSurname() + " | " + getNickname() + " | " +
                getStatus() + " | " + getUser_role();
    }
}
