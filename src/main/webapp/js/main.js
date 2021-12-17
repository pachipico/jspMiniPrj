const toggle = document.getElementById("toggle_more");
const detailBtn = document.getElementById("detailToggle");
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
  getDetail().then((result) => {
    JSON.parse(result);
    console.log(JSON.parse(result));
  });
};

toggle.addEventListener("click", onToggleClick);
detailBtn.addEventListener("click", onDetailClick);
