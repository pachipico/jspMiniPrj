const closeBtn = document.getElementById("close_btn");

const handleCloseBtnClick = () => {
  history.back();
};

closeBtn.addEventListener("click", handleCloseBtnClick);
