package billboard.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("./")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

//		try {
//
//            HttpSession session = ((HttpServletRequest)request).getSession();
//
//            if (session == null){
//                session = ((HttpServletRequest)request).getSession(true);
//
//                ((HttpServletResponse)response).sendRedirect("login");
//            }else{
//                Object loginCheck = session.getAttribute("loginUser");
//                if (loginCheck == null){
//                    ((HttpServletResponse)response).sendRedirect("login");
//                    return;
//                }
//
//			chain.doFilter(request, response);
//            }
//		} catch (ServletException se) {
//
//		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) {
	}

	@Override
	public void destroy() {
	}

}