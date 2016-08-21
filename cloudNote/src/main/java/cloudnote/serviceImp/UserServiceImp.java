package cloudnote.serviceImp;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cloudnote.dao.UserDao;
import cloudnote.entity.ChangeInfo;
import cloudnote.entity.User;
import cloudnote.exception.NoteException;
import cloudnote.service.UserService;
import cloudnote.util.NoteResult;
import cloudnote.util.NoteUtil;

@Service("userService")
@Transactional
public class UserServiceImp implements UserService {
	@Resource(name = "userDao")
	private UserDao userDao;
	public NoteResult checkLogin(String name, String password) {
		NoteResult mr = new NoteResult();
		User user = userDao.findByName(name);
		if (user == null) {
			mr.setStatus(1);
			mr.setMsg("用户名不正确");
			mr.setObj(null);
			return mr;
		}
		try {
			String md5_password = NoteUtil.md5Encode(password);
			if (!md5_password.equals(user.getCn_user_password())) {
				mr.setStatus(2);
				mr.setObj(null);
				mr.setMsg("密码不正确");
				return mr;
			}
		} catch (NoSuchAlgorithmException e) {
			throw new NoteException("密码加密异常", e);
		}
		mr.setStatus(0);
		mr.setMsg("登录成功");
		user.setCn_user_password("");
		mr.setObj(user);
		return mr;

	}
	public NoteResult checkRegist(User user) {
		NoteResult nr=new NoteResult();
		User us=userDao.findByName(user.getCn_user_name());
		if(us!=null){
			nr.setStatus(1);
			nr.setMsg("用户名已存在");
			return nr;
		}
		user.setCn_user_id(NoteUtil.getId());
		try {
			user.setCn_user_password(NoteUtil.md5Encode(user.getCn_user_password()));
		} catch (NoSuchAlgorithmException e) {
			throw new NoteException("注册失败，请重新注册",e);
		}
		userDao.registUser(user);
		nr.setStatus(0);
		nr.setMsg("恭喜你，注册成功，请登录");
		return nr;
	}
	public NoteResult changeInfo(ChangeInfo info) {
		User user=userDao.findById(info.getUserId());
		NoteResult nr=new NoteResult();
		if(user==null){
			nr.setStatus(1);
			nr.setMsg("用户异常，请重新登录");
			return nr;
		}
		if(info.getOldPassword()!=null){
			try {
				info.setOldPassword(NoteUtil.md5Encode(info.getOldPassword()));
			} catch (NoSuchAlgorithmException e) {
				nr.setStatus(4);
				nr.setMsg("密码解密异常！");
				e.printStackTrace();
				return nr;
			}
		}
		if(!user.getCn_user_password().equals(info.getOldPassword())){
			nr.setStatus(2);
			nr.setMsg("用户密码错误！");
			return nr;
		}
		if(info.getPassword()!=null){
			try {
				user.setCn_user_password(NoteUtil.md5Encode(info.getPassword()));
			} catch (NoSuchAlgorithmException e) {
				nr.setStatus(4);
				nr.setMsg("密码解密异常！");
				e.printStackTrace();
				return nr;
			}
		}
		int x=userDao.changeUserInfo(user);
		if(x<=0){
			nr.setStatus(3);
			nr.setMsg("信息尚未做修改！");
			return nr;
		}
		nr.setStatus(0);
		nr.setMsg("更改成功!");
		return nr;
	}
	public NoteResult change(ChangeInfo info){
		NoteResult nr=new NoteResult();
		int x=userDao.change(info);
		nr.setMsg("修改成功！");
		return nr;
	}
}
