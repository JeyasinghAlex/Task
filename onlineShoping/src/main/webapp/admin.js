function validation() {
	var userName = document.getElementById("user").value;
	var password = document.getElementById("pwd").value;
	console.log(userName + ' -> ' + password);
	if (userName == "" || password == "") {
		alert("Please Fill All Required Field");
		return;
	}
	login(userName, password);
}

function login(userName, password) {
	var loginData = {
		"userName": userName,
		"password": password
	};

	$.ajax({
		url: 'http://localhost:8080/application/api/v1/login/admin',
		type: 'POST',
		dataType: "text",
		contentType: "application/x-www-form-urlencoded",
		data: loginData,
		success: function (data) {
			var b = data + "";
			if (b === "success") {
				window.location.replace("http://localhost:8080/application/adminView.html");
			} else {
				$('#lbl').text('Invalid user name or password ....');
			}
		},
		error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			window.location.replace("http://localhost:8080/application/welcome.html");
		}
	});
}

function getAllProduct() {
	$.ajax({
		url: 'http://localhost:8082/onlineShoping/api/v1/product',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json',
		//xhrFields: { withCredentials: true },
		success: function (data) {
			console.log(data);
			document.getElementById("producttbdy").innerHTML = '';
			for (var i = 0; i < data.length; i += 1) {
				$("#producttable").append("<tr>" + '<td>' + data[i].id + "</td>" + '<td>' + data[i].name + "</td>" + '<td>'
					+ data[i].brand + "</td>" + '<td>' + data[i].description + "</td>" + '<td>' + data[i].price + "</td>" + '<td>' + data[i].quantity + "</td>" + "</tr>");
			}
		}
		, error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			//window.location.replace("http://localhost:8080/application/welcome.html");
		}
	});
}

function addProduct() {

	var name = document.getElementById("inputname").value;
	var brand = document.getElementById("inputbrand").value;
	var description = document.getElementById("inputdes").value;
	var price = document.getElementById("inputprice").value;
	var quantity = document.getElementById("inputqty").value;

	if (name == "" || brand == "" || description == "" || price == "" || quantity == "") {
		return;
	}
	var product = {
		"name": name,
		"brand": brand,
		"description": description,
		"price": Number(price),
		"quantity": Number(quantity)
	}
	console.log(product);
	$.ajax({
		url: 'http://localhost:8082/onlineShoping/api/v1/product',
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		data: JSON.stringify(product),
		//xhrFields: { withCredentials: true },
		success: function (data) {
			console.log(data);
		}
		, error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			//window.location.replace("http://localhost:8080/application/welcome.html");
		}
	});
}

// function updateProduct() {

// 	var id = document.getElementById("inputid").value;
// 	var quantity = document.getElementById("inputqunatity").value;

// 	if (id == "" || quantity == "") {
// 		return;
// 	}
// 	var product = {
// 		"quantity": Number(quantity)
// 	}
// 	console.log(product);
// 	$.ajax({
// 		url: 'http://localhost:8082/onlineShoping/api/v1/product/' + id,
// 		type: 'PUT',
// 		dataType: 'json',
// 		contentType: 'application/json; charset=utf-8',
// 		data: JSON.stringify(product),
// 		//xhrFields: { withCredentials: true },
// 		success: function (data) {
// 			console.log(data);
// 			document.getElementById("producttbdy").innerHTML = '';
// 			$("#producttable").append("<tr>" + '<td>' + data.id + "</td>" + '<td>' + data.name
// 				+ "</td>" + '<td>' + data.brand + "</td>" + '<td>' + data.description + "</td>" + '<td>'
// 				+ data.price + "</td>" + '<td>' + data.quantity + "</tr>");
// 		}
// 		, error: function (jqXHR, textStatus, err) {
// 			alert('text status ' + textStatus + ', err ' + err);
// 			//window.location.replace("http://localhost:8080/application/welcome.html");
// 		}
// 	});
// }

function getAll() {
	$.ajax({
		url: 'http://localhost:8082/onlineShoping/api/v1/product/',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		//xhrFields: { withCredentials: true },
		success: function (data) {
			console.log(data);
			document.getElementById("dropdown").innerHTML = '';
			for (var i = 0; i < data.length; i++) {
				$('#dropdown').append('<option value = ' + data[i].id + '>' + data[i].name + '</option');
			}

		}
		, error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			//window.location.replace("http://localhost:8080/application/welcome.html");
		}
	});
}

function removeProduct() {

	var id = document.getElementById("dropdown").value;

	if (id == "") {
		return;
	}
	console.log(id);
	$.ajax({
		url: 'http://localhost:8082/onlineShoping/api/v1/product/' + id,
		type: 'DELETE',
		dataType: 'json',
		contentType: 'application/json',
		//xhrFields: { withCredentials: true },
		success: function (data) {
			console.log(data);
		}
		, error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			//window.location.replace("http://localhost:8080/application/welcome.html");
		}
	});
}

function getProductById() {

	var id = document.getElementById("inputprodid").value;

	if (id == "") {
		return;
	}
	console.log(id);
	$.ajax({
		url: 'http://localhost:8082/onlineShoping/api/v1/product/' + id,
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		//xhrFields: { withCredentials: true },
		success: function (data) {
			console.log(data);
			document.getElementById("producttbdy").innerHTML = '';
			$("#producttable").append("<tr>" + '<td>' + data.id + "</td>" + '<td>' + data.name
				+ "</td>" + '<td>' + data.brand + "</td>" + '<td>' + data.description + "</td>" + '<td>'
				+ data.price + "</td>" + '<td>' + data.quantity + "</tr>");
		}
		, error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			//window.location.replace("http://localhost:8080/application/welcome.html");
		}
	});
}

function updateProduct() {

	$.ajax({
		url: 'http://localhost:8082/onlineShoping/api/v1/product',
		type: 'GET',
		dataType: "json",
		contentType: "application/json",
		success: function (data) {
			console.log(data);
			document.getElementById("updatetbdy").innerHTML = '';
			for (var i = 0; i < data.length; i += 1) {
				$("#updatetable").append("<tr>" + '<td> <input id =' + i + ' type = "checkbox"/> </td> <input type = "hidden" value = "' + data[i].id + '" id = "prods' + i + '">' + "<td>" + data[i].id + "</td>" +'<td>' + data[i].name + "</td>" + '<td>'
					+ data[i].brand + "</td>  <td><input type = 'text' id='test" + i + "' value = '" + data[i].quantity + "' disabled/> </td> <td>" + data[i].price + "</td> <td>" + data[i].description + "</td> </tr>");
			}
			$('input[type=checkbox]').on('change', function () {

				if (this.checked) {
					$('#test' + this.id).prop('disabled', false);
				} else {
					$('#test' + this.id).prop('disabled', true);
				}
			})
		},
		error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			return false;
			// window.location.replace("http://localhost:8080/application/welcome.html");
		}
	});
}

function update() {
	var length = $('input[type=checkbox]').length
	var obj = {};
	for (var i = 0; i < length; ++i) {
		if ($('#' + i).is(":checked")) {
			var a = $('#prods' + i).val();
			var b = $('#test' + i).val();
			console.log(a);
			console.log(b);
			addToUpdate(a, b);
		}
	}
	// window.location.replace("/userView.html");
}

function addToUpdate(productId, quantity) {
	var product = {
		"quantity": Number(quantity)
	}
	console.log(product);
	$.ajax({
		url: 'http://localhost:8082/onlineShoping/api/v1/product/'+productId,
		type: 'PUT',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		data: JSON.stringify(product),
		//xhrFields: { withCredentials: true },
		success: function (data) {
			console.log(data);
		}
		, error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			//window.location.replace("http://localhost:8080/application/welcome.html");
		}
	});
}