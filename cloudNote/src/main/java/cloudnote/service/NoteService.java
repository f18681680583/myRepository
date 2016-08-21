package cloudnote.service;

import cloudnote.entity.Note;
import cloudnote.util.NoteResult;

public interface NoteService {
	public NoteResult findNoteById(String cn_book_id);
	public NoteResult findNoteText(String cn_note_id);
	public NoteResult saveNote(Note note);
	public NoteResult createNote(String bookId,String notename,String userId);
	public NoteResult moveNote(String bookId,String noteId);
	public NoteResult changeNoteStatus(String noteId,String status);
	public NoteResult findSpecialNote(String status);
	public NoteResult deleteNote(String noteId);
	public NoteResult replayNote(String bookId,String noteId);

}
