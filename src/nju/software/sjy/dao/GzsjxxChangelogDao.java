package nju.software.sjy.dao;

import java.util.List;

import nju.software.sjy.model.xy.TGzsjChangelog;
import nju.software.sjy.model.xy.TGzsjxx;

public interface GzsjxxChangelogDao {
	void save(TGzsjChangelog changelog);
	void update(TGzsjChangelog changelog);
	void delete(TGzsjChangelog changelog);
	Integer getMaxBh();
	List<TGzsjChangelog> getGzsjxxChangelogByGzsjxx(TGzsjxx gzsjxx);
}
