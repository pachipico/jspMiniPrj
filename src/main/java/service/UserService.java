package service;

import java.util.List;

import domain.UserVO;

public interface UserService {
	public int register(UserVO uvo);
	public List<UserVO> getList();
	public UserVO getDetail(String email);
	public int modify(UserVO uvo);
	public int remove(String email);
	public UserVO logIn(UserVO uvo);
	public void follow(String from, String to);
	public int unFollow(String from, String to);
	public List<UserVO> getFollowerList(String email);
	public List<UserVO> getFollowingList(String email);
}
