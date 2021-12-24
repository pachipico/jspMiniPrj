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

	}

	@Override
	public int insert(CommentVO cvo) {
		sql = DataBaseBuilder.getFactory().openSession();
		int isUp = sql.insert(ns + "reg", cvo);
		if (isUp > 0) {
			sql.commit();
		}
		sql.close();
		return isUp;
	}

	@Override
	public List<CommentVO> selectList(long postId) {
		sql = DataBaseBuilder.getFactory().openSession();
		List<CommentVO> list = sql.selectList(ns + "list", postId);
		sql.close();
		return list;
	}

	@Override
	public int update(CommentVO cvo) {
		sql = DataBaseBuilder.getFactory().openSession();
		int isUp = sql.update(ns + "mod", cvo);
		if (isUp > 0) {
			sql.commit();
		}
		sql.close();
		return isUp;
	}

	@Override
	public int delete(long cmtId) {
		sql = DataBaseBuilder.getFactory().openSession();
		int isUp = sql.delete(ns + "del", cmtId);
		if (isUp > 0) {
			sql.commit();
		}
		sql.close();
		return isUp;
	}

	@Override
	public int deleteAll(String writer) {
		try {
			SqlSession sql = DataBaseBuilder.getFactory().openSession();
			int isUp = sql.delete(ns + "delAll", writer);
			if (isUp > 0) {
				sql.commit();
			}
			sql.close();
			return isUp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
