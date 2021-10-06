/*
   測試:   http://localhost:8081/Local-BA103G5_Jacky/timelinefile_uploaded/file.xxx
   應注意     (※1)注意當有用到【額外路徑資訊】時必須使用【前置路徑對應】的設定
   同時注意(※2)web.xml內的<url-pattern>是<url-pattern>/timelinefile_uploaded/file.xxx</url-pattern>                        
*/

package com.util.tool;
	
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.oreilly.servlet.ServletUtils;

import com.oreilly.servlet.ServletUtils;

public class FileDownload extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {
    
	  req.setCharacterEncoding("UTF-8");
	  String fileName = new String(req.getPathInfo().getBytes("ISO-8859-1"),"UTF-8");
	  System.out.println("FileDownload - fileName = "+ fileName);
	    // Use a ServletOutputStream because we may pass binary information
	    ServletOutputStream out = res.getOutputStream();
	  //log
		System.out.println("[FileDownLoad]-[ServletOutStream out is null]:"+(out==null));
	    	
	    // Get the file to view
	    String file = new String(req.getPathTranslated().getBytes("ISO-8859-1"),"UTF-8");
		//String file = "/Users/Apple/Dropbox/Servelet/BA103_WebApp/WorkspaceforMac/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Local-BA103G5_Jacky/timelinefile_uploaded"+fileName;
		
	  //log
		System.out.println("[FileDownLoad]-[ file]:"+file);

	    // No file, nothing to view
	    if (file == null) {
	      out.println("No file to view");
	      return;
	    }

	    // Get and set the type of the file
	    String contentType = getServletContext().getMimeType(file);
	    System.out.println("contentType="+contentType);

	//●測試2-以下進行檔案下載    
	res.setContentType("application/octet-stream");
	//res.setHeader("Content-Disposition", "attachment; filename=\""+(new File(file).getName())+"\" ");
	res.setHeader("Content-Disposition", "attachment; filename="+fileName);

	  res.setContentType(contentType);

//	    // Return the file
//	    try {
	      ServletUtils.returnFile(file, out);
//	    }
//	    catch (FileNotFoundException e) {
//	      out.println("File not found");
//	    }
//	    catch (IOException e) {
//	      out.println("Problem sending file: " + e.getMessage());
//	    }
	  }
}



