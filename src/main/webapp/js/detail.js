const closeBtn = document.getElementById("close_btn");
const submitBtn = document.getElementById("submitBtn");

const handleCloseBtnClick = () => {
  history.back();
};

const handleSubmitBtnClick = async (e) => {
  e.preventDefault();
  const cvo = {
    pid: document.querySelector("[name='pid']").value,
    writer: document.querySelector("[name='writer']").value,
    content: document.querySelector("[name='content']").value,
  };
  const config = {
    method: "POST",
    content: "application/json; charset=utf-8",
    body: JSON.stringify(cvo),
  };
  const res = await fetch("/cmtCtrl/post", config);
  const response = await res.text();
  console.log(`comment added ${response > 0 ? "successfully" : "failed"}`);
  return response;
};

const addToList = () => {
  document.querySelector("div.scroll_div").innerHTML =
    ` <div class="cmt">
                <div class="cmt_img">
                  <img class="avatar" src="https://picsum.photos/25/25/" alt="" />
                </div>
                <div class="cmt_text">
                  <div class="cmt_writer_area">
                    <div>
                      <span class="cmt_writer_nickname">${
                        document.querySelector("[name='writer']").value
                      }</span>
                      <span class="cmt_writer_email">${document.querySelector("[name='writer']").value}</span>
                    </div>
                    <span class="cmt_write_time">9분전</span>
                  </div>
                  <div class="cmt_content">${document.querySelector("[name='content']").value}</div>
                </div>
              </div>` + document.querySelector("div.scroll_div").innerHTML;
};

closeBtn.addEventListener("click", handleCloseBtnClick);
submitBtn.addEventListener("click", (e) => {
  handleCloseBtnClick(e);
  addToList();
});
