package GUI.TEST;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Javagui {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                new menubar();
            }            
        });
    }
}

class menubar extends JFrame{

    public menubar(){       
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(menubar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(menubar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(menubar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(menubar.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        this.setTitle("MenuBar");    //��������
        this.setSize(1024,512);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setAlwaysOnTop(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                int n = JOptionPane.showConfirmDialog(menubar.this,"��Ҫ�˳���","ѯ��",JOptionPane.YES_NO_OPTION);
                if (n==JOptionPane.YES_OPTION){
                    System.out.println("ִ�����˳�ָ��");
                    System.exit(0);
                }
            }
        });

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        this.getContentPane().add(jPanel);
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        jPanel1.setPreferredSize(new Dimension(500,150));
        jPanel2.setPreferredSize(new Dimension(500,150));
        jPanel1.setBorder(BorderFactory.createTitledBorder("TICYSIS"));
        jPanel2.setBorder(BorderFactory.createTitledBorder("My Next"));
        jPanel.add(jPanel1);
        jPanel.add(jPanel2);

        JSplitPane jSplitPane = new JSplitPane();
        jSplitPane.setPreferredSize(new Dimension(500,150));
        JPanel jPanelleft = new JPanel();
        JPanel jPanelright = new JPanel();
        jSplitPane.setLeftComponent(jPanelleft);
        jSplitPane.setRightComponent(jPanelright);
        jPanel.add(jSplitPane);

        JButton jButton = new JButton("˫������");
        jPanel1.add(jButton);
        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int times = e.getClickCount();
                if(times==2){
                    System.out.println("���˫����");
                }

            }
        });

        JLabel checkLabel = new JLabel();
        jPanel2.add(checkLabel);
        Thread checkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    }catch (Exception e){
                        System.out.println("checkThread������");
                    }
                    ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
                    int noThreads = threadGroup.activeCount();
                    Thread[] lstThreads = new Thread[noThreads];
                    threadGroup.enumerate(lstThreads);
                    for (int i = 0; i < noThreads; i++) {
                        System.out.println("�̺߳ţ�" + i + " = " + lstThreads[i].getName());
                    }
                    checkLabel.setText("��ǰ�����߳���>>"+noThreads+"<<");
                }
            }
        }); checkThread.start();






        JMenuBar jmb = new JMenuBar();
        this.setJMenuBar(jmb);
        JMenu jm1 = new JMenu("�ļ�");
        jmb.add(jm1);
        JMenu jm2 = new JMenu("��");
        jmb.add(jm2);
        JMenu jm3 = new JMenu("��ͼ");
        jmb.add(jm3);
        JMenu jm5 = new JMenu("����");
        jmb.add(jm5);
        JMenu jm4 = new JMenu("����");
        jmb.add(jm4);
        
        JMenuItem jmi11 = new JMenuItem("��ʼ");
        jm1.add(jmi11);
        JMenuItem jmi12 = new JMenuItem("����");
        jm1.add(jmi12);
        JMenuItem jmi13 = new JMenuItem("���");
        jm1.add(jmi13);
        JMenuItem jmi14 = new JMenuItem("�˳�");
        jm1.add(jmi14);
        jmi14.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }          
        });
        JMenuItem jmi21 = new JMenuItem("�����ļ�");
        jm2.add(jmi21);    
        jmi21.addActionListener(new openAction());

        JMenuItem jmi52 = new JMenuItem("����ʼ���ö�");
        jm5.add(jmi52);
        jmi52.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menubar.this.setAlwaysOnTop(true);
            }
        });
        JMenuItem jmi51 = new JMenuItem("�ر�ʼ���ö�");
        jm5.add(jmi51);
        jmi51.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menubar.this.setAlwaysOnTop(false);
            }
        });
        JMenuItem jmi53 = new JMenuItem("���ķ��");
        jm5.add(jmi53);



        JMenuItem jmi41 = new JMenuItem("����");
        jm4.add(jmi41);
        jmi41.addActionListener(new ActionListener() {   //��ť�¼�
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"���������ǲ����ô���\n���ԣ�Ticysis","����",JOptionPane.CLOSED_OPTION);
            }
        });
        
        this.setVisible(true);
    }
}

 class openAction implements ActionListener{    //��ť�¼���ѡ���ļ�
    public void actionPerformed(ActionEvent e){
        Object o = e.getSource();
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jfc.showDialog(new JLabel(), "ѡ��");
        File file = jfc.getSelectedFile();
        if(file.isDirectory()){
                System.out.println("�ļ���:"+file.getAbsolutePath());
            }else if(file.isFile()){
                System.out.println("�ļ�:"+file.getAbsolutePath());
            }
            System.out.println(jfc.getSelectedFile().getName());       
    }
}



