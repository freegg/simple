package com.you.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.you.bean.User;
import com.you.service.UserService;

@Controller  
@RequestMapping("/user")  
public class TestController {
	
	 	@Resource  
	    private UserService userService;  
		@RequestMapping(value = "/tree", method = RequestMethod.GET)
		public @ResponseBody String tree(Integer pageSize)
		{
			List list= userService.selectAll();
			System.out.println(JSON.toJSONString(list));
			return JSON.toJSONString(list);
		}
		@RequestMapping(value = "/loginsc", method = RequestMethod.GET)
		public  ModelAndView loginin(HttpServletRequest request)
		{
			Cookie [] cookies = request.getCookies();
			ModelAndView mv = new ModelAndView();
			JSONObject obj = new JSONObject();
			if(cookies.length>0){
				for (int i = 0; i < cookies.length; i++) {
					if(cookies[i].getName().equals("username")){
						String userString = cookies[i].getValue();
						obj.put("username", userString);
					}else if(cookies[i].getName().equals("password")){
						String pass = cookies[i].getValue();
						obj.put("password", pass);
					}
				}
			}
			mv.addObject("userJSON",obj);
			mv.setViewName("login");
			return mv;
		}
		@RequestMapping(value = "/main", method = RequestMethod.GET)
		public  String main()
		{
			return "main";
		}
		@RequestMapping(value = "/sock", method = RequestMethod.GET)
		public  String sock()
		{
			return "testSocket";
		}
		@RequestMapping(value = "/pag", method = RequestMethod.GET)
		public @ResponseBody String pag(Integer pageSize,Integer pageNumber,Integer offset,Integer limit)
		{
				int count = userService.getCount();
				List list= userService.selectLimit(offset,limit);
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append("{\"total\":"+count+",\"rows\":"+JSON.toJSONString(list)+"}");
				return stringBuffer.toString();
		}
		@RequestMapping(value = "/del", method = RequestMethod.GET)
		public @ResponseBody String del(Integer userId){
			  User user = new User();
			  user.setId(userId);
	    	  int list= userService.delete(user);
	    	  return JSON.toJSONString(list);
		  }
		@RequestMapping(value = "/inse", method = RequestMethod.GET)
		public @ResponseBody String inse(Integer id,String name,String password,Integer age){
			  User user = new User();
			  user.setId(id);
			  user.setAge(age);
			  user.setName(name);
			  user.setPassword(password);
	    	  int list= userService.insert(user);
	    	  return JSON.toJSONString(list);
		  }
		@RequestMapping(value = "/somedel", method = RequestMethod.POST)
		public @ResponseBody String somedel(@RequestBody List<User> params){
			//根据id 批量删除 select * from user where id in(0,1)
			//批量插入 insert into user values (0,1,2,3),(0,1,2,3)原理都一样
			Integer[] arr = new Integer [params.size()];
			for(int i = 0;i<params.size();i++)
			{
				arr[i]=params.get(i).getId();
			}
	    	  int list= userService.somedel(arr);
	    	  return JSON.toJSONString(list);
		  }
		@RequestMapping(value = "/fordel", method = RequestMethod.POST)
		public @ResponseBody String fordel(@RequestBody List<User> params){
			//根据id 循环删除 
			//如果有一个失败，怎么办
			int succ=0;
			for(int i = 0;i<params.size();i++)
			{
				User user = new User();
				user.setId(params.get(i).getId());
				succ = userService.delete(user);
			}
	    	  return JSON.toJSONString(succ);
		  }
		@RequestMapping(value = "/update", method = RequestMethod.GET)
		public @ResponseBody String update(Integer id,String name,String password,Integer age){
			 User user = new User();
			  user.setId(id);
			  user.setAge(age);
			  user.setName(name);
			  user.setPassword(password);
	    	  int list= userService.update2(user);
	    	  return JSON.toJSONString(list);	
		}
		@RequestMapping(value = "/twosel", method = RequestMethod.GET)
		public @ResponseBody String twosel(){
	    	  List list= userService.twosel();
	    	  return JSON.toJSONString(list);	
		}
		@RequestMapping(value = "/login", method = RequestMethod.POST)
		public @ResponseBody String login(String user,String password,String cooki,HttpServletRequest request,HttpServletResponse response){
			User user2 = new User();
			user2.setName(user);
			user2.setPassword(password);
			String succ = "";
			User use = userService.sele(user2);
			if(use!=null){
				HttpSession session = request.getSession();
				session.setAttribute("username", use.getName());
				succ = session.getId();
				if(cooki!=""&&cooki!=null){
					 Cookie nameCookie = new Cookie("username", use.getName());
		             nameCookie.setMaxAge(60 * 60 * 24 * 3);
		             Cookie pwdCookie = new Cookie("password", use.getPassword());
		             pwdCookie.setMaxAge(60 * 60 * 24 * 3);
		             response.addCookie(nameCookie);
		             response.addCookie(pwdCookie);
				}
			}else{
				succ = "1";
			}
	    	  return JSON.toJSONString(succ);	
		}
		@RequestMapping(value = "/logout", method = RequestMethod.GET)
		public String  logininn(HttpServletRequest request){
				HttpSession session = request.getSession();
				session.removeAttribute("username");
				return "login";
		}
		@RequestMapping("/tt")
	    @ResponseBody
	    public String sendMsgToUser(HttpServletRequest request){
			String msg = "2016，与你相遇，猴幸运！";
	    	String userCd = (String) request.getSession().getAttribute("USER_CD");
	      //  myHandler.sendMessageToUser(userCd, new TextMessage(msg));
	        return JSON.toJSONString(msg);
	    }
}