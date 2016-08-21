package cloudnote.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cloudnote.entity.ChangeInfo;
import cloudnote.entity.User;
import cloudnote.service.UserService;
import cloudnote.util.NoteResult;
import cloudnote.util.VerifyCode;

@Controller
@RequestMapping("/user")
public class LoginController {
	@Resource(name="userService")
	private UserService userService;
	@RequestMapping("/login.vc")
	@ResponseBody
	public NoteResult excute(HttpServletRequest request){
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String code=request.getParameter("verifycode");
		NoteResult nr=userService.checkLogin(name,password);
		HttpSession session=request.getSession();
		String codeTrue=(String) session.getAttribute("code");
		if(code.equalsIgnoreCase(codeTrue)){
			nr.setCodestatus(0);
		}
		if(nr.getCodestatus()==0&&nr.getStatus()==0){
			User user = (User) nr.getObj();
			session.setAttribute("user", user);
		}
//		String s=null;
//		s.length();
		return nr;
	}
	@RequestMapping("/verifycode.vc")
	public void verifyCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		VerifyCode vc=new VerifyCode(100, 30);
		BufferedImage bf=vc.getCodeImage();
		String code=vc.getText();
		HttpSession session=request.getSession();
		session.setAttribute("code", code);
		vc.write(bf, response.getOutputStream());		
	}
	@RequestMapping("/regist.vc")
	@ResponseBody
	public NoteResult regist(User user,String verifycode,HttpSession session){
		String code=(String) session.getAttribute("code");
		NoteResult ns=new NoteResult();
		if(!verifycode.equalsIgnoreCase(code)){
			ns.setCodestatus(1);
			return ns;
		}
		ns=userService.checkRegist(user);
		ns.setCodestatus(0);
		return ns;
	}
	@RequestMapping("/changeInfo.do")
	@ResponseBody
	public NoteResult changeInfo(ChangeInfo info){
		NoteResult nr=userService.changeInfo(info);
		return nr;
	}
}
