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
			mr.setMsg("�û�������ȷ");
			mr.setObj(null);
			return mr;
		}
		try {
			String md5_password = NoteUtil.md5Encode(password);
			if (!md5_password.equals(user.getCn_user_password())) {
				mr.setStatus(2);
				mr.setObj(null);
				mr.setMsg("���벻��ȷ");
				return mr;
			}
		} catch (NoSuchAlgorithmException e) {
			throw new NoteException("��������쳣", e);
		}
		mr.setStatus(0);
		mr.setMsg("��¼�ɹ�");
		user.setCn_user_password("");
		mr.setObj(user);
		return mr;

	}
	public NoteResult checkRegist(User user) {
		NoteResult nr=new NoteResult();
		User us=userDao.findByName(user.getCn_user_name());
		if(us!=null){
			nr.setStatus(1);
			nr.setMsg("�û����Ѵ���");
			return nr;
		}
		user.setCn_user_id(NoteUtil.getId());
		try {
			user.setCn_user_password(NoteUtil.md5Encode(user.getCn_user_password()));
		} catch (NoSuchAlgorithmException e) {
			throw new NoteException("ע��ʧ�ܣ�������ע��",e);
		}
		userDao.registUser(user);
		nr.setStatus(0);
		nr.setMsg("��ϲ�㣬ע��ɹ������¼");
		return nr;
	}
	public NoteResult changeInfo(ChangeInfo info) {
		User user=userDao.findById(info.getUserId());
		NoteResult nr=new NoteResult();
		if(user==null){
			nr.setStatus(1);
			nr.setMsg("�û��쳣�������µ�¼");
			return nr;
		}
		if(info.getOldPassword()!=null){
			try {
				info.setOldPassword(NoteUtil.md5Encode(info.getOldPassword()));
			} catch (NoSuchAlgorithmException e) {
				nr.setStatus(4);
				nr.setMsg("��������쳣��");
				e.printStackTrace();
				return nr;
			}
		}
		if(!user.getCn_user_password().equals(info.getOldPassword())){
			nr.setStatus(2);
			nr.setMsg("�û��������");
			return nr;
		}
		if(info.getPassword()!=null){
			try {
				user.setCn_user_password(NoteUtil.md5Encode(info.getPassword()));
			} catch (NoSuchAlgorithmException e) {
				nr.setStatus(4);
				nr.setMsg("��������쳣��");
				e.printStackTrace();
				return nr;
			}
		}
		int x=userDao.changeUserInfo(user);
		if(x<=0){
			nr.setStatus(3);
			nr.setMsg("��Ϣ��δ���޸ģ�");
			return nr;
		}
		nr.setStatus(0);
		nr.setMsg("���ĳɹ�!");
		return nr;
	}
	public NoteResult change(ChangeInfo info){
		NoteResult nr=new NoteResult();
		int x=userDao.change(info);
		nr.setMsg("�޸ĳɹ���");
		return nr;
	}
}
