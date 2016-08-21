package cloudnote.dao;

import java.util.List;
import java.util.Map;

import cloudnote.entity.Pro;

public interface ProDao {
	public List<Pro> findProvince(Map map);
}
