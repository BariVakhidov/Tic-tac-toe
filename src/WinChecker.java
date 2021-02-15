import javax.swing.*;

public class WinChecker implements Runnable {
    public static boolean win;
    @Override
    public void run() {
        check();
    }
    public void check() {
        win = false;
        int n =0;
        while (true && Draw.field.length != 0) {
            if (!win) {
                try {
                    int[][] fieldNext = Draw.field;
                    for (int j = 0; j < 3; j++) {
                        if (fieldNext[0][j] == fieldNext[1][j] && fieldNext[1][j] == fieldNext[2][j] && fieldNext[0][j] != 0) {
                            if (fieldNext[0][j] == 10) {
                                System.out.println("Победили Х");
                                attempt(GUI.getName1());
                                GUI.getWinX().setText(GUI.getName1().getText() + "    " + Draw.getxWin());
                                win = true;

                            } else {
                                System.out.println("Победили О");
                                attempt(GUI.getName2());
                                GUI.getWinO().setText(GUI.getName2().getText() + "    " + Draw.getoWin());
                                win = true;
                            }
                            break; //Горизонтальный ряд
                        }
                        if (fieldNext[j][0] == fieldNext[j][1] && fieldNext[j][1] == fieldNext[j][2] && fieldNext[j][0] != 0) {
                            if (fieldNext[j][0] == 10) {
                                System.out.println("Победили Х");
                                attempt(GUI.getName1());
                                GUI.getWinX().setText(GUI.getName1().getText() + "    " + Draw.getxWin());
                                win = true;
                            } else {
                                System.out.println("Победили О");
                                attempt(GUI.getName2());
                                GUI.getWinO().setText(GUI.getName2().getText() + "    " + Draw.getoWin());
                                win = true;
                            }
                            break; //Вертикальный ряд
                        }
                        if (fieldNext[0][0] == fieldNext[1][1] && fieldNext[1][1] == fieldNext[2][2] && fieldNext[0][0] != 0) {
                            if (fieldNext[0][0] == 10) {
                                System.out.println("Победили Х");
                                attempt(GUI.getName1());
                                GUI.getWinX().setText(GUI.getName1().getText() + "    " + Draw.getxWin());
                                win = true;
                            } else {
                                System.out.println("Победили О");
                                attempt(GUI.getName2());
                                GUI.getWinO().setText(GUI.getName2().getText() + "    " + Draw.getoWin());
                                win = true;
                            }
                            break; //Диагональ
                        }

                        if (fieldNext[0][2] == fieldNext[1][1] && fieldNext[1][1] == fieldNext[2][0] && fieldNext[0][2] != 0) {
                            if (fieldNext[0][2] == 10) {
                                System.out.println("Победили Х");
                                attempt(GUI.getName1());
                                GUI.getWinX().setText(GUI.getName1().getText() + "    " + Draw.getxWin());
                                win = true;
                            } else {
                                System.out.println("Победили О");
                                attempt(GUI.getName2());
                                GUI.getWinO().setText(GUI.getName2().getText() + "    " + Draw.getoWin());
                                win = true;
                            }
                            break;//Диагональ
                        }
                    }
                } catch (NullPointerException e) {
                    System.out.println("Пока пусто");
                }
            }
            n++;
        }
    }

    public void attempt(JTextField textField) {
        JOptionPane.showMessageDialog(GUI.getFrame(),"Победил " + textField.getText());
    }
}
