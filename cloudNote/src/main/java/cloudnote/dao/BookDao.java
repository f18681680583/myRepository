package cloudnote.dao;

import java.util.List;

import cloudnote.entity.NoteBook;

public interface BookDao {
	public List<NoteBook> findNoteBook(String id);
	public int createNoteBook(NoteBook book);
	public int renameNoteBook(NoteBook book);
	public int deleteBook(String bookId);
}
