//package by.htp.library.filtert;
//
//
//import by.htp.library.command.AttributeName;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Random;
//

//@WebFilter(urlPatterns = { "/*" })
//public class PageUpdateFilter implements Filter {
//
//	public void init(FilterConfig fConfig) throws ServletException {
//	}
//
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//		String pageUnique = request.getParameter(AttributeName.PAGE_UNIQUE);
//		if (pageUnique != null) {
//			int currentPageUnique = Integer.parseInt(pageUnique);
//			HttpSession session = httpRequest.getSession();
//			int sessionPageUnique = (int) session.getAttribute(AttributeName.PAGE_UNIQUE);
//			if (currentPageUnique == sessionPageUnique) {
//				int newPageUnique = new Random().nextInt();
//				session.setAttribute(AttributeName.PAGE_UNIQUE, newPageUnique);
//				chain.doFilter(request, response);
//
//			} else {
//				String lastPage = (String) session.getAttribute(AttributeName.LAST_PAGE);
//				httpRequest.getRequestDispatcher(lastPage).forward(httpRequest, httpResponse);
//			}
//		} else {
//			chain.doFilter(request, response);
//		}
//	}
//
//	public void destroy() {
//	}
//}
