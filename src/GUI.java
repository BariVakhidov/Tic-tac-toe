import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class GUI {
    private static JFrame frame;
    JPanel panel;
    JCheckBox checkBox;
    private static JTextField name11;
    private static JTextField name22;
    JButton start;
    private static JLabel winX;
    private static JLabel winO;
    Draw draw;
    public static int xWin;
    public static int oWin;

    public void setGUI() {
        WinChecker winChecker = new WinChecker();
        Thread thread = new Thread(winChecker);
        xWin = 0;
        oWin = 0;
        frame = new JFrame("XO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();

        String name = JOptionPane.showInputDialog(frame,"Player 1");
        String nameee = JOptionPane.showInputDialog(frame,"Player 2");


        Box buttonBox = new Box(BoxLayout.Y_AXIS);
        panel.add(buttonBox);

        JButton newGame = new JButton("Новая партия");
        newGame.addActionListener((ActionEvent a) -> {
                draw.go();
                draw.repaint();
            WinChecker.win = false;

            });

        buttonBox.add(newGame);

        start = new JButton("Start");
        start.addActionListener((ActionEvent st) ->{
            name11.setEnabled(false);
            name22.setEnabled(false);
            winX.setText(name11.getText() + "    " + draw.getxWin());
            winO.setText(name22.getText()  + "    "+ draw.getoWin());
            JOptionPane.showMessageDialog(frame,"Первым ходит " + name11.getText());
            start.setEnabled(false);
            thread.start();

        });
        buttonBox.add(start);

        JButton NewGame = new JButton("Новая игра");
        NewGame.addActionListener((ActionEvent a) -> {
            frame.dispose();
            setGUI();
            draw.go();
            draw.repaint();
            WinChecker.win = false;

        });
        buttonBox.add(NewGame);

        Box nameBox1 = new Box(BoxLayout.X_AXIS);
        buttonBox.add(nameBox1);

        JLabel name1 = new JLabel("Player 1: ");
        name11 = new JTextField(7);
        name11.setText(name);

        Box nameBox2 = new Box(BoxLayout.X_AXIS);
        buttonBox.add(nameBox2);

        JLabel name2 = new JLabel("Player 2: ");
        name22 = new JTextField(7);
        name22.setText(nameee);

        nameBox1.add(name1);
        nameBox1.add(name11);
        nameBox2.add(name2);
        nameBox2.add(name22);

        checkBox = new JCheckBox("Computer");
        checkBox.setSelected(false);
        checkBox.addActionListener((ActionEvent aa) -> {
            checkBox.setEnabled(false);
            name22.setText("Computer");
            winO.setText(name22.getText()  + "    "+ draw.getoWin());
            name22.setEnabled(false);
        });
        buttonBox.add(checkBox);

        draw = new Draw();
        winX = new JLabel(name11.getText() + "    " + draw.getxWin());
        winO = new JLabel(name22.getText() + "    " + draw.getoWin());
        buttonBox.add(winX);
        buttonBox.add(winO);

        frame.getContentPane().add(BorderLayout.CENTER, draw);
        frame.getContentPane().add(BorderLayout.EAST, panel);
        frame.setSize(700, 500);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    public static JFrame getFrame() {
        return frame;
    }
    public static JTextField getName1() {
        return name11;
    }

    public static JTextField getName2() {
        return name22;
    }

    public static JLabel getWinX() {
        return winX;
    }

    public static JLabel getWinO(){
        return winO;
    }
}
