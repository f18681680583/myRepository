package cloudnote.serviceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.webkit.SharedBuffer;

import cloudnote.dao.NoteDao;
import cloudnote.dao.ShareDao;
import cloudnote.entity.Note;
import cloudnote.entity.ShareNote;
import cloudnote.service.ShareService;
import cloudnote.util.NoteResult;
import cloudnote.util.NoteUtil;
import jdk.nashorn.internal.runtime.ECMAException;
@Service
@Transactional
public class ShareServiceImp  implements ShareService{
	@Resource
	private NoteDao noteDao;
	@Resource
	private ShareDao shareDao;
	public NoteResult shareNote(String noteId) {
		Note note=noteDao.findByNoteId(noteId);
		note.setCn_note_status_id("3");
		ShareNote shareNote=new ShareNote();
		shareNote.setCn_note_id(noteId);
		shareNote.setCn_share_body(note.getCn_note_body());
		shareNote.setCn_share_title(note.getCn_note_title());
		shareNote.setCn_share_id(NoteUtil.getId());
		int x=shareDao.shareNote(shareNote);
		NoteResult nr=new NoteResult();
		if(x<=0){
			nr.setStatus(1);
			nr.setMsg("分享失败！");
			return nr;
		}
		nr.setStatus(0);
		nr.setMsg("分享成功！");
		int y =noteDao.changeNoteStatus(note);
		if(y<=0){
			nr.setStatus(1);
			nr.setMsg("分享失败！");
			shareDao.deleteShare(noteId);
			return nr;
		}
		return nr;
	}
	public NoteResult searchNote(String title,int page) {
		Map<String,Object> map=new HashMap<String, Object>();
		if(page<1){
			page=1;
		}
		int start=(page-1)*5;
		title="%"+title+"%";
		map.put("keyword",title);
		map.put("start", start);
		map.put("length", 5);
		List<ShareNote> list=shareDao.searchNote(map);
		NoteResult nr=new NoteResult();
		nr.setObj(list);
		return nr;
	}
	public NoteResult findShareBody(String shareId) {
		ShareNote share=shareDao.findShareBody(shareId);
		NoteResult nr=new NoteResult();
		nr.setObj(share);
		return nr;
	}
	public NoteResult deleteShare(String noteId) {
		NoteResult nr=new NoteResult();
		Note note=noteDao.findByNoteId(noteId);
		note.setCn_note_status_id("1");
		int x=shareDao.deleteShare(noteId);
		if(x<=0){
			nr.setStatus(1);
			nr.setMsg("取消分享失败，请重试！");
			return nr;
		}
		int y =noteDao.changeNoteStatus(note);
		if(y<=0){
			nr.setStatus(2);
			nr.setMsg("取消分享失败！。。。");
			shareDao.deleteShare(noteId);
			return nr;
		}
		nr.setStatus(0);
		nr.setMsg("取消分享成功！");
		return nr;
	}

}
