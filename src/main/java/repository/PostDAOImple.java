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
	private int isUp;

	public PostDAOImple() {
		new DataBaseBuilder();
	}

	@Override
	public int insert(PostVO pvo) {

		try {
			sql = DataBaseBuilder.getFactory().openSession();

			isUp = sql.insert(ns + "reg", pvo);
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

	@Override
	public List<PostVO> selectList(int page) {
		try {
			sql = DataBaseBuilder.getFactory().openSession();

			List<PostVO> list = sql.selectList(ns + "list", page);
			sql.close();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PostVO> selectList(String writer) {
		try {
			SqlSession sql = DataBaseBuilder.getFactory().openSession();
			List<PostVO> list = sql.selectList(ns+"profileList", writer);
			sql.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public PostVO selectOne(long postId) {
		try {
			sql = DataBaseBuilder.getFactory().openSession();
			PostVO post = sql.selectOne(ns + "one", postId);
			sql.close();
			return post;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int selectCnt() {
		try {
			sql = DataBaseBuilder.getFactory().openSession();
			int cnt = sql.selectOne(ns + "cnt");
			sql.close();
			return cnt;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(PostVO pvo) {
		try {
			sql = DataBaseBuilder.getFactory().openSession();
			isUp = sql.update(ns + "mod", pvo);
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

	@Override
	public int delete(long postId) {
		try {
			sql = DataBaseBuilder.getFactory().openSession();
			isUp = sql.delete(ns + "del", postId);
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

	@Override
	public int updateReadcount(long postId) {
		try {
			sql = DataBaseBuilder.getFactory().openSession();
			isUp = sql.update(ns + "addCnt", postId);
			if (isUp > 0) {
				sql.commit();
			}
			sql.close();
			return isUp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUp;
	}

}
