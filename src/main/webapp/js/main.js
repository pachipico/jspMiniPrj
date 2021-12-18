let toggle = document.querySelectorAll("div #toggle_more");
let hidden = true;
let delPostBtn = document.querySelectorAll("#delPost");

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

const deleteRequest = async (pid) => {
  fetch("/postCtrl/remove?pid=" + pid);
};

[].forEach.call(delPostBtn, function (delPostBtn) {
  delPostBtn.addEventListener("click", () => {
    console.log(delPostBtn.dataset.pid);
    deleteRequest(delPostBtn.dataset.pid);
  });
});
