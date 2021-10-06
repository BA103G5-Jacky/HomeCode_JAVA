package com.favorite_case.model;

import java.util.List;


public interface FavoriteCaseDAO_interface {

	
	public void insert(FavoriteCaseVO favoriteCaseVO);
//	public void update(FavoriteCaseVO favoriteCaseVO);
	public void delete(String csNo, String mbNo);
	public List<FavoriteCaseVO> getAll();
	public FavoriteCaseVO findByPrimaryKey(String csNo, String mbNo);
	public List<String> getOneMbFavoriteCase(String mbNo);
	
	public void deleteAll(String csNo);
	public List<String> getOneCaseFavoritMb(String csNo);
	
}
