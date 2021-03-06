package by.htp.library.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * This filter protect from illegal direct acces to the pages. In case attemtp
 * to access jsp page directly, this filter will redirect user to index.jsp
 *
 * @author Sergei Levkovskii
 */

@WebFilter(urlPatterns = { "/jsp/*" }, initParams = { @WebInitParam(name = "INDEX_PATH", value = "/index.jsp") })
public class PageRedirectFilter implements Filter {

	private String indexPath;

	public void init(FilterConfig fConfig) throws ServletException {
		indexPath = fConfig.getInitParameter("INDEX_PATH");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
		chain.doFilter(request, response);
	}

	public void destroy() {
	}
}
