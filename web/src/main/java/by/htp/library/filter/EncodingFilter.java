package by.htp.library.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Filters all requests and checks the encoding. If encoding isn't correct,
 * changes it in the right encoding
 * 
 * @author Sergei Levkovskii
 *
 */
@WebFilter(urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "encoding", value = "UTF-8") })
public class EncodingFilter implements Filter {
	private String encoding;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		String requestEncoding = request.getCharacterEncoding();
		if (requestEncoding == null || !encoding.equalsIgnoreCase(requestEncoding)) {
			request.setCharacterEncoding(encoding);
			response.setCharacterEncoding(encoding);
		}
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");
	}
}
