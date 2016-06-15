package priv.pcap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import priv.online.sip_Dict;
/**
 * ���ߣ�������
 * ʱ�䣺2016/05/20
 * ���ܣ����ļ�newTemp.txt�ж�ȡsip���õ�sip�Ļ��������ϣ���ȥ�صĽ������
 * ���洢��sip_dict_list��ArrayList<String>������
 */
public class pcap_ReadFromFile_ByLines {
	ArrayList<String> sip_dict_list=new ArrayList<String>();
	/**
     * ����Ϊ��λ��ȡ�ļ��������ڶ������еĸ�ʽ���ļ�
     */
    public void readFileByLines(String fileName) {
    	sip_Dict sip=new sip_Dict();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
            	//��ȡ���ĵ�ַ����sip��ַ�ֵ��ļ�online.sip_Dict.java
            	sip.sip_flow_ip_Dict(tempString,sip_dict_list);
                // ��ʾ�к�
                //System.out.println("line " + line + ": " + tempString);
                //line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String fileName = "C:/Users/Administrator/Desktop/ping.pcap";
		pcap_ReadFromFile_ByLines prb =new pcap_ReadFromFile_ByLines(); 
		String fileName = "C:/Users/Administrator/Desktop/yuan/newTemp.txt";  
		prb.readFileByLines(fileName); 
		
		//System.out.println(prb.sip_dict_list.get(7566));//ȥ�ص�sip��������sip_dict_list��
		//System.out.println(prb.sip_dict_list.indexOf("200.217.184.147"));//ȡ����ip���ڵ�λ��
	}
	

}
