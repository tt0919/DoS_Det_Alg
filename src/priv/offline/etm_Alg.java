package priv.offline;
import java.lang.Math;

import com.sun.java_cup.internal.internal_error;

public class etm_Alg {

	/**
	 * @param args
	 */
	//t=1
	public static void one_persist_spread(String []Matrix){
		int num_zero_everyline=0;
		for (int i = 0; i < Matrix.length; i++) {
			int m=Matrix[i].length();
			num_zero_everyline=0;
			for (int j = 0; j < Matrix[i].length(); j++) {
				if(Matrix[i].charAt(j)=='0')
				{
					num_zero_everyline++;
					
				}
			}
			//System.out.println("第"+i+"行的0的个数"+num_zero_everyline);
			double p=1.0*num_zero_everyline/Matrix[i].length();//初始值Z*(0的比例)
			//System.out.println(Matrix[i].length());
			//System.out.println(p);
			//System.out.println(Math.log(p)/Math.log((double)2.718286));
			System.out.print(Math.round(-m*Math.log(p)/Math.log((double)2.718286)));
			System.out.print("  ");
		}
		System.out.print("  ");
		
	}
	//t=2
	public static void two_persist_spread(String []Matrix){
		int num_zero_everyline1=0;
		int num_zero_everyline2=0;
		int persist_num_zero_everyline=0;
		double pp;
		int num=0;
		for (int i = 0; i < Matrix.length-1; i++) {
			int m=Matrix[i].length();
			num_zero_everyline1=0;
			num_zero_everyline2=0;
			for (int j = 0; j < Matrix[i].length(); j++) {
				if(Matrix[i].charAt(j)=='0')
				{
					num_zero_everyline1++;
					
				}
			}
			for (int j = 0; j < Matrix[i+1].length(); j++) {
				if(Matrix[i+1].charAt(j)=='0')
				{
					num_zero_everyline2++;
					
				}
			}
			num=0;
			for (int j = 0; j < Matrix[i].length(); j++) {
				
				if((Matrix[i].charAt(j)=='0')&&(Matrix[i+1].charAt(j)=='0'))
				{
					num++;
				}
			}
			//System.out.println("num:"+num);
			//System.out.println("第"+i+"行的0的个数"+num_zero_everyline);
			double p1=1.0*num_zero_everyline1/Matrix[i].length();//Zi(0的比例)
			double p2=1.0*num_zero_everyline2/Matrix[i+1].length();//Zi(0的比例)
			//System.out.println("p1:"+p1+"p2:"+p2);
			pp=num*1.0/Matrix[i].length();
			//System.out.println("pp:"+pp);
			double p=(p1*p2)/(p1+p2-pp);
			System.out.print(Math.round(-m*Math.log(p)/Math.log((double)2.718286)));
			//System.out.println(m*(Math.log(p1+p2-pp)/Math.log((double)2.718286)-Math.log(p1)/Math.log((double)2.718286)-Math.log(p2)/Math.log((double)2.718286)));
			System.out.print("  ");
		}
	}
	
	//t=3
	public static void three_persist_spread(String []Matrix){
		int num_zero_everyline1=0;
		int num_zero_everyline2=0;
		int num_zero_everyline3=0;
		int persist_num_zero_everyline=0;
		double pp;
		int num=0;
		for (int i = 0; i < Matrix.length-2; i++) {
			int m=Matrix[i].length();
			num_zero_everyline1=0;
			num_zero_everyline2=0;
			for (int j = 0; j < Matrix[i].length(); j++) {
				if(Matrix[i].charAt(j)=='0')
				{
					num_zero_everyline1++;
					
				}
			}
			for (int j = 0; j < Matrix[i+1].length(); j++) {
				if(Matrix[i+1].charAt(j)=='0')
				{
					num_zero_everyline2++;
					
				}
			}
			for (int j = 0; j < Matrix[i+2].length(); j++) {
				if(Matrix[i+1].charAt(j)=='0')
				{
					num_zero_everyline3++;
					
				}
			}
			num=0;
			for (int j = 0; j < Matrix[i].length(); j++) {
				
				if((Matrix[i].charAt(j)=='0')&&(Matrix[i+1].charAt(j)=='0')&&(Matrix[i+2].charAt(j)=='0'))
				{
					num++;
				}
			}
			//System.out.println("num"+num);
			//System.out.println("num:"+num);
			//System.out.println("第"+i+"行的0的个数"+num_zero_everyline);
			double p1=1.0*num_zero_everyline1/Matrix[i].length();//Zi(0的比例)
			double p2=1.0*num_zero_everyline2/Matrix[i+1].length();//Zi(0的比例)
			double p3=1.0*num_zero_everyline2/Matrix[i+2].length();//Zi(0的比例)
			//System.out.println("p1:"+p1+"p2:"+p2);
			pp=num*1.0/Matrix[i].length();
			double A,B,C;
			A=p1*p2*p3;
			B=p1*p2+p1*p3+p2*p3;
			C=p1+p2+p3-pp;
			double p=((B-Math.sqrt(B*B-4*A*C))/(2*A));
			System.out.print(Math.round(m*Math.log(p)/Math.log((double)2.718286)));
			//System.out.println(m*(Math.log(p1+p2-pp)/Math.log((double)2.718286)-Math.log(p1)/Math.log((double)2.718286)-Math.log(p2)/Math.log((double)2.718286)));
			System.out.print("  ");
		}
	}
	//多周期
	public static void stm_persist_spread(String []Matrix){
		//the proportion is idea
		//the number of bits of the intersection bitmap that are zeros
		int ct_inter=0;//持续为0的个数
		//the proportion is idea
		//the number of bits of the bitmap that are zeros in every period
		int []count=new int [Matrix.length];//每一个周期为0的个数
		for (int i = 0; i < Matrix.length; i++) {
			for (int j = 0; j < Matrix[i].length(); j++) {
				//System.out.print(Matrix[i].charAt(j));
				count[i]=(Matrix[i].charAt(j)=='0')?count[i]+1:count[i];
				//if(Matrix[i].charAt(j)=='0')
				//	count[i]++;
			}
			//System.out.println();
		}
		for (int i : count) {
			//判断每一行
			//System.out.println("count="+i);			
		}
		
		//for (int i = 0; i < Matrix.length; i++) {
			for (int j = 0; j < Matrix[0].length(); j++) {
				int num=0;
				for (int i = 0; i < Matrix.length; i++) {
					if(Matrix[i].charAt(j)=='0')
					{
						num++;
						//System.out.println("num="+num);
					}
				}
				if(num==Matrix.length)
				{
					ct_inter++;
					//System.out.println("ct_inter-temp="+ct_inter+"列数："+j);
				}
			}
		//}
			//零
			System.out.println("ct_inter="+ct_inter);
		int n=0;//估计的结果
		int m=Matrix[0].length();
		double p=1.0*ct_inter/Matrix[0].length();//初始值Z*
		System.out.println("ct_inter:"+ct_inter+"    Matrix[0].length():"+Matrix[0].length());
		System.out.println("p:"+p);
		int t=Matrix.length;//每个窗口中的周期数
		double f1=0,f2=0;
		double liancheng=1;
		double lianjia=0;
		for (int i = 0; i < t; i++) {
			System.out.println("liancheng:"+liancheng+"   p:"+p+"   count[i]:"+count[i]);
			liancheng*=p-count[i];
		}
		
		for (int i = 0; i < t; i++) {
			System.out.println("lianjia:"+lianjia+"   p:"+p+"   count[i]:"+count[i]);
			lianjia+=1/(p-count[i]);
		}
		System.out.println("lianjia:"+lianjia);
		
		do {
			System.out.println("f1:"+f1+"	f2:"+f2+"	p:"+p+"	f1/f2:"+f1/f2);
			f1=Math.pow(p,t)-Math.pow(p,t-1)*ct_inter/Matrix.length-liancheng;
			f2=t*Math.pow(p,t-1)-(t-1)*Math.pow(p,t-2)*ct_inter/Matrix.length-liancheng*lianjia;
			p-=f1/f2;
			
		} while(Math.abs(f1/f2)>=0.00001);
		System.out.println("P:"+p);
		//多周期结果输出
		System.out.print(-m*Math.log((double)p)/Math.log((double)2.718286));
		System.out.print("  ");
			
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//20*10
		String []Matrix={"000000000000000000000011101001000010101000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",//8
				 		 "000000000000000000000011101001001011101000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",//10	8
				 		 "000000000000000000000010100001000010101100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",//7		6	6
				 		 "000000000000000000000011111011011110111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",//14	6	6
				 		 "000000000000000000000011101101000111101000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",//11	9	6
				 		 "000000000000000000000010101001011110001010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",//10	7	7
				 		 "000000000000000000000010101011010110101000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",//10	8	7
				 		 "000000000000000000000011111001000111101000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",//11	8	7
				 		 "000000000000000000000011101001000010101000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",//8		8	7
				 		 "000000000000000000000011101001010010101000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",};//9	8	8	5
		//System.out.println(Matrix[0]);
		//if(Matrix.length==1){
			one_persist_spread(Matrix);
			System.out.println("");
		//}
		//System.out.println("");
		//else if(Matrix.length==2){
			two_persist_spread(Matrix);
			System.out.println("");
		//}
			
		//else if(Matrix.length==3){
			three_persist_spread(Matrix);
			System.out.println("");
		//}
		//else if(Matrix.length>=4){
			etm_Alg.stm_persist_spread(Matrix);
			System.out.println("");
		//}
		
		//System.out.println(Matrix[0].length());
	}

}
