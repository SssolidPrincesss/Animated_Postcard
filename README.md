# Animated_Postcard
В этот раз напишем открытку с анимированным фоном на космическую тематику. Если тебе интересен полный код — перейди в ветку Postcard_sourseCode. Если у тебя есть какие-нибудь предложения по улучшению кода — у второй ветки открыт pull request.  
Примерно так выглядит открытка:  
![menu](https://github.com/SssolidPrincesss/Animated_Postcard/blob/main/Images/1.png)
![menu](https://github.com/SssolidPrincesss/Animated_Postcard/blob/main/Images/2.png)
![menu](https://github.com/SssolidPrincesss/Animated_Postcard/blob/main/Images/3.png)  

Что насчет составляющих программы?  
Разберем подробнее основные моменты:  

Класс Postcard — точка входа программы, то, с чего начинается всё веселье:  
```java
import javax.swing.*;
import java.awt.*;

public class Postcard{
	JFrame frame;
	
	public static void main(String[] args) {
		Postcard card  = new Postcard();
		card.go();
	}

	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyDrawPanel panel = new MyDrawPanel();
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setSize(450, 700);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
```

Класс MyDrawPanel расширяет класс JPanel. При помощи него происходит отрисовка открытки:   
```java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class MyDrawPanel extends JPanel {
```

В конструкторе класса запускается таймер, который с периодичностью в 100 мс перерисовывает фигуры на открытке:  
```java
    public MyDrawPanel() {
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();
    }
```
Метод generateColor позволяет нам сгенерировать яркости цветов цветовой модели RGB:  
```java
    private Color generateColor() {
        int red = (int) (100 + Math.random() * 155);
        int green = (int) (100 + Math.random() * 155);
        int blue = (int) (100 + Math.random() * 155);
        return new Color(red, green, blue);
    }
```

Класс paintComponent содержит аргумент Graphics g, который можно воспринимать как кисть, при помощи которой и происходит всё волшебство:  
```java
public void paintComponent(Graphics g) {
```
Сначала необходимо произвести приведение типов для расширения функционала нашей «кисти»:  
```java
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
```  
Теперь часть кода, которая отвечает за фон:  
```java
GradientPaint background = new GradientPaint(0, 0, Color.DARK_GRAY, getWidth(), getHeight(), Color.BLACK);
        g2d.setPaint(background);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < 100; i++) {
            g2d.setColor(generateColor());
            int x = (int) (Math.random() * getWidth());
            int y = (int) (Math.random() * getHeight());
            int size = (int) (2 + Math.random() * 5);
            g2d.fillOval(x, y, size, size);
        }

       
        g2d.setColor(new Color(255, 130, 100, 15));
        g2d.setStroke(new BasicStroke(3f));
        for (int i = 0; i < 10; i++) {
            int glowX = 170 + i * 20;
            int glowY = 155 + i * 5;
            g2d.drawOval(glowX, glowY, 255, 255);
        }
```
И наконец, отрисовка фигур и текста:  
```java
        Color metalColor = new Color(150, 100, 180);
        g2d.setColor(metalColor);
        g2d.fillRect(70, 160, 150, 400);

        g2d.setColor(new Color(120, 170, 170));
        g2d.fillRect(200, 60, 250, 20);
        g2d.fillRect(300, 90, 150, 20);

        g2d.setColor(new Color(240, 150, 150));
        g2d.fillRect(250, 50, 20, 600);

        Color fireColor = new Color(255, 100, 0);
        g2d.setColor(fireColor);
        g2d.fillOval(170, 155, 255, 255);

        g2d.setStroke(new BasicStroke(5f));
        g2d.setColor(new Color(255, 215, 0));
        int x1 = 90, y1 = 250;
        int x2 = 150, y2 = 50;
        int x3 = 400, y3 = 600;
        g2d.drawLine(x1, y1, x2, y2);
        g2d.drawLine(x2, y2, x3, y3);
        g2d.drawLine(x3, y3, x1, y1);
        
        
        
        g2d.setColor(Color.lightGray);
        g2d.fillRoundRect(90, 280, 260, 60, 40, 40);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Serif", Font.BOLD, 28));
        g2d.drawString("Ура-а-а... Победа!", 110, 320);


    }
}
```
