package tools;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
	
	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public void sendMail(String to, String subject, String messageText) {
			
	   try {
		   // 設定使用SSL連線至 Gmail smtp Server
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");

       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
       // ●須將myGmail的【安全性較低的應用程式存取權】打開
	     final String myGmail = "HomecodeCompany@gmail.com";
	     final String myGmail_password = "homecode1";
	     
		   Session session = Session.getInstance(props, new Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() { //填入公司信箱
				   return new PasswordAuthentication(myGmail, myGmail_password);
			   }
		   });

		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(myGmail));
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		  
		   //設定信中的主旨  
		   message.setSubject(subject);
		   //設定信中的內容 
		   message.setText(messageText);

		   Transport.send(message);
		   System.out.println("傳送成功!");
     }catch (MessagingException e){
	     System.out.println("傳送失敗!");
	     e.printStackTrace();
     }
   }
	
	 public static void main (String args[]){

      
      
      String subject = "HomeCode!忘記密碼信";	//主旨
      String email = "ts00395109@yahoo.com.tw";						//要寄給誰
      String ch_name = "tim";
      
      String newPassword ="homecode"+((int)(Math.random()*(100000))+1000);
      String messageText ="您好, "+ch_name+ "這是您的新密碼 : "+newPassword+ "\n 請妥善保管並盡快更改密碼!" ;
       
      MailService mailService = new MailService();
     
		
		
		mailService.sendMail(email, subject, messageText);
		
      //測試抓東西
//		List list = new ArrayList();
//		if(list.contains(list.get(0))) {
//			System.out.println("沒東西用contain方法抓第一個值他也有東西!?");
//		}else {
//			System.out.println("contain這方法抓不到東西");
//		}
  
	
	 
	 
	 }


}
