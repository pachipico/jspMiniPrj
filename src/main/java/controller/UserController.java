package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api.KakaoAPI;
import domain.UserVO;
import net.coobird.thumbnailator.Thumbnails;
import service.UserService;
import service.UserServiceImple;

@WebServlet("/userCtrl/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	private final UserService usv;
	private RequestDispatcher rdp;
	private int isUp;
	private String UTF8 = "utf-8";
	private UserVO uvo;
	private KakaoAPI kakaoApi = new KakaoAPI();

	public UserController() {
		usv = new UserServiceImple();
	}

	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding(UTF8);
		res.setCharacterEncoding(UTF8);
		res.setContentType("text/html; charset=utf-8");

		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/") + 1);
		log.info("userCtrl");
		// 세션에 유저가 없으면 로그인페이지로
		switch (path) {
		case "kakaologin":
			log.info(req.getParameter("code"));
			String accessToken = kakaoApi.getAccessToken(req.getParameter("code"));
			HashMap<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);

			log.info("login_info : {}", userInfo.toString());

			if (userInfo.get("email") == null) {
				req.setAttribute("msg_kakao_login", 0);
				req.getRequestDispatcher("/index.jsp").forward(req, res);
				break;
			}
			log.info(userInfo.get("email").toString());

			HttpSession kSes = req.getSession();
//			kSes.setAttribute("kEmail", userInfo.get("email")); 
			kSes.setAttribute("access_token", accessToken);
			req.setAttribute("email", userInfo.get("email"));
			req.getRequestDispatcher("/user/restregister.jsp").forward(req, res);

//			kakaoApi.kakaoLogout(accessToken);
//			kakaoApi.kakaoUnlink(accessToken);
			break;
		case "kakaologout":

			break;
		case "register":
			// 회원가입 페이지로
			req.getRequestDispatcher("/user/register.jsp").forward(req, res);
			break;
		case "insert":
			// 회원가입
			// 이후 로그인페이지로
			isUp = usv.register(new UserVO(req.getParameter("email"), Integer.parseInt(req.getParameter("age")),
					req.getParameter("name"), req.getParameter("pwd"),
					Boolean.parseBoolean(req.getParameter("isAdmin")), req.getParameter("nickName")));
			log.info(">> register {}", isUp > 0 ? "Success" : "Fail");
			req.getRequestDispatcher("/index.jsp").forward(req, res);
			break;
		case "insertwithkakao":
			// 회원가입
			// 이후 로그인페이지로
			isUp = usv.register(new UserVO(req.getParameter("email"), Integer.parseInt(req.getParameter("age")),
					req.getParameter("name"), req.getParameter("pwd"),
					Boolean.parseBoolean(req.getParameter("isAdmin")), req.getParameter("nickName")));
			log.info(">> register {}", isUp > 0 ? "Success" : "Fail");
			req.getRequestDispatcher(
					"/userCtrl/login?email=" + req.getParameter(path) + "$pwd=" + req.getParameter("pwd"))
					.forward(req, res);
			break;
		case "list":
			// 관리자용 유저관리 리스트
			break;
		case "profile":
			// 회원 페이지로
			uvo = usv.getDetail(req.getParameter("email"));
			// follower와 following 각각 객체로 만들어서 넘겨주기
			List<String> follower = new ArrayList<>();
			List<String> following = new ArrayList<>();
			
			if (uvo.getFollower() != null && uvo.getFollower().length() != 0) {
				for (String folwer : uvo.getFollower().split(",")) {
					follower.add(folwer);
				}
			}
			if (uvo.getFollowing() != null && uvo.getFollowing().length() != 0) {
				for (String foling : uvo.getFollowing().split(",")) {
					following.add(foling);
				}
			}
			log.info(Integer.toString(following.size()));
			req.setAttribute("follower", follower);
			req.setAttribute("following", following);
			req.setAttribute("uvo", usv.getDetail(req.getParameter("email")));
			req.getRequestDispatcher("/user/profile.jsp").forward(req, res);
			break;
		case "detail":
			// 회원정보 수정을 위한 페이지로
			uvo = usv.getDetail(req.getParameter("email"));

			req.setAttribute("uvo", usv.getDetail(req.getParameter("email")));
			req.getRequestDispatcher("/user/detail.jsp").forward(req, res);
			break;
		case "modify":
			// 회원정보 수정
			// 이후 디테일 페이지로
			isUp = usv.modify(new UserVO(req.getParameter("email"), Integer.parseInt(req.getParameter("age")),
					req.getParameter("name"), Boolean.parseBoolean(req.getParameter("isAdmin")),
					req.getParameter("nickName")));
			log.info(">> modify {}", isUp > 0 ? "Success" : "Fail");
			req.getRequestDispatcher("/userCtrl/detail?email=" + req.getParameter("email"));
			break;
		case "changePwd":
			// 비밀번호 수정 페이지로
			req.setAttribute("email", req.getParameter("email"));
			req.getRequestDispatcher("/user/changePwd.jsp").forward(req, res);
			break;
		case "checkPwd":
			// 비밀번호 수정 페이지로
			try {
				StringBuffer sb = new StringBuffer();
				String line = null;
				BufferedReader br = req.getReader();
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				log.info(">>> sb {}", sb.toString());
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());
				uvo = usv.logIn(new UserVO(jsonObj.get("email").toString(), jsonObj.get("pwd").toString()));
				PrintWriter out = res.getWriter();
				if (uvo == null) {
					out.print(0);
				} else {
					out.print(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "modifyPwd":
			// 비밀번호 수정
			// 이후 로그아웃시키고 로그인페이지로
			isUp = usv.modifyPwd(new UserVO(req.getParameter("email"), req.getParameter("newPwd"), true));
			log.info(">>> pwd change > {}", isUp > 0 ? "Success" : "Fail");
			res.sendRedirect("/userCtrl/logout");
			/* req.getRequestDispatcher("/userCtrl/logout").forward(req, res); */
			break;
		case "changeAvatar":
			// 프로필사진 수정 페이지로
			req.setAttribute("email", req.getParameter("email"));
			req.getRequestDispatcher("/user/changeAvatar.jsp").forward(req, res);
			break;
		case "modifyAvatar":
			// 프로필사진 수정
			// 이후 로그아웃시키고 로그인페이지로
			uvo = new UserVO();
			String oldAvatar = "";
			try {
				String savePath = getServletContext().getRealPath("/_fileUpload");
				File fileDir = new File(savePath);

				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}

				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);
				fileItemFactory.setSizeThreshold(1 * 1024 * 1024);

				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);

				List<FileItem> itemList = fileUpload.parseRequest(req);
				for (FileItem item : itemList) {
					switch (item.getFieldName()) {
					case "email":
						uvo.setEmail(item.getString(UTF8));
						break;
					case "avatar":
						oldAvatar = item.getString(UTF8);

						break;
					case "profileImage":
						if (item.getSize() > 0) {
							String fileName = System.currentTimeMillis() + "-"
									+ item.getName().substring(item.getName().lastIndexOf(File.separator) + 1);
							File uploadFilePath = new File(fileDir + File.separator + fileName);
							try {
								item.write(uploadFilePath);
								uvo.setAvatar(fileName);
								Thumbnails.of(uploadFilePath).size(75, 75)
										.toFile(new File(fileDir + File.separator + "th_" + fileName));
							} catch (Exception e) {
								log.info(">>> File Write > Fail");
								e.printStackTrace();
							}
						}
						break;

					default:
						break;
					}
				}
				log.info(oldAvatar);
				if (uvo.getAvatar().length() > 0 && oldAvatar != "") {
					File removeFile = new File(fileDir + File.separator + oldAvatar);
					File removeFileThumb = new File(fileDir + File.separator + "th_" + oldAvatar);
					boolean rm = true;
					if (removeFile.exists()) {
						rm = removeFile.delete();
						if (rm) {
							rm = removeFileThumb.delete();
						}
					}
					log.info(">>> profileImg delete {}", rm ? "Success" : "Fail");
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}

			log.info(">>> uvo > {}", uvo);
			isUp = usv.modifyAvatar(uvo);
			req.getRequestDispatcher("/userCtrl/profile?email=" + uvo.getEmail()).forward(req, res);
			break;
		case "follow":
			// 비동기방식
			log.info("follow from : " + req.getParameter("from"));
			log.info("follow to : " + req.getParameter("to"));
			usv.follow(req.getParameter("from"), req.getParameter("to"));
			req.getRequestDispatcher("/userCtrl/profile?email=" + req.getParameter("to")).forward(req, res);
			break;
		case "unfollow":
			// 비동기방식
			log.info("unfollow from : " + req.getParameter("from"));
			log.info("unfollow to : " + req.getParameter("to"));
			usv.unFollow(req.getParameter("from"), req.getParameter("to"));
			req.getRequestDispatcher("/userCtrl/profile?email=" + req.getParameter("to")).forward(req, res);
			break;
		case "checkEmail": // 수정 중
			try {
				StringBuffer sb = new StringBuffer();
				String line = null;
				BufferedReader br = req.getReader();
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				log.info(">>> sb {}", sb.toString());
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());
//				isUp = usv.checkEmail(jsonObj.get("email").toString());
				PrintWriter out = res.getWriter();
				if (isUp == 0) {
					out.print(0);
				} else {
					out.print(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "login":
			System.out.println("asfnsk");
			uvo = usv.logIn(new UserVO(req.getParameter("email"), req.getParameter("pwd")));
			if (uvo != null) {
				HttpSession ses = req.getSession();
				ses.setAttribute("ses", uvo);
				ses.setMaxInactiveInterval(60 * 20);
			} else {
				req.setAttribute("msg_u_login", 0);
				req.getRequestDispatcher("/").forward(req, res);
				break;
			}
			req.getRequestDispatcher("/postCtrl/list").forward(req, res);
			break;
		case "logout":
			// 로그아웃
			// 인덱스 페이지로
			HttpSession currSes = req.getSession();
			currSes.invalidate();
			res.sendRedirect("/index.jsp");
			/*
			 * req.setAttribute("msg_u_logout", 1);
			 * req.getRequestDispatcher("/index.jsp").forward(req, res);
			 */
			break;
		case "remove":
			// 회원정보 삭제
			// 이후 로그아웃시키고 인덱스페이지로
			isUp = usv.remove(req.getParameter("email"));
			log.info(">>> Remove > {}", isUp > 0 ? "Success" : "Fail");
			HttpSession currSES = req.getSession();
			currSES.invalidate();
			res.sendRedirect("/index.jsp");
			break;

		default:
			break;
		}
	}
}
