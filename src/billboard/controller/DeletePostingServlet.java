package billboard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import billboard.beans.User;
import billboard.service.PostingService;
import billboard.service.UserService;

@WebServlet(urlPatterns = { "/deleteposting" })
public class DeletePostingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		String postingId = request.getParameter("postingId");

		if (StringUtils.isEmpty(postingId)) {
			messages.add("予期せぬエラーが発生しました");
			request.setAttribute("errorMessages", messages);
			response.sendRedirect("./");
		}

		if (isValid(request, messages) == true) {
			new PostingService().deleteUserPosting(Integer.parseInt(postingId));
		}

		request.getSession().setAttribute("errorMessages", messages);
		response.sendRedirect("./");
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String postingUserId = request.getParameter("postingUserId");
		User postingUser = (User) new UserService().getUser(Integer.parseInt(postingUserId));
		User loginUser = (User) request.getSession().getAttribute("loginUser");

		if (!(loginUser.getDepartmentId() == 2 ||
				(loginUser.getDepartmentId() == 3 && (loginUser.getBranchId() == postingUser.getBranchId())))) {
			messages.add("権限がありません");
		}

		return (messages.size() == 0);

	}

}