const toggle = document.getElementById("toggle_more");
let detailBtn = document.getElementById("detailToggle");
let hidden = true;
const onToggleClick = () => {
  console.log("click");
  if (hidden) {
    document.querySelector("ul.toggle_box").setAttribute("style", "display:block;");
    console.log("hidden");
    hidden = false;
  } else {
    document.querySelector("ul.toggle_box").setAttribute("style", "display:none;");
    console.log("shown");
    hidden = true;
  }
};

const getDetail = async () => {
  let pid = detailBtn.dataset.pid;
  console.log(pid);
  try {
    const url = "/postCtrl/detail?pid=" + pid;

    const res = await fetch(url);
    const result = await res.text();
    return result;
  } catch (e) {
    console.log(e);
  }
};

const onDetailClick = async () => {
  console.log(detailBtn.dataset.pid);
  getDetail().then((result) => {
    let pvo = JSON.parse(result);
    console.log(pvo);
    console.log(pvo.pvo);
    console.log(pvo.pvo.writer);
    document.getElementById("writerDiv").innerHTML = pvo.pvo.writer;
    document.getElementById("contentDiv").innerHTML = pvo.pvo.content;
  });
};

toggle.addEventListener("click", onToggleClick);
detailBtn.addEventListener("click", () => {});
