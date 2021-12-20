<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../header1.jsp" />
<link rel="stylesheet" href="../css/login.css">
<jsp:include page="../header2.jsp" />

<div id="main_container">
	<div class="form_container">
		<div class="detail">
			<div class="top">
				<a href="/userCtrl/changePwd?email=${ses.email }"
					class="profile_edit">비밀번호 변경</a> 
				<a href="/userCtrl/changeAvatar?email=${ses.email }"
					class="profile_edit">프로필사진 변경</a>
			</div>
		</div>
		<div class="form">
			<h1 class="sprite_insta_big_logo title"></h1>
			<form action="/userCtrl/modify">
				<p class="login_user_name">
					<input type="hidden" name="email" value="${uvo.email }">
				</p>

				<p class="login_user_password">
					<input type="text" name="age" placeholder="나이" value="${uvo.age }">
				</p>

				<p class="login_user_password">
					<input type="text" name="name" placeholder="성명"
						value="${uvo.name }">
				</p>

				<p class="login_user_password">
					<input type="text" name="nickName" placeholder="사용자이름"
						value="${uvo.nickname }">
				</p>

				<p class="login_user_password">
					<input type="hidden" name="isAdmin" value="false">
				</p>

				<input type="submit" value="제출" class="submit_btn">
			</form>
		</div>
	</div>
</div>
</section>


</body>
</html>
