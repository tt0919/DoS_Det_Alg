import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import priv.online.dealip_three_to_one_read_write;
import priv.online.dealip_three_to_one_write;;

class dealip_three_to_one_write {

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

}

public class main_read_write {
	
	public void get_write_everyline(String line) {
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dealip_three_to_one_read_write prb =new dealip_three_to_one_read_write(); 
		String readName = "C:/Users/Administrator/Desktop/yuan/weiqu/dirB.20130117-125904.txt";  
		prb.readFileByLines_dealip(readName); 
		
		String writeName = "C:/Users/Administrator/Desktop/yuan/newTemp.txt";
        String content = "new append!";
        content+="\r\n";
        //������A׷���ļ�
        dealip_three_to_one_write.appendMethodA(writeName, content);
	}

}
