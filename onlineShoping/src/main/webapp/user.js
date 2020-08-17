function validation() {
    var email = document.getElementById("user").value;
    var password = document.getElementById("pwd").value;
    console.log(email + ' -> ' + password);
    if (email == "" || password == "") {
        alert("Please Fill All Required Field");
        return;
    }
    login(email, password);
}

function login(email, password) {
    var loginData = {
        "email": email,
        "password": password
    };

    $.ajax({
        url: 'http://localhost:8080/onlineShoping/api/v1/login/user',
        type: 'POST',
        dataType: "text",
        contentType: "application/x-www-form-urlencoded",
        data: loginData,
        success: function (data, textStatus, request) {
            console.log(data);
            addTokenLocalStorage(data);
            window.location.replace("http://localhost:8080/onlineShoping/userView.html");
        },
        error: function (jqXHR, textStatus, err) {
            alert('invalid user name or password');
            window.location.replace("http://localhost:8080/onlineShoping/userLogin.html");
        }
    });
}

function addTokenLocalStorage(data) {
    localStorage.setItem("jwt", data);
}

function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : evt.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    if ($('#inputcon').val().length >= 10) {
        return false;
    }
    return true;
}

function register() {

    var name = document.getElementById("inputuser").value;
    var email = document.getElementById("inputemail").value;
    var contact = document.getElementById("inputcon").value;
    var password = document.getElementById("pwd").value;
    var conformPassword = document.getElementById("repwd").value;

    if (name == "" || email == "" || contact == "" || password == "" || conformPassword == "") {
        alert('All field required ... ')
        return false;
    }

    if (password !== conformPassword) {
        $('#lbl').text('Password is mismatch ....');
        return false;
    }

    var register = {
        "name": name,
        "email": email,
        "contactNumber": contact,
        "password": password
    }
   
    $.ajax({
        url: 'http://localhost:8080/onlineShoping/api/v1/registration',
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(register),
        success: function (data) {
            console.log(data);
            // return true;
            // alert('success');
            // window.location.replace("http://localhost:8082/onlineShoping/userIndex.html");
        },
        error: function (jqXHR, textStatus, err) {
            alert('text status ' + textStatus + ', err ' + err);
            return false;
        }
    });
    window.location.replace("http://localhost:8080/onlineShoping/userIndex.html");
}

function getAllProduct() {
    $.ajax({
        url: '/onlineShoping/api/v1/user/products',
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        headers: { "Authorization": 'Bearer ' + localStorage.getItem('jwt') },
        success: function (data) {
            console.log(data);
            if (data.status === "No Product Found") {
                alert('Product is not avalilable ...')
                window.location.replace("http://localhost:8080/onlineShoping/userView.html");
            }
            document.getElementById("producttbdy").innerHTML = '';
            for (var i = 0; i < data.length; i += 1) {
                if (data[i].quantity <= 5 && data[i].quantity > 0) {
                    $("#producttable").append("<tr>" + '<td> <input id =' + i + ' type = "checkbox"/> </td> <input type = "hidden" value = "' + data[i].id + '" id = "prod' + i + '">' + '<td>' + data[i].name + "</td>" + '<td>'
                        + data[i].brand + "</td>" + '<td>' + data[i].description + "</td>" + '<td>' + data[i].price + "</td>" + '<td> limited ( ' + data[i].quantity + " )</td>"
                        + '<td><input type="text" id = "txtinput' + i + '" style= "display:none;"/></td>' + "</tr>");
                } else {
                    $("#producttable").append("<tr>" + '<td> <input id =' + i + ' type = "checkbox"/></td>  <input type = "hidden" value = "' + data[i].id + '" id = "prod' + i + '">' + '<td>' + data[i].name + "</td>" + '<td>'
                        + data[i].brand + "</td>" + '<td>' + data[i].description + "</td>" + '<td>' + data[i].price + "</td>" + '<td> ' + "</td>"
                        + '<td><input type="text" id = "txtinput' + i + '"  style= "display:none;"/></td>' + "</tr>");
                }
            }
            $('input[type=checkbox]').on('change', function (i) {

                if (this.checked) {
                    $('#txtinput' + this.id).show();
                } else {
                    $('#txtinput' + this.id).hide();
                }
            })
        }
        , error: function (jqXHR, textStatus, err) {
            alert('Authorization Error ...');
            window.location.replace("http://localhost:8080/onlineShoping/userView.html");
        }
    });
}

function addCart() {
    var length = $('input[type=checkbox]').length
    var obj = {};
    for (var i = 0; i < length; ++i) {
        if ($('#' + i).is(":checked")) {
            var a = $('#prod' + i).val();
            var b = $('#txtinput' + i).val();
            addToCart(a, b);
        }
    }
    window.location.replace("http://localhost:8080/onlineShoping/userView.html");
}

function addToCart(productId, quantity) {
    var product = {
        "id": Number(productId),
        "quantity": Number(quantity)
    }
    console.log(product);
    $.ajax({
        url: 'http://localhost:8080/onlineShoping/api/v1/user/product',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(product),
        headers: { "Authorization": 'Bearer ' + localStorage.getItem('jwt') },
        success: function (data) {
            console.log(data);
            if (data === "success") {
                alert('Product successfully added your cart ...');
            } else {
                alert('Product not successfully added your cart ...');
            }
        }
        , error: function (jqXHR, textStatus, err) {
            alert('error message - ' + err);
            window.location.replace("http://localhost:8080/onlineShoping/userView.html");
        }
    });
}

function showCart() {

    $.ajax({
        url: 'http://localhost:8080/onlineShoping/api/v1/user/cart/products/',
        type: 'GET',
        dataType: "json",
        contentType: "application/json",
        headers: { "Authorization": 'Bearer ' + localStorage.getItem('jwt') },
        success: function (data) {
            console.log(data);
            if (data.status === "No Product Found") {
                alert('Cart is Empty ...');
                window.location.replace("http://localhost:8080/onlineShoping/userView.html");
            }
            document.getElementById("showcarttbdy").innerHTML = '';
            for (var i = 0; i < data.length; i += 1) {
                $("#carttable").append("<tr>" + '<td>' + data[i].name + "</td>" + '<td>'
                    + data[i].brand + "</td>" + '<td>' + data[i].quantity + "</td>" + '<td>' + data[i].price + "</td>" + "</tr>");
            }
        },
        error: function (jqXHR, textStatus, err) {
            alert('Cart is Empty ...');
        }
    });
}

function updateCart() {

    $.ajax({
        url: 'http://localhost:8080/onlineShoping/api/v1/user/cart/products/',
        type: 'GET',
        dataType: "json",
        contentType: "application/json",
        headers: { "Authorization": 'Bearer ' + localStorage.getItem('jwt') },
        success: function (data) {
            console.log(data);
            if (data.status === "No Product Found") {
                alert('Cart is Empty ...');
                window.location.replace("http://localhost:8080/onlineShoping/userView.html");
            }
            document.getElementById("updatetbdy").innerHTML = '';
            for (var i = 0; i < data.length; i += 1) {
                $("#updatetable").append("<tr>" + '<td> <input id =' + i + ' type = "checkbox"/> </td> <input type = "hidden" value = "' + data[i].id + '" id = "prods' + i + '">' + '<td>' + data[i].name + "</td>" + '<td>'
                    + data[i].brand + "</td>  <td><input type = 'number' id='test" + i + "' value = '" + data[i].quantity + "' disabled onchange = 'getNumber(this)'/> </td> <td>" + data[i].price + "</td> </tr>");
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
            alert('error message ' + err);
            window.location.replace("http://localhost:8080/onlineShoping/userView.html");
        }
    });
}

function getNumber(input) {
    if (input.value < 0) input.value = 0;
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
    window.location.replace("http://localhost:8080/onlineShoping/userView.html");
}

function addToUpdate(productId, quantity) {
    var product = {
        "id": Number(productId),
        "quantity": Number(quantity)
    }
    console.log(product);
    $.ajax({
        url: 'http://localhost:8080/onlineShoping/api/v1/user/product',
        type: 'PUT',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(product),
        headers: { "Authorization": 'Bearer ' + localStorage.getItem('jwt') },
        success: function (data) {
            console.log(data);
            if (data.status === "success") {
                alert(data.name + 'successfully updated ');
            } else {
                alert(data.name + 'not successfully updated ');
                window.location.replace("http://localhost:8080/onlineShoping/userView.html");
            }
        }
        , error: function (jqXHR, textStatus, err) {
            alert('error message ' + err);
            window.location.replace("http://localhost:8080/onlineShoping/userView.html");
        }
    });
}

function removeCart() {

    $.ajax({
        url: 'http://localhost:8080/onlineShoping/api/v1/user/cart/products/',
        type: 'GET',
        dataType: "json",
        contentType: "application/json",
        headers: { "Authorization": 'Bearer ' + localStorage.getItem('jwt') },
        success: function (data) {
            console.log(data);
            if (data.status === "No Product Found") {
                alert('Cart is Empty ...');
                window.location.replace("http://localhost:8080/onlineShoping/userView.html");
            }

            document.getElementById("removetbdy").innerHTML = '';
            for (var i = 0; i < data.length; i += 1) {
                $("#removetable").append("<tr>" + '<td> <input id =' + i + ' type = "checkbox"/> </td> <input type = "hidden" value = "' + data[i].id + '" id = "prods' + i + '">' + '<td>' + data[i].name + "</td>" + '<td>'
                    + data[i].brand + "</td><td>" + data[i].quantity + "</td><td>" + data[i].price + "</td> </tr>");
            }
        },
        error: function (jqXHR, textStatus, err) {
            alert('error message ' + err);
            window.location.replace("http://localhost:8080/onlineShoping/userView.html");
        }
    });
}

function remove() {
    var length = $('input[type=checkbox]').length
    var obj = {};
    for (var i = 0; i < length; ++i) {
        if ($('#' + i).is(":checked")) {
            var a = $('#prods' + i).val();
            console.log(a);
            removeFromCart(a);
        }
    }
    window.location.replace("http://localhost:8080/onlineShoping/userView.html");
}


function removeFromCart(productId) {
    var product = {
        "id": Number(productId)
    }
    console.log(product);
    $.ajax({
        url: 'http://localhost:8080/onlineShoping/api/v1/user/product',
        type: 'DELETE',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        headers: { "Authorization": 'Bearer ' + localStorage.getItem('jwt') },
        data: JSON.stringify(product),
        //xhrFields: { withCredentials: true },
        success: function (data) {
            console.log(data);
            if (data.status === "success") {
                alert('successfully removed product from cart ...')
            } else {
                alert('not successfully removed product ...')
                window.location.replace("http://localhost:8080/onlineShoping/userView.html");
            }
        }
        , error: function (jqXHR, textStatus, err) {
            alert('error message ' + err);
            window.location.replace("http://localhost:8080/onlineShoping/userView.html");
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
            localStorage.removeItem("jwt");
            window.location.replace("http://localhost:8080/onlineShoping/welcome.html");
        }
        , error: function (jqXHR, textStatus, err) {
            alert('text status ' + textStatus + ', err ' + err);
            window.location.replace("http://localhost:8080/onlineShoping/welcome.html");
        }
    });
}