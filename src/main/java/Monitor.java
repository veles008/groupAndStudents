import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Monitor {
    private JFrame frame;
    private JLabel label;
    private JTextField nameField;
    private JTextField ageField;
    private JButton addButton;

    public Monitor() {
        frame = new JFrame("Gritsay");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel();
        frame.getContentPane().add(label);

        nameField = new JTextField(10);
        ageField = new JTextField(10);
        addButton = new JButton("Добавить");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Имя: "));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Группа: "));
        inputPanel.add(ageField);
        inputPanel.add(addButton);

        frame.getContentPane().add(inputPanel, BorderLayout.NORTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Обработчик события кнопки "Добавить"
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                addDataToDatabase(name, age);
                retrieveAndDisplayData();
            }
        });

        frame.setPreferredSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);
    }

    public void retrieveAndDisplayData() {
        // Здесь вы можете добавить код для получения данных.
        // Например, если вы хотите отобразить какой-то текст:
        String data = Datab.retrieveAndDisplayData();

        // Устанавливаем полученные данные в JLabel для отображения на экране с HTML-разметкой
        label.setText(data);
    }

    public void addDataToDatabase(String name, int age) {
        Datab database = new Datab();
        database.insertData(name, age);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Monitor monitor = new Monitor();
            monitor.retrieveAndDisplayData();
        });
    }
}
