public class Main {
    public static void main(String[] args) {
        // Подключение к БД
        Datab database = new Datab();

        // Вывод данных из БД
        System.out.println("Данные из БД до вставки:");
        Datab.retrieveAndDisplayData();

        // Вставка данных в БД
        database.insertData("John Doe", 185);

        // Отрисовка GUI и вывод данных на экран
        Monitor monitor = new Monitor();

        // Устанавливаем текст в JLabel после вставки данных
        monitor.retrieveAndDisplayData();
    }
}