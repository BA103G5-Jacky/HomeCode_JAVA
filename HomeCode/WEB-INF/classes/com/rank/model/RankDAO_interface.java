package com.rank.model;

import java.util.*;



public interface RankDAO_interface {

	 public void insert(RankVO rankVO);
     public void update(RankVO rankVO);
     public void delete(String mbno);
     public RankVO findRankByRankNo(String rankno);
     public RankVO findRankByMbNo(String mbno);
     public List<RankVO> findtopN(Integer num);
     public List<RankVO> getAll();
     //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//   public List<EmpVO> getAll(Map<String, String[]> map);
}
