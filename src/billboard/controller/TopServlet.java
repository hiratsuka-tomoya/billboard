package billboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import billboard.beans.Bean;
import billboard.service.UserCommentService;
import billboard.service.UserPostingService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		if (request.getSession().getAttribute("loginUser") == null) {
			response.sendRedirect("login");
			return;
		}
		String categories[] = new String[3];
		categories[0] = "デフォルト値";
		categories[1] = "テストカテゴリ1";
		categories[2] = "テストカテゴリ2";
		request.getSession().setAttribute("categories", categories);

//		if (request.getParameter("account") != null) {
//			postings = new PostingService().getPostings("account", request.getParameter("account"));
//		} else if (request.getParameter("user_id") != null) {
//			postings = new PostingService().getPostings("user_id", request.getParameter("user_id"));
//		} else {

//		}
		List<Bean> userPostings = null;
		String refineCategory = request.getParameter("refineCategory");
		String refineStartDate = request.getParameter("refineStartDate");
		String refineEndDate = request.getParameter("refineEndDate");
		if (StringUtils.isEmpty(refineCategory)) {
			userPostings = new UserPostingService().getUserPostings();
		} else {
			userPostings = new UserPostingService().getUserPostings("category", refineCategory);
		}

		List<Bean> userComments = new UserCommentService().getUserComments();
		request.getSession().setAttribute("userPostings", userPostings);
		request.getSession().setAttribute("userComments", userComments);

		request.getRequestDispatcher("/top.jsp").forward(request, response);

//		User user = (User) request.getSession().getAttribute("loginUser");
//		if (user == null) {
//
//		}
////		boolean isShowMessageForm;
////		if (user != null) {
////			isShowMessageForm = true;
////		} else {
////			isShowMessageForm = false;
////		}
//
//		List<UserMessage> messages;

//
//		request.setAttribute("messages", messages);
//		request.setAttribute("isShowMessageForm", isShowMessageForm);


	}

}
