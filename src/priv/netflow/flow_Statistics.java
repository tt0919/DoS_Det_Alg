/**
 * 作者：刘婷婷
 * 时间：2016/05/13
 * 功能：统一读取之后，统计出源地址、目的地址和和协议。（模拟边读边存）
 */
package priv.netflow;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;


public class flow_Statistics extends JFrame implements ActionListener  {
	
	 private JLabel fileaddrL, headerL;
	 private JRadioButton proA;
	 private JRadioButton SouA;
	 private JRadioButton DesA;
	 private JButton open;
	 private JTextField fileF;
	 private ButtonGroup bg;
	 private Vector No = new Vector();
	 private Vector Time = new Vector();
	 private Vector Source = new Vector();
	 private Vector Destination = new Vector();
	 private Vector Protocol = new Vector();
	 private Vector Info = new Vector();
	 private String[] Protocolp = new String[200];
	 private int[] ProtocolD = new int[200];
	 private String[] Sourcep = new String[20000];
	 private int[] SourceD = new int[20000];
	 private String[] Destinationp = new String[20000];
	 private int[] DestinationD = new int[20000];
	 private int radioResult=1;
	 public flow_Statistics() {
	  super("简单网络协议分析工具");
	  this.setLayout(null);
	  this.setSize(500, 553);
	  headerL = new JLabel("简单网络协议分析工具");
	  fileaddrL = new JLabel("文件路径 : ");
	  open = new JButton();
	  open.setText("打开");
	  bg = new ButtonGroup();
	  fileF = new JTextField(20);
	  proA = new JRadioButton("协议分析", true);
	  SouA = new JRadioButton("源地址分析", false);
	  DesA = new JRadioButton("目的地址分析", false);
	  headerL.setBounds(195, 25, 150, 20);
	  fileaddrL.setBounds(100, 55, 100, 20);
	  fileF.setBounds(170, 55, 150, 20);
	  open.setBounds(325, 55, 60, 20);
	  proA.setBounds(80, 80, 100, 20);
	  SouA.setBounds(185, 80,90, 20);
	  DesA.setBounds(290, 80,120, 20);
	  add(proA); add(SouA); add(DesA);
	  add(headerL);
	  add(fileaddrL);
	  add(fileF);
	  add(open);
	  open.addActionListener(this);
	  proA.addActionListener(this);
	  SouA.addActionListener(this);
	  DesA.addActionListener(this);
	  bg.add(proA);
	  bg.add(SouA);
	  bg.add(DesA);
	  for (int i = 0; i < 20000; i++) {
	   Sourcep[i]="";
	   Destinationp[i]="";
	   SourceD[i]=0;
	   DestinationD[i]=0;
	  }
	  for (int i = 0; i <200; i++) {
	   Protocolp[i]="";
	   ProtocolD[i]=0;
	  }
	  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	  this.setVisible(true); 
	 }

	 public void actionPerformed(ActionEvent e) {
	  if (e.getActionCommand().equals("打开")) {
	   JFileChooser jFileChooser = new JFileChooser();
	   jFileChooser.setDialogTitle("打开文件");
	   int result = jFileChooser.showOpenDialog(this);
	   if (result == JFileChooser.APPROVE_OPTION) {
	    //File select = jFileChooser.getSelectedFile();
	    try {
	     fileF.setText(jFileChooser.getSelectedFile().getPath());
	     BufferedReader keyboard = new BufferedReader(
	       new FileReader(jFileChooser
	         .getSelectedFile().getPath()));
	     StringTokenizer to;
	     String temp = keyboard.readLine();
	     int i = 1;
	     Protocol.clear();
	     Source.clear();
	     Destination.clear();
	     while (temp!= null) {
	    	 if(temp.length()>7&&temp.substring(0,7).trim().equals(Integer.toString(i)))//?
	    	 {
	    			String aaa="",sss="";
	    			to=new StringTokenizer(temp);
	    			No.addElement(to.nextToken());
	    			Time.addElement(to.nextToken());
	    			Source.addElement(to.nextToken());
	    			Destination.addElement(to.nextToken());
	    			Protocol.addElement(to.nextToken());
	    			while(to.hasMoreTokens())
	    			{
	    				aaa=to.nextToken()+" ";
	    			}
	    			Info.addElement(aaa);
	    			i++;
	    	 }
	      temp = keyboard.readLine();
	     }
	     Vector temp1 = new Vector();
	     Vector temp2 = new Vector();
	     temp1.addAll(Protocol);
	     int j = 0;
	     while (!temp1.isEmpty()) {
	      Protocolp[j] = temp1.firstElement().toString();
	      int n = temp1.size();
	      temp2.clear();
	      temp2.add(temp1.firstElement());
	      temp1.removeAll(temp2);
	      ProtocolD[j] = n - temp1.size();
	      j++;
	     }
	     for (int ii = 0; ii<5; ii++) {
	      for (int jj = 19; jj > ii; jj--)
	       if (ProtocolD[jj]> ProtocolD[jj-1]) {
	        int  tmp = ProtocolD[jj];
	        String tmp1=new String();
	         tmp1=Protocolp[jj];
	        ProtocolD[jj] = ProtocolD[jj-1];
	        Protocolp[jj] = Protocolp[jj-1];
	        ProtocolD[jj-1] = tmp;
	        Protocolp[jj-1] = tmp1;
	       }
	     }
	     temp1.clear();
	     temp2.clear();
	     temp1.addAll(Source);
	        j = 0;
	     while (!temp1.isEmpty()) {
	      Sourcep[j] = temp1.firstElement().toString();
	      int n = temp1.size();
	      temp2.clear();
	      temp2.add(temp1.firstElement());
	      temp1.removeAll(temp2);
	      SourceD[j] = n - temp1.size();
	      j++;
	     }
	     for (int ii = 0; ii<5; ii++) {
	         for (int jj = 199; jj > ii; jj--)
	          if (SourceD[jj]> SourceD[jj-1]) {
	           int  tmp = SourceD[jj];
	           String tmp1=new String();
	            tmp1= Sourcep[jj];
	            SourceD[jj] = SourceD[jj-1];
	           Sourcep[jj] = Sourcep[jj-1];
	           SourceD[jj-1] = tmp;
	           Sourcep[jj-1] = tmp1;
	          }
	        }
	     temp1.clear();
	     temp2.clear();
	     temp1.addAll(Destination);
	        j = 0;
	     while (!temp1.isEmpty()) {
	      Destinationp[j] = temp1.firstElement().toString();
	      int n = temp1.size();
	      temp2.clear();
	      temp2.add(temp1.firstElement());
	      temp1.removeAll(temp2);
	      DestinationD[j] = n - temp1.size();
	      j++;
	     }
	     for (int ii = 0; ii<5; ii++) {
	         for (int jj = 199; jj > ii; jj--)
	          if (DestinationD[jj]> DestinationD[jj-1]) {
	           int  tmp = DestinationD[jj];
	           String tmp1=new String();
	            tmp1= Destinationp[jj];
	            DestinationD[jj] = DestinationD[jj-1];
	            Destinationp[jj] = Destinationp[jj-1];
	            DestinationD[jj-1] = tmp;
	            Destinationp[jj-1] = tmp1;
	          }
	        }
	    } catch (IOException qe) {

	    }
	   }
	  
	  }
	  else if (e.getActionCommand().equals("协议分析"))
	     {
	      radioResult=1;
	   this.paint(this.getGraphics());
	     }
	     else if (e.getActionCommand().equals("源地址分析"))
	     {
	      radioResult=2;
	   this.paint(this.getGraphics());
	   
	     }
	     else if(e.getActionCommand().equals("目的地址分析"))
	     {
	      radioResult=3;
	   this.paint(this.getGraphics());
	     }
	 }
	 public void paint(Graphics g){
	  super.paintComponents(g);
	  if (Protocol.size()!= 0 && radioResult==1) {
	    int i1=0,x0 = 80, y0 = 350;
	    for(int i=0;i<200;i++)
	    {
	    	if(!Protocolp[i].equals(""))
	    	{
	    	i1++;	
	    	}
	    }
	    g.drawLine(x0, y0, x0+300, y0);
	    g.drawLine(x0, y0, x0, 250);
	    g.drawLine(x0, y0 - 20, x0 - 5, y0 - 20);
	    g.drawLine(x0, y0 - 40, x0 - 5, y0 - 40);
	    g.drawLine(x0, y0 - 60, x0 - 5, y0 - 60);
	    g.drawLine(x0, y0 - 80, x0 - 5, y0 - 80);
	    g.drawLine(x0, y0 - 100, x0 - 5, y0 -100);
	    g.drawString("1. 抓获的数据中共有"+i1+"种协议，总共有 "+Protocol.size()+"个数据包。各协议所占比例如下图所示：", 30, 200);
	    int t = 85;
	    for (int i = 0; i < 5; i++) {
	     if (!Protocolp[i].equals("")) {
	      g.setFont(new Font("Helvetica",Font.ITALIC,10));
	      g.drawString(Protocolp[i], t, 365);
	      String a = "";
	      g.setFont(new Font("宋体",Font.ITALIC,12));
	      g.drawString(a + ProtocolD[i], t+10, 375);
	      t = t + 60;
	     }
	    }
	    for (int i = 0; i < 5; i++) {
	     if (ProtocolD[i]>0) {
	      g.setColor(Color.blue);
	      double a=((double)ProtocolD[i])/Protocol.size()*100+0.5;
	      if(a<1)
	      {
	       a=1.0;
	      }
	      g.fillRect(85 + i * 60, 350 - (int)a,30,(int)a);

	     }

	    }
	  }
	  else if(Source.size()!=0 && radioResult==2)
	  {
	   int m=0;
	   int i1=0,x0 = 250, y0 = 300,r=100,start=0,nt=0;
	   double num[],sum=0.0;
	   num=new double[5];
	   for(int i=0;i<5;i++)
	   {
	   	num[i]=0.0;
	   }
	   for(int i=0;i<20000;i++)
	   {
	   	if(!Sourcep[i].equals(""))
	   	{
	   	i1++;	
	   	}
	   }
	   g.drawString("1. 抓获的数据中有"+i1+"个源地址，总共有 "+Source.size()+"个数据包。流量前五的各源地址", 30, 180);
	   g.drawString("所占比例如下图所示：", 30, 200);
	   for (int i = 0; i < 5; i++) {
	       if (SourceD[i]>0) {
	       	nt++;
	        if(m==0){
	         g.setColor(Color.red); 
	         m=1;
	        }else if(m==1){
	         g.setColor(Color.blue);
	         m=2;
	        }
	        else if(m==2){
	         g.setColor(Color.black);
	         m=3;
	        }
	        else if(m==3){
	         g.setColor(Color.green);
	         m=4;
	        }
	        else if(m==4){
		          g.setColor(Color.orange);
		          m=5;
		      }
	        double a=((double)SourceD[i])/Source.size()*360;
	        if(i<4)
	        sum+=(a/360*100);
	        num[i]= (a/360*100);
	        if(m<=4)
	        {
	       	 g.fillArc(x0-r,y0-r,2*r ,2*r,start,(int)a);
		         start+=a;
	        }
	        else
	        {
	       	 g.fillArc(x0-r,y0-r,2*r ,2*r,start,360-start);
	        }
	        
	       
	       }
	      }
	  }
	  else if(Destination.size()!=0 && radioResult==3)
	  {
		  int m=0;
		   int i1=0,x0 = 250, y0 = 300,r=100,start=0,nt=0;
		   double num[],sum=0.0;
		   num=new double[5];
		   for(int i=0;i<5;i++)
		   {
		   	num[i]=0.0;
		   }
		   for(int i=0;i<20000;i++)
		   {
		   	if(!Sourcep[i].equals(""))
		   	{
		   	i1++;	
		   	}
		   }
		   g.drawString("1. 抓获的数据中有"+i1+"个 目的地址，总共有 "+Destination.size()+"个数据包。流量前五的各 目的地址", 30, 180);
		   g.drawString("所占比例如下图所示：", 30, 200);
		   for (int i = 0; i < 5; i++) {
		       if (DestinationD[i]>0) {
		       	nt++;
		        if(m==0){
		         g.setColor(Color.red); 
		         m=1;
		        }else if(m==1){
		         g.setColor(Color.blue);
		         m=2;
		        }
		        else if(m==2){
		         g.setColor(Color.black);
		         m=3;
		        }
		        else if(m==3){
		         g.setColor(Color.green);
		         m=4;
		        }
		        else if(m==4){
			          g.setColor(Color.orange);
			          m=5;
			      }
		        double a=((double)DestinationD[i])/Destination.size()*360;
		        if(i<4)
		        sum+=(a/360*100);
		        num[i]= (a/360*100);
		        if(m<=4)
		        {
		       	 g.fillArc(x0-r,y0-r,2*r ,2*r,start,(int)a);
			         start+=a;
		        }
		        else
		        {
		       	 g.fillArc(x0-r,y0-r,2*r ,2*r,start,360-start);
		        }
		        
		       
		       }
		      }
	  }

	 }
	    



/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		flow_Statistics a=new flow_Statistics();
	}
*/
}
