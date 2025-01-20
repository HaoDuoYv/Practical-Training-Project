package pdsu.goodsharing.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import pdsu.goodsharing.dao.UserDao;
import pdsu.goodsharing.model.User;

public class RegFrm extends JFrame{
	JLabel title;//上部标签 显示图片
	JLabel lbUname,lbPwd,lbPwd2;//三哥提示标签
	JTextField tfUname;//输入用户名的文本框
	JPasswordField pfPwd,pfPwd2;//输入密码及确认密码的密码框
	JButton btnReg,btnReset;//注册和重置
	JPanel jp;//中部面板
	
	
	//定义字体
	Font f=new Font("宋体",Font.BOLD,20);
	public RegFrm() {
		init();
		addCompenent();
		addListeners();
		setVisible(true);
	}
	private void addListeners() {
		// TODO 自动生成的方法存根
		//当点击重置按钮时清空内容
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				tfUname.setText("");
				pfPwd.setText("");
				pfPwd2.setText("");
				
			}
		});
		btnReg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				//1.获取用户的输入信息，判断两次输入密码是否一样，只有一致时候，才能进入第2步。否则，进入提醒两次密码不一致信息
				//2.将输入的信息封装成对象
				//3.将对象传递UserDao.reg(user),如果返回注册成功，可以给出对话框提示用户注册成功，返回登录界面，
				//4.如果注册失败，三个框置空，提示注册失败原因
				
				if(Arrays.equals(pfPwd.getPassword(), pfPwd2.getPassword())) {
					String uname=tfUname.getText();
					char[] pwd=pfPwd.getPassword();
					User user=new User(uname,pwd);
			String info=UserDao.reg(user);
			if(info=="注册成功") {
			JOptionPane.showMessageDialog(null, info,"恭喜你注册成功",JOptionPane.INFORMATION_MESSAGE);
			    new LogFrm();
				RegFrm.this.dispose();
			}else {
				JOptionPane.showMessageDialog(null, info,"注册失败",JOptionPane.WARNING_MESSAGE);
				tfUname.setText("");
				pfPwd.setText("");
				pfPwd2.setText("");
			}
				}
				else {
					JOptionPane.showMessageDialog(null, "两次输入的密码不一致！请重新输入","提示",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
	//初始化窗口本身属性
	public void init() {
		setIconImage(new ImageIcon("image/icon.jpg").getImage());//设置图标
		setSize(607,469);//设置大小
		setResizable(false);//不可设置大小
		setLocationRelativeTo(null);//位置
		setTitle("用户注册");//设置标题
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	//在窗口中创建并添加组件
	void addCompenent() {
		
		title=new JLabel(new ImageIcon("image/zhuce.png"));
		add(title,BorderLayout.NORTH);//创建标签，显示图片，添加到窗口北部
		jp=new JPanel();
		jp.setBackground(new Color(212,232,248));
		jp.setLayout(null);
		add(jp,BorderLayout.CENTER);//创建面板，添加至窗口的中部，设置面板布局为null
		
		//在面板中添加组件
		lbUname=new JLabel("用 户 名:");
		lbUname.setFont(f);
		lbUname.setBounds(130,45,120,35);//用户名：标签（130，45，120，35）
		lbPwd=new JLabel("密    码:");
		lbPwd.setFont(f);
		lbPwd.setBounds(130,95,120,35);//密码：标签（130，95，120，35）
		lbPwd2=new JLabel("确认密码:");
		lbPwd2.setFont(f);
		lbPwd2.setBounds(130,145,120,35);//确认密码：标签（130，145，120，25）
		tfUname=new JTextField();
		tfUname.setBounds(230,45,200,35);//用户名文本框及位置
		tfUname.setFont(f);
		pfPwd=new JPasswordField();
		pfPwd.setBounds(230,95,200,35);//密码框及位置
		pfPwd2=new JPasswordField();
		pfPwd2.setBounds(230,145,200,35);//确认密码框及位置
		btnReg=new JButton("注册");
		btnReg.setFont(f);
		btnReset =new JButton("重置");
		btnReset.setFont(f);
		btnReg.setBounds(180,210,80,30);
		btnReset.setBounds(300,210,80,30);//注册和重置密码框位置
		//把组件添加到面板中
		jp.add(lbUname);
		jp.add(lbPwd);
		jp.add(lbPwd2);
		jp.add(tfUname);
		jp.add(pfPwd);
		jp.add(pfPwd2);
		jp.add(btnReg);
		jp.add(btnReset);
	}
	
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new RegFrm();
	}

}
