package cloudnote.dao;

import java.util.List;
import java.util.Map;

import cloudnote.entity.ShareNote;

public interface ShareDao {
	public int shareNote(ShareNote note);
	public int deleteShare(String noteId);
	public List<ShareNote> searchNote(Map map);
	public ShareNote findShareBody(String shareId);
}
