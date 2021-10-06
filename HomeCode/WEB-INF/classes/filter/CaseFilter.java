package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/CaseFilter")
public class CaseFilter implements Filter {
	FilterConfig config;
	
	@Override
	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String csNo = (String) req.getAttribute("csNo");
		if(csNo == null){
			res.sendRedirect(req.getContextPath() + "/index.jsp");
			return;
		} else{
			chain.doFilter(request, response);
		}
		
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
