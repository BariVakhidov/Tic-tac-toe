import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Draw extends JPanel {

    public static final int FIELD_IS_EMPTY = 0;
    boolean Xturn;
    public static int [][] field;
    private static int xWin;
    private static int oWin;
    private static boolean GAME;


    public Draw() {
        xWin = 0;
        oWin = 0;
        GAME = true;
        field = new int[3][3];
        go();

    }

    public void go() {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j< 3; j++) {
                field[i][j] = FIELD_IS_EMPTY;
            }
        }
        Xturn = true;
    }
    public static int getxWin() {
        return xWin;
    }

    public static int getoWin() {
        return oWin;
    }
    @Override
  protected void  processMouseEvent(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            int k = (int) ((float) mouseEvent.getX()/(getWidth()/3)); //перевод координаты клика в ячейку, т.е.
            int t = (int) ((float) mouseEvent.getY()/(getHeight()/3));// на какую ячейку кликнули

            if (field[k][t] ==FIELD_IS_EMPTY) {
                if (Xturn) {
                    field[k][t] = 10;
                    Xturn = false;
                    repaint();
                } else {
                    field[k][t] = 100;
                    Xturn = true;
                    repaint();
                }
            }
        }
    }

    @Override
    protected void paintComponent (Graphics g) {
        g.clearRect(0,0,getWidth(),getHeight());
        g.setColor(Color.GRAY);
        g.fillRect(0,0,getWidth(),getHeight());
        drawLine(g);
        XO(g);
        win(g);
        System.out.println(xWin +" " + oWin);
    }
    

    public void drawLine(Graphics graphics) {
        Graphics2D gg = (Graphics2D) graphics;
        gg.setStroke(new BasicStroke(5.0f));
        gg.setColor(Color.DARK_GRAY);
        for (int i = 1; i <3; i++) {
            gg.drawLine(0,((getHeight()/3)*i), getWidth(),((getHeight()/3)*i) );
            gg.drawLine(((getWidth()/3)*i), 0, ((getWidth()/3)*i), getHeight());
        }
    }

    public void XO (Graphics graphics) {
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j <3; j ++) {
                if (field[i][j] == 10) {
                    X(i,j,graphics);
                }
                if (field[i][j] == 100) {
                    O(i,j,graphics);
                }
            }
        }

    }

    public void O (int i, int j, Graphics graphics) {
        line(graphics).setColor(Color.BLUE);
        line(graphics).drawOval(i * getWidth()/3 +20,j * getHeight()/3 + 20,(getWidth()/3 -40 ),(getHeight()/3-40));
    }

    public void X (int i, int j, Graphics graphics) {
        line(graphics).setColor(Color.green);
        line(graphics).drawLine(i * getWidth()/3 +20, j * getHeight()/3 +20, (i+1)*getWidth()/3 - 20, (j+1)*getHeight()/3-20);
        line(graphics).drawLine((i+1)*getWidth()/3 - 20, (j)*getHeight()/3 + 20, i * getWidth()/3 +20,(j+1)*getHeight()/3 - 20);
    }

    public void win(Graphics graphics) {
        for (int j = 0; j < 3; j++) {

            line(graphics).setColor(Color.red);

           if (field[0][j] == field[1][j] && field[1][j]== field[2][j] && field[0][j] != 0) {
               line(graphics).drawLine(0, (j*2+1)*getHeight() / 6,  getWidth(), (j*2+1)*getHeight() / 6);
               disableEvents(AWTEvent.MOUSE_EVENT_MASK);
                if (field[0][j] == 10) {
                    xWin++ ;
                }
                else {
                    oWin++;
                }
               break; //Горизонтальный ряд
           }
            if (field[j][0] == field[j][1] && field[j][1]== field[j][2] && field[j][0] != 0) {
                line(graphics).drawLine((j*2+1)*getWidth() / 6, 0,  (j*2+1)*getWidth() / 6, getHeight());
                disableEvents(AWTEvent.MOUSE_EVENT_MASK);
                if (field[j][0] == 10) {
                    xWin++ ;
                }
                else {
                    oWin++;
                }
                break; //Вертикальный ряд
            }
            if (field[0][0] == field[1][1] && field[1][1]== field[2][2] && field[0][0] != 0) {
                line(graphics).drawLine(0, 0,  getWidth() , getHeight());
                disableEvents(AWTEvent.MOUSE_EVENT_MASK);
                if (field[0][0] == 10) {
                    xWin++ ;
                }
                else {
                    oWin++;
                }
                break; //Диагональ
            }
            if (field[0][2] == field[1][1] && field[1][1]== field[2][0] && field[0][2] != 0) {
                line(graphics).drawLine(0, getHeight(),  getWidth() , 0);
                disableEvents(AWTEvent.MOUSE_EVENT_MASK);
                if (field[0][2] == 10) {
                    xWin++ ;
                }
                else {
                    oWin++;
                }
                break;//Диагональ
            }
        } //Проверка на выигрыш
    }

    public Graphics2D line(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setStroke(new BasicStroke(10.0f));
        return g2;
    }
}
