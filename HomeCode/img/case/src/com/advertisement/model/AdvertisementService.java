package com.advertisement.model;

import java.util.List;

public class AdvertisementService {

	AdvertisementDAO_interface dao;

	public AdvertisementService() {
		dao = new AdvertisementDAO();
	}

	public AdvertisementVO insertAd(String adname, String adcontent, byte[] adimg) {
		AdvertisementVO advertisementVO = new AdvertisementVO();
		advertisementVO.setAdname(adname);
		advertisementVO.setAdcontent(adcontent);
		advertisementVO.setAdimg(adimg);

		dao.insert(advertisementVO);
		return advertisementVO;
	}

	public void deleteAdByAdNo(String adNo) {
		dao.deleteByAdno(adNo);
	}

	public void deleteAdByAdName(String adName) {
		dao.deleteByAdName(adName);
	}

	public AdvertisementVO findAdByAdno(String adNo) {
		return dao.findAdByAdno(adNo);
	}

	public AdvertisementVO findAdByAdname(String adName) {
		return dao.findAdByAdname(adName);
	}

	public List<AdvertisementVO> getAllAd() {
		return dao.getAll();
	}

}
