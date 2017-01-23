package billboard.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import billboard.beans.User;

@WebFilter("/*")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String target = ((HttpServletRequest) request).getRequestURI();
		HttpSession session = ((HttpServletRequest) request).getSession();
		List<String> messages = new ArrayList<String>();
		if (target.matches(".*/login$") == false) {
			if (session == null) {
				session = ((HttpServletRequest) request).getSession(true);
//				session.setAttribute("target", target);
				((HttpServletResponse) response).sendRedirect("login");
			} else {
				User loginCheck = (User)session.getAttribute("loginUser");
				if (loginCheck == null) {
//					session.setAttribute("target", target);
					((HttpServletResponse) response).sendRedirect("login");
					return;
				} else if (loginCheck.isStopped() == true) {
					messages.add("アカウントが停止されています");
					session.setAttribute("errorMessages", messages);
					session.removeAttribute("loginUser");
//					session.setAttribute("target", target);
					((HttpServletResponse) response).sendRedirect("login");
					return;
				}
			}
		} else {
			session.removeAttribute("target");
		}

		chain.doFilter(request, response); // サーブレットを実行
	}

	@Override
	public void init(FilterConfig config) {
	}

	@Override
	public void destroy() {
	}

}