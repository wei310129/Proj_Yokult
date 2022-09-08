window.onload = (e) => {
    document.getElementById("btn_login").addEventListener("click", () => {
        let memID = document.getElementById("memID").value;
        let memPassword = document.getElementById("memPassword").value;
        // console.log(memID);
        // console.log(memPassword);
        axios
            .post(
                "http://localhost:8080/my-first-project/api/0.01/member/login",
                {
                    memID: memID,
                    memPassword: memPassword,
                }
            )
            .then((response) => {
                let msg = response.data["msg"];
                let member = response.data["member"];
                let firstName = member["memFirstName"];
                let lastName = member["memLastName"];
                console.log(response.data);
                if (msg === "success") {
                    alert(`${lastName} ${firstName}歡迎`);
                } else {
                    alert("登入失敗");
                }
            })
            .catch((error) => console.log(error));
    });
};
