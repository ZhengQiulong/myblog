
package com.zql.filter.interceptor;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class SessionInterceptor extends MethodFilterInterceptor{

	private Map<String, Object> session;
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		session=ActionContext.getContext().getSession();
		if(session!=null&&session.get("user_id")!=null)
		return arg0.invoke();
		else {
		return Action.LOGIN;
		}
	}

	public void setSession(Map<String, Object> arg0) {
		
		this.session=arg0;
	}

	

}
