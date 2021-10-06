package com.favorite_case.model;

import java.util.List;

public class FavoriteCaseService {
	private FavoriteCaseDAO_interface dao;

	public FavoriteCaseService() {
		dao = new FavoriteCaseDAO();
	}
	
	public FavoriteCaseVO insert(String mbNo, String csNo) {
		FavoriteCaseVO favoriteCaseVO = new FavoriteCaseVO();
		favoriteCaseVO.setMbNo(mbNo);
		favoriteCaseVO.setCsNo(csNo);
		
		dao.insert(favoriteCaseVO);
		return favoriteCaseVO;
	}
	
	public void delete(String csNo, String mbNo) {
		dao.delete(csNo, mbNo);
	}
	
	public List<FavoriteCaseVO> getAll() {
		return dao.getAll();
	}
	
	public FavoriteCaseVO findByPrimaryKey(String csNo, String mbNo) {
		return dao.findByPrimaryKey(csNo, mbNo);
	}
	
	public List<String> getOneMbFavoriteCase(String mbNo) {
		return dao.getOneMbFavoriteCase(mbNo);
	}
	
	public void deleteAll(String csNo){
		dao.deleteAll(csNo);
	}
	
	public List<String> getOneCaseFavoritMb(String csNo) {
		return dao.getOneCaseFavoritMb(csNo);
	}
}
