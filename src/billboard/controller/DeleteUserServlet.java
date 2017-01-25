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
import billboard.service.UserService;

@WebServlet(urlPatterns = { "/management/deleteUser" })
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		String deleteUserId = request.getParameter("deleteUserId");

		if (StringUtils.isEmpty(deleteUserId)) {
			messages.add("予期せぬエラーが発生しました");
			request.setAttribute("errorMessages", messages);
			response.sendRedirect("./");
		}

		if (isValid(request, messages) == true) {
			new UserService().deleteUser(Integer.parseInt(deleteUserId));
		}

		request.getSession().setAttribute("errorMessages", messages);
		response.sendRedirect("/billboard/management/top");
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String deleteUserId = request.getParameter("deleteUserId");
		User loginUser = (User) request.getSession().getAttribute("loginUser");

		if (Integer.parseInt(deleteUserId) == loginUser.getId()) {
			messages.add("権限がありません");
		}

		return (messages.size() == 0);

	}

}