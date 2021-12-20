<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../header1.jsp" />
<link rel="stylesheet" href="../css/profile.css">
<jsp:include page="../header2.jsp" />

<div id="main_container">

	<section class="b_inner">

		<div class="hori_cont">
			<div class="profile_wrap">
				<div class="profile_img">
					<c:choose>
						<c:when test="${uvo.avatar ne '' && uvo.avatar ne null }">
							<img src="../_fileUpload/${uvo.avatar }">
						</c:when>
						<c:otherwise>
							<img src="../imgs/thumb.jpeg">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="detail">
				<div class="top">
					<div class="user_name">${uvo.nickname }</div>
					<c:choose>
						<c:when test="${uvo.email eq ses.email }">
							<a href="/userCtrl/detail?email=${ses.email }" class="profile_edit">프로필 편집</a>					
						</c:when>
						<c:otherwise>
							<a href="/userCtrl/follow?from=${ses.email }&to=${uvo.email }" class="profile_edit" id="follow">팔로우</a>
							<a href="/userCtrl/unfollow?from=${ses.email }&to=${uvo.email }" class="profile_edit" id="follow">언팔로우</a>
						</c:otherwise>
					</c:choose>
				</div>

				<ul class="middle">
					<li><span>게시물</span> 3</li>
					<li><a href="#" data-bs-toggle="modal"
						data-bs-target="#follower"><span>팔로워</span> ${follower.size() }</a></li>
					<li><a href="#" data-bs-toggle="modal"
						data-bs-target="#following"><span>팔로우</span> ${following.size() }</a></li>
				</ul>
				<p class="about">
					<span class="nick_name">게시물</span> <span class="book_mark">북마크</span>
				</p>
			</div>
		</div>

		<!-- 팔로워 모달 -->
		<div class="modal" id="follower">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">

					<!-- 헤더크기 줄이고 모달 가운데로 -->
					<div class="modal-header">
						<h4 class="modal-title">팔로워</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>

					<!-- 팔로워 리스트 각각 a링크 -->
					<div class="modal-body">
						<c:if test="${follower.size() == 0 }">
							팔로워 목록이 없습니다.
						</c:if>
						<c:forEach var="item" items="${follower }">
							<div style="height:50px;">
							<!-- 썸네일 -->
							<div style="width:20%; float:left;">
							<img  class="circle" src="../imgs/thumb.jpeg" style="width:100%;height:100%;">
							</div>
							<!-- 닉네임 -->
							<div style="width:60%; float:left;">
								<a href="/userCtrl/profile?email=${item }">${item }</a>
							</div>
							<!-- 삭제버튼 -->
							<div style="width:20%; float:right;">
								<button type="button" class="btn btn-sm btn-outline-secondary">삭제</button>
							</div>
							</div>
						</c:forEach>
					</div>

				</div>
			</div>
		</div>

		<!-- 팔로잉 모달 -->
		<div class="modal" id="following">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">팔로우</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>

					<!-- 팔로워 리스트 각각 a링크 -->
					<div class="modal-body">
						<c:if test="${following.size() == 0 }">
							팔로우 목록이 없습니다.
						</c:if>
						<c:forEach var="item" items="${following }">
							<div style="height:50px;">
							<!-- 썸네일 -->
							<div style="width:20%; float:left;">
							<img  class="circle" src="../imgs/thumb.jpeg" style="width:100%;height:100%;">
							</div>
							<!-- 닉네임 -->
							<div style="width:60%; float:left;">
								<a href="/userCtrl/profile?email=${item }">${item }</a>
							</div>
							<!-- 삭제버튼 -->
							<div style="width:20%; float:right;">
								<button type="button" class="btn btn-sm btn-outline-secondary">삭제</button>
							</div>
							</div>
						</c:forEach>
					</div>

				</div>
			</div>
		</div>

		<!-- 내가 올린 게시물 -->
		<div class="mylist_contents contents_container active">
			<div class="pic">
				<a href="#"><img src="../imgs/img_section/img01.jpg" alt=""></a>
			</div>
			<div class="pic">
				<a href="#"><img src="../imgs/img_section/img02.jpg" alt=""></a>
			</div>
			<div class="pic">
				<a href="#"> <img src="../imgs/img_section/img03.jpg" alt=""></a>
			</div>
			<div class="pic">
				<a href="#"> <img src="../imgs/img_section/img02.jpg" alt=""></a>
			</div>
			<div class="pic">
				<a href="#"> <img src="../imgs/img_section/img03.jpg" alt=""></a>
			</div>
			<div class="pic">
				<a href="#"> <img src="../imgs/img_section/img01.jpg" alt=""></a>
			</div>
			<div class="pic">
				<a href="#"> <img src="../imgs/img_section/img02.jpg" alt=""></a>
			</div>
			<div class="pic">
				<a href="#"> <img src="../imgs/img_section/img03.jpg" alt=""></a>
			</div>
			<div class="pic">
				<a href="#"> <img src="../imgs/img_section/img01.jpg" alt=""></a>
			</div>
		</div>

		<!-- 북마크 -->
		<div class="bookmark_contents contents_container">
			<div class="pic">
				<a href="#"><img src="../imgs/img_section/img03.jpg" alt=""></a>
			</div>
			<div class="pic">
				<a href="#"><img src="../imgs/img_section/img01.jpg" alt=""></a>
			</div>
			<div class="pic">
				<a href="#"> <img src="../imgs/img_section/img02.jpg" alt=""></a>
			</div>
			<div class="pic">
				<a href="#"> <img src="../imgs/img_section/img01.jpg" alt=""></a>
			</div>
			<div class="pic">
				<a href="#"> <img src="../imgs/img_section/img02.jpg" alt=""></a>
			</div>
			<div class="pic">
				<a href="#"> <img src="../imgs/img_section/img03.jpg" alt=""></a>
			</div>
			<div class="pic">
				<a href="#"> <img src="../imgs/img_section/img01.jpg" alt=""></a>
			</div>
			<div class="pic">
				<a href="#"> <img src="../imgs/img_section/img02.jpg" alt=""></a>
			</div>
			<div class="pic">
				<a href="#"> <img src="../imgs/img_section/img02.jpg" alt=""></a>
			</div>
		</div>

	</section>
</div>

<script src="../js/profile.js"></script>
<script>
	
</script>
</body>
</html>