package priv.online;
public class LRU {
	private int theArray[];
	private int back;			//�����β
	private int currentSize;	//�����д��Ԫ�ظ���
	private int maxSize=3;	//�������ܴ��Ԫ�صĸ���
	
	public LRU(){
		theArray=new int[maxSize];
	    back=0;
	    currentSize=0;
	}
	
    public void queue(int a[]){
    	for(int i=0;i<a.length;i++){
    		enQueue(a[i]);
    	}
    }
    //���
    public void enQueue(int x){
    	beUsed(x);			
    	//���ж��Ƿ��Ѵ��ڸ�ҳ�ţ������ڣ�ɾ��
        if(currentSize<maxSize){
        	theArray[back]=x;
            back++;	
            currentSize++;
        }else if(currentSize==maxSize){//����
        	for(int i=0;i<maxSize-1;i++){
        		theArray[i]=theArray[i+1];
        	}
        	theArray[maxSize-1]=x;
        }
        for(int i=0;i<currentSize;i++){
        	System.out.print(theArray[i]);
        }
        System.out.println();
    }
    //�ж��Ƿ��Ѵ��ڸ�ҳ��,�����ڣ�ɾ�����е�
    public void beUsed(int x){
    	for(int i=0;i<currentSize;i++){
    		if(theArray[i]==x){
    			for(int j=i;j<currentSize-1;j++){
    				theArray[j]=theArray[j+1];
    			}
    			currentSize--;
      	        back--;
    		}
    	}
    }
    
    public static void main(String[] args) {
    	LRU lru=new LRU();
        int a[]={4,7,0,7,1,0,1,2,1,2,6};
        lru.queue(a);
    }

}