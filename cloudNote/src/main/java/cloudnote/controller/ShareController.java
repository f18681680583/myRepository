package cloudnote.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cloudnote.service.ShareService;
import cloudnote.util.NoteResult;

@Controller
@RequestMapping("/share")
public class ShareController {
	@Resource
	private ShareService shareService;
	@RequestMapping("/shareNote.do")
	@ResponseBody
	public NoteResult shareNote(String noteId){
		NoteResult nr=shareService.shareNote(noteId);
		return nr;
	}
	@RequestMapping("/searchNote.do")
	@ResponseBody
	public NoteResult searchNote(String title,int page){
		return shareService.searchNote(title,page);
	}
	@RequestMapping("/findShareBody.do")
	@ResponseBody
	public NoteResult findShareBody(String shareId){
		return shareService.findShareBody(shareId);
	}
	@RequestMapping("/deleteShare.do")
	@ResponseBody
	public NoteResult deleteShare(String noteId){
		return shareService.deleteShare(noteId);
	}
}
