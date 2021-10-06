package com.rank.model;

import java.util.List;



public class RankService {

	
	private RankDAO_interface dao;

	public RankService() {
		dao = new RankDAO();
	}
			//新增排行榜
	public RankVO addRank(String rankno, String mbno ) {

		RankVO rankVO = new RankVO();
		
		rankVO.setRankno(rankno);
		rankVO.setMbno(mbno);
		
		dao.insert(rankVO);

		return rankVO;
	}


			//利用mbno刪除排行榜
	public void deleteRank(String mbno) {
		dao.delete(mbno);
	}


			//搜尋某個人的排名!
	public RankVO getOneRankByMbNo(String mbno) {
		return dao.findRankByMbNo(mbno);
	}
	
			//搜尋第幾名4誰!
	public RankVO getOneRankNo(String rankno) {
		return dao.findRankByRankNo(rankno);
	}
	
			//搜尋排行榜ㄉ前n名!
	public List<RankVO> findtopN(int num){
		
		return dao.findtopN(num);
	}
			
			//調出所有ㄉ榜上人資料!
	public List<RankVO> getAll() {
		return dao.getAll();
	}
}
