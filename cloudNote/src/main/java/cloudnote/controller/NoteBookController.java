package cloudnote.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cloudnote.entity.NoteBook;
import cloudnote.entity.User;
import cloudnote.service.NoteBookService;
import cloudnote.util.NoteResult;
import cloudnote.util.NoteUtil;

@Controller
@RequestMapping("/book")
public class NoteBookController {
	@Resource
	private NoteBookService bookService;

	@RequestMapping("/findNoteBook.do")
	@ResponseBody
	public NoteResult findNoteBook(String cn_user_id) {
//		String user_id = (String) session.getAttribute("user_id");
//		System.out.println(cn_user_id);
		NoteResult nr=bookService.findNoteBook(cn_user_id);	
		return nr;
	}
	@RequestMapping("/createNoteBook.do")
	@ResponseBody
	public NoteResult createNoteBook(String userId,String bookname){
		NoteResult nr=bookService.createNoteBook(userId, bookname);
		return nr;		
	}
	@RequestMapping("/rename.do")
	@ResponseBody
	public NoteResult renameNoteBook(String bookId,String bookname){
		NoteResult nr=bookService.renameNoteBook(bookId, bookname);
		return nr;
	}
	@RequestMapping("/deleteBook.do")
	@ResponseBody
	public NoteResult deleteBook(String bookId){
		NoteResult nr=bookService.deleteBook(bookId);
		return nr;
	}
}
