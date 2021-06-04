package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/AdminFilter")
public class AdminFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		HttpSession session = req.getSession(false);
		if (uri.endsWith("html")) {
			res.sendError(401, "Cannot access html files");
			return;
		}
		if (uri.equals("/admin.jsf")) {
			if (session == null) {
				res.sendError(401, "Admin only");
				return;
			}
			if (session.getAttribute("admin") == null) {
				res.sendError(401, "Admin only");
				return;
			}
			if (session.getAttribute("admin").equals(Boolean.FALSE)) {
				res.sendError(401, "Admin only");
				return;
			}
		}
		chain.doFilter(request, response);
	}

}
