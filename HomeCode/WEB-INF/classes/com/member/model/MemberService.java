package com.member.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class MemberService {

	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO addMember(String mblstname, String mbfstname, String comname, String eMail, String mbPw,
			String mbLoc, String mbAddress, String phone, String mbUserName, byte[] portrait, String mbIntroduce) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMbLstName(mblstname);
		memberVO.setMbFstName(mbfstname);
		memberVO.setComName(comname);
		memberVO.seteMail(eMail);
		memberVO.setMbPw(mbPw);
		memberVO.setMbLoc(mbLoc);
		memberVO.setMbAddress(mbAddress);
		memberVO.setPhone(phone);
		memberVO.setMbUserName(mbUserName);
		memberVO.setPortrait(portrait);
		memberVO.setMbIntroduce(mbIntroduce);

		dao.insert(memberVO);

		return memberVO;
	}

	public MemberVO updateMember(String mbLstName, String mbFstName, String comName, String mbLoc, String mbAddress, String phone,
			String mbUsrName, byte[] poritait, String mbIntroduce, String mbNo) {

		MemberVO memberVO = new MemberVO();
		memberVO.setMbLstName(mbLstName);
		memberVO.setMbFstName(mbFstName);
		memberVO.setComName(comName);
		memberVO.setMbLoc(mbLoc);
		memberVO.setMbAddress(mbAddress);
		memberVO.setPhone(phone);
		memberVO.setMbUserName(mbUsrName);
		memberVO.setPortrait(poritait);
		memberVO.setMbIntroduce(mbIntroduce);
		memberVO.setMbNo(mbNo);
		dao.update(memberVO);

		return memberVO;
	}
	
	public MemberVO setSecQuestion(String secQuestion, String secAnswer, String mbNo) {

		MemberVO memberVO = new MemberVO();

		memberVO.setSecQuestion(secQuestion);
		memberVO.setSecAnswer(secAnswer);
		memberVO.setMbNo(mbNo);
		
		dao.setSecQuestion(memberVO);
		return memberVO;
	}


	public List<MemberVO> getAll() {

		return dao.getAll();
	}

	public List<MemberVO> findBySuccessTimes(Integer csSuccessTimes) {
		return dao.findBySuccessTimes(csSuccessTimes);

	}

	public List<MemberVO> findByLoc(String mbLoc) {
		return dao.findByLoc(mbLoc);
	}

	public List<MemberVO> findBySuspenionStatus(String suspensionStatus) {
		return dao.findBySuspenionStatus(suspensionStatus);
	}

//	public MemberVO loginCheck(String eMail, String passWord) {
//		return dao.loginCheck(eMail, passWord);
//	}
	
	public MemberVO loginCheck(String eMail ) {
		 return dao.loginCheck(eMail);
	 }

	public List<String> emailCheck() {
		return dao.emailCheck();

	}
	
	public MemberVO getOneMember(String mbNo) {
		return dao.findByPrimaryKey(mbNo);
	}
	
	public byte[] getPoritait(String eMail) {
		return dao.getPoritait(eMail);
	}
	 
	public List<MemberVO> getAll_CompositeQuery(Map<String,String[]>map) {
		return dao.getAll(map);
	}
	 
	public void updateMbPw (String mbPw,String eMail) {
		dao.updateMbPw(mbPw, eMail);
	}
	
	public MemberVO findByEmail(String eMail) {
		return dao.findByEMail(eMail);
	}
	
	public MemberVO getOneMemberNoImg(String mbNo) {
		return dao.findByPKNoImg(mbNo);
	}
	
	public MemberVO findByUsrName(String userName) {
		return dao.findByUsrName(userName);
	}
	
	public MemberVO updateCsSuccesTimes(Integer csSuccessTimes, String mbNo){
		MemberVO memberVO = new MemberVO();
		memberVO.setCsSuccessTimes(csSuccessTimes);
		memberVO.setMbNo(mbNo);
		
		dao.updateCsSuccessTimes(memberVO);
		return memberVO;
	}
	
	public MemberVO updateCsTimes(Integer csTimes, String mbNo){
		MemberVO memberVO = new MemberVO();
		memberVO.setCsTimes(csTimes);
		memberVO.setMbNo(mbNo);
		
		dao.updateCsTimes(memberVO);
		return memberVO;
	}
	
	public List<String> getAllMbUserName(){
		return dao.getAllMbUserName();
	}

}
