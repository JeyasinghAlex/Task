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
        url: 'http://localhost:8080/application/api/v1/login/student',
        type: 'POST',
        dataType: "text",
        contentType: "application/x-www-form-urlencoded",
        data: loginData,
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {
            console.log(data);
            var b = data + "";
            if (b === "success") {
                window.location.replace("http://localhost:8080/application/studentView.html");
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

function getResult() {

    var id = document.getElementById("studentid").value;
    if (id == "") {
        return;
    }
    $.ajax({
        url: 'http://localhost:8080/application/api/v1/students/' + id + '/exams/results',
        type: 'GET',
        dataType: "json",
        contentType: "application/json",
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {
            document.getElementById("tblbdy").innerHTML = '';
            document.getElementById("lblname").innerHTML = data.name;
            document.getElementById("lbldept").innerHTML = data.department;
            for (var i = 0; i < data.subjects.length; ++i) {
                $("#tblbdy").append("<tr>" + '<td>' + data.subjects[i].name + "</td>" + '<td>' + data.subjects[i].mark + "</tr>");
            }
            if (data.length == 0) {
                $('#lbl').text('Result is not updated ....');
            }
        },
        error: function (jqXHR, textStatus, err) {
            alert('text status ' + textStatus + ', err ' + err);
            window.location.replace("http://localhost:8080/application/welcome.html");
        }
    });
}

function logout() {
    $.ajax({
        url: 'http://localhost:8080/application/api/v1/logout',
        type: 'GET',
        dataType: 'text',
        contentType: "application/json",
        cache: false,
        crossDomain: true,
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {
            console.log(data);
            window.location.replace("http://localhost:8080/application/welcome.html");
        }
        , error: function (jqXHR, textStatus, err) {
            alert('text status ' + textStatus + ', err ' + err);
            window.location.replace("http://localhost:8080/application/welcome.html");
        }
    });
}
