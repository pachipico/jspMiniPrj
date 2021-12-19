let toggle = document.querySelectorAll("div #toggle_more");
let hidden = true;
let delPostBtn = document.querySelectorAll("#delPost");
let commentField = document.querySelectorAll("div .comment_field");

const commentArea = document.getElementById("comment_area");

const deleteRequest = async (pid) => {
  fetch("/postCtrl/remove?pid=" + pid);
};

const commentPostRequest = async (content, pid, writer) => {
  try {
    const cvo = {
      content,
      pid,
      writer,
    };
    const config = {
      method: "POST",
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
      body: JSON.stringify(cvo),
    };
    const res = await fetch("/cmtCtrl/post", config);
    return res;
  } catch (e) {
    console.log(e);
  }
};

[].forEach.call(toggle, function (toggle) {
  toggle.addEventListener("click", () => {
    if (hidden) {
      toggle.querySelector(".toggle_box").setAttribute("style", "display:block;");
      hidden = false;
    } else {
      toggle.querySelector(".toggle_box").setAttribute("style", "display:none;");
      hidden = true;
    }
  });
});

[].forEach.call(delPostBtn, function (delPostBtn) {
  delPostBtn.addEventListener("click", () => {
    console.log(delPostBtn.dataset.pid);
    deleteRequest(delPostBtn.dataset.pid);
  });
});

[].forEach.call(commentField, function (commentField) {
  let uploadBtn = commentField.querySelector("#commentUploadBtn");
  uploadBtn.addEventListener("click", () => {
    const content = commentField.querySelector("#commentInput").value;
    const pid = uploadBtn.dataset.pid;
    const writer = "123@123.com";
    const cmtFormat = `	<div class="comment_container">
											<div class="comment" id="comment-list-ajax-post37">
												<div class="comment-detail">
                        
													<div class="nick_name m_text">${writer}</div>
													<div>${content}</div>
											
												</div>
											</div>
											<div class="small_heart">
												<div class="sprite_small_heart_icon_outline"></div>
											</div>
										</div>`;
    commentPostRequest(content, pid, writer).then((response) => {
      if (response.status === 200) {
        document.querySelector("#comment_area" + pid).innerHTML += cmtFormat;
        commentField.querySelector("#commentInput").value = "";
      }
    });
  });
});
