package cloudnote.dao;

import java.util.List;
import java.util.Map;

import cloudnote.entity.Note;

public interface NoteDao {
	public List<Note> findNote(String bookId);
	public Note findNoteText(String noteId);
	public int saveNote(Note note);
	public int createNote(Note note);
	public int moveNote(Note note);
	public int changeNoteStatus(Note note);
	public List<Note> findSpecialNote(String status);
	public int deleteNote(String noteId);
	public int replayNote(Note note);
	public Note findByNoteId(String noteId);
}
