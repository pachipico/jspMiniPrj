<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
      	<c:choose>
          	<c:when test="${empty pvo.files }">
        <img id="post_img" src="https://picsum.photos/380/400/" alt="" />
          	
          	</c:when>
          	<c:otherwise>
			<img id="post_img" alt="" src="../_postImgUpload/${pvo.files }">          	
          	</c:otherwise>
          	
          	</c:choose>
      </div>
      <div class="right_container">
        <div class="writer">
          <div class="cmt_img">
          	<img class="avatar" src="../_fileUpload/${empty pvo.avatar ? 'default_avatar.jpeg' : pvo.avatar }" alt="" />
          		
          </div>
          <div class="cmt_text">
            <div class="cmt_writer_area">
              <div>
                <span class="cmt_writer_nickname">${pvo.writer }</span>
                <span class="cmt_writer_email cyan">${pvo.writer }</span>
              </div>
              <span class="cmt_write_time cyan">${pvo.modAt }</span>
            </div>
          </div>
        </div>
        <div class="content_area">
          <div class="content_text">${content }</div>
          <div class="htag_area">
          <c:forEach items="${hashtags }" var="tag">
           	 <a class="htag" href="https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=${fn:replace(tag, '#', '') }">${tag }</a>
           </c:forEach>
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
        <span> 댓글</span>
        <div class="cmt_area">
          <div class="scroll_div">
            <c:forEach items="${cvoList }" var="cvo">
              <div class="cmt">
                <div class="cmt_img">
                	
                  <img class="avatar" src="../_fileUpload/${empty cvo.avatar ? 'default_avatar.jpeg' : cvo.avatar }" alt="" />
                </div>
                <div class="cmt_text">
                  <div class="cmt_writer_area">
                    <div>
                      <span class="cmt_writer_nickname">${cvo.writer }</span>
                      <span class="cmt_writer_email cyan">${cvo.writer }</span>
                    </div>
                    <span class="cmt_write_time cyan">9분전</span>
                  </div>
                  <div class="cmt_content">${cvo.content }</div>
                </div>
              </div>
            </c:forEach>
          </div>
        </div>
        <div class="cmt_input">
            <input type="hidden" name="writer" value="${ses.email}" />
            <input type="hidden" name="pid" value="${pvo.postId}" />
            <input type="text" name="content" id="input_content" />
            <button type="submit" id="submitBtn">게시</button>
        </div>
      </div>
    </div>
    <script src="../js/detail.js"></script>
  </body>
</html>
