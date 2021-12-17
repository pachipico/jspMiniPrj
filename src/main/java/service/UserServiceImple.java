package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import domain.UserVO;
import repository.UserDAO;
import repository.UserDAOImple;

public class UserServiceImple implements UserService {
	private UserDAO udao;

	public UserServiceImple() {
		udao = new UserDAOImple();
	}

	@Override
	public int register(UserVO uvo) {
		return udao.insert(uvo);
	}

	@Override
	public List<UserVO> getList() {
		return udao.selectList();
	}

	@Override
	public UserVO getDetail(String email) {
		return udao.selectOne(email);
	}

	@Override
	public int modify(UserVO uvo) {
		return udao.update(uvo);
	}

	@Override
	public int remove(String email) {
		return udao.delete(email);
	}

	@Override
	public UserVO logIn(UserVO uvo) {
		return udao.selectOne(uvo);
	}

	@Override
	public void follow(String from, String to) {
		Map<String, String> fromMap = udao.selectFollow(from);
		Map<String, String> toMap = udao.selectFollow(to);
		String fromFollowing = (String) fromMap.get("following");
		List<String> fromFollowingList = new ArrayList<>(Arrays.asList(fromFollowing.split(",")));
		String toFollower = (String) toMap.get("follower");
		List<String> toFollowerList = new ArrayList<>(Arrays.asList(toFollower.split(",")));
		System.out.println("asdf");
		if (!fromFollowingList.contains(to)) {
			fromFollowing += to + ",";
		}
		if (!toFollowerList.contains(from)) {
			toFollower += from + ",";
		}

		udao.setFollow((String) fromMap.get("follower"), fromFollowing, from);
		udao.setFollow(toFollower, (String) toMap.get("following"), to);
	}

	@Override
	public int unFollow(String from, String to) {
		Map<String, String> fromMap = udao.selectFollow(from);
		Map<String, String> toMap = udao.selectFollow(to);
		String fromFollowing = (String) fromMap.get("following");
		List<String> fromFollowingList = new ArrayList<>(Arrays.asList(fromFollowing.split(",")));
		String toFollower = (String) toMap.get("follower");
		List<String> toFollowerList = new ArrayList<>(Arrays.asList(toFollower.split(",")));

		fromFollowingList.remove(to);
		toFollowerList.remove(from);
		fromFollowing = "";
		toFollower = "";
		for (String string : fromFollowingList) {
			fromFollowing += string + ",";

		}
		for (String string : toFollowerList) {
			toFollower += string + ",";
		}

		udao.setFollow((String) fromMap.get("follower"), fromFollowing, from);
		udao.setFollow(toFollower, (String) toMap.get("following"), to);
		return 0;
	}

	@Override
	public List<UserVO> getFollowerList(String email) {
		ArrayList<String> arr = new ArrayList<>(Arrays.asList(udao.selectFollowerCSV(email).split(",")));
	
		return udao.selectListByEmail(arr);
	}

	@Override
	public List<UserVO> getFollowingList(String email) {
		ArrayList<String> arr = new ArrayList<>(Arrays.asList(udao.selectFollowingCSV(email).split(",")));
		System.out.println(arr);
		return udao.selectListByEmail(arr);
	}

}
