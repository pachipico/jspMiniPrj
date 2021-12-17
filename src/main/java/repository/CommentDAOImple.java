package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import orm.DataBaseBuilder;


public class CommentDAOImple implements CommentDAO {
	private static Logger log = LoggerFactory.getLogger(CommentDAOImple.class);
	private SqlSession sql;
	private final String ns = "CommentMapper.";
	
	public CommentDAOImple() {
		new DataBaseBuilder();
		sql = DataBaseBuilder.getFactory().openSession();
	}
	
	@Override
	public int insert(CommentVO cvo) {
		int isUp = sql.insert(ns+"reg", cvo);
		if (isUp > 0) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public List<CommentVO> selectList(long cmtId) {
		return sql.selectList(ns+"list");
	}

	@Override
	public int update(CommentVO cvo) {
		int isUp = sql.update(ns+"mod", cvo);
		if (isUp > 0) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public int delete(long cmtId) {
		int isUp = sql.delete(ns+"del", cmtId);
		if (isUp > 0) {
			sql.commit();
		}
		return isUp;
	}
}
