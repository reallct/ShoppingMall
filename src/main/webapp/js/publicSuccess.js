window.onload = function(){
    var thisURL = document.URL;
    var getval = thisURL.split('?')[1];
    var str1 = getval.split('&')[0];
    var str2 = getval.split('&')[1];
    var goodsId = str1.split("=")[1];
    var type = str2.split("=")[1];
    
    
    $("#showDetail").attr('href',"detail.html?id=" + goodsId);
    if(type == 1){
    	$("#successTitle").html("编辑成功！");
    }

}