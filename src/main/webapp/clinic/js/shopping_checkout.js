$(window).on("load", () => {
    let orderlist;
    let delivery;
    let payment = {};
    let consignee = {};
    let paymethod;
    //====測試session storage====//
    sessionStorage.setItem(
        "orderlist",
        `[
    {
        "proid": "1",
        "proname": "MARVIS 義大利經典牙膏85ml(銀-亮白)",
        "propicture": "https://raw.githubusercontent.com/Peggy79/first/main/img/000.jpg",
        "quantity": 1,
        "proprice": 339
    },
    {
        "proid": "2",
        "proname": "舒酸定強化琺瑯質兒童牙膏",
        "propicture": "https://raw.githubusercontent.com/Peggy79/first/main/img/images62.jpg",
        "quantity": 2,
        "proprice": 180
    },
    {
        "proid": "3",
        "proname": "舒酸定專業修復牙膏",
        "propicture": "https://raw.githubusercontent.com/Peggy79/first/main/img/images63.jpg",
        "quantity": 5,
        "proprice": 180
    }]`
    );
    sessionStorage.setItem("delivery", "mailing");
    orderlist = JSON.parse(sessionStorage.getItem("orderlist"));
    orderlist.forEach((order) => {
        addList(order);
    });
    delivery = sessionStorage.getItem("delivery");
    getDelivery(delivery);
    subtotal();
    totalaccount();

    // 收貨人同付款
    $("#same-payment").on("click", () => {
        if ($("#same-payment").is(":checked")) {
            payment = getPaymentInfo();
            $("#consignee-name").val(payment["name"]);
            $("#consignee-cellphone").val(payment["cellphone"]);
            $("#consignee-phone").val(payment["phone"] ?? "");
        }
    });

    // 結帳按鈕
    $("#btn-checkout").on("click", () => {
        payment = getPaymentInfo();
        consignee = getConsigneeInfo(payment);
        paymethod = $('input[name="payment"]:checked').val();
        axios
            .post("http://localhost:8080/Proj_Yokult/Checkout", {
                payment,
                consignee,
                paymethod,
            })
            .then((response) => {
                // let msg = response.data["msg"];
                // if (msg === "success") {
                //     alert("付款成功");
                // } else {
                //     alert("付款失敗");
                // }
                console.log(response);
            })
            .catch((error) => console.log(error));
    });
});

// 取得付款資訊
function getPaymentInfo() {
    let payment = {};
    payment["name"] = $("#payment-name").val();
    payment["cellphone"] = $("#payment-cellphone").val();
    payment["phone"] = $("#payment-phone").val() ?? "";
    payment["creditcard-number"] = $("#payment-creditcard-number").val();
    payment["creditcard-code"] = $("#payment-creditcard-code").val();
    return payment;
}

// 取得收貨人資訊
function getConsigneeInfo(payment) {
    let consignee = {};
    consignee["name"] = $("#consignee-name").val();
    consignee["cellphone"] = $("#consignee-cellphone").val();
    consignee["phone"] = $("#consignee-phone").val() ?? "";
    consignee["city"] = $("#consignee-city option:selected").val() ?? "";
    consignee["dist"] = $("#consignee-dist option:selected").val() ?? "";
    return consignee;
}

function getDelivery(delivery) {
    if (delivery === "pickup") {
        $("#delivery").text("0");
    } else if (delivery === "mailing") {
        $("#delivery").text("50");
    } else if (delivery === "shipping") {
        $("#delivery").text("100");
    }
}

//計算總和
function totalaccount() {
    let total = 0;
    total += parseInt($("#subtotal").text());
    total += parseInt($("#delivery").text());
    $("#total").text(total);
}

// 計算小計
function subtotal() {
    let subtotal = 0;
    $(".total_price").each((i, e) => {
        subtotal += parseInt($(e).text());
    });
    $("#subtotal").text(subtotal);
}

function addList(order) {
    let list = `
    <li class="product_list     
    data-id="${order["proid"]}" 
    data-name="${order["proname"]}"
    data-quantity="${order["quantity"]}"
    data-price="${order["proprice"]}">
    商品名稱：
    <span>${order["proname"]}</span>
    <ul>
        <li>價格：$<span>${order["proprice"] ?? ""}</span></li>
        <li>數量：<span>${order["quantity"] ?? ""}</span></li>
        <li>小計：$<span class="total_price">${
            order["proprice"] * order["quantity"]
        }</span>></li>
    </ul>
    </li>`;
    $("#orderlist").append(list);
}
