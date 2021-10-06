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

import com.cs.model.CaseService;

@WebFilter("/CaseFreelancerHirerFilter")
public class CaseFreelancerHirerFilter implements Filter {
	FilterConfig config;
	
	@Override
	public void destroy() {
		config = null;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String mbNo = (String) req.getSession().getAttribute("mbNo");
		String csNo = (String) req.getParameter("csNo");
		CaseService csSvc = new CaseService();
		if(csSvc.getOneCase(csNo).getHirerNo().equals(mbNo) || csSvc.getOneCase(csNo).getFreelancerNo().equals(mbNo)){
			chain.doFilter(request, response);
			
		} else{
			res.sendRedirect(req.getContextPath() + "/index.jsp");
			return;
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
