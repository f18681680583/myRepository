package cloudnote;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInstantiator implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(1);
		Cookie[] cookies=request.getCookies();
		String id=null;
		String path=request.getContextPath();
		if(cookies==null){
			response.sendRedirect(path+"/log_in.html");
			return false;
		}
		for(Cookie c:cookies){
			if("cn_user_id".equals(c.getName())){
				id=c.getValue();
				break;
			}
		}
		if(id!=null){	
			return true;
		}
		response.setHeader("status", "login");
		response.sendRedirect(path+"/log_in.html");
		return false;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println(2);
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println(3);
		
	}


}
