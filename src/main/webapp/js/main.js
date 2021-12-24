const userSession = document.querySelector("#userSession").value;
let toggle = document.querySelectorAll("div #toggle_more");
let hidden = true;
let delPostBtn = document.querySelectorAll("#delPost");
let updatePostBtn = document.querySelectorAll("#updatePost");
let commentField = document.querySelectorAll("div .comment_field");
let likeBtn = document.querySelectorAll("#likeBtn");
let limit = 5;

const commentArea = document.getElementById("comment_area");

const deleteRequest = async (pid) => {
  const res = await fetch("/postCtrl/remove?pid=" + pid);
  return res;
};

const getContent = async (pid) => {
  const res = await fetch("/postCtrl/modify?pid=" + pid);
  return res;
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

const likePost = async (pid, email) => {
  const lvo = {
    pid,
    email,
  };
  const config = {
    method: "POST",
    headers: {
      "Content-Type": "application/json; charset=utf-8",
    },
    body: JSON.stringify(lvo),
  };
  try {
    const res = await fetch("/postCtrl/like", config);
    return res.text();
  } catch (e) {
    console.log(e);
  }
};

const unlikePost = async (pid, email) => {
  const lvo = {
    pid,
    email,
  };
  const config = {
    method: "POST",
    headers: {
      "Content-Type": "application/json; charset=utf-8",
    },
    body: JSON.stringify(lvo),
  };
  try {
    const res = await fetch("/postCtrl/unlike", config);
    return res.text();
  } catch (e) {
    console.log(e);
  }
};

[].forEach.call(likeBtn, (likeBtn) => {
  likeBtn.addEventListener("click", () => {
    const heart = likeBtn.querySelector(".bi");
    if (heart.classList.contains("bi-heart")) {
      heart.classList.remove("bi-heart");
      heart.classList.add("bi-heart-fill");
      heart.classList.add("red");
      likePost(likeBtn.dataset.pid, userSession);
      document.querySelector("#like-count" + likeBtn.dataset.pid).innerHTML =
        Number(document.querySelector("#like-count" + likeBtn.dataset.pid).innerHTML) + 1;
    } else {
      heart.classList.remove("bi-heart-fill");
      heart.classList.remove("red");
      heart.classList.add("bi-heart");
      unlikePost(likeBtn.dataset.pid, userSession);
      document.querySelector("#like-count" + likeBtn.dataset.pid).innerHTML =
        Number(document.querySelector("#like-count" + likeBtn.dataset.pid).innerHTML) - 1;
    }
  });
});

[].forEach.call(toggle, (toggle) => {
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

[].forEach.call(delPostBtn, (delPostBtn) => {
  delPostBtn.addEventListener("click", () => {
    console.log(delPostBtn.dataset.pid);
    deleteRequest(delPostBtn.dataset.pid).then((result) => {
      document.querySelector("#post" + delPostBtn.dataset.pid).innerHTML = "";
    });
  });
});

[].forEach.call(updatePostBtn, (updatePostBtn) => {
  updatePostBtn.addEventListener("click", (e) => {
    getContent(updatePostBtn.dataset.pid)
      .then((result) => {
        return result.json();
      })
      .then((result) => {
        console.log(result);
        document.querySelector("#updateContent").value = result.content;
        document.querySelector("#updatePid").value = result.pid;
      });
  });
});

[].forEach.call(commentField, function (commentField) {
  let uploadBtn = commentField.querySelector("#commentUploadBtn");
  uploadBtn.addEventListener("click", () => {
    const content = commentField.querySelector("#commentInput").value;
    const pid = uploadBtn.dataset.pid;
    const writer = userSession;
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
        document.querySelector("#comment_area" + pid).innerHTML =
          cmtFormat + document.querySelector("#comment_area" + pid).innerHTML;
        commentField.querySelector("#commentInput").value = "";
      }
    });
  });
});
