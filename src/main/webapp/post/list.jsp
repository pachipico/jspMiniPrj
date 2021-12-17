<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>Title</title>
<!-- Latest compiled and minified CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" href="../css/reset.css">
<link rel="stylesheet" href="../css/common.css">
<link rel="stylesheet" href="../css/style.css">


</head>
<body>

	<section id="container">

		<header id="header">
			<section class="inner">

				<h1 class="logo">
					<a href="index.html">
						<div class="sprite_insta_icon"></div>
						<div class="sprite_write_logo"></div>
					</a>
				</h1>

				<div class="search_box">
					<input type="text" placeholder="검색" id="search-field">

					<div class="fake_field">
						<span class="sprite_small_search_icon"></span> <span>검색</span>
					</div>
				</div>

				<div class="right_icons">
					<a href="" data-bs-toggle="modal" data-bs-target="#regModal"><div
							class="sprite_camera_icon"></div></a> <a href="login.html"><div
							class="sprite_compass_icon"></div></a> <a href="follow.html"><div
							class="sprite_heart_icon_outline"></div></a> <a href="profile.html"><div
							class="sprite_user_icon_outline"></div></a>
				</div>

			</section>

		</header>

		<div class="hidden_menu">
			<div class="scroll_inner">
				<div class="user">
					<div class="thumb_img">
						<img src="imgs/thumb.jpeg" alt="프로필사진">
					</div>
					<div class="id">asdfasdf</div>
				</div>

				<div class="user">
					<div class="thumb_img">
						<img src="imgs/thumb.jpeg" alt="프로필사진">
					</div>
					<div class="id">kindtigerrr</div>
				</div>
				<div class="user">
					<div class="thumb_img">
						<img src="imgs/thumb.jpeg" alt="프로필사진">
					</div>
					<div class="id">kindtigerrr</div>
				</div>
				<div class="user">
					<div class="thumb_img">
						<img src="imgs/thumb.jpeg" alt="프로필사진">
					</div>
					<div class="id">kindtigerrr</div>
				</div>
				<div class="user">
					<div class="thumb_img">
						<img src="imgs/thumb.jpeg" alt="프로필사진">
					</div>
					<div class="id">kindtigerrr</div>
				</div>
				<div class="user">
					<div class="thumb_img">
						<img src="imgs/thumb.jpeg" alt="프로필사진">
					</div>
					<div class="id">kindtigerrr</div>
				</div>
				<div class="user">
					<div class="thumb_img">
						<img src="imgs/thumb.jpeg" alt="프로필사진">
					</div>
					<div class="id">kindtigerrr</div>
				</div>
				<div class="user">
					<div class="thumb_img">
						<img src="imgs/thumb.jpeg" alt="프로필사진">
					</div>
					<div class="id">kindtigerrr</div>
				</div>
				<div class="user">
					<div class="thumb_img">
						<img src="imgs/thumb.jpeg" alt="프로필사진">
					</div>
					<div class="id">kindtigerrr</div>
				</div>
				<div class="user">
					<div class="thumb_img">
						<img src="imgs/thumb.jpeg" alt="프로필사진">
					</div>
					<div class="id">kindtigerrr</div>
				</div>

			</div>
		</div>


		<section id="main_container">
			<div class="inner">

				<!-- 게시물 반복 시작 -->
				<c:set value="0" var="pid" />
				<c:forEach items="${postList }" var="post">
					<c:if test="${pid < post.postId}">
						<div class="contents_box">
							<article class="contents">
								<header class="top">
									<div class="user_container">
										<div class="profile_img">
											<img src="../imgs/thumb.jpeg" alt="프로필이미지">
										</div>
										<div class="user_name">
											<div class="nick_name m_text">${post.writer }</div>
											<div class="country s_text">Seoul, South Korea</div>
										</div>

									</div>

									<div class="sprite_more_icon" data-name="more" id="toggle_more">
										<ul class="toggle_box">
											<li><input type="submit" class="follow" value="팔로우"
												data-name="follow"></li>
											<li>수정</li>
											<li>삭제</li>
										</ul>
									</div>
								</header>

								<div class="img_section">
									<div class="trans_inner">
										<div>
											<a href="/postCtrl/detail?pid=${post.postId }" id="detailToggle" data-pid="${post.postId }" >
											<img src="../imgs/img_section/img01.jpg" alt="visual01">
											</a>
										</div>
									</div>
								</div>

								<div class="bottom_icons">
									<div class="left_icons">
										<div class="heart_btn">
											<a href="/postCtrl/like"  id="likeBtn">
											<div class="sprite_heart_icon_outline" name="39"
												data-name="heartbeat"></div>
												</a>
										</div>
										<div class="sprite_bubble_icon"></div>
										<div class="sprite_share_icon" data-name="share"></div>
									</div>
									<div class="right_icon">
										<div class="sprite_bookmark_outline" data-name="bookmark"></div>
									</div>
								</div>

								<div class="likes m_text">
									좋아요 <span id="like-count-39">${post.likeCnt}</span> <span
										id="bookmark-count-39"></span> 개
								</div>
								<c:forEach items="${postList }" var="p" varStatus="st" >
									<c:if test="${p.postId == post.postId}">
										<div class="comment_container">
											<div class="comment" id="comment-list-ajax-post37">
												<div class="comment-detail">
													<div class="nick_name m_text">${p.cmtWriter }</div>
													<div>${p.cmtContent }</div>
													<div>count${st.count }</div>
													<div>pid${pid}</div>
												</div>
											</div>
											<div class="small_heart">
												<div class="sprite_small_heart_icon_outline"></div>
											</div>
										</div>
									</c:if>
								</c:forEach>
								<div class="timer">${post.modAt}</div>

								<div class="comment_field" id="add-comment-post37">
									<input type="text" placeholder="댓글달기...">
									<div class="upload_btn m_text" data-name="comment">게시</div>
								</div>
							</article>
						</div>
						<c:set var="pid" value="${post.postId }" />
					</c:if>
				</c:forEach>
				<!-- 게시물 반복 끝 -->

				<!-- register모달 -->
				<div class="modal" id="regModal">
					<div class="modal-dialog">
						<div class="modal-content">

							<!-- Modal Header -->
							<div class="modal-header">
								<h4 class="modal-title">새 게시물 만들기</h4>
								<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
							</div>

							<!-- Modal body -->
							<div class="modal-body">
								<form action="/postCtrl/insert" method="post">
									<input type="text" name="content"> 
									<input type="text"
										name="writer" value="123@123.com">
									<button type="submit" >submit</button>
								</form>

							</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="button" class="btn btn-danger"
									data-bs-dismiss="modal">Close</button>
							</div>

						</div>
					</div>
				</div>
				<!-- detail모달  -->
				<div class="modal" id="detailModal">
					<div class="modal-dialog modal-fullscreen" style="padding:40px;">
						<div class="modal-content">

							<!-- Modal Header -->
							<div class="modal-header">
								<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
							</div>

							<!-- Modal body -->
							<div class="modal-body">
							<div> image</div>
							<div>
							<div id="writerDiv">작성자</div>
							<div id="contentDiv">본문내용</div>
							<div>댓글</div>
							</div>

							</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="button" class="btn btn-danger"
									data-bs-dismiss="modal">Close</button>
							</div>

						</div>
					</div>
				</div>
				<!--  -->
				<input type="hidden" id="page" value="1">

				<div class="side_box">
					<div class="user_profile">
						<div class="profile_thumb">
							<img src="../imgs/thumb.jpeg" alt="프로필사진">
						</div>
						<div class="detail">
							<div class="id m_text">KindTiger</div>
							<div class="ko_name">심선범</div>
						</div>
					</div>

					<article class="story">
						<header class="story_header">
							<div>스토리</div>
							<div class="more">모두 보기</div>
						</header>

						<div class="scroll_inner">

							<c:forEach items="${followingList }" var="following">
								<div class="thumb_user">
									<div class="profile_thumb">
										<img src="../imgs/thumb02.jpg" alt="프로필사진">
									</div>
									<div class="detail">
										<div class="id">${following.nickname}</div>
										<div class="time">1시간 전</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</article>

					<article class="recommend">
						<header class="reco_header">
							<div>회원님을 위한 추천</div>
							<div class="more">모두 보기</div>
						</header>

						<div class="thumb_user">
							<div class="profile_thumb">
								<img src="../imgs/thumb02.jpg" alt="프로필사진">
							</div>
							<div class="detail">
								<div class="id">kind_tigerrrr</div>
								<div class="time">1시간 전</div>
							</div>
						</div>
						<div class="thumb_user">
							<div class="profile_thumb">
								<img src="../imgs/thumb02.jpg" alt="프로필사진">
							</div>
							<div class="detail">
								<div class="id">kind_tigerrrr</div>
								<div class="time">1시간 전</div>
							</div>
						</div>
					</article>
				</div>
			</div>
		</section>
	</section>

	<script src="../js/main.js"></script>
</body>
</html>