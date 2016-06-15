package priv.online;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
/**
 * 作者：刘婷婷
 * 时间：2016/05/20
 * 功能：将[index,dip,sip]->[sip],并“追加”写入文件newTemp.txt中
 */

public class dealip_three_to_one_read_write {
	String writeName = "C:/Users/Administrator/Desktop/yuan/newTemp.txt";
	
	/**
     * A方法追加文件：使用RandomAccessFile
     */
    public static void appendMethodA(String fileName, String content) {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            //将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * B方法追加文件：使用FileWriter
     */
    public static void appendMethodB(String fileName, String content) {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFileByLines_dealip(String fileName) {
    	sip_Dict sip=new sip_Dict();
        File file = new File(fileName);
        String []tempString_split;
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
            	//读取到的地址调入sip地址字典文件online.sip_Dict.java
            	//sip.sip_flow_ip_Dict(tempString,sip_dict_list);
                //按方法A追加文件
            	tempString_split=tempString.trim().split("\\s+",3);
            	tempString_split[2]+="\r\n";
            	//tempString+="\r\n";
                dealip_three_to_one_read_write.appendMethodA(writeName, tempString_split[2]);
                // 显示行号
                System.out.println("line " + line + ": " + tempString_split[2]);
                line++;
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
		dealip_three_to_one_read_write prb =new dealip_three_to_one_read_write(); 
		String fileName = "C:/Users/Administrator/Desktop/yuan/weiqu/test.txt";  
		prb.readFileByLines_dealip(fileName); 
	}

}
