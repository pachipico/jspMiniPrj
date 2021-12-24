package repository;

import java.util.List;

import domain.PostVO;

public interface PostDAO {
	public int insert(PostVO pvo);
	public List<PostVO> selectList(int page);
	public PostVO selectOne(long postId);
	public List<PostVO> selectList(String writer);
	public List<PostVO> selectList(List<String> likedList);
	public int selectCnt();
	public int update(PostVO pvo);
	public int delete(long postId);
	public int updateReadcount(long postId);
}
