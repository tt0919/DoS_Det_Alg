package priv.online;

import java.util.ArrayList;
import java.util.Iterator;

public class ip_ArrayList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  // 创建ArrayList
        //ArrayList list = new ArrayList();
        ArrayList<String> list=new ArrayList<String>();
        // 将“”
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        System.out.println("某个元素的位置"+list.indexOf("4"));
        // 将下面的元素添加到第1个位置
        list.add(0, "5");
        // 获取第1个元素
        System.out.println("the first element is: "+ list.get(0));
        // 删除“3”
        list.remove("3");
        // 获取ArrayList的大小
        System.out.println("Arraylist size=: "+ list.size());
        // 判断list中是否包含"3"
        System.out.println("ArrayList contains 3 is: "+ list.contains(3));
        // 设置第2个元素为10
        list.set(1, "10");
        // 通过Iterator遍历ArrayList
        for(Iterator iter = list.iterator(); iter.hasNext(); ) {
            System.out.println("next is: "+ iter.next());
        }
        // 将ArrayList转换为数组
        String[] arr = (String[])list.toArray(new String[0]);
        for (String str:arr)
            System.out.println("str: "+ str);
        // 清空ArrayList
        list.clear();
        // 判断ArrayList是否为空
        System.out.println("ArrayList is empty: "+ list.isEmpty());
       
    }


}
