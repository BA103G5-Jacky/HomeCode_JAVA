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
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;
import com.member.model.MemberVO;


public class SuspensiveFilter implements Filter {
	FilterConfig config;
    public SuspensiveFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		String mbNo = (String) session.getAttribute("mbNo");
		MemberService mbSvc = new MemberService();
		MemberVO mbVO = mbSvc.getOneMember(mbNo);
		System.out.println(mbVO.getSuspensionStatus());
		if("是".equals(mbVO.getSuspensionStatus())){
			res.sendRedirect(req.getContextPath()+"/index.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = config;
	}

}
