package com.message.model;

import java.util.List;

public interface MessageDAO_interface {
	public void insert(MessageVO messageVO);
    public void updateReadStatus(MessageVO messageVO);
    public void deleteByMailMb(String mesgNo);
    public void deleteByRecvMb(String mesgNo);
    public List<MessageVO> findByMailMbNo(String mailMbNo);
    public List<MessageVO> findByMailRecvMbNo(String mailRecvMbNo);
    public List<MessageVO> findByAdmin(String mailRecvMbNo);
}
