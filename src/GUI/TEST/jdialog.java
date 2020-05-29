package GUI.TEST;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class jdialog extends JFrame implements ActionListener
{
    JPanel jp = new JPanel ();
    JButton jb1 = new JButton("ֻ��ok��ť");
    JButton jb2 = new JButton("Yes/No��ť");
    JButton jb3 = new JButton("YES/No/Cancle3����ť");
    JButton jb4 = new JButton("YSE/No/Cancle3����ť���Զ��壩");
    JButton jb5 = new JButton("������Ϣ�Ի���");
    JButton jb6 = new JButton("ѡ��Ի���");
    JButton []jbuttonArray = new JButton[]{jb1,jb2,jb3,jb4,jb5,jb6};
    JLabel jl = new JLabel("�����ε�����ť�����õ���ͬ�ĶԻ���");
    public jdialog()
    {
        jp.setLayout(new GridLayout(2,3));
        for(int i=0;i<jbuttonArray.length;i++)
        {
            jp.add(jbuttonArray[i]);
            jbuttonArray[i].addActionListener(this);
        }
        this.add(jp);
        this.add(jl,BorderLayout.SOUTH);
        this.setTitle("JOptionPane�Ի���");
        this.setBounds(200,200,500,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent a)
    {
        if(a.getSource()==jbuttonArray[0])
        {
            JOptionPane.showMessageDialog(this, "��ӭ���ٱ��꣡","ֻ��OK��ť����Ϣ�Ի���",JOptionPane.INFORMATION_MESSAGE);
            jl.setText("��ӭ����ӭ�����һ�ӭ");
        }
        else if(a.getSource()==jbuttonArray[1])
        {
            int index = JOptionPane.showConfirmDialog(this,"���ã����ǵ�һ�ι��ٱ�����","��YES/ON��ť��ȷ�϶Ի���",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            jl.setText("����"+((index==0)?"�¹˿ͣ���ӭ":"�Ϲ˿��ˣ���ӭ��"));
        }
        else if(a.getSource()==jbuttonArray[2])
        {
            Object[] options = {"ϲ��","��ϲ��"};
            int index = JOptionPane.showOptionDialog(this,"���ã���ϲ�����������","��Yes/No��ť���Զ��壩��ȷ�϶Ի���",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
            jl.setText("�Ҽ����ˣ���"+((index==0)?"ϲ��":"��ϲ��")+"�������");
        }
        else if(a.getSource()==jbuttonArray[3])
        {
            Object[] options = {"�ð�������Ҳ��һ��","���ˣ����ǲ˹�����","������������Ϻ"};
            int index = JOptionPane.showOptionDialog(this, "��ã������������Ʋ��������","��Yes/No/CANCEL_OPTION��ȷ�϶Ի���", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options, options[2]);
            String reply = (index == 0)?"�Եȣ�����������ܿ�͵���":(index==1)?"�õģ�����Ҫ�ٺ���":(index==2)? "�õģ���������Ϻ�ܿ�͵���":"�Բ��������ˣ�";
            jl.setText(reply);
        }
        else if(a.getSource()==jbuttonArray[4])
        {
            String dishes = JOptionPane.showInputDialog(this,"������������ԵĲˣ�","������Ϣ�Ի���",JOptionPane.PLAIN_MESSAGE);
            jl.setText(dishes);
        }
        else if(a.getSource()==jbuttonArray[5])
        {
            String[] options = new String [] {"����Ϻ","�����","����ţ������","�߹���"};
            int index = JOptionPane.showOptionDialog(this,"�����Ǳ���������͵Ĳˣ�����ѡһ����Ĭ�����߹���","ѡ��Ի���",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[3]);
            String dishes = (index==0)?"����Ϻ":(index==1)?"�����":(index==2)?"����ţ������":"�߹���";
            jl.setText("��ѡ���˱������͵�"+dishes+"!");
        }
    }
    public static void main(String args[])
    {
        new jdialog();
    }
}