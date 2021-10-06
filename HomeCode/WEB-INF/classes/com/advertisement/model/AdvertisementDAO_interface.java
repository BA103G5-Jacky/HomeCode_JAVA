package com.advertisement.model;

import java.util.List;

public interface AdvertisementDAO_interface {

	public void insert(AdvertisementVO adVO);

	public void deleteByAdno(String adNo);

	public void deleteByAdName(String adName);

	public AdvertisementVO findAdByAdno(String adNo);

	public AdvertisementVO findAdByAdname(String adName);

	public List<AdvertisementVO> getAll();

	public void updateByAdNo(AdvertisementVO adVO);

	public void updateByAdName(AdvertisementVO adVO);
	// public List<EmpVO> getAll(Map<String, String[]> map);

}
