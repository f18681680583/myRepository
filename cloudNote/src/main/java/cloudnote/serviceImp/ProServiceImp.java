package cloudnote.serviceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cloudnote.dao.ProDao;
import cloudnote.entity.Pro;
import cloudnote.service.ProService;
import cloudnote.util.NoteResult;
@Service
public class ProServiceImp implements ProService{
	@Resource
	private ProDao proDao;
	public NoteResult findProvince(double parent_id) {
		NoteResult nr=new NoteResult();
		Map<String,Double> map=new HashMap<String,Double>();
		map.put("parent_id", parent_id);
		List<Pro> list=proDao.findProvince(map);
		nr.setObj(list);
		return nr;
	}

}
