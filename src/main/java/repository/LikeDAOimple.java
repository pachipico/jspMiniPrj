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
		sql = DataBaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(LikeVO lvo) {
		int isUp = sql.insert(ns + "like", lvo);
		if (isUp > 0) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public List<LikeVO> selectList(String email) {

		return sql.selectList(ns + "list", email);
	}

	@Override
	public int delete(LikeVO lvo) {
		int isUp = sql.delete(ns + "unlike", lvo);
		if (isUp > 0) {
			sql.commit();
		}
		return isUp;
	}
}
