package cloudnote.service;

import cloudnote.util.NoteResult;

public interface ShareService {
	public NoteResult shareNote(String noteId);
	public NoteResult searchNote(String title,int page);
	public NoteResult findShareBody(String shareId);
	public NoteResult deleteShare(String noteId);
}
