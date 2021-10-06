package jdbc.util.CompositeQuery;
/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位

 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Case_CompositeQuery {
	
	
	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("lowpayment".equals(columnName)) { // 用於其他
			
			aCondition = "cspayment" + ">=" + value;
		}else if("highpayment".equals(columnName)){
			aCondition = "cspayment" + "<=" + value;
		}
		else if ("csloc".equals(columnName) || "csname".equals(columnName) || "csdesc".equals(columnName)
				|| "hirerno".equals(columnName) ) // 用於varchar
			
			aCondition = columnName + " like '%" + value + "%'";
		
//		else if ("hiredate".equals(columnName)) 
		// 用於Oracle的date
//			
//			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		
		for (String key : keys) {          //把所有參數map的key都取出來
			String value = map.get(key)[0]; //因為get(key)回傳陣列型態,所以每個陣列要拿他第[0]個,[0]是第一個 也是唯一一個值!
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) { //檢查是否為空字串or null
				count++;
				
				
				
				String aCondition = get_aCondition_For_Oracle(key, value.trim()); //丟到這支程式最上面那個method

				if (count == 1)
					whereCondition.append(" and " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new HashMap<String, String[]>();
		//map.put("cspayment", new String[] { "100" });
	//map.put("csname", new String[] { "哈" });
		//map.put("csdesc", new String[] { "這是案件描述" });
		//map.put("csloc", new String[] { "台北" });
//		map.put("hirerno", new String[] { "MB00000015" });
//		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key
		
		
		map.put("w", new String[] { "0","0" });
		map.put("w", new String[] { "1" });
		map.put("w", new String[] { "2" });
		String finalSQL = "select * from case "
				          + Case_CompositeQuery.get_WhereCondition(map)
				          + " order by csloc";
		System.out.println("●●finalSQL = " + finalSQL);
System.out.println(map);
	}
}
