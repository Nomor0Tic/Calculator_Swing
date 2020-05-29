package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuTutorial extends JFrame
{
    JMenuBar menuBar;  //预置
    JMenu file,second,third;
    JMenuItem exit,secondd,thirdd;
    JButton button1;
    JTextField textField;

    public menuTutorial()
    {
        menuBar=new JMenuBar();   //创建一个新的菜单栏对象
        setJMenuBar(menuBar);  //添加到JFrame

        file=new JMenu("File"); //创建一个叫File的菜单
        menuBar.add(file);//添加到JFrame
        exit=new JMenuItem("Exit");//创建一个叫exit的子项目
        file.add(exit);


        second=new JMenu("Second");
        menuBar.add(second);
        secondd=new JMenuItem("sa");
        second.add(secondd);

        button1=new JButton("Click Me");
        secondd.add(button1);
        textField=new JTextField("");
        secondd.add(textField);

        file.add(button1);

        click e=new click();
        button1.addActionListener(e);

        event a=new event();
        exit.addActionListener(a);



        setLayout(new FlowLayout());
    }
    public class click implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            textField.setText("Hello");
        }
    }
    public class event implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }

    public static void main(String [] args)
    {
        menuTutorial mt=new menuTutorial();

        mt.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mt.setVisible(true);
        mt.setTitle("MENU");
        mt.setSize(500,500);

    }

}
