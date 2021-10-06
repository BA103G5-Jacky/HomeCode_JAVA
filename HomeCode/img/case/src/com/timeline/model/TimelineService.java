package com.timeline.model;

import java.util.List;

import com.timeline.model.TimelineDAO;
import com.timeline.model.TimelineDAO_interface;
import com.timeline.model.TimelineDAO;

public class TimelineService {
	
	//建立介面物件 提供MilestoneDAO的多形創造
		//父介面來宣告的話，可以有比較大的彈性，不一定要指定的類別（多形的重要概念）
		private TimelineDAO_interface dao;
		
		//why建立建構子..因為在呼叫的時候就產生一個dao物件 也才能給jsp useBean使用
		//S:在呼叫工頭的時候，就可以把工人一併生出來
		//service 跟 dao 是生命共同體，所以要在預設建構子者邊就產出
		public TimelineService(){
			
			dao = new TimelineDAO();
		}
		
		public List<TimelineVO> getAll() {
			return dao.getAll();
		}
		
		public List<TimelineVO> findByOneCase(String csNo){															
			
			return dao.findByOneCase(csNo);
			
		}
		
		public void insert_withFile(TimelineVO timelineVO){															
			
			dao.insert_withFile(timelineVO);
			//log
			System.out.println("i have been here in service:function insert_withFile");
			
		}

		public void delete(String noteNo) {
			dao.delete(noteNo);
		}

		public void update(TimelineVO timelineVO) {
			dao.update(timelineVO);
			//log
			System.out.println("service:update");
		}
		
		

}
