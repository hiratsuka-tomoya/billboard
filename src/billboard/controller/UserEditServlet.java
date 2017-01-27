package billboard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import billboard.beans.User;
import billboard.service.UserService;

@WebServlet(urlPatterns = { "/management/userEdit" })
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		User loginUser = (User) request.getSession().getAttribute("loginUser");
		List<String> messages = new ArrayList<String>();
		if (loginUser == null) {
			response.sendRedirect("login");
		} else if (loginUser.getDepartmentId() != 1) {
			messages.add("権限がありません");
			request.getSession().setAttribute("errorMessages", messages);
			response.sendRedirect("./");
		} else {
			int editUserId;
			try {
				editUserId = Integer.parseInt(request.getParameter("editUserId"));
			} catch (Exception e) {
				messages.add("不正なアクセスです");
				request.getSession().setAttribute("errorMessages", messages);
				response.sendRedirect("/billboard/management/top");
				return;
			}
			User editUser = (User) new UserService().getUser(editUserId);
			if (editUser == null) {
				messages.add("不正なアクセスです");
				request.getSession().setAttribute("errorMessages", messages);
				response.sendRedirect("/billboard/management/top");
				return;
			}
			request.getSession().setAttribute("editUser", editUser);
			request.setAttribute("loginId", editUser.getLoginId());
			request.setAttribute("name", editUser.getName());
			request.setAttribute("branchId", editUser.getBranchId());
			request.setAttribute("departmentId", editUser.getDepartmentId());
			request.getRequestDispatcher("/userEdit.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		User fromUser = (User)request.getSession().getAttribute("editUser");
		if (isValid(request, messages, fromUser) == true) {

			User toUser = new User();
			toUser.setId(fromUser.getId());
			toUser.setLoginId(request.getParameter("loginId"));
			toUser.setPassword(StringUtils.isNotEmpty(request.getParameter("password")) ? request.getParameter("password") : fromUser.getPassword());
			toUser.setName(request.getParameter("name"));
			toUser.setBranchId(Integer.parseInt(request.getParameter("branchId")));
			toUser.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
			toUser.setStopped(fromUser.isStopped());
			new UserService().update(toUser,StringUtils.isNotEmpty(request.getParameter("password")));

			request.getSession().removeAttribute("editUser");
			response.sendRedirect("./top");
		} else {
			request.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("/userEdit.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages, User fromUser) {
		HttpSession session = request.getSession();
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		int branchId = Integer.valueOf(request.getParameter("branchId"));
		int departmentId = Integer.valueOf(request.getParameter("departmentId"));
		User editUser = (User)request.getSession().getAttribute("editUser");
		User editUserOnDB = (User)new UserService().getUser(editUser.getId());

		if (editUser.getUpdatedDate().compareTo(editUserOnDB.getUpdatedDate()) != 0) {
			messages.add("対象の情報が取得時と異なるため、変更できませんでした");
		}

		session.removeAttribute("loginId");
		session.removeAttribute("name");

		request.setAttribute("branchId", branchId);
		request.setAttribute("departmentId", departmentId);

		if (StringUtils.isEmpty(loginId)) {
			messages.add("ログインIDを入力してください");
		} else if (!loginId.matches("^[0-9a-zA-Z]{6,20}$")) {
			if (!loginId.matches("^[0-9a-zA-Z]+$")) {
				messages.add("ログインIDは半角英数字のみ使用できます");
			}
			if (!(loginId.length() >= 6 && loginId.length() <= 20)) {
				messages.add("ログインIDは6文字以上20文字以内で入力してください");
			}
		} else if(!editUser.getLoginId().equals(loginId)) {
			if (new UserService().getUserFromLoginId(loginId) != null) {
				messages.add("そのログインIDは登録済みです");
			}
		}

		if (StringUtils.isNotEmpty(password)) {
			if (!password.matches("^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]{6,255}+$")) {
				if (!password.matches("^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]+$")) {
					messages.add("パスワードは記号を含む半角文字のみ使用できます");
				}
				if (!(password.length() >= 6 && password.length() <= 255)) {
					messages.add("パスワードは6文字以上255文字以内で入力してください");
				}
			}
		}

		if (StringUtils.isEmpty(name)) {
			messages.add("ユーザー名を入力してください");
		} else if (!(name.length() <= 10)) {
			messages.add("ユーザー名は10文字以下で入力してください");
		}

		request.setAttribute("loginId", loginId);
		request.setAttribute("name", name);

		return (messages.size() == 0);

	}

}