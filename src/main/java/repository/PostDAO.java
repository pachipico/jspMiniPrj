package repository;

import java.util.List;

import domain.PostVO;

public interface PostDAO {
	public int insert(PostVO pvo);
	public List<PostVO> selectList();
	public PostVO selectOne(long postId);
	public int update(PostVO pvo);
	public int delete(long postId);
	public int updateReadcount(long postId);
}
