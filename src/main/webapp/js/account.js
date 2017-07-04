$(function(){
	$.ajax({
		url : '/hello/myOrders.do',
		success : function(json) {
			if (json.code == 200) {
				var list = json.orderList;
				var goodsList = json.goodsList;
				var str = "";
				var total = 0;
				for(var i = 0; i < list.length; i++){
					var order = list[i];
					str = str + 
		            "<tr>" + 
	                "<td><a href=\"detail.html?id=" + order.goodsId + "\"><img src=\"" + goodsList[i].picUrl + "\" alt=\"\"></a></td>" + 
	                "<td><h4><a href=\"detail.html?id=" + order.goodsId +"\">" + goodsList[i].name + "</a></h4></td>" + 
	                "<td><span class=\"v-time\">" + format(order.createTime) + "</span></td>" +
	                "<td><span class=\"v-num\">" + order.num + "</span></td>" + 
	                "<td><span class=\"v-unit\">¥</span><span class=\"value\">" + order.totalPrice +"</span></td>" + 
	                "</tr>";
					total = total + order.totalPrice;
				}
				
				$("#myTable").html(str);
				$("#totalPrice").html(total);
				
			}else if(json.code == -1) {
				delCookie("user");
				location.href = getIndexHtml();
			}
		},
	   error : function(){ 
		   loading.result("获取购买信息失败");
		} 
	});
	
});

