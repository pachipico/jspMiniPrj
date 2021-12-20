<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../header1.jsp" />
    <link rel="stylesheet" href="../css/login.css">
<jsp:include page="../header2.jsp" />

    <div id="main_container">

        <div class="form_container">

            <div class="form">


                <h1 class="sprite_insta_big_logo title"></h1>

                <form action="/userCtrl/insertwithkakao" method="POST">
                    <p class="login_user_name">
                        <input type="text" name="email" placeholder="이메일 주소" value="${email }" readonly>
                    </p>
                    
                    <p class="login_user_password">
                        <input type="text" name="age" placeholder="나이">
                    </p>

                    <p class="login_user_password">
                        <input type="text" name="name" placeholder="성명">
                    </p>
                    
                    <p class="login_user_password">
                        <input type="text" name="nickName" placeholder="사용자이름">
                    </p>
                    
                    <p class="login_user_password">
                        <input type="text" name="pwd" placeholder="비밀번호">
                    </p>
                    
                    <p class="login_user_password">
                        <input type="hidden" name="isAdmin" value="false">
                    </p>

                    <input type="submit" value="회원가입" class="submit_btn">
                </form>



            </div>

            <div class="bottom_box">
                <div>
                    <span>계정이 있으신가요?</span><a href="/index.jsp">로그인</a>
                </div>
            </div>


        </div>

    </div>


</section>


<script src="../js/insta.js"></script>
</body>
</html>
