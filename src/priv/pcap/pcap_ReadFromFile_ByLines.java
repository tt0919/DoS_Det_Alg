package priv.pcap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import priv.online.sip_Dict;
/**
 * 作者：刘婷婷
 * 时间：2016/05/20
 * 功能：从文件newTemp.txt中读取sip并得到sip的基数（集合，即去重的结果），
 * 并存储在sip_dict_list的ArrayList<String>类型中
 */
public class pcap_ReadFromFile_ByLines {
	ArrayList<String> sip_dict_list=new ArrayList<String>();
	/**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public void readFileByLines(String fileName) {
    	sip_Dict sip=new sip_Dict();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
            	//读取到的地址调入sip地址字典文件online.sip_Dict.java
            	sip.sip_flow_ip_Dict(tempString,sip_dict_list);
                // 显示行号
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
		
		//System.out.println(prb.sip_dict_list.get(7566));//去重的sip保存在了sip_dict_list中
		//System.out.println(prb.sip_dict_list.indexOf("200.217.184.147"));//取出该ip所在的位置
	}
	

}
