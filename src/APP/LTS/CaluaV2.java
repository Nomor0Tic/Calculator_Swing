package APP.LTS;

import java.awt.*; //�����awt
import java.awt.event.*;
import java.net.URL;
import java.awt.SystemTray;//�����
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

import APP.NEX.MyJFrame;

/**
 * @author Ticysis
 * @Version v1.0//v2.0.1
 * @By ???
 * @From CSDN
 * @Descript This is the calculator changed by Ticysis
 * @since JDK 11.0
 * #########
 * @Version 1.0  ->>  �������ϱʼǱ�д���������ʵ��Ķ�
 * @Version 2.1  ->>  �������µĲ��ֺ�ʱ����ʾ(Thread),����������ͼ��
 * @Version 3.1  ->>  �����˶���̣߳�������µĲ��֣�����˲˵���
 * @Version 4.2  ->>  �¶���-�������ء��ϲ������߳�
 * #########
 */

public class CaluaV2 extends JFrame { //��Calua�̳���JFrame�ࣨ�����ࣩ
    private static final long serialVersionUID = 1L; //������������������//������Java�����л�����

    private double version = 4.1;

    private StringBuilder sBuilder = new StringBuilder(); //��ż���ʽ��
    private Double a; //�洢�м����-��һ����
    private Double b; //�洢�м����-�ڶ�����
    private Double result; //�洢���
    private Integer ip; //��ʾ�Ӽ��˳�
    private SystemTray systemTray; //����
    private TrayIcon trayIcon; //����ͼ��
    private static Thread t;  //��˵�еĶ��߳�
    private int markIcon = 0;

    public CaluaV2() { //���췽��
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) { //����UI���
            e.printStackTrace();
        }
        this.setTitle("һ������������������");//���ñ���
        this.setSize(310, 650);//���ô�С
        this.setLocationRelativeTo(null);//����Ĭ��λ��-����
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//���ùرշ�ʽ(ԭ����Exit.ON.CLOSE)
                this.setResizable(false); //�����϶������С
                ClassLoader classLoader2 = this.getClass().getClassLoader();
                URL url2 = classLoader2.getResource("Resource/easyicon/1226431.png");
                Image image = Toolkit.getDefaultToolkit().getImage(url2); //Can`t use ImageIcon Because awt=/=swing
                this.setIconImage(image);
                this.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        CaluaV2.this.setVisible(false);
                    }
                });


                try {
                    if (SystemTray.isSupported()) {
                        PopupMenu pop = new PopupMenu();
                        MenuItem mai = new MenuItem("Open Main Windows");
                        MenuItem exitalready = new MenuItem("Exit Without Asking");
                MenuItem exit = new MenuItem("Exit The Programme");
                pop.add(mai);
                pop.add(exitalready);
                pop.add(exit);
                mai.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CaluaV2.this.setVisible(true);
                    }
                });
                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int exNum = JOptionPane.showConfirmDialog(null, "\u771f\u7684\u8981\u9000\u51fa\u5417\u003f", "Exit Question", JOptionPane.YES_NO_OPTION);
                        if (exNum == JOptionPane.YES_OPTION) {   //���Ҫ�˳���?
                            System.exit(0);
                        }
                    }
                });
                exitalready.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("������ֱ���˳�����");
                        System.exit(0);
                    }
                });


                ClassLoader classLoader1 = this.getClass().getClassLoader();  //ClassLoader����ͼ��
                URL url1 = classLoader1.getResource("Resource/easyicon/1120534.png");
                ImageIcon imageIcon1 = new ImageIcon(url1);

                trayIcon = new TrayIcon(imageIcon1.getImage(), "��������������V2.0", pop); //ϵͳ����ͼ��
                trayIcon.setImageAutoSize(true);
                systemTray = SystemTray.getSystemTray();  //ϵͳ����
                trayIcon.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(2==e.getClickCount()){
                            CaluaV2.this.setVisible(true);
                        }
                    }
                });
                systemTray.add(trayIcon);

            }

        } catch (AWTException e) {
            System.out.println("AwtException������");
        }

        JMenuBar jMenuBar = new JMenuBar();  //==============================��==========��=========��=============================
        this.setJMenuBar(jMenuBar);
        JMenu jMenuFile = new JMenu("�ļ�(F)");
        JMenu jMenuEdit = new JMenu("����(W)");
        JMenu jMenuSet  = new JMenu("����(S)");
        JMenu jMenuAbout= new JMenu("����(A)");
        jMenuBar.add(jMenuFile);
        JMenuItem jMenuItemExit = new JMenuItem("ֱ���˳�");
        jMenuItemExit.addActionListener(e -> System.exit(0));
        jMenuFile.add(jMenuItemExit);

        jMenuBar.add(jMenuEdit);

        jMenuBar.add(jMenuSet);
        ClassLoader classLoader3 = this.getClass().getClassLoader();
        URL url3 = classLoader3.getResource("Resource/select.png");
        ImageIcon imageIcon3 = new ImageIcon(url3);
        JMenuItem jMenuItemOpenTop = new JMenuItem("����ʼ���ö�");
        jMenuItemOpenTop.addActionListener(e -> {
            System.out.println("�����ʼ���ö�");
            this.setAlwaysOnTop(true);
            markIcon=1;
        });
        JMenuItem jMenuItemClosTop = new JMenuItem("�ر�ʼ���ö�");
        jMenuItemClosTop.addActionListener(e -> {
            System.out.println("�����ȡ��ʼ���ö�");
            this.setAlwaysOnTop(false);
            markIcon=0;
        });


        jMenuSet.add(jMenuItemOpenTop);
        jMenuSet.add(jMenuItemClosTop);
        jMenuBar.add(jMenuAbout);


        JPanel panel = new JPanel();//newһ�����=======================================��==��==��==ǩ==��==��==================================
        JPanel pane2 = new JPanel();//new�ڶ������
        JPanel paneLabel = new JPanel(); //���������ʾ�������
        JPanel paneNum = new JPanel(); //��ʾ���ּ������
        paneNum.setLayout(new GridLayout(6,3));

        this.getContentPane().add(panel);//�����嵽����
        this.add(paneLabel,BorderLayout.NORTH);
        this.add(paneNum,BorderLayout.CENTER);
        this.add(pane2,BorderLayout.SOUTH);
        panel.setLayout(null);//���ò��ֹ�����-null

        JLabel checkLabel = new JLabel(); //��������ñ�ǩ

        JLabel label1 = new JLabel();//newһ����ǩ
        label1.setBounds(0, 0, 300, 50);//����label�ķ�Χ
        label1.setFont(new Font("dialog", 1, 30));//��������
        label1.setOpaque(true);//���ò�͸��Ϊtrue
        label1.setBackground(Color.WHITE);//���ñ���Ϊ��ɫ
        paneLabel.setPreferredSize(new Dimension(300,70));
        paneLabel.setBorder(BorderFactory.createTitledBorder("��ǰ�汾��"+version));
        paneLabel.add(label1);//��������label

        Thread maMOThread = new Thread(new Runnable() {  //======================================������ػ�=====================================
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("��ǰmarkIcon״̬>>>"+markIcon);
                        Thread.sleep(450);
                        if(markIcon==1){
                            jMenuItemOpenTop.setIcon(imageIcon3);
                            jMenuItemClosTop.setIcon(null);
                        }
                        if(markIcon==0){
                            jMenuItemClosTop.setIcon(imageIcon3);
                            jMenuItemOpenTop.setIcon(null);
                        }

                    }catch (Exception e){
                        System.out.println("ͼ���л�������");
                    }

                    try {
                        while (true) {
                            Thread.sleep(500);
                            System.out.println("label1.length>>>>>" + label1.getText().length());
                            if (label1.getText().length() >= 15) {
                                JOptionPane.showMessageDialog(null, "������15���ַ�������", "����", JOptionPane.WARNING_MESSAGE);
                                label1.setText("");
                            }
                        }
                    }catch (Exception e){
                        System.out.println("�ַ������̳߳�����");
                        System.out.println("������ԭ��>>>>>>>");
                        e.printStackTrace();
                        System.out.println("<<<<<<<�����ɣ�");
                    }
                }
            }
        });
        maMOThread.setName("������ػ�����");
        maMOThread.start();


        JLabel label2 = new JLabel();
        Font labelFont = new Font("����",1,15);
        label2.setPreferredSize(new Dimension(300,55));
       // label2.setBounds(0, 400, 300, 50);
        label2.setFont(labelFont);
        label2.setOpaque(true);
       // label2.setBackground(Color.WHITE);
        label2.setForeground(Color.BLACK);


        JButton b1 = new JButton("1");//����һ����ť����ʾ�ı�Ϊ1
        JButton b2 = new JButton("2");//����һ����ť����ʾ�ı�Ϊ2
        JButton b3 = new JButton("3");//����һ����ť����ʾ�ı�Ϊ3
        JButton b4 = new JButton("4");//����һ����ť����ʾ�ı�Ϊ4
        JButton b5 = new JButton("5");//����һ����ť����ʾ�ı�Ϊ5
        JButton b6 = new JButton("6");//����һ����ť����ʾ�ı�Ϊ6
        JButton b7 = new JButton("7");//����һ����ť����ʾ�ı�Ϊ7
        JButton b8 = new JButton("8");//����һ����ť����ʾ�ı�Ϊ8
        JButton b9 = new JButton("9");//����һ����ť����ʾ�ı�Ϊ9
        JButton b0 = new JButton("0");//����һ����ť����ʾ�ı�Ϊ0
        JButton ad = new JButton("+");//����һ����ť����ʾ�ı�Ϊ+
        JButton mn = new JButton("-");//����һ����ť����ʾ�ı�Ϊ-
        JButton xs = new JButton("*");//����һ����ť����ʾ�ı�Ϊ*
        JButton cu = new JButton("��");//����һ����ť����ʾ�ı�Ϊ��
        JButton eq = new JButton("=");//����һ����ť����ʾ�ı�Ϊ=
        JButton po = new JButton(".");//����һ����ť����ʾ�ı�Ϊ.
        JButton bk = new JButton("��");//����һ����ť����ʾ�ı�Ϊ��
        JButton cl = new JButton("C");//����һ����ť����ʾ�ı�ΪC

        b1.setBounds(0, 50, 100, 60);            //��ť1����λ�ô�С
        b1.setFont(new Font("dialog", 1, 30));     //��������
        paneNum.add(b1);                                              //������b1��ť
        b1.setMnemonic('Q');                                            //Ϊ��ť�����ּ�(����)===========================ʩ�����=======================================
        b2.setBounds(100, 50, 100, 60);
        b2.setFont(new Font("dialog", 1, 30));
        paneNum.add(b2);
        b3.setBounds(200, 50, 100, 60);
        b3.setFont(new Font("dialog", 1, 30));
        paneNum.add(b3);
        b4.setBounds(0, 110, 100, 60);
        b4.setFont(new Font("dialog", 1, 30));
        paneNum.add(b4);
        b5.setBounds(100, 110, 100, 60);
        b5.setFont(new Font("dialog", 1, 30));
        paneNum.add(b5);
        b6.setBounds(200, 110, 100, 60);
        b6.setFont(new Font("dialog", 1, 30));
        paneNum.add(b6);
        b7.setBounds(0, 170, 100, 60);
        b7.setFont(new Font("dialog", 1, 30));
        paneNum.add(b7);
        b8.setBounds(100, 170, 100, 60);
        b8.setFont(new Font("dialog", 1, 30));
        paneNum.add(b8);
        b9.setBounds(200, 170, 100, 60);
        b9.setFont(new Font("dialog", 1, 30));
        paneNum.add(b9);
        b0.setBounds(100, 230, 100, 60);
        b0.setFont(new Font("dialog", 1, 30));
        paneNum.add(b0);
        ad.setBounds(0, 230, 100, 60);
        ad.setFont(new Font("dialog", 1, 30));
        paneNum.add(ad);
        mn.setBounds(200, 230, 100, 60);
        mn.setFont(new Font("dialog", 1, 30));
        paneNum.add(mn);
        xs.setBounds(0, 290, 100, 60);
        xs.setFont(new Font("dialog", 1, 30));
        paneNum.add(xs);
        cu.setBounds(200, 290, 100, 60);
        cu.setFont(new Font("dialog", 1, 30));
        paneNum.add(cu);
        eq.setBounds(100, 290, 100, 60);
        eq.setFont(new Font("dialog", 1, 30));
        paneNum.add(eq);
        po.setBounds(0, 350, 100, 60);
        po.setFont(new Font("dialog", 1, 30));
        paneNum.add(po);
        bk.setBounds(200, 350, 100, 60);
        bk.setFont(new Font("dialog", 1, 30));
        paneNum.add(bk);
        cl.setBounds(100, 350, 100, 60);
        cl.setFont(new Font("dialog", 1, 30));
        paneNum.add(cl);

        b0.addActionListener(new ActionListener() {            //b0��ť����¼�����--��������
            @Override
            public void actionPerformed(ActionEvent e) {       //�¼�����
                System.out.println("���°�ť0");                //����̨��ӡ
                sBuilder.append("0");                        //sBuilder�ַ�����ӡ�0��
                label1.setText(sBuilder.toString());         //��ǩ�ı�����ΪsBuilder��String
            }
        });
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("���°�ť1");
                sBuilder.append("1");
                label1.setText(sBuilder.toString());
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("���°�ť2");
                sBuilder.append("2");
                label1.setText(sBuilder.toString());
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("���°�ť3");
                sBuilder.append("3");
                label1.setText(sBuilder.toString());
            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("���°�ť4");
                sBuilder.append("4");
                label1.setText(sBuilder.toString());
            }
        });
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("���°�ť5");
                sBuilder.append("5");
                label1.setText(sBuilder.toString());
            }
        });
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("���°�ť6");
                sBuilder.append("6");
                label1.setText(sBuilder.toString());
            }
        });
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("���°�ť7");
                sBuilder.append("7");
                label1.setText(sBuilder.toString());
            }
        });
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("���°�ť8");
                sBuilder.append("8");
                label1.setText(sBuilder.toString());
            }
        });
        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("���°�ť9");
                sBuilder.append("9");
                label1.setText(sBuilder.toString());
            }
        });

        ad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("���°�ť�Ӻ�");
                a = Double.parseDouble(sBuilder.toString());
                sBuilder = new StringBuilder();
                label1.setText("+");
                ip = 0;      //��0��Ϊ�ӷ��ı�־
            }
        });
        mn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("���°�ť����");
                a = Double.parseDouble(sBuilder.toString());
                sBuilder = new StringBuilder();
                label1.setText("-");
                ip = 1;     //��1��Ϊ�����ı�־
            }
        });
        xs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("���°�ť�˺�");
                a = Double.parseDouble(sBuilder.toString());
                sBuilder = new StringBuilder();
                label1.setText("*");
                ip = 2;    //��2��Ϊ�˷��ı�־
            }
        });
        cu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("���°�ť����");
                a = Double.parseDouble(sBuilder.toString());
                sBuilder = new StringBuilder();
                label1.setText("��");
                ip = 3;         //��3��Ϊ�����ı�־
            }
        });
        eq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {     //�������㷨
                System.out.println("���°�ť���ں�");
                if (!"".equals(sBuilder.toString()) && (!(a == 0))) {   //��sBuilder���ǿ��ַ����Ҳ�Ϊ0ʱ��if
                    b = Double.parseDouble(sBuilder.toString());  //��b��Ϊ����
                    if (ip == 0) {            //ipΪ0ʱ-�ӷ���־
                        result = a + b;     //�������
                        label1.setText(result.toString());//��ǩ����Ϊ���
                        sBuilder = new StringBuilder();//��ֵsBuilder
                        sBuilder.append(result);//��������ӵ�sBuilder��
                    } else if (ip == 1) {
                        result = a - b;
                        label1.setText(result.toString());
                        sBuilder = new StringBuilder();
                        sBuilder.append(result);
                    } else if (ip == 2) {
                        result = a * b;
                        label1.setText(result.toString());
                        sBuilder = new StringBuilder();
                        sBuilder.append(result);
                    } else if (ip == 3) {
                        result = a / b;
                        label1.setText(result.toString());
                        sBuilder = new StringBuilder();
                        sBuilder.append(result);
                    } else {
                        label1.setText(sBuilder.toString());
                    }
                }
            }
        });
        po.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("���°�ťС����");
                sBuilder.append(".");//���һ��-��-��
                label1.setText(sBuilder.toString());
            }
        });
        cl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("���°�ť�����");
                sBuilder = new StringBuilder();//��ֵsBuilder
                label1.setText("");//����ǩ����Ϊ���ַ�
            }
        });
        bk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("���°�ťɾ����");
                if (!"".equals(sBuilder.toString())) {//��sBuilder��Ϊ���ַ���ʱ
                    sBuilder.deleteCharAt(sBuilder.length() - 1);//sBuilderɾ�����һλ
                    label1.setText(sBuilder.toString());//���ñ�ǩ
                }
            }
        });

        this.setVisible(true);//���ô���Ϊ�ɼ�--�������������ɺ����ô��ڿɼ�

        t = new Thread(new Runnable() {   //����ʱ����ʾ�Ķ��߳�
            @Override

            public void run() {
                while (true) {
                    try {

                        Thread.sleep(500);
                        SimpleDateFormat dat = new SimpleDateFormat(" ��ǰʱ��:yyyy-MM-dd  " + "HH:mm:ss");
                        String Tome = dat.format(new Date());
                        label2.setText(Tome);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }
            }
        });
        t.setName("ʱ����ʾ�߳�");
        t.start();  //�����߳�

        checkLabel.setPreferredSize(new Dimension(300,15));
        checkLabel.setFont(labelFont);
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
                    checkLabel.setText("  ��ǰ�����߳���>>>"+noThreads+"<<<");
                }
            }
        });
        checkThread.setName("�̼߳�����");
        checkThread.start();


        pane2.add(checkLabel);
        pane2.add(label2);






    }




    public static void main(String[] args) {   //JAVA��main����
//        Calua A =new Calua();
//        new Thread(A).start();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyJFrame(new CaluaV2());  //ʵ����Calua����
            }
        });


    }


}



//==============================================================================================================

//class asd implements Runnable {  //����Ķ��߳���
//    @Override
//    public void run() {
//        while (true) {
//            try {
//
//                Thread.sleep(1000);  //����1s
//                SimpleDateFormat dat = new SimpleDateFormat("yyyy-MM-dd" + "HH:mm:ss");//��ʽ
//                String Tome = dat.format(new Date());//��ʽ��ʱ��
//                System.out.println(Tome);//��ӡ
//            } catch (InterruptedException e) {  //��׽�쳣
//                e.printStackTrace();
//
//            }
//        }
//
//
//    }
//}