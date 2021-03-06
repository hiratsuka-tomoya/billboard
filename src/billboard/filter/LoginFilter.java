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
import billboard.service.UserService;

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
				((HttpServletResponse) response).sendRedirect("/billboard/login");
			} else {
				User loginUser = (User) session.getAttribute("loginUser");
				if (loginUser == null) {
					((HttpServletResponse) response).sendRedirect("/billboard/login");
					return;
				} else {
					// ログイン済みならログインユーザーをDBから再取得
					loginUser = (User) new UserService().getUser(loginUser.getId());
					if (loginUser == null) {
						messages.add("アカウントが存在しません");
						session.setAttribute("errorMessages", messages);
						session.removeAttribute("loginUser");
						((HttpServletResponse) response).sendRedirect("/billboard/login");
						return;
					} else if (loginUser.isStopped() == true) {
						loginUser = (User) new UserService().getUser(loginUser.getId());
						messages.add("アカウントが停止されています");
						session.setAttribute("errorMessages", messages);
						session.removeAttribute("loginUser");
						((HttpServletResponse) response).sendRedirect("/billboard/login");
						return;
					} else {
						session.setAttribute("loginUser", loginUser);
					}
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