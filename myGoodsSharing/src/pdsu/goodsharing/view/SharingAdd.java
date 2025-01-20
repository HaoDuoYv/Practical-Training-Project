package pdsu.goodsharing.view;

import java.awt.Font;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pdsu.goodsharing.dao.GoodDao;
import pdsu.goodsharing.model.Good;




public class SharingAdd extends JPanel implements ActionListener{
	//private static final long serialVersionUID = 1L;
	//----------------------------（1）定义组件
	JLabel lbId;     //编号标签
	JLabel lbName;   //名称标签
	JLabel lbType;    //类别标签
	JLabel lbUser;    //发布者标签
	JLabel lbDesc;    //描述标签
	
	JTextField tfId;    //编号文本框
	JTextField tfName;  //名称文本框
	JTextField tfUser;  //发布者文本框
	JTextArea taDesc;   //描述信息文本区
	
	JRadioButton rbDurable;   //单选按钮（耐用品）
	JRadioButton rbConsum; //单选按钮（消耗品）
	
	
	JButton btnAdd;  //添加按钮
	
	String curName;
	
	public SharingAdd(String curName) {
		this.curName = curName;
		
		setLayout(null);
		
		//--------------------------（2）创建组件并加入窗口
		//--------（2.1）创建组件
		//------编号------
		lbId = new JLabel("编号");
		lbId.setBounds(30, 20, 50, 40);
		
		tfId = new JTextField();
		tfId.setBounds(90,20,140,40);
		//设置默认的编号
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String id = sdf.format(new Date());
		tfId.setText(id);
		tfId.setEditable(false);
		
		
		//------名称------
		lbName = new JLabel("名称");
		lbName.setBounds(280, 20, 50, 40);
		
		tfName = new JTextField();
		tfName.setBounds(340,20,140,40);
		
		
		//------类别------
		lbType = new JLabel("类别");
		lbType.setBounds(30, 85, 50, 40);
		
		rbDurable = new JRadioButton("耐用品");
		rbConsum = new JRadioButton("消耗品");
		rbDurable.setBounds(85, 85, 70, 40);
		rbConsum.setBounds(160, 85, 70, 40);
		
		//把两个单选按钮设为同一组（实现单选效果）
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbDurable);
		bg.add(rbConsum);
		rbDurable.setSelected(true);
		
		//------发布者------
		lbUser = new JLabel("发布者");
		lbUser.setBounds(280, 85, 50, 40);
		
		tfUser = new JTextField();
		tfUser.setBounds(340,85,140,40);
		tfUser.setText(curName);
		tfUser.setEditable(false);
		
		//------描述 ------
		lbDesc = new JLabel("描述");
		lbDesc.setBounds(30, 150, 50, 40);
		
		taDesc = new JTextArea();
		taDesc.setBounds(90,150,390,80);
		
		
		
		
		//------添加按钮------
		btnAdd = new JButton("添加");
		btnAdd.setBounds(200, 245, 100, 40);
		btnAdd.addActionListener(this);
		
		//修改字体
		Font f = new Font("宋体",Font.BOLD,14);
		lbId.setFont(f);
		tfId.setFont(f);
		lbName.setFont(f);
		tfName.setFont(f);
		
		lbType.setFont(f);
		rbDurable.setFont(f);
		rbConsum.setFont(f);
		lbUser.setFont(f);
		tfUser.setFont(f);
		
		lbDesc.setFont(f);
		taDesc.setFont(f);
		
		btnAdd.setFont(f);

		//--------（2.2）加入窗口
		//把组件添加到面板中
		add(lbId);
		add(tfId);
		add(lbName);
		add(tfName);
		
		add(lbType);
		add(rbDurable);
		add(rbConsum);
		add(lbUser);
		add(tfUser);
		
		add(lbDesc);
		add(taDesc);
		
		add(btnAdd);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(tfName.getText().equals("")) {				
			JOptionPane.showMessageDialog(SharingAdd.this,"物品名称不能为空");
		}
		else {
			  String id=tfId.getText();
			  String name=tfName.getText();
			  int type = 0;
			  if(rbConsum.isSelected()) {
				  type=Good.TYPE_CONSUM;
			  }
			  else if(rbDurable.isSelected()) {
				  type=Good.TYPE_DURABL;
			  }
			  String user=tfUser.getText();
			  char stat=Good.STATUS_NORMAL;
			  String area=taDesc.getText();
			  Good goods=new Good(id, name, type, user, stat, area);
			  String info=GoodDao.add(goods);
			  if(info.equals("发布成功")) {
				  JOptionPane.showMessageDialog(null, "保存共享物品成功");
			  }
			  else {
				  JOptionPane.showMessageDialog(null, info,"错误信息",JOptionPane.ERROR_MESSAGE);
			  }
		
	}
}
}