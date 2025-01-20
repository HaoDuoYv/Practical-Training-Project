package pdsu.goodsharing.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import pdsu.goodsharing.model.Good;
import pdsu.goodsharing.model.User;
import pdsu.goodsharing.utils.FileUitil;

/**
 * 管理和维护共享物品，功能有：1.显示物品，2初始化物品，3.添加物品，
 * 4.显示某个用户借用物品信息列表，5.借用物品 6.归还物品
 * @author dtk
 *
 */
@SuppressWarnings("unchecked")
public class GoodDao {
	static List<Good> goods;
	static String fname="goods.dat";
	//静态代码块 当Good类加载时 把goods.dat的物品信息读到list列表里
	static{
	
		goods=(List<Good>)FileUitil.load(fname);
		if(goods==null) {
			goods=new ArrayList<Good>();
		}
	}
	//初始化物品
	public static void initData() {
		goods.clear();
		goods.add(new Good("200328150001","图书",Good.TYPE_DURABL,"zs",Good.STATUS_NORMAL,"Java2实用教程（第5版）"));
		goods.add(new Good("200331170002","玩具车",Good.TYPE_DURABL,"zs",Good.STATUS_NORMAL,"适合1-2岁儿童"));
		goods.add(new Good("200410150003","积木",Good.TYPE_DURABL,"zs",Good.STATUS_NORMAL,"适合1-2岁儿童的积木"));
		goods.add(new Good("200328150004","皮球",Good.TYPE_DURABL,"zs",Good.STATUS_NORMAL,"红色皮球，直径20cm左右"));
		goods.add(new Good("200328150005","充电宝",Good.TYPE_DURABL,"zs",Good.STATUS_NORMAL,"小米充电宝10400mAh"));
		goods.add(new Good("200328150006","梯子",Good.TYPE_DURABL,"zs",Good.STATUS_NORMAL,"高3米的可折叠梯子，承重100KG。"));
		goods.add(new Good("200528150007","5号电池",Good.TYPE_CONSUM,"zs",Good.STATUS_NORMAL,"南孚电池，2节。"));
		goods.add(new Good("200528150107","口罩",Good.TYPE_CONSUM,"zs",Good.STATUS_NORMAL,"一次性医用外科口罩"));
		goods.add(new Good("200528150206","84消毒液",Good.TYPE_CONSUM,"zs",Good.STATUS_NORMAL,"500ml装，1瓶。"));
		goods.add(new Good("200528150305","酒精湿巾",Good.TYPE_CONSUM,"zs",Good.STATUS_NORMAL,"50片装，1袋。"));
		FileUitil.save(goods, fname);
	}
	//显示物品列表
	public static String[][]list() {
		//initData();
		String[][] data=new String [goods.size()][5];
		int row=0;
		//遍历
		Iterator<Good> it=goods.iterator();
		while(it.hasNext()) {
			Good g=it.next();
			String stat = null;
			String type = null;
			switch(g.getStatus()) {
			/*
			 * data[row][0]=g.getId() data[row][1]= data[row][2]= data[row][3]=
			 * data[row][4]=
			 */
			case Good.STATUS_INVALID:
				stat="失效";break;
			case Good.STATUS_NORMAL:
				stat="正常";break;	
			case Good.STATUS_BORROWED:
				stat="借出";break;
			}
			switch(g.getType()) {
			case Good.TYPE_CONSUM:
				type="消耗品";break;
			case Good.TYPE_DURABL:
				type="耐用品";break;
			}
			data[row][0]=g.getId();
			data[row][1]= g.getName();
			data[row][2]=type;
			//data[row][2]=(g.getType()== Good.TYPE_CONSUM)?"消耗品":"耐用品";
			data[row][3]=stat;
			data[row][4]=g.getUserId();
			row++;
		}
		return data;
	}
	//借用
	public static String borrow(String id, String borrowUser) {
		// TODO 自动生成的方法存根
		for(Good g:goods) {
			if(g.getId().equals(id)) {
				if(g.getType()==Good.TYPE_DURABL) {
					if(g.getStatus()==Good.STATUS_NORMAL)
					{g.setStatus(Good.STATUS_BORROWED);}
					else {
						return "你选择的物品是借出状态不能借用，请重新选择";
					}
				}
				
				else {
					if(g.getStatus()==Good.STATUS_NORMAL)
					{g.setStatus(Good.STATUS_INVALID);}
					else {
						return "你选择的物品是失效状态，不能借用，请重新选择";
					}
					g.setStatus(Good.STATUS_INVALID);
				}
				g.setBorrowId(borrowUser);
				FileUitil.save(goods, fname);
				return "借用成功";
			}
		}
		return "借用的物品不存在，借用失败";
	}
	public static String add(Good good) {
		// TODO 自动生成的方法存根
		for(Good g:goods) {
			if(g.getId().equals(good)) {
				return "发布失败，物品编号重复";
			}
		}
		goods.add(good);
		FileUitil.save(goods, fname);
		return "发布成功";
	}
	public static String returngood(String id) {
		// TODO 自动生成的方法存根
		for(Good g:goods) {
			if(g.getId().equals(id)) {
				g.setStatus(Good.STATUS_NORMAL);
				FileUitil.save(goods, fname);
				return "归还成功";
			}
		}
		return "归还失败";
	}
	public static String[][] returnlist(String curUser) {
		// TODO 自动生成的方法存根
	     int shu=0;
	     for(Good go:goods) {
				
				if(go.getStatus()==Good.STATUS_BORROWED) {
				if(go.getBorrowId().equals(curUser)) {						    
					    shu++;		}
				}
	     }
		String [][]data = new String [shu][5] ; 
		int row=0;
		
		
				for(Good g:goods) {
					
					if(g.getStatus()==Good.STATUS_BORROWED) {
					if(g.getBorrowId().equals(curUser)) {						    
						    												
							String stat = null;							
							switch(g.getStatus()) {
							
							case Good.STATUS_INVALID:
							case Good.STATUS_NORMAL:
								stat="正常";break;	
							case Good.STATUS_BORROWED:
								stat="借出";break;
							}
							
							data[row][0]=g.getId();
							data[row][1]= g.getName();
							
							data[row][2]=(g.getType()== Good.TYPE_CONSUM)?"消耗品":"耐用品";
							data[row][3]=stat;
							data[row][4]=g.getUserId();
							row++;
							
						}
					}
				}
				
			
		
		return data;
	}
	
	public static void main(String[] args) {
		Mylist("马嘉祺");
		 dele("240630155734");
		//initData();
	}
	public static String[][] Mylist(String user) {
		// TODO 自动生成的方法存根
		int shu=0;
	     for(Good go:goods) {
				System.out.println(go.getUserId());
				
				if(go.getUserId().equals(user)) {						    
					    shu++;		}
				}
	     System.out.println(shu);
		String [][]data = new String [shu][5] ; 
		int row=0;
		
		
				for(Good g:goods) {
					
					
					if(g.getUserId().equals(user)) {						    
						 System.out.println(g.getId());											
							String stat = null;							
							switch(g.getStatus()) {
							
							case Good.STATUS_INVALID:
							case Good.STATUS_NORMAL:
								stat="正常";break;	
							case Good.STATUS_BORROWED:
								stat="借出";break;
							}
							
							data[row][0]=g.getId();
							data[row][1]= g.getName();
							
							data[row][2]=(g.getType()== Good.TYPE_CONSUM)?"消耗品":"耐用品";
							data[row][3]=stat;
							data[row][4]=g.getUserId();
							row++;
							
						}
					}
				
				
			
		
		return data;
	}
	public static String dele(String id) {
		// TODO 自动生成的方法存根
		
		for(Good g:goods) {
			if(g.getId().equals(id)) {
				if(g.getType()==Good.TYPE_DURABL) {
					if(g.getStatus()==Good.STATUS_BORROWED)
					
						{return "你选择的物品是借出状态不能删除，请重新选择";}
					
				}
				
			  goods.remove(g);
			  FileUitil.save(goods, fname);
			  return "删除成功";
				}
	          
}
		return "删除失败";
}
}