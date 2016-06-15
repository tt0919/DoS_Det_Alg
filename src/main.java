

import java.io.IOException;

import priv.online.BloomFilter;



/**
 * Created by ltt on 18/05/2016.
 */
public class main
{
    public static void main(String[] args) throws IOException
    {
    	//bf过滤查找加上哈希
        BloomFilter bf = new BloomFilter(500, 0.01);
        bf.add("192.5.6.8");
        bf.add("165.32.3.6");
        bf.add("16.53.23.6");
        bf.add("5.63.5.6");

        System.out.println(bf.contains("19.5.6.8"));
        System.out.println(bf.contains("2.3.6.5"));
        System.out.println(bf.contains("16.53.23.6"));
        System.out.println(bf.contains("5.63.5.7"));
    }
}
