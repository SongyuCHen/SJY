package nju.software.sjy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import nju.software.sjy.dao.GzsjxxChangelogDao;
import nju.software.sjy.model.xy.TGzsjChangelog;
import nju.software.sjy.model.xy.TGzsjxx;
import nju.software.sjy.service.GzsjChangelogService;
@Service
public class GzsjChangelogServiceImpl implements GzsjChangelogService {

	@Autowired
	private GzsjxxChangelogDao changelogDao;
	@CacheEvict(value="sjyCache", allEntries=true)
	public void save(TGzsjChangelog changelog) {
		// TODO Auto-generated method stub
		changelogDao.save(changelog);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void update(TGzsjChangelog changelog) {
		// TODO Auto-generated method stub
		changelogDao.update(changelog);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void delete(TGzsjChangelog changelog) {
		// TODO Auto-generated method stub
		changelogDao.delete(changelog);
	}

	@Override
	public Integer getMaxBh() {
		// TODO Auto-generated method stub
		return changelogDao.getMaxBh();
	}

	@Cacheable(value="sjyCache")
	public List<TGzsjChangelog> getGzsjxxChangelogByGzsjxx(TGzsjxx gzsjxx) {
		// TODO Auto-generated method stub
		return changelogDao.getGzsjxxChangelogByGzsjxx(gzsjxx);
	}

}
