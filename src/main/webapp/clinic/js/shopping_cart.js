$(window).on("load", () => {
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
    //==========================//
    let orderlist = JSON.parse(sessionStorage.getItem("orderlist"));
    orderlist.forEach((order) => {
        addList(order);
    });
    subtotal();

    /*-------------------
	Quantity change 數量遞增遞減
	--------------------- */
    $("#shoppingcart_orderlist").on("click", ".qtybtn", function (e) {
        var $button = $(this);
        var oldValue = $button.parent().find("input").val();
        if ($button.hasClass("inc")) {
            var newVal = parseFloat(oldValue) + 1;
        } else {
            // Don't allow decrementing below zero
            if (oldValue > 0) {
                var newVal = parseFloat(oldValue) - 1;
            } else {
                newVal = 0;
            }
        }
        $button.parent().find("input").val(newVal);
        $(e.target).closest("tr").data("quantity", newVal);
        // Get product price by jquery.
        let product_price = $button
            .parent()
            .parent()
            .parent()
            .prev()
            .find(".product_price")
            .text();
        // Get total price by jquery.
        let total_price = $button
            .parent()
            .parent()
            .parent()
            .next()
            .find(".total_price")
            .text(product_price * newVal);
        subtotal();
        totalaccount();
    });

    //從購車中刪除
    $("#shoppingcart_orderlist").on("click", ".btn_delete", (e) => {
        $(e.target)
            .closest("tr")
            .fadeOut(1000, () => {
                $(e.target).closest("tr").remove();
                subtotal();
                totalaccount();
            });
    });

    // 運費選擇
    $("#delivery_form").on("click", (event) => {
        if (event.target && event.target.matches("input[type='radio']")) {
            $("#delivery").text(event.target.value);
        }
        totalaccount();
    });

    // 計算總和
    totalaccount();
    // //   加入購物車
    // $("#the_btn1").on("click", function () {
    //     // 取得欄位的值
    //     alert($("select[name='the_select']").text());
    $("#btn_checkout").on("click", () => {
        let checkoutList = [];
        let product = {};
        let delivery = $('input[name="deliver_radio"]:checked').attr("id");
        $("tr.product_list").each((i, el) => {
            product = {};
            product["proid"] = $(el).data("id");
            product["propicture"] = $(el).data("pic");
            product["proname"] = $(el).data("name");
            product["quantity"] = $(el).data("quantity");
            product["proprice"] = $(el).data("price");
            checkoutList.push(product);
        });
        sessionStorage.setItem("orderlist", JSON.stringify(checkoutList));
        sessionStorage.setItem("delivery", delivery);
    });
});

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

// 加入列表
function addList(order) {
    let list = `<tr class="product_list"
    data-id="${order["proid"]}" 
    data-pic="${order["propicture"]}" 
    data-name="${order["proname"]}"
    data-quantity="${order["quantity"]}"
    data-price="${order["proprice"]}"
    >
    <td class="shoping__cart__picture">
    <div style="height: 70px;width: 70px;">
    <img src="${
        order["propicture"]
    }" alt="" style="max-width: 100%; max-height: 100%;margin-left: auto;
    margin-right: auto;
    display: block;"/>
    </div>
    </td>
    <td class="shoping__cart__item">
    <h5>${order["proname"]}</h5>
    </div>
    </td>
    <td class="shoping_cart_price">NT$<span class="product_price">${
        order["proprice"] ?? ""
    }</span></td>
    <td class="shoping__cart__quantity">
        <div class="quantity">
            <div class="pro-qty">
                <i class="fa-solid fa-minus dec qtybtn"></i>
                <input type="text" value="${order["quantity"] ?? ""}"/>
                <i class="fa-solid fa-plus inc qtybtn"></i>
            </div>
        </div>
    </td>
    <td class="shoping_cart_total">NT$<span class="total_price">${
        order["proprice"] * order["quantity"]
    }</span></td>
    <td class="shoping__cart__item_close">
        <span class="fa-solid fa-xmark btn_delete"></span>
    </td>
    </tr>`;
    $("#shoppingcart_orderlist").append(list);
}
