window.onload = function(){
    initNum();

    var thisURL = document.URL;
    var getval =thisURL.split('?')[1];
    var showval= getval.split("=")[1];
    $.ajax({
        url : '/hello/goodsDetail',
        type : 'POST',
        data : {goodsId : showval},
        dataType : 'json',
        success : function(json) {
            if (json.code == 200) {
                var goods = json.goods;
                $("#picImg").attr('src',goods.picUrl);
                $("#name").html(goods.name);
                $("#summary").html(goods.summary);
                $("#price").html(goods.price);
                $("#detail").html(goods.detail);

            } else {
                alert("获取商品详情失败！");
            }
        }
    });

    var user = getUser();
    if(user != null && user.type == 1){    //卖家
        $("#editOrCart").html("<a href=\"public.html?id=" + showval + "\" class=\"u-btn u-btn-primary\">编 辑</a>");
        $.ajax({
            url : '/hello/goodsSelledNum.do',
            data : {goodsId : showval},
            success : function(json) {
                if (json.code == 200) {
                    var num = json.num;
                    $("#num").html("已售数量： " + num);
                }else if(json.code == -1) {
                    delCookie("user");
                    window.location.reload();
                }
            }
        });
    }else if(user != null && user.type == 0){ //买家
        $.ajax({
            url : '/hello/goodsUserBuyNum.do',
            data : {goodsId : showval,  userId:user.id},
            success : function(json) {
                if (json.code == 200) {
                    var num = json.num;
                    if(num>0){
                        var oldPrice = json.price;
                        $("#num").html("已购买数量： " + num);
                        $("#editOrCart").html("<span class=\"u-btn u-btn-primary z-dis\">已购买</span>" +
                            "<span class=\"buyprice\">当时购买价格：¥" + oldPrice + "</span>"
                        );
                    }else{      //未购买
                        $("#editOrCart").html(
                            "<button class=\"u-btn u-btn-primary\"  data-id=\""+ showval + "\" onclick=\"addCart(this);\">加入购物车</button>"
                        );
                    }
                }else if(json.code == -1) {
                    delCookie("user");
                    window.location.reload();
                }
            }
        });
    }
}

function addCart(element){
    var loading = new Loading();
    var layer = new Layer();
    layer.reset({
        content:'确认加入购物车吗？',
        onconfirm:function(){
            layer.hide();
            loading.show();

            var goodsId = $(element).attr("data-id");
            var num = $("#allNum").html();
            var userId = getUser().id;

            $.ajax({
                url : '/hello/addToMyCart.do',
                data : {goodsId : goodsId, num : num, userId : userId},
                success : function(json) {
                    if (json.code == 200) {
                        loading.result('添加购物车成功');
                    }else if(json.code == -1) {
                        delCookie("user");
                        window.location.reload();
                    }
                }
            });
        }.bind(this)
    }).show();

}

function initNum(){
    $("#plusNum").click(function(){
        var num = $("#allNum").html();
        if(num > 0){
            num --;
            $("#allNum").html(num);
        }else{
            alert("您没有购买任何商品");
        }
    });

    $("#addNum").click(function(){
        var num = $("#allNum").html();
        num ++;
        $("#allNum").html(num);
    });
}