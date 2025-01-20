package pdsu.goodsharing.view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import pdsu.goodsharing.dao.UserDao;

public class PwdVary extends JPanel{
	String curuser;
	JLabel lbpwd,lbpwd2,lbpwd3;
	static int K=0;
	JPasswordField tfpwd,tfpwd2,tfpwd3;
	JButton btn;
	public  PwdVary(String curname) {
		setLayout(null);
		curuser=curname;
		Font f = new Font("宋体",Font.BOLD,14);
		lbpwd=new JLabel("原密码");
		lbpwd.setBounds(130,45,120,35);
		add(lbpwd);
		lbpwd.setFont(f);
		lbpwd2=new JLabel("修改的密码");
		lbpwd2.setBounds(130,95,120,35);
		add(lbpwd2);
		lbpwd2.setFont(f);
		lbpwd3=new JLabel("确认密码");
		lbpwd3.setFont(f);
		lbpwd3.setBounds(130,145,120,35);
		add(lbpwd3);
		tfpwd=new JPasswordField();
		tfpwd.setBounds(230,45,200,35);
		add(tfpwd);
		tfpwd2 =new JPasswordField();
		tfpwd2.setBounds(230,95,200,35);
		add(tfpwd2);
		tfpwd3=new JPasswordField();
		tfpwd3.setBounds(230,145,200,35);
		add(tfpwd3);
		btn=new JButton("确认");
		btn.setFont(f);
		btn.setBounds(200, 245, 100, 40);
		add(btn);
		addListenter();
	}
	private void addListenter() {
		// TODO 自动生成的方法存根
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				if(tfpwd.getPassword().length==0||tfpwd.getPassword()==null) {
					JOptionPane.showMessageDialog(null, "请输入原密码","错误",JOptionPane.ERROR_MESSAGE);
					
				}
				else {
					if(Arrays.equals(tfpwd2.getPassword(), tfpwd3.getPassword())) {
						String info=UserDao.gai(curuser,tfpwd2.getPassword(),tfpwd.getPassword());
						if(info.equals("修改成功")) {
							JOptionPane.showMessageDialog(null, info);
							 K=1;
							
						}
						else {
							JOptionPane.showMessageDialog(null, info,"错误",JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "两次密码不一致","错误",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	
}
