package com.member.model;

import java.util.List;
import java.util.Map;

import com.cs.model.CaseVO;

public interface MemberDAO_interface {
	public void insert(MemberVO memberVO);
	public void setSecQuestion(MemberVO memberVO);
    public void update(MemberVO memberVO);
    public MemberVO findByEMail(String eMail);
    public MemberVO findByPrimaryKey(String mbNo);
    public List<MemberVO> findBySuccessTimes(Integer csSuccessTimes);
    public List<MemberVO> findByLoc(String mbLoc);
    public List<MemberVO> findBySuspenionStatus(String suspensionStatus);
    public List<MemberVO> getAll();
    public MemberVO loginCheck(String eMail); //for Android
//    public MemberVO loginCheck(String eMail , String passWord);
    public List<String> emailCheck();
    
    //update 2017-09-27 from Tim
    public byte[] getPoritait(String eMail);
    public void updateMbPw(String mbPw,String eMail);
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<MemberVO> getAll(Map<String, String[]> map);
    //update 0927 Maya for Json
    public MemberVO findByPKNoImg(String mbNo);
    public MemberVO findByUsrName(String mbUserName);
    //0930
    public void updateCsSuccessTimes(MemberVO memberVO);
    public void updateCsTimes(MemberVO memberVO);
    
    public List<String> getAllMbUserName();
    
}
