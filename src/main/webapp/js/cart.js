$(function () {
    $("#back").click(function () {
        history.go(-1);
    });

    var user = getUser();
    if (user != null && user.type == 0) {    //买家
        $.ajax({
            url: '/hello/MyCart.do',
            data: {userId: user.id},
            success: function (json) {
                if (json.code == 200) {
                    var goodsList = json.goodsList;
                    var numList = json.numList;

                    if (goodsList != null && goodsList.length > 0) {
                        var str = "<tr>" +
                            "<th>" + '内容名称' + "</th>" +
                            "<th>" + '数量' + "</th>" +
                            "<th>" + '价格' + "</th>" +
                            "</tr>";

                        for (var i = 0; i < goodsList.length; i++) {
                            if (goodsList[i] != null) {
                                str = str +
                                    "<tr>" +
                                    "<td>" + goodsList[i].name + "</td>" +
                                    "<td>" +
                                    "<span class=\"lessNum\">" + "-" + "</span>" +
                                    "<span title=\"num\" class=\"totalNum\" id=\"allNum\">" + numList[i] + "</span>" +
                                    "<span title=\"goodsId\" id=\"thisId\">" + goodsList[i].id + "</span>" +
                                    "<span class=\"moreNum\">" + "+" + "</span>" + "</td>" +
                                    "<td title=\"price\">" + goodsList[i].price + "</td>" +
                                    "</tr>";
                            }
                        }

                        $("#newTable").html(str);
                    }
                } else if (json.code == -1) {
                    delCookie("user");
                    location.href = getIndexHtml();
                }
            }
        });
    }

    $("#newTable").click(function (e) {
        var e = arguments[0] || window.event;
        target = e.srcElement ? e.srcElement : e.target;
        if (target.nodeName == "SPAN" && target.className == "moreNum") {
            var num = target.parentElement.children[1].textContent;
            var id = target.parentElement.children[2].textContent;
            num++;
            target.parentElement.children[1].textContent = num;
        } else if (target.nodeName == "SPAN" && target.className == "lessNum") {
            var num = target.parentElement.children[1].textContent;
            var id = target.parentElement.children[2].textContent;
            num--;
            if (num < 0) {
                alert("该商品数量为0");
            } else {
                target.parentElement.children[1].textContent = num;
            }
        }
        return false;
    });
});


function buy() {
    var loading = new Loading();
    var layer = new Layer();

    layer.reset({
        content: '确认购买吗？',
        onconfirm: function () {
            layer.hide();
            loading.show();
            var nums = "";
            var goodsIds = "";
            var prices = "";
            $('[title=num]').each(function () {
                nums += $(this).html() + ",";
            });
            $('[title=goodsId]').each(function () {
                goodsIds += $(this).html() + ",";
            });
            $('[title=price]').each(function () {
                prices += $(this).html() + ",";
            });

            $.ajax({
                url: '/hello/buyGoods.do',
                data: {nums: nums, goodsIds: goodsIds, prices: prices},
                success: function (json) {
                    if (json.code == 200) {
                        loading.result('购买成功', function () {
                            location.href = 'account.html';
                        });
                    } else if (json.code == -1) {
                        delCookie("user");
                        location.href = getIndexHtml();
                    }
                },
                error: function () {
                    loading.result('购买失败');
                }
            });

        }.bind(this)
    }).show();
}
