import java.sql.*;

public class Datab {
    public static String dbUrl = "jdbc:sqlite:C:/Users/grits/IdeaProjects/zanyatia/src/main/java/data/database1.db";

    // Попытка подключения к базе данных
    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl);
            System.out.println("Успешно подключено к базе данных");
        } catch (SQLException e) {
            System.err.println("Ошибка при подключении к базе данных: " + e.getMessage());
        }
        return connection; // Возвращаем объект соединения при успешном подключении
    }

    // Метод для выполнения SELECT-запроса и вывода результатов
    public static String retrieveAndDisplayData() {
        Connection connection = connect();
        StringBuilder dataBuilder = new StringBuilder("<html>"); // Открываем HTML разметку
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                String tableName = "students";
                String sqlQuery = "SELECT * FROM " + tableName;
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                while (resultSet.next()) {
                    String name = resultSet.getString("NAME");
                    int age = resultSet.getInt("GROUP2");

                    // Добавляем данные в столбик с использованием HTML тега <br>
                    dataBuilder.append("Имя студента: ").append(name).append(", Группа студента: ").append(age).append("<br>");
                }

                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                System.err.println("Ошибка при выполнении SQL-запроса: " + e.getMessage());
            }
        }
        dataBuilder.append("</html>"); // Закрываем HTML разметку
        return dataBuilder.toString(); // Возвращаем данные в виде HTML-разметки
    }


    public void insertData(String name, int group2) {
        Connection connection = connect();
        if (connection != null) {
            try {
                // SQL-запрос для вставки данных
                String sqlQuery = "INSERT INTO students (NAME, GROUP2) VALUES (?, ?)";

                // Создаем объект PreparedStatement для безопасной вставки данных
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, group2);

                // Выполняем запрос на вставку
                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Данные успешно вставлены в таблицу.");
                }

                // Закрываем ресурсы
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                System.err.println("Ошибка при выполнении SQL-запроса: " + e.getMessage());
            }
        }
    }
}
