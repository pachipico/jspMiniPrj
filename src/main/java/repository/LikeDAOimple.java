package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.LikeVO;
import orm.DataBaseBuilder;

public class LikeDAOimple implements LikeDAO {

	private static final Logger log = LoggerFactory.getLogger(LikeDAOimple.class);
	private SqlSession sql;
	private final String ns = "LikeMapper.";

	public LikeDAOimple() {
		new DataBaseBuilder();
		
	}

	@Override
	public int insert(LikeVO lvo) {
		sql = DataBaseBuilder.getFactory().openSession();
		int isUp = sql.insert(ns + "like", lvo);
		if (isUp > 0) {
			sql.commit();
		}
		sql.close();
		return isUp;
	}


	
	@Override
	public List<LikeVO> selectList(String email) {
		sql = DataBaseBuilder.getFactory().openSession();
		List<LikeVO> like = sql.selectList(ns + "list", email);
		sql.close();
		return like;
	}

	@Override
	public int delete(LikeVO lvo) {
		sql = DataBaseBuilder.getFactory().openSession();
		int isUp = sql.delete(ns + "unlike", lvo);
		if (isUp > 0) {
			sql.commit();
		}
		sql.close();
		return isUp;
	}
}
