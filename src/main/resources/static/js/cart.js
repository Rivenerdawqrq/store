
//删除按钮
function delCartItem(btn,pid) {
	
	$(btn).parents("tr").remove();
	$.ajax({
		url:"/carts/"+ pid +"/del",
		type:"POST",
		success:function (json){
			if (json.state == 200){
				console.log("删除成功")
			}
		}
	})
	calcTotal();
}
//批量删除按钮
function selDelCart() {
	if ($("#cart-list input:checked").length==0){
		alert("未选中")
		return false;
	}
	let del_value = [];
	$("#cart-list input:checked").each(function (i,v){
		del_value.push($("#" + v.id).attr("pid"))
		console.log(del_value)
		$("#"+v.id).parents("tr").remove();
	})
	$.ajax({
		url:"/carts/"+ del_value +"/del",
		type:"POST",
		success:function (json){
			if (json.state == 200){
				console.log("删除成功")
			}
		}
	})

	//遍历所有按钮
	// for (var i = $(".ckitem").length - 1; i >= 0; i--) {
	// 	//如果选中
	// 	if ($(".ckitem")[i].checked) {
	// 		//删除
	// 		$($(".ckitem")[i]).parents("tr").remove();
	// 	}
	// }


	calcTotal();
}
$(function() {
	//单选一个也得算价格
	$(".ckitem").click(function() {
			//calcTotal();
		})
		//开始时计算价格
		//calcTotal();
})
//计算总价格的方法
function calcTotal() {
	let tmp = 0;
	let count = 0;
	console.log(111111)
	if ($("#cart-list input:checked").length < 1) {

		// console.log("进来啦")
		$("#selectTotal").html(0)
		$("#selectCount").html(0);
		return false;
	}

	$("#cart-list input:checked").each(function (i,c) {
		let a = parseFloat(c.value)
		console.log(c)
		tmp += a;
		count+=1;

		$("#selectTotal").html(tmp);
		$("#selectCount").html(count);
	})
}