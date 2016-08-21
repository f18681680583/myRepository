package cloudnote.service;

import cloudnote.entity.ChangeInfo;
import cloudnote.entity.User;
import cloudnote.util.NoteResult;

public interface UserService {
	public NoteResult checkLogin(String name,String password);
	public NoteResult checkRegist(User user);
//	public NoteResult findPhotoById(String id);
	public NoteResult changeInfo(ChangeInfo info);
}
