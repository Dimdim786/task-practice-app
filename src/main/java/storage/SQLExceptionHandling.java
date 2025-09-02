package storage;

public class SQLExceptionHandling {

    public static void handling (String SQLState) {
        switch (SQLState.substring(0,2)) {
            case "01" -> System.out.println("Предупреждение");
            case "02" -> System.out.println("Нет данных (это также класс предупреждений согласно стандарту SQL)");
            case "03" -> System.out.println("SQL-оператор ещё не завершён");
            case "08" -> System.out.println("Исключение, связанное с подключением");
            case "09" -> System.out.println("Исключение с действием триггера");
            case "0A" -> System.out.println("Неподдерживаемая функциональность");
            case "0B" -> System.out.println("Неверное начало транзакции");
            case "0F" -> System.out.println("Исключение с указателем на данные");
            case "0L" -> System.out.println("Неверный праводатель");
            case "0P" -> System.out.println("Неверное указание роли");
            case "0Z" -> System.out.println("Исключение диагностики");
            case "20" -> System.out.println("Case не найден");
            case "21" -> System.out.println("Нарушение количества");
            case "22" -> System.out.println("Исключение в данных");
            case "23" -> System.out.println("Нарушение ограничения целостности");
            case "24" -> System.out.println("Неверное состояние курсора");
            case "25" -> System.out.println("Неверное состояние транзакции");
            case "26" -> System.out.println("Неверное имя SQL-оператора");
            case "27" -> System.out.println("Нарушение при изменении данных в триггере");
            case "28" -> System.out.println("Неверное указание авторизации");
            case "2B" -> System.out.println("Зависимые описания привилегий всё ещё существуют");
            case "2D" -> System.out.println("Неверное завершение транзакции");
            case "2F" -> System.out.println("Исключение в подпрограмме SQL");
            case "34" -> System.out.println("Неверное имя курсора");
            case "38" -> System.out.println("Исключение во внешней подпрограмме");
            case "39" -> System.out.println("Исключение при вызове внешней подпрограммы");
            case "3B" -> System.out.println("Исключение точки сохранения");
            case "3D" -> System.out.println("Неверное имя каталога");
            case "3F" -> System.out.println("Неверное имя схемы");
            case "40" -> System.out.println("Откат транзакции");
            case "42" -> System.out.println("Ошибка синтаксиса или нарушение правила доступа");
            case "44" -> System.out.println("Нарушение WITH CHECK OPTION");
            case "53" -> System.out.println("Нехватка ресурсов");
            case "54" -> System.out.println("Превышение ограничения программы");
            case "55" -> System.out.println("Объект не в требуемом состоянии");
            case "57" -> System.out.println("Вмешательство оператора");
            case "58" -> System.out.println("Ошибка системы (ошибка, внешняя по отношению к PostgreSQL)");
            case "F0" -> System.out.println("Ошибка файла конфигурации");
            case "HV" -> System.out.println("Ошибка обёртки сторонних данных (SQL/MED)");
            case "P0" -> System.out.println("Ошибка PL/pgSQL");
            case "XX" -> System.out.println("Внутренняя ошибка");
            default -> System.out.println("Invalid");
        }
    }
}
