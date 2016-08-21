package cloudnote.service;

import java.util.List;

import cloudnote.entity.NoteBook;
import cloudnote.util.NoteResult;

public interface NoteBookService {
	public NoteResult findNoteBook(String id);
	public NoteResult createNoteBook(String id,String name);
	public NoteResult renameNoteBook(String id,String name);
	public NoteResult deleteBook(String bookId);
}
