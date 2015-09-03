package nju.software.sjy.service;

import java.util.List;

import nju.software.sjy.model.xy.TGzsjChangelog;
import nju.software.sjy.model.xy.TGzsjxx;

public interface GzsjChangelogService {
	void save(TGzsjChangelog changelog);
	void update(TGzsjChangelog changelog);
	void delete(TGzsjChangelog changelog);
	Integer getMaxBh();
	List<TGzsjChangelog> getGzsjxxChangelogByGzsjxx(TGzsjxx gzsjxx);

}
