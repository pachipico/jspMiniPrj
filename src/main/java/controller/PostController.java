package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.PostService;
import service.PostServiceImple;

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
		
		switch (path) {
		case "register":
			// 게시물 업로드 화면으로
			break;
		case "insert":
			// 게시물 업로드
			// 이후 리스트 페이지로
			break;
		case "list":
			// 일단은 최신 순서대로 보여주고 구현이 완료되면 팔로우한 계정의 게시물을 보여줄 예정
			// 세션이 있다면 여기가 홈 화면이 됨.
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
