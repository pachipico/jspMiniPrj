package service;

import java.util.List;

import domain.PostVO;

public interface PostService {
	public int register(PostVO pvo);
	public List<PostVO> getList(int page);
	public List<PostVO> getListForProfile(String writer);
	public PostVO getDetail(long postId);
	public PostVO getDetailAndUp(long postId);
	public int getCnt();
	public int modify(PostVO pvo);
	public int remove(long postId);
}
