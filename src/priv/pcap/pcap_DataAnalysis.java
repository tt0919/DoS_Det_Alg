/**
 * 作者：刘婷婷
 * 时间：2016/05/18
 * 功能：
 */
package priv.pcap;

/*
Pcap文件头24B各字段说明：
Magic：4B：0x1A 2B 3C 4D:用来标示文件的开始
Major：2B，0x02 00:当前文件主要的版本号     
Minor：2B，0x04 00当前文件次要的版本号
ThisZone：4B当地的标准时间；全零
SigFigs：4B时间戳的精度；全零
SnapLen：4B最大的存储长度    
LinkType：4B链路类型
常用类型：
0            BSD loopback devices, except for later OpenBSD
1            Ethernet, and Linux loopback devices
6            802.5 Token Ring
7            ARCnet
8            SLIP
9            PPP
*/
class pacp_file_header{
	int		magic;
	short	version_major;
	short	version_minor;
	int		thiszone;
	int		sigfigs;
	int		snaplen;
	int		linktype;
}
class timestamp{
	int  timestamp_s;
	int  timestamp_ms;
}
class pcap_header{
	timestamp	ts;
	int			capture_len;
	int			len;
}


class frameheader{
	char  dest_mac[];//6
	char  src_mac[];//6
	short frametype;
}


class ipheader{
	char   version_headlen;
	char   tos;
	short  total_len;
	short  id;
	short  flag;//:3
	short  frag_off;//:13
	char   ttl;
	char   protocol;
	short  checksum;
	char   src_ip[];//in_addr
	char   dest_ip[];//in_addr
}


class tcpheader{
	short src_port;
	short dest_port;
	int   seq_no;
	int   ack_no;
	short headlen_reserved_flag;
	short wnd_size;
	short checksum;
	short urgpointer;
}


class udpheader{
	short src_port;
	short dest_port;
	short headlen;
	short checksum;
}


class icmpheader{
	char   icmptype;
	char   code;
	short  checksum;
	short  identifier;
	short  seq;
}


class fivetuple
{
	char srcip[];//20
	char destip[];//20
	char protocol[];//8
	int srcport;
	int destport;
}


public class pcap_DataAnalysis {
	public void readFile(char []filename,char []outfile)
	{
		pacp_file_header pfh;
		pcap_header ph;
		ipheader    iphr;
		tcpheader   tcphr;
		udpheader   udphr;
		icmpheader  icmphr;
		fivetuple   tuple;
		long      count=0;
		short     datalen=0;
		char datastr[],protocol[],srcip[],destip[];	//256.8.20.20
		int  ipheadlen,srcport,destport;
		FILE *in,*out;
		if((in=fopen(filename,"rb"))==NULL)
		{
			printf("%s can not open!\n",filename);
			exit(-1);
		}
		remove(outfile);
		out=fopen(outfile,"w+");	
		fread(&pfh,sizeof(pcap_file_header),1,in);
		printf("%10s%16s%24s%12s%14s%12s\n","ID ","SrcIP ","DestIP ","Type ","SrcPort ","DestPort ");

		while(!feof(in)&&count<80)
		{		
			count++;
			fread(&ph,sizeof(pcap_header),1,in);
			memset(datastr,0,sizeof(datastr));
			fread(datastr,ph.capture_len ,1,in);
			memcpy(&iphr,datastr,20);
			ipheadlen=(iphr.version_headlen&0x0f)*4;
			inet_ntop(AF_INET,(void *)&iphr.src_ip,srcip,sizeof(srcip));
			inet_ntop(AF_INET,(void *)&iphr.dest_ip,destip,sizeof(destip));
			strcpy(tuple.srcip ,srcip);
			strcpy(tuple.destip ,destip);

			if(((iphr.version_headlen &0xf0)>>4)!=4)
			{
				printf("涓嶆槸IPv4鐗堟湰\n");
				continue;
			}
			
			switch(iphr.protocol)
			{       
				case 1: 
					strcpy(protocol,"ICMP");
					if(ipheadlen+8<=ph.capture_len)
					{
						memcpy(&icmphr,datastr+ipheadlen,8);
						srcport=destport=0;
						
					}
					break;
				case 2: 
					strcpy(protocol,"IGMP");
					srcport=destport=0;
					break;
				case 6:
					strcpy(protocol,"TCP");
					if(ipheadlen+20<=ph.capture_len)
					{
						memcpy(&tcphr,datastr+ipheadlen,20);
						srcport=tcphr.src_port;
					    destport=tcphr.dest_port;
					}
					break;
				case 8: 
					strcpy(protocol,"EGP");
					srcport=destport=0;
					break;
				case 9:
					strcpy(protocol,"IGP");
					srcport=destport=0;
					break;
				case 17: 
					strcpy(protocol,"UDP");
					if(ipheadlen+8<=ph.capture_len)
					{
						memcpy(&udphr,datastr+ipheadlen,8);
						srcport=udphr.src_port;
						destport=udphr.dest_port;
					}
					break;
				case 41:
					strcpy(protocol,"IPv6");
					srcport=destport=0;
					break;
				case 89: 
					strcpy(protocol,"OSPF");
					srcport=destport=0;
					break;
				default:
					strcpy(protocol,"default");
					srcport=destport=0;
			}
			printf("%8d%22s%22s%10s%12d%12d\n",count,srcip,destip,protocol,srcport,destport);
			strcpy(tuple.protocol,protocol); 
			tuple.srcport=srcport;
			tuple.destport=destport;
			fprintf(out,"%8d%22s%22s%10s%12d%12d\n",count,tuple.srcip,tuple.destip,tuple.protocol,tuple.srcport,tuple.destport);		
		}	
		fclose(in);
		fclose(out);
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
	}

}
