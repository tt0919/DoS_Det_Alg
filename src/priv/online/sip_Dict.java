package priv.online;

import java.util.ArrayList;

public class sip_Dict {
	int  line=0;//控制行数
	ArrayList<String> sip_dict_list=new ArrayList<String>();
	//BloomFilter bf = new BloomFilter(500, 0.01);
	public void sip_flow_ip_Dict(String sip,ArrayList<String> sip_dict_list) {
		
        if(sip_dict_list.indexOf(sip)==-1)
        {
        	sip_dict_list.add(sip);
        	System.out.println("line " + line + ": " + sip);
            line++;
        }
		
		
		/*bf.add(sip);
		System.out.println("line " + line + ": " +bf.contains(sip));
		line++;*/
		
		//7000-1000的数量级权当抽样的方法
        /*if(bf.contains(sip)==false)
        {	
        	bf.add(sip);
        	System.out.println("line " + line + ": " + sip);
        	line++;
        }*/
       
	}
	
	
		//sip_Dict aaa=new sip_Dict();
		//System.out.println("the last element is: "+ aaa.sip_dict_list.get(0));
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//C:/Users/Administrator/Desktop/yuan/1.txt
		//String sip="2.2.2";
		//sip_Dict a=new sip_Dict();
		//a.sip_flow_ip_Dict(sip);
	}

}
