package cloudnote.serviceImp;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cloudnote.dao.BookDao;
import cloudnote.dao.NoteDao;
import cloudnote.entity.Note;
import cloudnote.entity.NoteBook;
import cloudnote.service.NoteBookService;
import cloudnote.util.NoteResult;
import cloudnote.util.NoteUtil;
@Service
@Transactional
public class BookServiceImp implements NoteBookService{
	@Resource(name="bookDao")
	private BookDao bookDao;
	@Resource
	private NoteDao noteDao;
	public NoteResult findNoteBook(String id) {
		NoteResult nr=new NoteResult();
//		System.out.println(nr);
		List<NoteBook> books=bookDao.findNoteBook(id);
		nr.setObj(books);
		return nr;
	}
	public NoteResult createNoteBook(String userId ,String bookname) {
		NoteResult nr=new NoteResult();
		NoteBook book=new NoteBook();
		book.setCn_notebook_id(NoteUtil.getId());
		Timestamp time=new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(time);
		book.setCn_notebook_name(bookname);
		book.setCn_user_id(userId);
		int x=bookDao.createNoteBook(book);
		if(x<=0){
			nr.setStatus(1);
			nr.setMsg("���ݿ��쳣��");
			return nr;
		}
		nr.setStatus(0);
		nr.setMsg("�����ɹ���");
		nr.setObj(book);
		System.out.println(book);
		return nr;
	}
	public NoteResult renameNoteBook(String id, String name) {
		NoteBook book=new NoteBook();
		book.setCn_notebook_id(id);
		book.setCn_notebook_name(name);
		NoteResult nr=new NoteResult();
		int x=bookDao.renameNoteBook(book);
		if(x<=0){
			nr.setStatus(1);
			nr.setMsg("�������쳣���޸�ʧ��");
			return nr;
		}
		nr.setStatus(0);
		nr.setMsg("�޸ĳɹ���");
		return nr;
	}
	public NoteResult deleteBook(String bookId) {
		List<Note> notes=noteDao.findNote(bookId);
		NoteResult nr=new NoteResult();
		if(notes.size()!=0){
			nr.setStatus(1);
			nr.setMsg("�ʼǱ��к��бʼǲ���ɾ����");
			return nr;
		}
		int x= bookDao.deleteBook(bookId);
		if(x<=0){
			nr.setStatus(2);
			nr.setMsg("�ʼǱ�ɾ��ʧ�ܣ�������!");
			return nr;
		}
		nr.setStatus(0);
		nr.setMsg("ɾ���ɹ�");
		return nr;
	}
}
