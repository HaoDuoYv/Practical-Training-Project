package pdsu.goodsharing.utils; 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javax.swing.JOptionPane;
/**
 * 把List以对象流的形式存入到指定文件中，从文件中以对象流的形式读取数据到List
 * 通过List集合进行文件存取的工具类
 * @author dtk
 *
 */
public class FileUitil {
	
	
	//写文件用对象流
	public static void save(List<?> list,String fname) {
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;
		
		try {
			fos=new FileOutputStream(fname);
			oos=new ObjectOutputStream(fos);
			oos.writeObject(list);
			
		}
		 catch (FileNotFoundException e) {
		JOptionPane.showMessageDialog(null, "保存的数据文件路径错误!请检查文件路径");
		
	} catch (IOException e) {
			// TODO 自动生成的 catch 块
		JOptionPane.showMessageDialog(null, "保存的数据文件错误!请检查文件权限");
		}
		finally {
			if(oos!=null) {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			if(fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}       
	}	
	//读文件 返回list对象
		public static List<?> load(String fname){
			
			FileInputStream fin=null;
			ObjectInputStream iin=null;
			List<?> list = null;
		    File f=new File(fname);
			if(f.exists()) {							
			try {
				fin=new FileInputStream(f);
				iin=new ObjectInputStream(fin);				
			    list=(List<?>)iin.readObject();
			} catch (FileNotFoundException e) {
				// TODO 自动生成的 catch 块
				JOptionPane.showMessageDialog(null, "读取的数据文件路径错误!请检查文件路径");
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				JOptionPane.showMessageDialog(null, "读取的数据文件错误!请检查文件权限");
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				JOptionPane.showMessageDialog(null, "读取的数据文件类型转换异常!请检查类型是否错误");
			}
			finally {
				if(iin!=null) {
					try {
						iin.close();
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				if(fin!=null) {
					try {
						fin.close();
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}
			
		}
			return list;
		}
		
}
