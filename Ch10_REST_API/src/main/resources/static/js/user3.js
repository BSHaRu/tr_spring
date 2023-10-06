$(function() {
	$("#btnUser3List").click(function() {
		$.ajax({
			url: "user3",
			type: "GET",
			dataType: "JSON",
			success: function(data) {
				console.log(data);
			}
		});
	});

	$("#btnUser3Select").click(function() {
		$.ajax({
			url: "user3/p101",
			type: "GET",
			dataType: "JSON",
			success: function(data) {
				console.log(data);
			}
		});
	});

	$("#btnUser3Register").click(function() {
		$.ajax({
			url: "user3",
			type: "POST",
			data: {
				uid: "b101",
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

	$("#btnUser3Modify").click(function() {
		$.ajax({
			url: "user3",
			type: "PUT",
			data: {
				uid: "p101",
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

	$("#btnUser3Delete").click(function() {
		$.ajax({
			url: "user3/p102",
			type: "DELETE",
			dataType: "JSON",
			success: function(data) {
				console.log(data);
			}
		});
	});
});