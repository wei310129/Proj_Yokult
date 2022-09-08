const member = {};
window.onload = (e) => {
    document.getElementById("btn_signup").addEventListener("click", () => {
        member["memID"] = document.getElementById("memID").value;
        member["memPassword"] = document.getElementById("memPassword").value;
        member["memEmail"] = document.getElementById("memEmail").value;
        member["memFirstName"] = document.getElementById("memFirstName").value;
        member["memLastName"] = document.getElementById("memLastName").value;
        member["memBirth"] = document.getElementById("memBirth").value;
        member["memCellPhone"] = document.getElementById("memCellPhone").value;
        // member["memPhone"] = document.getElementById("memPhone").value;
        member["memAddress"] = document.getElementById("memAddress").value;
        console.log(member);
        axios
            .post(
                "http://localhost:8080/my-first-project/api/0.01/member/register",
                member
            )
            .then((response) => {
                let msg = response.data["msg"];
                if (msg === "success") {
                    alert(`註冊成功`);
                } else {
                    alert("註冊失敗");
                }
            })
            .catch((error) => console.log(error));
    });
};
