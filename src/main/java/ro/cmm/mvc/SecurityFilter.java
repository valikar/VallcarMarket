package ro.cmm.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.cmm.domain.LoginUser;
import ro.cmm.domain.User;
import ro.cmm.service.SecurityService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Joseph Sunday, 23.04.2017 at 18:16.
 */
@Component
public class SecurityFilter implements Filter {

    @Autowired
    private SecurityService securityService;

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        LoginUser user = (LoginUser) ((HttpServletRequest) request).getSession().getAttribute("currentUser");

        securityService.setCurrentUser(user);

        String url = ((HttpServletRequest) request).getRequestURL().toString();

//        if (url.contains("")) {
//            if (user == null) {
//                HttpServletResponse servletResponse = (HttpServletResponse) response;
//                //servletResponse.sendError(401);
//                servletResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
//                servletResponse.setHeader("Location", "/login");
//                return;
//            } else {
//                //if authorized do nothing
////				if (false) {
////					//not authorized
////						HttpServletResponse servletResponse = (HttpServletResponse) response;
////						servletResponse.sendError(401);
////						return;
////				}
//            }
//        }

        // System.out.println("Thread name: " + Thread.currentThread().getName()
        // +
        // ", current user: " + (user != null ? user.getUserName() : null));
        //

        chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }
}
