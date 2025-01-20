package pdsu.goodsharing.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import pdsu.goodsharing.dao.UserDao;
import pdsu.goodsharing.model.User;



public class LogFrm extends JFrame{
		JLabel top;//上部标签 显示图片
		JLabel lbUname,lbPwd;//三个提示标签
		JTextField tfUname;//输入用户名的文本框
		JPasswordField pfPwd;//输入密码及确认密码的密码框
		JButton btnLogin,btnReg;//注册和重置
		JPanel jp;//中部面板
		
		//定义字体
		Font f=new Font("宋体",Font.BOLD,20);
	public LogFrm() {
		PwdVary.K=0;
		init();
		addCompenent();
		addListeners();
		setVisible(true);
	}
	private void addCompenent() {
		// TODO 自动生成的方法存根
		top=new JLabel(new ImageIcon("image/top.jpg"));
		add(top,BorderLayout.NORTH);//创建标签，显示图片，添加到窗口北部
		jp=new JPanel();
		jp.setBackground(new Color(212,232,248));
		jp.setLayout(null);
		add(jp,BorderLayout.CENTER);//创建面板，添加至窗口的中部，设置面板布局为null
		
		//在面板中添加组件
		lbUname=new JLabel("用 户 名");
		lbUname.setFont(f);
		lbUname.setBounds(140,60,100,35);//用户名：
		lbPwd=new JLabel("密    码");
		lbPwd.setFont(f);
		lbPwd.setBounds(140,120,100,35);//密码：
		
		tfUname=new JTextField();
		tfUname.setFont(f);
		tfUname.setBounds(230,60,200,35);//用户名文本框及位置
		pfPwd=new JPasswordField();
		pfPwd.setBounds(230,120,200,35);//密码框及位置
		btnLogin =new JButton("登录");
		btnLogin.setFont(f);
		btnLogin.setBounds(180,210,80,30);
		btnReg=new JButton("注册");
		btnReg.setFont(f);
		btnReg.setBounds(300,210,80,30);//注册和登录密码框位置
		//把组件添加到面板中
		jp.add(lbUname);
		jp.add(lbPwd);
		
		jp.add(tfUname);
		jp.add(pfPwd);
		
		jp.add(btnReg);
		jp.add(btnLogin);
	}
	private void init() {
		// TODO 自动生成的方法存根
		setIconImage(new ImageIcon("image/icon.jpg").getImage());//设置图标
		setSize(607,469);//设置大小
		setResizable(false);//不可设置大小
		setLocationRelativeTo(null);//位置
		setTitle("用户窗口");//设置标题
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void addListeners() {
			btnReg.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					LogFrm.this.dispose();
					new RegFrm();
				}
			});
			btnLogin.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					String uname=tfUname.getText();
					char[] pwd=pfPwd.getPassword();//获取用户名和密码 
					//封装为user对象240630155734
					User user=new User(uname,pwd);
					String info=UserDao.login(user);
					if(info=="登录成功") {
						
						//JOptionPane.showMessageDialog(LogFrm.this, info,"恭喜你登录成功",JOptionPane.INFORMATION_MESSAGE);
						    
							LogFrm.this.dispose();
							new SharingManager(user);
						}else {
							JOptionPane.showMessageDialog(LogFrm.this, info,"登录失败",JOptionPane.ERROR_MESSAGE);
							tfUname.setText("");
							pfPwd.setText("");
							
						}
					
				}
			});
		}	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new LogFrm();
		
}
}