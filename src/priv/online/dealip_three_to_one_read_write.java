package priv.online;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
/**
 * ���ߣ�������
 * ʱ�䣺2016/05/20
 * ���ܣ���[index,dip,sip]->[sip],����׷�ӡ�д���ļ�newTemp.txt��
 */

public class dealip_three_to_one_read_write {
	String writeName = "C:/Users/Administrator/Desktop/yuan/newTemp.txt";
	
	/**
     * A����׷���ļ���ʹ��RandomAccessFile
     */
    public static void appendMethodA(String fileName, String content) {
        try {
            // ��һ����������ļ���������д��ʽ
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // �ļ����ȣ��ֽ���
            long fileLength = randomFile.length();
            //��д�ļ�ָ���Ƶ��ļ�β��
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * B����׷���ļ���ʹ��FileWriter
     */
    public static void appendMethodB(String fileName, String content) {
        try {
            //��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
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
            System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
            	//��ȡ���ĵ�ַ����sip��ַ�ֵ��ļ�online.sip_Dict.java
            	//sip.sip_flow_ip_Dict(tempString,sip_dict_list);
                //������A׷���ļ�
            	tempString_split=tempString.trim().split("\\s+",3);
            	tempString_split[2]+="\r\n";
            	//tempString+="\r\n";
                dealip_three_to_one_read_write.appendMethodA(writeName, tempString_split[2]);
                // ��ʾ�к�
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
