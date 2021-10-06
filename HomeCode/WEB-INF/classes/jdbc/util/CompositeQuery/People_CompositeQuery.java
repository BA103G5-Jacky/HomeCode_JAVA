package jdbc.util.CompositeQuery;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class People_CompositeQuery {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("mbName".equals(columnName) || "searchInput".equals(columnName)) { // 用於其他
			aCondition = "(mbusrname" + " like '%" + value + "%' " + " or " + " mbintroduce " + " like '%" + value
					+ "%' " + " or " + " mbLstName " + " like '%" + value
					+ "%' "+ " or " + " mbFstName " + " like '%" + value
					+ "%') or mbLoc like '%"+ value+ "%' " ;
		} else if ("mbLoc".equals(columnName)) { // 用於varchar
			aCondition = columnName + " = '" + value + "'";
		} else if ("csSuccessTimes".equals(columnName)) {
			aCondition = columnName + " >= " + value;

		}
		System.out.println(aCondition);
		return aCondition + " ";
	}
	
	public static String get_Condition_From_SkillNo(String columnName, String value){
		String condition = null;
		
		condition = "mbNo in ( select mbNo from Member_Skill where " + columnName + " = '" + value +"')";
		
		
		return condition + "";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();

		StringBuffer whereCondition = new StringBuffer();
		StringBuffer skillCondition = new StringBuffer();

		int count = 0;
		int skillCount = 0;

		for (String key : keys) { // 把所有參數map的key都取出來
			String[] values = map.get(key); // 因為get(key)回傳陣列型態,所以每個陣列要拿他第[0]個,[0]是第一個
											// 也是唯一一個值!
			System.out.println("Keys = " + key);

			for (String value : values) {

				System.out.println("value = " + value);

				if (value != null && value.trim().length() != 0 && !"action".equals(key) && !"skillNo".equals(key)) { // 檢查是否為空字串or
					count++;
					String aCondition = get_aCondition_For_Oracle(key, value.trim()); // 丟到這支程式最上面那個method

					if (count == 1)
						whereCondition.append(" and " + aCondition);
					else
						whereCondition.append(" and " + aCondition);

					System.out.println("有送出查詢資料的欄位數count = " + count);
				}
			}
		}

		// 測試,進來這個領域的key有幾個
		System.out.println("從人才搜尋前端抓進來的參數(以map接),map的size: " + map.size());
		System.out.println("count = " + count);
		System.out.println("有哪些key進來了 : " + map.keySet());

		// 如果有勾選skill的話 才會進入這個區塊!
		
		if(map.containsKey("skillNo") && count == 0){
			
			for(String skillNo: map.get("skillNo")){
				skillCount++;
				String stCondition = get_Condition_From_SkillNo("skillNo",skillNo);
				
				if(skillCount == 1) whereCondition.append(" and " + stCondition);
				
				else whereCondition.append(" and " + stCondition);
			}
			
		} else if(map.containsKey("skillNo") && count != 0) {
			for(String skillNo: map.get("skillNo")){
				skillCount++;
				String stCondition = get_Condition_From_SkillNo("skillNo",skillNo);
				
				whereCondition.append(" and " + stCondition);
			}
		}
		
		
		System.out.println(whereCondition);
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳
		// java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		// map.put("cspayment", new String[] { "100" });
		// map.put("csname", new String[] { "哈" });
		// map.put("csdesc", new String[] { "這是案件描述" });
		// map.put("csloc", new String[] { "台北" });
		// map.put("hirerno", new String[] { "MB00000015" });
		// map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key
		map.put("mbno", new String[] { "hghg", "hghg5" });
		// map.put("lowpayment", new String[] { "0" });
		map.put("mb_username_introduce", new String[] { "哈" });
		map.put("mbloc", new String[] { "hghg" });
		String finalSQL = "select * from member " + People_CompositeQuery.get_WhereCondition(map) + " order by mbno";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
