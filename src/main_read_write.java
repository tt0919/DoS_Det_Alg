import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import priv.online.dealip_three_to_one_read_write;
import priv.online.dealip_three_to_one_write;;

class dealip_three_to_one_write {

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
        //按方法A追加文件
        dealip_three_to_one_write.appendMethodA(writeName, content);
	}

}
