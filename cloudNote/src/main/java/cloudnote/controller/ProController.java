package cloudnote.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cloudnote.serviceImp.ProServiceImp;
import cloudnote.util.NoteResult;

@Controller
@RequestMapping("/info")
public class ProController {
	@Resource
	private ProServiceImp proService;
	@RequestMapping("/findProvince.do")
	@ResponseBody
	public NoteResult findProvince(Double parent_id){
//		double id=Double.parseDouble(parent_id);
//		System.out.println(id);
		NoteResult nr=proService.findProvince(parent_id);
		return nr;	
	}
}
