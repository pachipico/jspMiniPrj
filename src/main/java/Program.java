import java.util.ArrayList;
import java.util.List;

import domain.CommentVO;
import domain.PostVO;
import domain.UserVO;
import service.CommentService;
import service.CommentServiceImple;
import service.PostService;
import service.PostServiceImple;
import service.UserService;
import service.UserServiceImple;

public class Program {


	public static void main(String[] args) {
		UserService usv = new UserServiceImple();
		PostService psv = new PostServiceImple();
		CommentService csv = new CommentServiceImple();

		System.out.println(psv.getDetail(1));
	}
}
