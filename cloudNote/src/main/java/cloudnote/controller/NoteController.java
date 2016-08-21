package cloudnote.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cloudnote.entity.Note;
import cloudnote.service.NoteService;
import cloudnote.util.NoteResult;

@Controller
@RequestMapping("/note")
public class NoteController {
	@Resource
	private NoteService noteService;
	@RequestMapping("/findNote.do")
	@ResponseBody
	public NoteResult findNoteById(String cn_book_id) {
		NoteResult nr = noteService.findNoteById(cn_book_id);
		return nr;
	}
	@RequestMapping("/findNoteText.do")
	@ResponseBody
	public NoteResult findNoteText(String noteId){
		return noteService.findNoteText(noteId);
	}
	@RequestMapping("/saveNote.do")
	@ResponseBody
	public NoteResult saveNote(String title,String text,String noteId){
		Long time=System.currentTimeMillis();
		Note note=new Note();
		note.setCn_note_body(text);
		note.setCn_note_title(title);
		note.setCn_note_id(noteId);
		note.setCn_note_last_modify_time(time);
		NoteResult nr=noteService.saveNote(note);
		return nr;	
	}
	@RequestMapping("/createNote.do")
	@ResponseBody
	public NoteResult createNote(String bookId, String notename, String userId){
//		System.out.println(bookId+":"+notename+":"+userId);
		NoteResult nr=noteService.createNote(bookId, notename, userId);
		return nr;
	}
	@RequestMapping("/moveNote.do")
	@ResponseBody
	public NoteResult moveNote(String bookId,String noteId){
		NoteResult nr=noteService.moveNote(bookId, noteId);
		return nr;
	}
	@RequestMapping("/changeStatus.do")
	@ResponseBody
	public NoteResult changeNoteStatus(String noteId, String status){
		NoteResult nr=noteService.changeNoteStatus(noteId, status);
		return nr;
	}
	@RequestMapping("/findSpecialNote.do")
	@ResponseBody
	public NoteResult findSpecialNote(String status_id){
		NoteResult nr=noteService.findSpecialNote(status_id);
		return nr;
	}
	@RequestMapping("/deleteNote.do")
	@ResponseBody
	public NoteResult deleteNote(String noteId){
		NoteResult nr=noteService.deleteNote(noteId);
		return nr;
	}
	@RequestMapping("/replayNote.do")
	@ResponseBody
	public NoteResult replayNote(String bookId,String noteId){
		NoteResult nr=noteService.replayNote(bookId, noteId);
		return nr;
	}
}
