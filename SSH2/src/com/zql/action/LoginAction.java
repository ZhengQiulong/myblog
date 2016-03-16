package com.zql.action;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.zql.service.PersonService;

@Controller
public class LoginAction implements ServletResponseAware{
	@Resource
	private PersonService personService;
	private String user;
	private String password;
	private HttpServletResponse response;

	public void isLogin()
	{
		int user_id=personService.login(user, password);
		
			try {
				if(user_id!=0)
				{
				ActionContext.getContext().getSession().put("user_id",user_id);
				response.getWriter().print(1);
				}
				else {
					response.getWriter().print(-1);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		response=arg0;
	}


	
}
