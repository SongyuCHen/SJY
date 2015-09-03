package nju.software.sjy.dao.tdh;

import java.util.Date;
import java.util.List;

import nju.software.sjy.model.tdh.EajJz;

public interface EajJzDao {
	public List<EajJz> getEajJzByAhdmAndZzsj(String ahdm, Date kssj, Date jssj);

}
