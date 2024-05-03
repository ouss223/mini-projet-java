import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dessin extends JFrame {
    private String[] forms = {"Carree", "Ovale", "Triangle"};
    private String[] colors = {"Rouge", "Vert", "Bleu"};
    private String selectedForm = "Carree";
    private Color selectedColor = Color.RED;

    public Dessin() {
        setTitle("Dessin");
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.add(new JLabel("Forme:"));
        JComboBox<String> formSelect = new JComboBox<>(forms);
        formSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedForm = (String) formSelect.getSelectedItem();
                repaint();
            }
        });
        formPanel.add(formSelect);

        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));
        colorPanel.add(new JLabel("Couleur:"));
        ButtonGroup colorGroup = new ButtonGroup();
        for (String color : colors) {
            JRadioButton colorButton = new JRadioButton(color);
            colorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (color) {
                        case "Rouge":
                            selectedColor = Color.RED;
                            break;
                        case "Vert":
                            selectedColor = Color.GREEN;
                            break;
                        case "Bleu":
                            selectedColor = Color.BLUE;
                            break;
                    }
                    repaint();
                }
            });
            colorGroup.add(colorButton);
            colorPanel.add(colorButton);
            if (color.equals("Rouge")) {
                colorButton.setSelected(true);
            }
        }

        formPanel.setBackground(Color.CYAN);
        colorPanel.setBackground(Color.CYAN);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.CYAN);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        formPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        colorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        leftPanel.add(formPanel);
        leftPanel.add(colorPanel);

        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(leftPanel, BorderLayout.WEST);


        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(selectedColor);
                int panelWidth = getWidth();

                switch (selectedForm) {
                    case "Carree":
                        int squareX = (panelWidth - 100) / 2;
                        g.fillRect(squareX, 50, 100, 100);
                        break;
                    case "Ovale":
                        int ovalX = (panelWidth - 100) / 2;
                        g.fillOval(ovalX, 50, 100, 100);
                        break;
                    case "Triangle":
                        int[] triangleX = {(panelWidth - 100) / 2, (panelWidth - 100) / 2 + 50, (panelWidth - 100) / 2 + 100};
                        g.fillPolygon(triangleX, new int[]{150, 50, 150}, 3);
                        break;
                }
            }
        };

        add(drawingPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Dessin dessin = new Dessin();
            dessin.setVisible(true);
        });
    }
}