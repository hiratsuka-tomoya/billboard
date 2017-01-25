package billboard.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

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

		List<String> categories = new UserPostingService().getUserPostingCategories();

		request.getSession().setAttribute("categories", categories);
		List<String> messages = null;
		if (request.getSession().getAttribute("errorMessages") == null) {
			messages = new ArrayList<String>();
		} else {
			messages = (List<String>)request.getSession().getAttribute("errorMessages");
		}

		List<Bean> userPostings = null;
		List<Bean> userComments = new UserCommentService().getUserComments();
		Collections.reverse(userComments);
		String refineCategory = request.getParameter("refineCategory");
		String refineStartDate = request.getParameter("refineStartDate");
		String refineEndDate = request.getParameter("refineEndDate");
		if (isValidRefine(request, messages) &
				(
				StringUtils.isNotEmpty(refineCategory)  ||
				StringUtils.isNotEmpty(refineStartDate) ||
				StringUtils.isNotEmpty(refineEndDate))
				) {
			userPostings = new UserPostingService().getRefinedUserPostings(refineCategory, refineStartDate,
					refineEndDate);
		} else {
			userPostings = new UserPostingService().getUserPostings();
		}
		request.setAttribute("errorMessages", messages);
		request.setAttribute("refineCategory", refineCategory);
		request.getSession().setAttribute("userPostings", userPostings);
		request.getSession().setAttribute("userComments", userComments);
	    request.setAttribute("refineStartDate", refineStartDate);
	    request.setAttribute("refineEndDate", refineEndDate);
		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}

	private boolean isValidRefine(HttpServletRequest request, List<String> messages) {
		String refineStartDate = request.getParameter("refineStartDate");
		String refineEndDate = request.getParameter("refineEndDate");
		String refineCategory = request.getParameter("refineCategory");
		List<String> categoryList = new ArrayList<String>();
		categoryList = (List<String>) request.getSession().getAttribute("categories");
		Date dateFrom = null;
		Date dateTo = null;

		if (StringUtils.isNotEmpty(refineCategory)) {
			if (!categoryList.contains(refineCategory)) {
				messages.add("存在しないカテゴリーです");
			}
		}
		if (StringUtils.isNotEmpty(refineStartDate)) {
			try {
			    DateUtils.parseDateStrictly(refineStartDate, new String[] {"yyyy/MM/dd"});
			    dateFrom = DateFormat.getDateInstance().parse(refineStartDate);
			} catch (ParseException e) {
				messages.add("絞込み開始日が不正です");
			}
		}
		if (StringUtils.isNotEmpty(refineEndDate)) {
			try {
			    DateUtils.parseDateStrictly(refineEndDate, new String[] {"yyyy/MM/dd"});
			    dateTo = DateFormat.getDateInstance().parse(refineEndDate);
			} catch (ParseException e) {
				messages.add("絞込み末日が不正です");
			}
		}
		if (dateFrom != null && dateTo != null) {
			if (dateFrom.compareTo(dateTo) > 0) {
				messages.add("絞込み末日より未来の開始日が指定されています");
			}

		}

		return (messages.size() == 0);

	}

}