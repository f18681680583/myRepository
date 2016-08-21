package cloudnote.serviceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cloudnote.dao.NoteDao;
import cloudnote.entity.Note;
import cloudnote.service.NoteService;
import cloudnote.util.NoteResult;
import cloudnote.util.NoteUtil;
@Service
@Transactional
public class NoteServiceImp implements NoteService {
	@Resource
	private NoteDao noteDao;
	public NoteResult findNoteById(String cn_book_id) {
		NoteResult nr=new NoteResult();
		List<Note> notes=noteDao.findNote(cn_book_id);
		nr.setObj(notes);
		return nr;
	}
	public NoteResult findNoteText(String cn_note_id) {
		NoteResult nr=new NoteResult();
		Note note=noteDao.findNoteText(cn_note_id);
		if(note.getCn_note_body()==null){
			note.setCn_note_body("");
		}
		nr.setObj(note);
		return nr;
	}
	public NoteResult saveNote(Note note) {
		NoteResult nr=new NoteResult();
		int x=noteDao.saveNote(note);
		if(x<=0){
			nr.setStatus(1);
			nr.setMsg("保存失败，请重试");
			return nr;
		}
		nr.setStatus(0);
		nr.setMsg("保存成功");
		return nr;
	}
	public NoteResult createNote(String bookId, String notename, String userId) {
		Note note=new Note();
		String id=NoteUtil.getId();
		note.setCn_note_id(id);
		note.setCn_notebook_id(bookId);
		note.setCn_user_id(userId);
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		note.setCn_note_title(notename);
		note.setCn_note_status_id("1");
		int x=noteDao.createNote(note);
		NoteResult nr=new NoteResult();
		if(x<=0){
			nr.setStatus(1);
			nr.setMsg("创建失败！");
			return nr;
		}
		nr.setStatus(0);
		nr.setMsg("创建笔记成功");
		nr.setObj(id);
		return nr;
	}
	public NoteResult moveNote(String bookId, String noteId) {
//		Map<String, String> map=new HashMap<String, String>();
//		map.put("bookId", bookId);
//		map.put("noteId", noteId);
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_notebook_id(bookId);
		int x=noteDao.moveNote(note);
		System.out.println(x);
		NoteResult nr=new NoteResult();
		if(x<=0){
			nr.setStatus(1);
			nr.setMsg("更新失败！");
			return nr;
		}
		nr.setStatus(0);
		nr.setMsg("移动成功");
		return nr;
	}
	public NoteResult changeNoteStatus(String noteId, String status) {
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_status_id(status);
		int x=noteDao.changeNoteStatus(note);
		NoteResult nr=new NoteResult();
		if(x<=0){
			nr.setStatus(1);
			nr.setMsg("操作失败！");
			return nr;
		}
		nr.setStatus(0);
		nr.setMsg("操作成功！");
		return nr;
	}
	public NoteResult findSpecialNote(String status) {
		List<Note> list=noteDao.findSpecialNote(status);
		NoteResult nr=new NoteResult();
		nr.setObj(list);
		return nr;
	}
	public NoteResult deleteNote(String noteId) {
		int x=noteDao.deleteNote(noteId);
		NoteResult nr=new NoteResult();
		if(x<=0){
			nr.setStatus(1);
			nr.setMsg("删除失败！");
			return nr;
		}
		nr.setStatus(0);
		nr.setMsg("删除成功！");
		return nr;
	}
	public NoteResult replayNote(String bookId, String noteId) {
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_notebook_id(bookId);
		int x=noteDao.replayNote(note);
		NoteResult nr=new NoteResult();
		if(x<=0){
			nr.setStatus(1);
			nr.setMsg("操作失败！");
			return nr;
		}
		nr.setStatus(0);
		nr.setMsg("操作成功！");
		return nr;
	}

}
