package pdsu.goodsharing.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pdsu.goodsharing.model.User;

public class SharingManager extends JFrame implements ActionListener {
	JLabel top; 
	JPanel menuPanel;//西部菜单面板
	JPanel centerPanel;//中部面板
	String curUser;//定义当前用户名
	//JButton	
	public SharingManager(User user) {
		//初始化当前用户名
		curUser=user==null?"default":user.getUname();
		//初始化窗口本身
		init();
		addCompenent();
		
		setVisible(true);
	}
	private void addCompenent() {
		// TODO 自动生成的方法存根
		top=new JLabel(new ImageIcon("image/mainPic.Jpeg"));
		add(top,BorderLayout.NORTH);
		//设置左边菜单
		menuPanel=new JPanel();
		menuPanel.setLayout(new GridLayout(6,1));
		//定义菜单按钮
		String []menus= {"共享列表","我要发布","我要归还","修改密码","我的发布","退出"};
		JButton[] btns=new JButton[menus.length];
		for(int i=0;i<menus.length;i++) {
			btns[i]=new JButton(menus[i]);
			btns[i].setBackground(Color.WHITE);
			btns[i].addActionListener(this);
			btns[i].setFont(new Font("黑体",Font.BOLD,16));
			menuPanel.add(btns[i]);
		}
		//btns[4].setEnabled(false);
		add(menuPanel,BorderLayout.WEST);
		//中部初始面板
		centerPanel=new JPanel();
		//中部内容组件
		JLabel lbCenter=new JLabel("欢迎"+curUser+"使用共享物品管理平台");
		lbCenter.setHorizontalAlignment(JLabel.CENTER);//标签左右居中
	lbCenter.setVerticalAlignment(JLabel.TOP);
		lbCenter.setFont(new Font("宋体",Font.BOLD,26));
		centerPanel.add(lbCenter);
		add(centerPanel,BorderLayout.CENTER);
	}
	private void init() {
		// TODO 自动生成的方法存根
		setSize(650,450);
		setLocationRelativeTo(null);//屏幕居中
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(new ImageIcon("image/icon.jpg").getImage());//设置图标
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		 new SharingManager(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		String info=e.getActionCommand();
		switch(info){
			case "共享列表":
				//修改中部面板为展示共享物品信息面板
				centerPanel.setVisible(false);
				centerPanel=new SharingShow(curUser);
				add(centerPanel,BorderLayout.CENTER);
				if(PwdVary.K==1) {
					dispose();
					new  LogFrm();
				}
				break;
				
			case "我要发布":
				centerPanel.setVisible(false);
				centerPanel=new SharingAdd(curUser);
				add(centerPanel,BorderLayout.CENTER);
				if(PwdVary.K==1) {
					dispose();
					new  LogFrm();
				}
				//修改中部面板为发布共享物品的面板
				break;
			case "我要归还":
				centerPanel.setVisible(false);
				centerPanel=new SharingRenturn(curUser);
				add(centerPanel,BorderLayout.CENTER);
				if(PwdVary.K==1) {
					dispose();
					new  LogFrm();
				}
				//修改中部面板为归还共享物品面板
				break;
			case "修改密码":centerPanel.setVisible(false);
			centerPanel=new PwdVary(curUser);
			add(centerPanel,BorderLayout.CENTER);
			if(PwdVary.K==1) {
				dispose();
				new  LogFrm();
			}
			//修改中部面板为归还共享物品面板
			break;
			case "我的发布":
				centerPanel.setVisible(false);
				centerPanel=new MyFabu(curUser);
				add(centerPanel,BorderLayout.CENTER);
				if(PwdVary.K==1) {
					dispose();
					new LogFrm();
				}
				//修改中部面板为发布共享物品的面板
				break;
			case "退出":
				dispose();
				break;
		}
	}

}
