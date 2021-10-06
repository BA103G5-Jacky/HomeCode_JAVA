package com.message.model;

import java.util.List;

public class MessageService {
	
	private MessageDAO_interface dao;

	public MessageService() {
		dao = new MessageDAO();
	}
	
	public void insertMessagebyVO(MessageVO messageVO){	
		
		dao.insert(messageVO);
		
	}
	
	public MessageVO insertMessage(String mailMbNo, String meailRecvMbNo,
			String mesgTitle, String mesgContent){
		MessageVO messageVO = new MessageVO();
		messageVO.setMailMbNo(mailMbNo);
		messageVO.setMailRecvMbNo(meailRecvMbNo);
		messageVO.setMesgTitle(mesgTitle);
		messageVO.setMesgContent(mesgContent);		
		
		dao.insert(messageVO);
		return messageVO;
	}
	
	public MessageVO updateReadStatus(String readStatus, String mesgNo){
		MessageVO messageVO = new MessageVO();
		messageVO.setReadStatus(readStatus);
		messageVO.setMesgNo(mesgNo);
		
		dao.updateReadStatus(messageVO);
		return messageVO;
	}
	
	public void deleteByMailMb(String mailMb){
		dao.deleteByMailMb(mailMb);
	}
	
	public void deleteByRecvMb(String recvMb){
		dao.deleteByRecvMb(recvMb);
	}
	
	public List<MessageVO> findByMailMbNo(String mailMbNo){
		return dao.findByMailMbNo(mailMbNo);
	}
    public List<MessageVO> findByMailRecvMbNo(String mailRecvMbNo){
    	return dao.findByMailRecvMbNo(mailRecvMbNo);
    }
    public List<MessageVO> findByAdmin(String mailRecvMbNo){
    	return dao.findByAdmin(mailRecvMbNo);
    }
}
