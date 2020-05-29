package GUI.TEST;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * �������������
 *
 * @author ���
 * @since 20120806
 * @version 0.0.1
 * */
public class JExtensionPanel extends JPanel implements MouseListener {
    private static final long serialVersionUID = 1L;
    private boolean expand;// �Ƿ�չ��
    private String title;// ����
    private JLabel label;// �������
    private Component panel;// �����
    private ImageIcon up_icon, down_icon;// ͼ��

    // ----���ֹ��캯��---------------------------------------
    /**
     * �������������,Ĭ��չ��
     *
     * @author ���
     * @since 20120806
     * @version 0.0.1
     *
     * */
    public JExtensionPanel() {
        this("", null, true);
    }

    /**
     * �������������,Ĭ��չ��
     *
     * @author ���
     * @since 20120806
     * @version 0.0.1
     * @param c
     *            �������
     * */
    public JExtensionPanel(Component c) {
        this("", c, true);
    }

    /**
     * �������������,Ĭ��չ��
     *
     * @author ���
     * @since 20120806
     * @version 0.0.1
     * @param title
     *            ����
     * @param c
     *            �������
     * */
    public JExtensionPanel(String title, Component c) {
        this(title, c, true);
    }

    /**
     * �������������
     *
     * @author ���
     * @since 20120806
     * @version 0.0.1
     * @param title
     *            ����
     * @param c
     *            �������
     * @param expand
     *            Ĭ��չ�����
     * */
    public JExtensionPanel(String title, Component c, boolean expand) {
        this.title = title;
        this.expand = expand;
        this.panel = c;
        init();
    }

    // ----���ַ���---------------------------------------
    /**
     * �������������״̬
     * */
    public boolean isExpand() {
        return expand;
    }

    /**
     * ������������
     *
     * @param expand չ��
     * @param false ����
     * */
    public void setExpand(boolean expand) {
        this.expand = expand;
        if (this.expand) {
            this.label.setIcon(down_icon);
            this.label.setBorder(BorderFactory.createLoweredBevelBorder());
            if (null != this.panel) {
                panel.setVisible(true);
            }
        } else {
            this.label.setIcon(up_icon);
            this.label.setBorder(BorderFactory.createRaisedBevelBorder());
            if (null != this.panel) {
                panel.setVisible(false);
            }
        }
        this.updateUI();
        this.updateUI();
    }

    /**
     * ���������������
     * */
    public Component getPanel() {
        return panel;
    }

    /**
     * �����������
     *
     * @param panel
     *            �������
     * */
    public void setPanel(Component panel) {
        if (null != panel) {
            this.remove(this.panel);
            this.panel = panel;
            this.add(this.panel, BorderLayout.CENTER);
            if (this.expand) {
                panel.setVisible(true);
            } else {
                panel.setVisible(false);
            }
        } else {
            this.remove(this.panel);
            this.panel = null;
        }
        this.updateUI();
    }

    /**
     * ����������
     * */
    public String getTitle() {
        return title;
    }

    /**
     * ����������
     *
     * @param panel
     *            ������
     * */
    public void setTitle(String title) {
        this.title = title;
        this.label.setText(title);
        this.updateUI();
    }

    /**
     * ��������ָʾͼ��
     * */
    private void createImages() {
        int w = this.getPreferredSize().width, h = this.getPreferredSize().height;
        BufferedImage open = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB), close = new BufferedImage(
                w, h, BufferedImage.TYPE_INT_RGB);
        // չ����ͼ��
        Graphics2D g2 = open.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(this.getBackground());
        g2.fillRect(0, 0, w, h);
        int[] x = { 0, w / 2, w };
        int[] y = { h / 4, 3 * h / 4, h / 4 };
        Polygon p = new Polygon(x, y, 3);
        g2.setPaint(Color.BLACK);
        g2.fill(p);
        g2.setPaint(Color.BLACK);
        g2.draw(p);
        g2.dispose();
        down_icon = new ImageIcon(open);
        // �����ĵ�ͼ��
        g2 = close.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(getBackground());
        g2.fillRect(0, 0, w, h);
        x = new int[] { w / 4, 3 * w / 4, w / 4 };
        y = new int[] { 0, h / 2, h };
        p = new Polygon(x, y, 3);
        g2.setPaint(Color.GRAY);
        g2.fill(p);
        g2.setPaint(Color.GRAY);
        g2.draw(p);
        g2.dispose();
        up_icon = new ImageIcon(close);
    }

    /**
     * ��ʼ������
     * */
    private void init() {
        this.createImages();// ���ͼ��
        this.setLayout(new BorderLayout());
        if (this.expand) {
            this.label = new JLabel(this.title, this.down_icon,
                    SwingConstants.LEADING);
            this.label.setBorder(BorderFactory.createLoweredBevelBorder());
            if (null != this.panel) {
                this.add(this.panel, BorderLayout.CENTER);
            }
        } else {
            this.label = new JLabel(this.title, this.up_icon,
                    SwingConstants.LEADING);
            this.label.setBorder(BorderFactory.createRaisedBevelBorder());
            if (null != this.panel) {
                this.add(this.panel, BorderLayout.CENTER);
                this.panel.setVisible(false);
            }
        }
        this.add(this.label, BorderLayout.NORTH);

        this.label.addMouseListener(this);
    }

    // ----�ӿڷ�����ʵ��---------------------------------------

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == label) {
            expand = !expand;
            if (this.expand) {
                this.label.setIcon(down_icon);
                this.label.setBorder(BorderFactory.createLoweredBevelBorder());
                if (null != this.panel) {
                    panel.setVisible(true);
                }
            } else {
                this.label.setIcon(up_icon);
                this.label.setBorder(BorderFactory.createRaisedBevelBorder());
                if (null != this.panel) {
                    panel.setVisible(false);
                }
            }
            this.updateUI();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = f.getContentPane();
        c.setLayout(new BorderLayout());

        c
                .add(new JExtensionPanel("test1", new JButton("test1"),false),
                        BorderLayout.NORTH);
        c
                .add(new JExtensionPanel("test2", new JButton("test2"),false),
                        BorderLayout.CENTER);
        f.setSize(360, 500);
        f.setLocation(200, 100);
        f.setVisible(true);
    }
}