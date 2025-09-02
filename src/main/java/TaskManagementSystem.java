import entity.CommentEntity;
import entity.ProjectEntity;
import entity.TaskEntity;
import entity.UserEntity;
import status.ProjectStatus;
import status.TaskStatus;
import status.UserStatus;
import storage.CommentStorage;
import storage.ProjectStorage;
import storage.TaskStorage;
import storage.UserStorage;

import java.sql.Date;

public class TaskManagementSystem {

    private final UserStorage userStorage = new UserStorage();
    private final ProjectStorage projectStorage = new ProjectStorage();
    private final TaskStorage taskStorage = new TaskStorage();
    private final CommentStorage commentStorage = new CommentStorage();

    public void getAllUsers() {
        for(UserEntity user : this.userStorage.getAllUsers()){
            System.out.println(user.toString());
        }
        System.out.println();
    }

    public void getUserById(int id) {
        if (id < 1) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.userStorage.getUserByID(id) == null) {
            System.out.println("Пользователь не найден");
        }
        else{
            System.out.println(this.userStorage.getUserByID(id).toString());
        }
    }

    public void getUserByNickname(String nickname) {
        if (nickname == null || nickname.isBlank()) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.userStorage.getUserByNickname(nickname) == null) {
            System.out.println("Пользователь не найден");
        }
        else{
            System.out.println(this.userStorage.getUserByNickname(nickname).toString());
        }
    }

    public void getUsersByStatus(UserStatus status) {
        if (status == null) {
            System.out.println("Введите статус пользователя");
            return;
        }

        for(UserEntity user : this.userStorage.getUsersByStatus(status)){
            System.out.println(user.toString());
        }
        System.out.println();
    }

    public void getUsersByUserRole(String user_role) {
        if (user_role == null || user_role.isBlank()) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.userStorage.getUsersByUserRole(user_role) == null) {
            System.out.println("Пользователь не найден");
        }
        else{
            System.out.println(this.userStorage.getUsersByUserRole(user_role).toString());
        }
    }

    public void addUser(String name, String patronymic, String surname, String nickname, String user_role){
        if (name == null || name.isBlank() || patronymic == null || patronymic.isBlank() ||
                surname == null || surname.isBlank() ||
                nickname == null || nickname.isBlank() || user_role == null || user_role.isBlank()) {
            System.out.println("Введите корректное значение");
            return;
        }

        int returning_id = this.userStorage.addUser(name, patronymic, surname, nickname, user_role);
        if (returning_id > 0) {
            System.out.println("Пользователь успешно добавлен");
        }
        else {
            System.out.println("Не удалось добавить пользователя");
        }
    }

    public void correctNickname(String oldNick, String newNick) {
        if (oldNick == null || oldNick.isBlank() || newNick == null || newNick.isBlank()) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.userStorage.correctNickname(oldNick, newNick).equals(oldNick)) {
            System.out.println("Не удалось изменить параметр пользователя");
        }
        else if (this.userStorage.correctNickname(oldNick, newNick).equals(newNick)){
            System.out.println("Параметр пользователя успешно изменен");
        }
    }

    public void changeUserStatus(String nickname, UserStatus status) {
        if (nickname == null || nickname.isBlank() || status == null) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.userStorage.changeUserStatus(nickname, status).equals("NULL")) {
            System.out.println("Не удалось изменить параметр пользователя");
        }
        else{
            System.out.println("Параметр пользователя успешно изменен");
        }
    }

    public void correctUserRole(String nickname, String user_role) {
        if (nickname == null || nickname.isBlank() || user_role == null || user_role.isBlank()) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.userStorage.correctUserRole(nickname, user_role).equals("NULL")) {
            System.out.println("Не удалось изменить параметр пользователя");
        }
        else if (this.userStorage.correctUserRole(nickname, user_role).equals(user_role)){
            System.out.println("Параметр пользователя успешно изменен");
        }
    }

    public void deleteUserByNickname(String nickname) {
        if (nickname == null || nickname.isBlank()) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.userStorage.deleteUserByNickname(nickname) > 0) {
            System.out.println("Пользователь успешно удален");
        }
        else{
            System.out.println("Не удалось удалить пользователя");
        }
    }




    public void getAllProjects() {
        for(ProjectEntity project : this.projectStorage.getAllProjects()){
            System.out.println(project.toString());
        }
        System.out.println();
    }

    public void getProjectById(int id) {
        if (id < 1) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.projectStorage.getProjectByID(id) == null) {
            System.out.println("Проект не найден");
        }
        else{
            System.out.println(this.projectStorage.getProjectByID(id).toString());
        }
    }

    public void getProjectByName(String name) {
        if (name == null || name.isBlank()) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.projectStorage.getProjectByName(name) == null) {
            System.out.println("Проект не найден");
        }
        else{
            System.out.println(this.projectStorage.getProjectByName(name).toString());
        }
    }

    public void getProjectsByStatus(ProjectStatus status) {
        if (status == null) {
            System.out.println("Введите статус проекта");
            return;
        }

        for(ProjectEntity project : this.projectStorage.getProjectByStatus(status)){
            System.out.println(project.toString());
        }
        System.out.println();
    }

    public void addProject(String project_name){
        if (project_name == null || project_name.isBlank()) {
            System.out.println("Введите корректное значение");
            return;
        }

        int returning_id = this.projectStorage.addProject(project_name);
        if (returning_id > 0) {
            System.out.println("Проект успешно добавлен");
        }
        else {
            System.out.println("Не удалось добавить проект");
        }
    }

    public void correctProjectName(String oldName, String newName) {
        if (oldName == null || oldName.isBlank() || newName == null || newName.isBlank()) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.projectStorage.correctName(oldName, newName).equals(oldName)) {
            System.out.println("Не удалось изменить название проекта");
        }
        else if (this.projectStorage.correctName(oldName, newName).equals(newName)){
            System.out.println("Название проекта успешно изменено");
        }
    }

    public void changeProjectStatus(String project_name, ProjectStatus status) {
        if (project_name == null || project_name.isBlank() || status == null) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.projectStorage.changeProjectStatus(project_name, status).equals("NULL")) {
            System.out.println("Не удалось изменить статус проекта");
        }
        else{
            System.out.println("Статус проекта успешно изменен");
        }
    }

    public void deleteProjectByName(String project_name) {
        if (project_name == null || project_name.isBlank()) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.projectStorage.deleteProjectByName(project_name) > 0) {
            System.out.println("Проект успешно удален");
        }
        else{
            System.out.println("Не удалось удалить проект");
        }
    }




    public void getAllTasks() {
        for(TaskEntity task : this.taskStorage.getAllTasks()){
            System.out.println(task.toString());
        }
        System.out.println();
    }

    public void getTaskById(int id) {
        if (id < 1) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.taskStorage.getTaskByID(id) == null) {
            System.out.println("Задача не найдена");
        }
        else{
            System.out.println(this.taskStorage.getTaskByID(id).toString());
        }
    }

    public void getTasksByImplementer(String implementer) {
        if (implementer == null || implementer.isBlank()) {
            System.out.println("Введите корректное значение");
            return;
        }

        for(TaskEntity task : this.taskStorage.getTasksByImplementer(implementer)){
            System.out.println(task.toString());
        }
        System.out.println();
    }

    public void getTasksByStatus(TaskStatus status) {
        if (status == null) {
            System.out.println("Введите статус задачи");
            return;
        }

        for(TaskEntity task : this.taskStorage.getTasksByStatus(status)){
            System.out.println(task.toString());
        }
        System.out.println();
    }

    public void addTask(String project, String task_description, Date deadline, String implementer){
        if (project == null || project.isBlank() || task_description == null || task_description.isBlank() ||
                deadline == null ||
                implementer == null || implementer.isBlank()) {
            System.out.println("Введите корректное значение");
            return;
        }

        int returning_id = this.taskStorage.addTask(project, task_description, deadline, implementer);
        if (returning_id > 0) {
            System.out.println("Задача успешно добавлена");
        }
        else {
            System.out.println("Не удалось добавить задачу");
        }
    }

    public void correctTaskDescription(int id, String newD) {
        if (id <= 0 || newD == null || newD.isBlank()) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.taskStorage.correctDescription(id, newD).equals("NULL")) {
            System.out.println("Не удалось изменить описание задачи");
        }
        else if (this.taskStorage.correctDescription(id, newD).equals(newD)){
            System.out.println("Описание задачи успешно изменено");
        }
    }

    public void correctImplementer(int id, String newImpl) {
        if (id <= 0 || newImpl == null || newImpl.isBlank()) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.taskStorage.correctImplementer(id, newImpl).equals("NULL")) {
            System.out.println("Не удалось изменить исполнителя задачи");
        }
        else if (this.taskStorage.correctImplementer(id, newImpl).equals(newImpl)){
            System.out.println("Исполнитель задачи успешно изменен");
        }
    }

    public void changeTaskStatus(String id, TaskStatus status) {
        if (id == null || id.isBlank() || status == null) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.taskStorage.changeTaskStatus(id, status).equals("NULL")) {
            System.out.println("Не удалось изменить статус задачи");
        }
        else{
            System.out.println("Статус задачи успешно изменен");
        }
    }

    public void deleteTaskByID(int id) {
        if (id <= 0) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.taskStorage.deleteTaskById(id) > 0) {
            System.out.println("Задача успешно удалена");
        }
        else{
            System.out.println("Не удалось удалить задачу");
        }
    }




    public void getAllComments() {
        for(CommentEntity comment : this.commentStorage.getAllComments()){
            System.out.println(comment.toString());
        }
        System.out.println();
    }

    public void getCommentById(int id) {
        if (id < 1) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.commentStorage.getCommentByID(id) == null) {
            System.out.println("Комментарий не найден");
        }
        else{
            System.out.println(this.commentStorage.getCommentByID(id).toString());
        }
    }

    public void getCommentByTaskID(int task_id) {
        if (task_id < 1) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.commentStorage.getCommentByTaskID(task_id) == null) {
            System.out.println("Комментарий не найден");
        }
        else{
            System.out.println(this.commentStorage.getCommentByTaskID(task_id).toString());
        }
    }

    public void getCommentByAuthorAndTaskID(String author, int task_id) {
        if (task_id < 1 || author == null || author.isBlank()) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.commentStorage.getCommentByAuthorAndTaskID(author, task_id) == null) {
            System.out.println("Комментарий не найден");
        }
        else{
            System.out.println(this.commentStorage.getCommentByAuthorAndTaskID(author, task_id).toString());
        }
    }

    public void addComment(int task_id, String author, String task_comment){
        if (task_id < 1 || author == null || author.isBlank() || task_comment == null || task_comment.isBlank()) {
            System.out.println("Введите корректное значение");
            return;
        }

        int returning_id = this.commentStorage.addComment(task_id, author, task_comment);
        if (returning_id > 0) {
            System.out.println("Комментарий успешно добавлен");
        }
        else {
            System.out.println("Не удалось добавить комментарий");
        }
    }

    public void correctComment(int id, String newCom) {
        if (id <= 0 || newCom == null || newCom.isBlank()) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.commentStorage.correctComment(id, newCom).equals("NULL")) {
            System.out.println("Не удалось изменить комментарий");
        }
        else if(this.commentStorage.correctComment(id, newCom).equals(newCom)){
            System.out.println("Комментарий успешно изменен");
        }
    }

    public void deleteCommentByID(int id) {
        if (id <= 0) {
            System.out.println("Введите корректное значение");
            return;
        }

        if (this.commentStorage.deleteCommentById(id) > 0) {
            System.out.println("Комментарий успешно удален");
        }
        else{
            System.out.println("Не удалось удалить комментарий");
        }
    }
}
