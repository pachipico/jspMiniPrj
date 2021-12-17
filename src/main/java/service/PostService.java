package service;

import java.util.List;

import domain.PostVO;

public interface PostService {
	public int register(PostVO pvo);
	public List<PostVO> getList();
	public PostVO getDetail(long postId);
	public PostVO getDetailAndUp(long postId);
	public int modify(PostVO pvo);
	public int remove(long postId);
}
