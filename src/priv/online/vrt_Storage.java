package priv.online;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.String;

import com.sun.java_cup.internal.internal_error;

public class vrt_Storage {

	/**
	 * @param args
	 * 流的虚拟存储向量
	 * 输入：控制台/读取文件/实时读取流信息中的地址对<src,des>,地址对输入格式为[1.1.1.1 2.2.2.2]中间为空格
	 * 操作：hash映射
	 * 输出：一个分组一个周期内的虚拟向量（0,1）对
	 */
	public void flow_vrt_Storage(String[] sip,String[] dip) {
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//test数据
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String str;
		String []str_split;//将地址对用空格分开成源地址和目的地址
		int count=0;
		try {
			while (count<10) {//一个组的一个周期有十个数
				//System.out.println(str.split(" "));//使用split()将字符串用空格分开并分别存储
				count++;
				str=br.readLine();
				str_split=str.trim().split("\\s+",3);
				/*for(String i:str_split )
					System.out.println(i);
				for(int i=0;i<str_split.length;i++)
					System.out.println(str_split[i]);*/
				System.out.println("0"+str_split[0]);
				System.out.println("1"+str_split[1]);
				System.out.println("2"+str_split[2]);
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
