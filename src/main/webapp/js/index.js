const a = document.getElementById("list");

if (a) {
    a.click();
}

// async function checkEmailExist(data) {
//     try {
//         const url = "/userCtrl/checkEmail";
//         const config = {
//             method: "POST",
//             headers: {
//                 'Content-Type': 'application/json; charset=utf-8',
//             },
//             body: JSON.stringify(data)
//         };
//         const resp = await fetch(url, config);
//         return await resp.text();
//     } catch (error) {
//         console.log(error);
//     }
// }

// document.querySelector(".submit_btn").addEventListener("click", (e) => {
//     e.preventDefault();
//     let data = {
//         email = document.getElementById("email").value,
//     }
//     checkEmailExist(data).then(result => {

//     });
// });