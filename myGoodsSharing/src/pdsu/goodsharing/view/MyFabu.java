package pdsu.goodsharing.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import pdsu.goodsharing.dao.GoodDao;
import pdsu.goodsharing.model.Good;

@SuppressWarnings("serial")
public class MyFabu extends JPanel {
	JTable table;
	JScrollPane tablePane;
	JButton returnbtn;
	String curUser;
	String title[]= {"编号","名称","类别","状态","发布人"};
	String[][]data=GoodDao.Mylist(curUser);
	public  MyFabu(String unname) {
		curUser=unname;
		data=GoodDao.Mylist(unname);
		//创建表格并展示数据
		
		table=new JTable(data,title);
		//设置表格属性和参数
		 settable();
		tablePane=new JScrollPane(table);
		tablePane.setPreferredSize(new Dimension(500,240));
		add(tablePane);
		//创建借用按钮添加事件处理
		returnbtn= new JButton("删除");
		add(returnbtn);
		addListeners();
	}
	private void addListeners() {
		// TODO 自动生成的方法存根
returnbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				
				//向用户弹出曲儿对话框，用户来选择
				//用户选择否结束
				//如果选择是，1.找到表格中招到的行号，如果行号为-1则提醒用户选择并返回
				//获得了行号，根据行号找到物品编号，在二维数组里找，若编号非空则完成借用（Good。borrow)方法
				//更新表格数据
				int c=JOptionPane.showConfirmDialog(null, "确认要删除该物品吗","确认对话框",JOptionPane.YES_NO_OPTION);
				if(c==JOptionPane.NO_OPTION) {
					return;
				}
				else if(c==JOptionPane.YES_OPTION) {
					int row=table.getSelectedRow();
					if(row==-1) {
						JOptionPane.showMessageDialog(null, "请先选择你要删除的物品!!!");
						return ;
					}
					String id=data[row][0];
					
					
					
					
					if(!id.equals("")) {
						String info=GoodDao.dele(id);
						if(info.equals("删除成功")) {
							JOptionPane.showMessageDialog(null, "删除成功");
						}
						else {
							JOptionPane.showMessageDialog(null, info);
						}
					}
					
				}
			//更新表格数据
				String[][]data=GoodDao.Mylist(curUser);			
			    DefaultTableModel model=new DefaultTableModel(data,title);
			    table.setModel(model);
			    
			}
			
		});
			
	}
	
	private void settable() {
		// TODO 自动生成的方法存根
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getTableHeader().setReorderingAllowed(false);//不可变列顺序
		table.getTableHeader().setResizingAllowed(false);//不可变宽
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
}
