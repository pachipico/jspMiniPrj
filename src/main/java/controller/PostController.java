package controller;

import java.io.BufferedReader;
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

import domain.LikeVO;
import domain.PostVO;
import domain.UserVO;
import service.CommentService;
import service.CommentServiceImple;
import service.LikeService;
import service.LikeServiceImple;
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
		LikeService lsv = new LikeServiceImple();
		log.info("path : {}", path);
		switch (path) {

		case "insert":
			// 게시물 업로드
			// 이후 리스트 페이지로
			isUp = psv.register(
					new PostVO(req.getParameter("writer"), req.getParameter("files"), req.getParameter("content")));
			log.info("== insert {}", isUp>0 ? "success" : "fail");
			req.getRequestDispatcher("/postCtrl/list").forward(req, res);
			break;
		case "list":
			// 일단은 최신 순서대로 보여주고 구현이 완료되면 팔로우한 계정의 게시물을 보여줄 예정
			// 세션이 있다면 여기가 홈 화면이 됨.
			int limit = 5;
			if (req.getParameter("limit") != null && !req.getParameter("limit").equals("")) {
				limit = Integer.parseInt(req.getParameter("limit"));
			}
			HttpSession session = req.getSession();
			if(session.getAttribute("ses") == null) {
				res.sendRedirect("/userCtrl/login");
			}
			log.info("count : {}", psv.getCnt());
			List<PostVO> postList = psv.getList(limit);
			UserVO uvo = (UserVO) session.getAttribute("ses");
			
			
			List<LikeVO> likeList = lsv.getList(uvo.getEmail());
			List<UserVO> followingList = usv.getFollowingList(uvo.getEmail());// 세션 이메일 넣을것

			req.setAttribute("likeList", likeList);
			req.setAttribute("cnt", psv.getCnt());
			req.setAttribute("limit", limit);
			req.setAttribute("postList", postList);
			req.setAttribute("followingList", followingList);
			int n = 0;
			for (PostVO post : postList) {
				req.setAttribute("cmt" + post.getPostId(), csv.getList(post.getPostId()));
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
			// 게시물 수정을 위한 정보 받아오기
			PostVO pvo = psv.getDetail(Long.parseLong(req.getParameter("pid")));
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("content", pvo.getContent());
			jsonObject.put("pid", pvo.getPostId());
			PrintWriter out = res.getWriter();
			System.out.println(jsonObject);
			out.print(jsonObject);

			break;
		case "update":
			// 게시물 수정
			// 이후 내 게시물로 이동
			isUp = psv.modify(new PostVO(Long.parseLong(req.getParameter("pid")), null, req.getParameter("content")));
			log.info(">>> update {}", isUp > 0 ? "success" : "fail");
			res.sendRedirect("/postCtrl/list");
			break;
		case "remove":
			// 게시물 삭제
			// 이후 내 게시물로 이동
			psv.remove(Long.parseLong(req.getParameter("pid")));

			break;
		case "like":
			// 좋아요 표시
			// 비동기
			StringBuffer sb = new StringBuffer();
			String line = null;
			BufferedReader br = req.getReader();

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			JSONParser parser2 = new JSONParser();
			try {
				JSONObject jsonObj = (JSONObject) parser2.parse(sb.toString());
				isUp = lsv.like(new LikeVO((String) jsonObj.get("email"), Long.valueOf((String) jsonObj.get("pid"))));
				if(isUp > 0) {
					log.info("=== {} likes {}", jsonObj.get("email"), jsonObj.get("pid"));
				}
			} catch (ParseException e) {

				e.printStackTrace();
			}
			req.getRequestDispatcher("/postCtrl/list").forward(req, res);
			break;
		case "unlike":
			// 좋아요 없애기
			// 비동기
			StringBuffer sb2 = new StringBuffer();
			String line2 = null;
			BufferedReader br2 = req.getReader();

			while ((line = br2.readLine()) != null) {
				sb2.append(line);
			}
			JSONParser parser3 = new JSONParser();
			try {
				JSONObject jsonObj2 = (JSONObject) parser3.parse(sb2.toString());
				isUp = lsv.unLike(new LikeVO((String) jsonObj2.get("email"), Long.valueOf((String) jsonObj2.get("pid"))));
				if(isUp > 0) {
					log.info("=== {} unlikes {}", jsonObj2.get("email"), jsonObj2.get("pid"));
				}
			} catch (ParseException e) {

				e.printStackTrace();
			}
			req.getRequestDispatcher("/postCtrl/list").forward(req, res);
			break;

		default:
			break;
		}
	}

}
