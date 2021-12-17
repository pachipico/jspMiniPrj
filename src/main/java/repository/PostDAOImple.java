package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.PostVO;
import orm.DataBaseBuilder;

public class PostDAOImple implements PostDAO {
	private static Logger log = LoggerFactory.getLogger(PostDAOImple.class);
	private SqlSession sql;
	private final String ns = "PostMapper.";
	
	public PostDAOImple() {
		new DataBaseBuilder();
		sql = DataBaseBuilder.getFactory().openSession();
	}
	
	@Override
	public int insert(PostVO pvo) {
		int isUp = sql.insert(ns+"reg", pvo);
		if (isUp > 0 ) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public List<PostVO> selectList() {
		return sql.selectList(ns+"list");
	}

	@Override
	public PostVO selectOne(long postId) {
		return sql.selectOne(ns+"one", postId);
	}

	@Override
	public int update(PostVO pvo) {
		int isUp = sql.update(ns+"mod", pvo);
		if (isUp > 0 ) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public int delete(long postId) {
		int isUp = sql.delete(ns+"del", postId);
		if (isUp > 0 ) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public int updateReadcount(long postId) {
		int isUp = sql.update(ns+"addCnt", postId);
		if (isUp > 0 ) {
			sql.commit();
		}
		return isUp;
	}

}
