package service;

import java.util.List;

import domain.LikeVO;

public interface LikeService {
	public int like(LikeVO lvo);
	public List<LikeVO> getList(String email);
	public int unLike(LikeVO lvo);
	
}
