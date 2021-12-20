<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="../css/detail.css" />
    <meta charset="UTF-8" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <title>Insert title here</title>
  </head>
  <body>
    <div class="container">
      <a class="close_btn" id="close_btn">X</a>
      <div class="left_container">
        <img id="post_img" src="https://picsum.photos/380/400/" alt="" />
      </div>
      <div class="right_container">
        <div class="writer">
          <div class="cmt_img">
            <img class="avatar" src="https://picsum.photos/25/25/" alt="" />
          </div>
          <div class="cmt_text">
            <div class="cmt_writer_area">
              <div>
                <span class="cmt_writer_nickname">${pvo.writer }</span>
                <span class="cmt_writer_email">${pvo.writer }</span>
              </div>
              <span class="cmt_write_time">${pvo.modAt }</span>
            </div>
          </div>
        </div>
        <div class="content_area">
          <div class="content_text">${pvo.content }</div>
          <div class="htag_area">
            <a class="htag" href="#">해쉬태그</a>
            <a class="htag" href="#">해쉬태그</a>
            <a class="htag" href="#">해쉬태그</a>
            <a class="htag" href="#">해쉬태그</a>
            <a class="htag" href="#">해쉬태그</a>
          </div>
          <div class="sns_btn">
            <a href="#" class="fa fa-facebook"></a>
            <a href="#" class="fa fa-twitter"></a>
            <a href="#" class="fa fa-google"></a>
            <a href="#" class="fa fa-tumblr"></a>
          </div>
          <div class="back_btn_area">
            <button id="back_btn">&lt;&nbsp;이전</button>
          </div>
        </div>
        <div class="cmt_area">
          <span> 댓글</span>
          <div class="scroll_div">
          <c:forEach items="${cvoList }" var="cvo">
            <div class="cmt ">
              <div class="cmt_img">
                <img class="avatar" src="https://picsum.photos/25/25/" alt="" />
              </div>
              <div class="cmt_text">
                <div class="cmt_writer_area">
                  <div>
                    <span class="cmt_writer_nickname">${cvo.writer }</span>
                    <span class="cmt_writer_email">${cvo.writer }</span>
                  </div>
                  <span class="cmt_write_time">9분전</span>
                </div>
                <div class="cmt_content">${cvo.content }</div>
              </div>
            </div>
          </c:forEach>
          </div>
        </div>
      </div>
    </div>
    <script src="../js/detail.js"></script>
  </body>
</html>
