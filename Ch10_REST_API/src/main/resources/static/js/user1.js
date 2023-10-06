$(function() {
	$("#btnUser1List").click(function() {
		$.ajax({
			url: "user1",
			type: "GET",
			dataType: "JSON",
			success: function(data) {
				console.log(data);
			}
		});
	});

	$("#btnUserSelect").click(function() {
		$.ajax({
			url: "user1/p101",
			type: "GET",
			dataType: "JSON",
			success: function(data) {
				console.log(data);
			}
		});
	});

	$("#btnUserRegister").click(function() {
		$.ajax({
			url: "user1",
			type: "POST",
			data: {
				uid: "a101",
				name: "강아지",
				hp: "010-4871-8747",
				age: 3
			},
			dataType: "JSON",
			success: function(data) {
				console.log(data);
			}
		});
	});

	$("#btnUserModify").click(function() {
		$.ajax({
			url: "user1",
			type: "PUT",
			data: {
				uid: "ppp",
				name: "고양이",
				hp: "010-1579-8747",
				age: 5
			},
			dataType: "JSON",
			success: function(data) {
				console.log(data);
			}
		});
	});

	$("#btnUserDelete").click(function() {
		$.ajax({
			url: "user1/ppp",
			type: "DELETE",
			dataType: "JSON",
			success: function(data) {
				console.log(data);
			}
		});
	});
});