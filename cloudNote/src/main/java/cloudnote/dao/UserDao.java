package cloudnote.dao;

import cloudnote.entity.ChangeInfo;
import cloudnote.entity.User;

public interface UserDao {
	public User findByName(String name);
	public User findById(String userId);
	public void registUser(User user);
	public String getIdByName(String name);
	public String findPhotoById(String id);
	public int changeUserInfo(User user);
	public int change(ChangeInfo info);
	
}
