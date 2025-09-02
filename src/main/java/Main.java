import status.ProjectStatus;
import status.TaskStatus;
import status.UserStatus;

import java.sql.Date;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskManagementSystem system = new TaskManagementSystem();

    public static void main(String[] args) {

        System.out.println("Task Distributor");

        try {
            runApplication();
        } finally {
            scanner.close();
            System.out.println("Приложение завершено.");
        }
    }

    private static void runApplication() {
        while (true) {
            printMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> userMenu();
                case "2" -> projectMenu();
                case "3" -> taskMenu();
                case "4" -> commentMenu();
                case "0" -> { return; }
                default -> System.out.println("Неверный выбор! Попробуйте снова.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\nГлавное меню");
        System.out.println("1. Управление пользователями");
        System.out.println("2. Управление проектами");
        System.out.println("3. Управление задачами");
        System.out.println("4. Управление комментариями");
        System.out.println("0. Выход");
        System.out.print("Выберите раздел: ");
    }

    private static void userMenu() {
        while (true) {
            System.out.println("\nУправление пользователями");
            System.out.println("1. Показать всех пользователей");
            System.out.println("2. Найти пользователя по ID");
            System.out.println("3. Найти пользователя по никнейму");
            System.out.println("4. Найти пользователей по статусу");
            System.out.println("5. Найти пользователей по роли");
            System.out.println("6. Добавить пользователя");
            System.out.println("7. Изменить никнейм");
            System.out.println("8. Изменить статус пользователя");
            System.out.println("9. Изменить роль пользователя");
            System.out.println("10. Удалить пользователя");
            System.out.println("0. Назад");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> system.getAllUsers();
                case "2" -> getUserById();
                case "3" -> getUserByNickname();
                case "4" -> getUsersByStatus();
                case "5" -> getUsersByUserRole();
                case "6" -> addUser();
                case "7" -> correctNickname();
                case "8" -> changeUserStatus();
                case "9" -> correctUserRole();
                case "10" -> deleteUserByNickname();
                case "0" -> { return; }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    private static void getUserById() {
        System.out.print("Введите ID пользователя: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        system.getUserById(id);
    }

    private static void getUserByNickname() {
        System.out.print("Введите никнейм пользователя: ");
        String nickname = scanner.nextLine().trim();
        system.getUserByNickname(nickname);
    }

    private static void getUsersByStatus() {
        System.out.println("Выберите статус:");
        System.out.println("1. FREE");
        System.out.println("2. WORK");
        System.out.print("Ваш выбор: ");

        String choice = scanner.nextLine().trim();
        UserStatus status = switch (choice) {
            case "1" -> UserStatus.FREE;
            case "2" -> UserStatus.WORK;
            default -> {
                System.out.println("Неверный выбор статуса");
                yield null;
            }
        };

        if (status != null) {
            system.getUsersByStatus(status);
        }
    }

    private static void getUsersByUserRole() {
        System.out.print("Введите роль пользователя: ");
        String role = scanner.nextLine().trim();
        system.getUsersByUserRole(role);
    }

    private static void addUser() {
        System.out.print("Имя: ");
        String name = scanner.nextLine().trim();

        System.out.print("Отчество: ");
        String patronymic = scanner.nextLine().trim();

        System.out.print("Фамилия: ");
        String surname = scanner.nextLine().trim();

        System.out.print("Никнейм: ");
        String nickname = scanner.nextLine().trim();

        System.out.print("Роль: ");
        String role = scanner.nextLine().trim();

        system.addUser(name, patronymic, surname, nickname, role);
    }

    private static void correctNickname() {
        System.out.print("Текущий никнейм: ");
        String oldNick = scanner.nextLine().trim();

        System.out.print("Новый никнейм: ");
        String newNick = scanner.nextLine().trim();

        system.correctNickname(oldNick, newNick);
    }

    private static void changeUserStatus() {
        System.out.print("Никнейм пользователя: ");
        String nickname = scanner.nextLine().trim();

        System.out.println("Выберите новый статус:");
        System.out.println("1. FREE");
        System.out.println("2. WORK");
        System.out.print("Ваш выбор: ");

        String choice = scanner.nextLine().trim();
        UserStatus status = switch (choice) {
            case "1" -> UserStatus.FREE;
            case "2" -> UserStatus.WORK;
            default -> {
                System.out.println("Неверный выбор статуса");
                yield null;
            }
        };

        if (status != null) {
            system.changeUserStatus(nickname, status);
        }
    }

    private static void correctUserRole() {
        System.out.print("Никнейм пользователя: ");
        String nickname = scanner.nextLine().trim();

        System.out.print("Новая роль: ");
        String newRole = scanner.nextLine().trim();

        system.correctUserRole(nickname, newRole);
    }

    private static void deleteUserByNickname() {
        System.out.print("Введите никнейм пользователя для удаления: ");
        String nickname = scanner.nextLine().trim();
        system.deleteUserByNickname(nickname);
    }

    private static void projectMenu() {
        while (true) {
            System.out.println("\nУправление проектами");
            System.out.println("1. Показать все проекты");
            System.out.println("2. Найти проект по ID");
            System.out.println("3. Найти проект по названию");
            System.out.println("4. Найти проекты по статусу");
            System.out.println("5. Добавить проект");
            System.out.println("6. Изменить название проекта");
            System.out.println("7. Изменить статус проекта");
            System.out.println("8. Удалить проект");
            System.out.println("0. Назад");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> system.getAllProjects();
                case "2" -> getProjectById();
                case "3" -> getProjectByName();
                case "4" -> getProjectsByStatus();
                case "5" -> addProject();
                case "6" -> correctProjectName();
                case "7" -> changeProjectStatus();
                case "8" -> deleteProjectByName();
                case "0" -> { return; }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    private static void getProjectById() {
        System.out.print("Введите ID проекта: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        system.getProjectById(id);
    }

    private static void getProjectByName() {
        System.out.print("Введите название проекта: ");
        String name = scanner.nextLine().trim();
        system.getProjectByName(name);
    }

    private static void getProjectsByStatus() {
        System.out.println("Выберите статус проекта:");
        System.out.println("1. planning");
        System.out.println("2. in_progress");
        System.out.println("3. on_hold");
        System.out.println("4. review");
        System.out.println("5. completed");
        System.out.print("Ваш выбор: ");

        String choice = scanner.nextLine().trim();
        ProjectStatus status = switch (choice) {
            case "1" -> ProjectStatus.planning;
            case "2" -> ProjectStatus.in_progress;
            case "3" -> ProjectStatus.on_hold;
            case "4" -> ProjectStatus.review;
            case "5" -> ProjectStatus.completed;
            default -> {
                System.out.println("Неверный выбор статуса");
                yield null;
            }
        };

        if (status != null) {
            system.getProjectsByStatus(status);
        }
    }

    private static void addProject() {
        System.out.print("Название проекта: ");
        String name = scanner.nextLine().trim();
        system.addProject(name);
    }

    private static void correctProjectName() {
        System.out.print("Текущее название: ");
        String oldName = scanner.nextLine().trim();

        System.out.print("Новое название: ");
        String newName = scanner.nextLine().trim();

        system.correctProjectName(oldName, newName);
    }

    private static void changeProjectStatus() {
        System.out.print("Название проекта: ");
        String name = scanner.nextLine().trim();

        System.out.println("Выберите новый статус:");
        System.out.println("1. planning");
        System.out.println("2. in_progress");
        System.out.println("3. on_hold");
        System.out.println("4. review");
        System.out.println("5. completed");
        System.out.print("Ваш выбор: ");

        String choice = scanner.nextLine().trim();
        ProjectStatus status = switch (choice) {
            case "1" -> ProjectStatus.planning;
            case "2" -> ProjectStatus.in_progress;
            case "3" -> ProjectStatus.on_hold;
            case "4" -> ProjectStatus.review;
            case "5" -> ProjectStatus.completed;
            default -> {
                System.out.println("Неверный выбор статуса");
                yield null;
            }
        };

        if (status != null) {
            system.changeProjectStatus(name, status);
        }
    }

    private static void deleteProjectByName() {
        System.out.print("Введите название проекта для удаления: ");
        String name = scanner.nextLine().trim();
        system.deleteProjectByName(name);
    }

    private static void taskMenu() {
        while (true) {
            System.out.println("\nУправление задачами");
            System.out.println("1. Показать все задачи");
            System.out.println("2. Найти задачу по ID");
            System.out.println("3. Найти задачи по исполнителю");
            System.out.println("4. Найти задачи по статусу");
            System.out.println("5. Добавить задачу");
            System.out.println("6. Изменить описание задачи");
            System.out.println("7. Изменить исполнителя задачи");
            System.out.println("8. Изменить статус задачи");
            System.out.println("9. Удалить задачу");
            System.out.println("0. Назад");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> system.getAllTasks();
                case "2" -> getTaskById();
                case "3" -> getTasksByImplementer();
                case "4" -> getTasksByStatus();
                case "5" -> addTask();
                case "6" -> correctTaskDescription();
                case "7" -> correctImplementer();
                case "8" -> changeTaskStatus();
                case "9" -> deleteTaskByID();
                case "0" -> { return; }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    private static void getTaskById() {
        System.out.print("Введите ID задачи: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        system.getTaskById(id);
    }

    private static void getTasksByImplementer() {
        System.out.print("Введите никнейм исполнителя: ");
        String implementer = scanner.nextLine().trim();
        system.getTasksByImplementer(implementer);
    }

    private static void getTasksByStatus() {
        System.out.println("Выберите статус задачи:");
        System.out.println("1. undone");
        System.out.println("2. in_progress");
        System.out.println("3. done");
        System.out.print("Ваш выбор: ");

        String choice = scanner.nextLine().trim();
        TaskStatus status = switch (choice) {
            case "1" -> TaskStatus.undone;
            case "2" -> TaskStatus.in_progress;
            case "3" -> TaskStatus.done;
            default -> {
                System.out.println("Неверный выбор статуса");
                yield null;
            }
        };

        if (status != null) {
            system.getTasksByStatus(status);
        }
    }

    private static void addTask() {
        System.out.print("Название проекта: ");
        String project = scanner.nextLine().trim();

        System.out.print("Описание задачи: ");
        String description = scanner.nextLine().trim();

        System.out.print("Срок выполнения (гггг-мм-дд): ");
        String deadlineStr = scanner.nextLine().trim();

        System.out.print("Никнейм исполнителя: ");
        String implementer = scanner.nextLine().trim();

        try {
            Date deadline = java.sql.Date.valueOf(deadlineStr);
            system.addTask(project, description, deadline, implementer);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: неверный формат даты. Используйте гггг-мм-дд");
        }
    }

    private static void correctTaskDescription() {
        System.out.print("ID задачи, которую хотите изменить: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Новое описание: ");
        String newDesc = scanner.nextLine().trim();

        system.correctTaskDescription(id, newDesc);
    }

    private static void correctImplementer() {
        System.out.print("ID задачи, которую хотите изменить: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Новый исполнитель: ");
        String newImpl = scanner.nextLine().trim();

        system.correctImplementer(id, newImpl);
    }

    private static void changeTaskStatus() {
        System.out.print("ID задачи: ");
        String id = scanner.nextLine().trim();

        System.out.println("Выберите новый статус:");
        System.out.println("1. undone");
        System.out.println("2. in_progress");
        System.out.println("3. done");
        System.out.print("Ваш выбор: ");

        String choice = scanner.nextLine().trim();
        TaskStatus status = switch (choice) {
            case "1" -> TaskStatus.undone;
            case "2" -> TaskStatus.in_progress;
            case "3" -> TaskStatus.done;
            default -> {
                System.out.println("Неверный выбор статуса");
                yield null;
            }
        };

        if (status != null) {
            system.changeTaskStatus(id, status);
        }
    }

    private static void deleteTaskByID() {
        System.out.print("Введите ID задачи для удаления: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        system.deleteTaskByID(id);
    }

    private static void commentMenu() {
        while (true) {
            System.out.println("\nУправление комментариями");
            System.out.println("1. Показать все комментарии");
            System.out.println("2. Найти комментарий по ID");
            System.out.println("3. Найти комментарии по ID задачи");
            System.out.println("4. Найти комментарий по автору и задаче");
            System.out.println("5. Добавить комментарий");
            System.out.println("6. Изменить комментарий");
            System.out.println("7. Удалить комментарий");
            System.out.println("0. Назад");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> system.getAllComments();
                case "2" -> getCommentById();
                case "3" -> getCommentByTaskID();
                case "4" -> getCommentByAuthorAndTaskID();
                case "5" -> addComment();
                case "6" -> correctComment();
                case "7" -> deleteCommentByID();
                case "0" -> { return; }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    private static void getCommentById() {
        System.out.print("Введите ID комментария: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        system.getCommentById(id);
    }

    private static void getCommentByTaskID() {
        System.out.print("Введите ID задачи: ");
        int taskId = Integer.parseInt(scanner.nextLine().trim());
        system.getCommentByTaskID(taskId);

    }

    private static void getCommentByAuthorAndTaskID() {
        System.out.print("Никнейм автора: ");
        String author = scanner.nextLine().trim();

        System.out.print("ID задачи: ");
        int taskId = Integer.parseInt(scanner.nextLine().trim());
        system.getCommentByAuthorAndTaskID(author, taskId);
    }

    private static void addComment() {
        System.out.print("ID задачи: ");
        int taskId = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Никнейм автора: ");
        String author = scanner.nextLine().trim();

        System.out.print("Текст комментария: ");
        String comment = scanner.nextLine().trim();

        system.addComment(taskId, author, comment);
    }

    private static void correctComment() {
        System.out.print("ID комментария, который хотите изменить: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Новый комментарий: ");
        String newComment = scanner.nextLine().trim();

        system.correctComment(id, newComment);
    }

    private static void deleteCommentByID() {
        System.out.print("Введите ID комментария для удаления: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        system.deleteCommentByID(id);
    }
}
