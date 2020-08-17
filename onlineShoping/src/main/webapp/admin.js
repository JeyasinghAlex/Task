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
		url: 'http://localhost:8080/onlineShoping/api/v1/login/admin',
		type: 'POST',
		dataType: "text",
		contentType: "application/x-www-form-urlencoded",
		data: loginData,
		success: function (data, textStatus, request) {
			console.log(data);
			addTokenLocalStorage(data);
			window.location.replace("http://localhost:8080/onlineShoping/adminView.html");
		},
		error: function (jqXHR, textStatus, err) {
			alert('invalid user name or password');
			window.location.replace("http://localhost:8080/onlineShoping/adminLogin.html");
		}
	});
}

function addTokenLocalStorage(data) {
	localStorage.setItem("adminjwt", data);
}

function getAllProduct() {
	$.ajax({
		url: 'http://localhost:8080/onlineShoping/api/v1/product',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json',
		headers: { "Authorization": 'Bearer ' + localStorage.getItem('adminjwt') },
		success: function (data) {
			console.log(data);
			if (data.status === "No Records Found") {
				alert('empty shop, please add  product');
				window.location.replace("http://localhost:8080/onlineShoping/adminView.html");
			}
			document.getElementById("producttbdy").innerHTML = '';
			for (var i = 0; i < data.length; i += 1) {
				$("#producttable").append("<tr>" + '<td>' + data[i].id + "</td>" + '<td>' + data[i].name + "</td>" + '<td>'
					+ data[i].brand + "</td>" + '<td>' + data[i].description + "</td>" + '<td>' + data[i].price + "</td>" + '<td>' + data[i].quantity + "</td>" + "</tr>");
			}
		}
		, error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			window.location.replace("http://localhost:8080/onlineShoping/adminView.html");
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
		url: 'http://localhost:8080/onlineShoping/api/v1/product',
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		data: JSON.stringify(product),
		headers: { "Authorization": 'Bearer ' + localStorage.getItem('adminjwt') },
		success: function (data) {
			console.log(data);
			alert('successfully product added ...');
			window.location.replace("http://localhost:8080/onlineShoping/adminView.html");
		}
		, error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			window.location.replace("http://localhost:8080/onlineShoping/adminView.html");
		}
	});
}

function getAll() {
	$.ajax({
		url: 'http://localhost:8080/onlineShoping/api/v1/product/',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		headers: { "Authorization": 'Bearer ' + localStorage.getItem('adminjwt') },
		success: function (data) {
			console.log(data);
			if (data.length == 0) {
				alert('empty shop, please add  product');
				window.location.replace("http://localhost:8080/onlineShoping/adminView.html");
			}
			document.getElementById("dropdown").innerHTML = '';
			for (var i = 0; i < data.length; i++) {
				$('#dropdown').append('<option value = ' + data[i].id + '>' + data[i].name + '</option');
			}

		}
		, error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			window.location.replace("http://localhost:8080/onlineShoping/adminView.html");
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
		url: 'http://localhost:8080/onlineShoping/api/v1/product/' + id,
		type: 'DELETE',
		dataType: 'json',
		contentType: 'application/json',
		headers: { "Authorization": 'Bearer ' + localStorage.getItem('adminjwt') },
		success: function (data) {
			console.log(data);
			if (data.status === "success") {
				alert('successfully product removed');
				window.location.replace("http://localhost:8080/onlineShoping/adminView.html");
			} else {
				alert('not successfully product removed');
				window.location.replace("http://localhost:8080/onlineShoping/adminView.html");
			}
		}
		, error: function (jqXHR, textStatus, err) {
			alert('Invalid product id, text status ' + textStatus + ', err ' + err);
			window.location.replace("http://localhost:8080/onlineShoping/adminView.html");
		}
	});
}

function getProductById() {

	var id = document.getElementById("inputprodid").value;

	if (id == "") {
		$('#lbl').text('Please enter the product Id ....');
		return;
	}
	console.log(id);
	$.ajax({
		url: 'http://localhost:8080/onlineShoping/api/v1/product/' + id,
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		headers: { "Authorization": 'Bearer ' + localStorage.getItem('adminjwt') },
		success: function (data) {
			console.log(data);
			if (data.length == 0) {
				alert('product not found');
			}
			document.getElementById("producttbdy").innerHTML = '';
			$("#producttable").append("<tr>" + '<td>' + data.id + "</td>" + '<td>' + data.name
				+ "</td>" + '<td>' + data.brand + "</td>" + '<td>' + data.description + "</td>" + '<td>'
				+ data.price + "</td>" + '<td>' + data.quantity + "</tr>");
		}
		, error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			window.location.replace("http://localhost:8080/onlineShoping/adminView.html");
		}
	});
}

function updateProduct() {

	$.ajax({
		url: 'http://localhost:8080/onlineShoping/api/v1/product',
		type: 'GET',
		dataType: "json",
		contentType: "application/json",
		headers: { "Authorization": 'Bearer ' + localStorage.getItem('adminjwt') },
		success: function (data) {
			console.log(data);
			if (data.status === "No Records Found") {
				alert('Empty shop, plese add some product');
				window.location.replace("http://localhost:8080/onlineShoping/adminView.html");
			}
			document.getElementById("updatetbdy").innerHTML = '';
			for (var i = 0; i < data.length; i += 1) {
				$("#updatetable").append("<tr>" + '<td> <input id =' + i + ' type = "checkbox"/> </td> <input type = "hidden" value = "' + data[i].id + '" id = "prods' + i + '">' + "<td>" + data[i].id + "</td>" + '<td>' + data[i].name + "</td>" + '<td>'
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
			window.location.replace("http://localhost:8080/onlineShoping/adminView.html");
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
	window.location.replace("http://localhost:8080/onlineShoping/adminView.html");
}

function addToUpdate(productId, quantity) {
	var product = {
		"quantity": Number(quantity)
	}
	console.log(product);
	$.ajax({
		url: 'http://localhost:8080/onlineShoping/api/v1/product/' + productId,
		type: 'PUT',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		data: JSON.stringify(product),
		headers: { "Authorization": 'Bearer ' + localStorage.getItem('adminjwt') },
		success: function (data) {
			console.log(data);
			if (data.length != 0) {
				alert('successfully updated product ..')
			} else {
				alert('not successfully updated product ..')
			}
		}
		, error: function (jqXHR, textStatus, err) {
			alert('product not added, text status ' + textStatus + ', err ' + err);
			window.location.replace("http://localhost:8080/onlineShoping/adminView.html");
		}
	});
}

function logout() {
    $.ajax({
        url: 'http://localhost:8080/onlineShoping/api/v1/logout',
        type: 'GET',
        dataType: 'text',
        contentType: "application/json",
        headers: { "Authorization": 'Bearer ' + localStorage.getItem('jwt') },
        success: function (data) {
            console.log(data);
            localStorage.removeItem("adminjwt");
            window.location.replace("http://localhost:8080/onlineShoping/welcome.html");
        }
        , error: function (jqXHR, textStatus, err) {
            alert('text status ' + textStatus + ', err ' + err);
            window.location.replace("http://localhost:8080/onlineShoping/welcome.html");
        }
    });
}