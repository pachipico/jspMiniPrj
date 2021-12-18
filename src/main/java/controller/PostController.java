package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import domain.PostVO;
import domain.UserVO;
import service.CommentService;
import service.CommentServiceImple;
import service.PostService;
import service.PostServiceImple;
import service.UserService;
import service.UserServiceImple;

@WebServlet("/postCtrl/*")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(PostController.class);
	private final PostService psv;
	private RequestDispatcher rdp;
	private int isUp;
	private String UTF8 = "utf-8";

	public PostController() {
		psv = new PostServiceImple();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding(UTF8);
		res.setCharacterEncoding(UTF8);
		res.setContentType("text/html; charset=utf-8");

		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/") + 1);

		PostService psv = new PostServiceImple();
		UserService usv = new UserServiceImple();
		CommentService csv = new CommentServiceImple();
		log.info("path : {}",path);
		switch (path) {

		case "insert":
			// 게시물 업로드
			// 이후 리스트 페이지로
			psv.register(
					new PostVO(req.getParameter("writer"), req.getParameter("files"), req.getParameter("content")));
			req.getRequestDispatcher("/postCtrl/list").forward(req, res);
			break;
		case "list":
			// 일단은 최신 순서대로 보여주고 구현이 완료되면 팔로우한 계정의 게시물을 보여줄 예정
			// 세션이 있다면 여기가 홈 화면이 됨.
			int limit = 5;
			if(req.getParameter("limit") != null && !req.getParameter("limit").equals("")) {
				limit = Integer.parseInt(req.getParameter("limit"));
			}
			HttpSession session = req.getSession();
			log.info("count : {}",psv.getCnt());
			List<PostVO> postList = psv.getList(limit);

			List<UserVO> followingList = usv.getFollowingList("abc@abc.com");// 세션 이메일 넣을것
			req.setAttribute("cnt", psv.getCnt());
			req.setAttribute("limit", limit);
			req.setAttribute("postList", postList);
			req.setAttribute("followingList", followingList);
			int n  =0;
			for (PostVO post : postList) {
				req.setAttribute("cmt"+post.getPostId(), csv.getList(post.getPostId()));
			}
			req.getRequestDispatcher("/post/list.jsp").forward(req, res);

			break;
		case "listall":
			// 인기가 많은 게시물들
			break;
		case "mylist":
			// 내 게시물
			// 내 게시물의 기본적인 정보(ex. email, nickname, avatar..)는 세션에서 가지고 있을 예정
			break;
		case "detail":
			// 게시물의 디테일로 이동
			Long pid = Long.parseLong(req.getParameter("pid"));
			req.setAttribute("pvo", psv.getDetailAndUp(pid));
			req.setAttribute("cvoList", csv.getList(pid));
			req.getRequestDispatcher("/post/detail.jsp").forward(req, res);
			break;
		case "modify":
			// 게시물 수정 페이지로
			break;
		case "update":
			// 게시물 수정
			// 이후 내 게시물로 이동
			break;
		case "remove":
			// 게시물 삭제
			// 이후 내 게시물로 이동
			psv.remove(Long.parseLong(req.getParameter("pid")));
			req.getRequestDispatcher("/postCtrl/list");
			break;
		case "like":
			// 좋아요 표시
			// 비동기
			break;
		case "unlike":
			// 좋아요 없애기
			// 비동기
			break;

		default:
			break;
		}
	}

}
