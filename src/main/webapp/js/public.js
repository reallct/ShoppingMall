
$(function(){
    var thisURL = document.URL;    
    var getval =thisURL.split('?')[1];  
    if(getval != null && getval.length >0){
	    var showval= getval.split("=")[1]; 
		$.ajax({
			url : '/hello/goodsDetail',
			type : 'POST',
			data : {goodsId : showval},
			dataType : 'json',
			success : function(json) {
				if (json.code == 200) {
					var goods = json.goods;
					$("#name").val(goods.name);
					$("#summary").val(goods.summary);
					$("#detail").val(goods.detail);
					$("#price").val(goods.price);
					$("#imgpre").attr('src',goods.picUrl);
				} else {
					alert("获取商品详情失败！");
				}
			}
		});
    }
    
    //异步提交表单
    $("#ajaxSubmit").on("click",function(){
		var maxAllowedSize = 1204*1024;
		var realSize = $('#file')[0].files[0].size;

		if(realSize > maxAllowedSize) {
			alert("超过文件上传大小限制");
		}else{
	        var formData = new FormData();
	        formData.append('file', $('#file')[0].files[0]);
	        $.ajax({
	            url: '/hello/fileUpload.do',
	            type: 'POST',
	            cache: false,
	            data: formData,
	            processData: false,   // 告诉jQuery不要去处理发送的数据
	            contentType: false,    // 告诉jQuery不要去设置Content-Type请求头
	            success : function(json) {
					if (json.code == 200) {
						alert("图片上传成功！");
						$("#imgpre").attr("src", json.path);
					}else if(json.code == -1) {
						delCookie("user");
						location.href = getIndexHtml();
					}
				},
				error : function(msg) {
					alert(msg + "图片上传失败！");
				}
	        });
		}
    });
    
}); 

function publicGoods(){
	var data={};
	var name = $("#name").val();
	var summary = $("#summary").val();
	var picUrl = $("#picUrl").val();
	if(picUrl == null || picUrl == "")
		picUrl = $("#imgpre").attr("src");
	var price = $("#price").val();
	var detail = $("#detail").val();
	data.name = name;
	data.summary = summary;
	data.price = price;
	data.detail = detail;
	data.picUrl = picUrl;
	
	if(!checkLength(data)){
		return;
	}
	
	var isEdit = false;
    var thisURL = document.URL;    
    var getval =thisURL.split('?')[1];  
    if(getval != null && getval.length >0){
    	isEdit = true;
	    var showval= getval.split("=")[1];
	    data.id = showval;
    }
	
	$.ajax({
		url : '/hello/public.do',
		type : 'POST',
		data : data,
		dataType : 'json',
		success : function(json) {
			if (json.code == 200) {
				var goodsId = json.goodsId;
				if(isEdit)
					location.href = 'publicSuccess.html?goodsId=' + goodsId + "&type=1";
				else
					location.href = 'publicSuccess.html?goodsId=' + goodsId + "&type=0";
			}else if(json.code == -1) {
				delCookie("user");
				location.href = getIndexHtml();
			}else{
				if(isEdit)
					alert("编辑失败！");
				else
					alert("发布失败！");
			}
		}
	});

}

function picUrlChange(){
	var picUrl = $("#picUrl").val();
	$("#imgpre").attr("src", picUrl);
}

function changeType(type){
	if(type == 1){
		$("#urlUpload").attr("style","display:block");
		$("#fileUpload").attr("style","display:none");
	}else{
		$("#urlUpload").attr("style","display:none");
		$("#fileUpload").attr("style","display:block");
	}
}

function checkLength(goods){
	if(goods.name=="" || goods.name.length<2 || goods.name.length>80){
		alert("标题字数不合规范");
		return false;
	}
	if(goods.summary=="" || goods.summary.length<2 || goods.summary.length>140){
		alert("摘要字数不合规范");
		return false;
	}
	if(goods.detail=="" || goods.detail.length<2 || goods.detail.length>1000){
		alert("正文字数不合规范");
		return false;
	}
	if(goods.price=="" || isNaN(goods.price) || goods.price<0){
		alert("价格不合规范");
		return false;
	}
	if(goods.picUrl==""){
		alert("请输入图片地址或者上传图片");
		return false;
	}
	return true;
}
