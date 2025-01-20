package pdsu.goodsharing.model;

import java.io.Serializable;

/**
 * 封装共享物品信息：物品编号，名称，类别，发布人，状态，描述，借用人
 * @author dtk
 *
 */

@SuppressWarnings("serial")
public class Good implements Serializable{
	//定义属性 私有的
	//物品编号，名称，类别，发布人，状态，描述，借用人
	private String id;//物品编号（格式为yy年mmddhhmmss+name）
	private String name;//物品名称
	private int type;//物品类别（0为消耗品，1为耐用品）
	private String userId;//发布者
	private char status;//物品状态（“0”为失效，“1”为正常，“2”借出状态）
	private String desc;//物品描述
	private String borrowId;//借用者
	//定义常量
	
	//物品状态取值
	public static final char STATUS_INVALID='0';
	
	public static final char STATUS_NORMAL='1';
	public static final char STATUS_BORROWED='2';
	//物品状态取值
	public static final int TYPE_DURABL=1;
	public static final int TYPE_CONSUM=0;
	//构造方法
	
	public Good(String id, String name, int type, String userId, char status, String desc) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.userId = userId;
		this.status = status;
		this.desc = desc;
		
	}
	//get set方法
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}
	public static char getStatusInvalid() {
		return STATUS_INVALID;
	}
	public static char getStatusNormal() {
		return STATUS_NORMAL;
	}
	public static char getStatusBorrowed() {
		return STATUS_BORROWED;
	}
	public static int getTypeDurabl() {
		return TYPE_DURABL;
	}
	public static int getTypeConsum() {
		return TYPE_CONSUM;
	}	
}
