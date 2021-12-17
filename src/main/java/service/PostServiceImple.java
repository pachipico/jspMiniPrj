package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.PostVO;
import repository.PostDAO;
import repository.PostDAOImple;

public class PostServiceImple implements PostService {
	private static Logger log = LoggerFactory.getLogger(PostServiceImple.class);
	private PostDAO pdao;
	
	public PostServiceImple() {
		pdao = new PostDAOImple();
	}

	@Override
	public int register(PostVO pvo) {
		return pdao.insert(pvo);
	}

	@Override
	public List<PostVO> getList() {
//		List<PostVO> list = pdao.selectList();
//		List<PostVO> result = null;
//		for (PostVO pvo : list) {
//			
//		}
		return pdao.selectList();
	}

	@Override
	public PostVO getDetail(long postId) {
		return pdao.selectOne(postId);
	}
	
	@Override
	public PostVO getDetailAndUp(long postId) {
		pdao.updateReadcount(postId);
		return pdao.selectOne(postId);
	}

	@Override
	public int modify(PostVO pvo) {
		return pdao.update(pvo);
	}

	@Override
	public int remove(long postId) {
		return pdao.delete(postId);
	}

}
