import com.google.gson.Gson;

import service.CommentService;
import service.CommentServiceImple;
import service.PostService;
import service.PostServiceImple;

public class Program {


	public static void main(String[] args) {
		PostService psv = new PostServiceImple();
		CommentService csv = new CommentServiceImple();
		
		Gson gson = new Gson();
		
		System.out.println(gson.toJson(psv.getList(10)));
		
	}
}
