package priv.online;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.String;

import com.sun.java_cup.internal.internal_error;

public class vrt_Storage {

	/**
	 * @param args
	 * ��������洢����
	 * ���룺����̨/��ȡ�ļ�/ʵʱ��ȡ����Ϣ�еĵ�ַ��<src,des>,��ַ�������ʽΪ[1.1.1.1 2.2.2.2]�м�Ϊ�ո�
	 * ������hashӳ��
	 * �����һ������һ�������ڵ�����������0,1����
	 */
	public void flow_vrt_Storage(String[] sip,String[] dip) {
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//test����
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String str;
		String []str_split;//����ַ���ÿո�ֿ���Դ��ַ��Ŀ�ĵ�ַ
		int count=0;
		try {
			while (count<10) {//һ�����һ��������ʮ����
				//System.out.println(str.split(" "));//ʹ��split()���ַ����ÿո�ֿ����ֱ�洢
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
