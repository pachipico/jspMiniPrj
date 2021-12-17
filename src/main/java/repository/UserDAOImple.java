package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.UserVO;
import orm.DataBaseBuilder;

public class UserDAOImple implements UserDAO {
	private static Logger log = LoggerFactory.getLogger(UserDAOImple.class);
	private SqlSession sql;
	private final String ns = "UserMapper.";

	public UserDAOImple() {
		new DataBaseBuilder();
		sql = DataBaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(UserVO uvo) {
		int isUp = sql.insert(ns + "reg", uvo);
		if (isUp > 0) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public List<UserVO> selectList() {
		return sql.selectList(ns + "list");
	}

	@Override
	public UserVO selectOne(String email) {
		return sql.selectOne(ns + "one", email);
	}

	@Override
	public int update(UserVO uvo) {
		int isUp = sql.update(ns + "mod", uvo);
		if (isUp > 0) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public int delete(String email) {
		int isUp = sql.delete(ns + "del", email);
		if (isUp > 0) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public UserVO selectOne(UserVO uvo) {
		return sql.selectOne(ns + "login", uvo);
	}

	@Override
	public HashMap<String, String> selectFollow(String email) {

		return sql.selectOne(ns + "getFollow", email);
	}

	@Override
	public String selectFollowerCSV(String email) {

		return sql.selectOne(ns + "getFollowerCSV", email);
	}

	@Override
	public String selectFollowingCSV(String email) {
		return sql.selectOne(ns + "getFollowingCSV", email);
	}

	@Override
	public int setFollow(String followerList, String followingList, String email) {
		int isUp = 0;
		Map<String, String> map = new HashMap<>();
		map.put("follower", followerList);
		map.put("following", followingList);
		map.put("email", email);
		isUp = sql.update(ns + "setFollow", map);
		if (isUp > 0) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public List<UserVO> selectListByEmail(ArrayList<String> csv) {
		System.out.println(csv);
		return sql.selectList(ns + "selectListByEmail", csv);
	}

}
