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
        url: 'http://localhost:8080/application/api/v1/login/staff',
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
                window.location.replace("http://localhost:8080/application/staffView.html");
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

function getSubjectsResults() {
    $.ajax({
        url: 'http://localhost:8080/application/api/v1/departments/subjects/exams/results',
        type: 'GET',
        dataType: "json",
        contentType: "application/json",
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {
            console.log(data);
            document.getElementById("subtblbdy").innerHTML = '';
            for (var j in data) {
                var sub_key = j;
                var sub_val = data[j];
                $("#subtblbdy").append("<tr>" + '<td>' + sub_key + "</td>" + '<td>' + sub_val + "</tr>");
            }
            if (data.status === "failed") {
                $('#lbl').text('Result is not updated ....');
            }
        },
        error: function (jqXHR, textStatus, err) {
            alert('text status ' + textStatus + ', err ' + err);
            window.location.replace("http://localhost:8080/application/welcome.html");
        }
    });
}

function getStaffsResults() {
    $.ajax({
        url: 'http://localhost:8080/application/api/v1/staffs/exams/results',
        type: 'GET',
        dataType: "json",
        contentType: "application/json",
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {
            console.log(data);
            document.getElementById("tblbdy").innerHTML = '';
            for (var j in data) {
                var sub_key = j;
                var sub_val = data[j];
                $("#tblbdy").append("<tr>" + '<td>' + sub_key + "</td>" + '<td>' + sub_val + "</tr>");
            }
            if (data.status === "failed") {
                $('#lbl').text('Result is not updated ....');
            }
        },
        error: function (jqXHR, textStatus, err) {
            alert('text status ' + textStatus + ', err ' + err);
            window.location.replace("http://localhost:8080/application/welcome.html");
        }
    });
}

function getDepartmentsResults() {
    $.ajax({
        url: 'http://localhost:8080/application/api/v1/departments/exams/results',
        type: 'GET',
        dataType: "json",
        contentType: "application/json",
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {
            console.log(data);
            document.getElementById("depttblbdy").innerHTML = '';
            for (var j in data) {
                var sub_key = j;
                var sub_val = data[j];
                $("#depttblbdy").append("<tr>" + '<td>' + sub_key + "</td>" + '<td>' + sub_val + "</tr>");
            }
            if (data.status === "failed") {
                $('#lbl').text('Result is not updated ....');
            }
        },
        error: function (jqXHR, textStatus, err) {
            alert('text status ' + textStatus + ', err ' + err);
            window.location.replace("http://localhost:8080/application/welcome.html");
        }
    });
}

function updateMark() {
    var studentId = document.getElementById("stdId").value;
    var subjectId = document.getElementById("subId").value;
    var mark = document.getElementById("mark").value;

    if (studentId == "" || subjectId == "" || mark == "") {
        return;
    }
    var updateMark = {
        "subjectId": subjectId,
        "mark": mark
    };
    $.ajax({
        url: 'http://localhost:8080/application/api/v1/students/' + studentId + '/exams/mark',
        type: 'PUT',
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        data: updateMark,
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {
            console.log(data);
            if (data.status === "failed") {
                $('#lbl').text('Mark is not updated ....');
            } else {
                $('#lbl').text('Mark is succesfully updated ....');
            }
        },
        error: function (jqXHR, textStatus, err) {
            console.log(err);
            alert('text status ' + textStatus + ', err ' + err);
            window.location.replace("http://localhost:8080/application/welcome.html");
        }
    });
}

function getSubjects() {
    var studentId = document.getElementById("inputadd").value;
    console.log(studentId);
    $.ajax({
        url: 'http://localhost:8080/application/api/v1/departments/subjects?studentId=' + studentId,
        type: 'GET',
        dataType: "json",
        contentType: "application/json",
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {
            console.log(data);
            document.getElementById("divid").innerHTML = '';
            for (var i = 0; i < data.length; ++i) {
                $("#divid").append("<h id ='pdata" + i + "'>" + data[i] + "</h>  : <input type='text' id = 'addsub" + i + "' onchange = 'getNumber(this)'><br><br>");
                if (i == data.length - 1) {
                    $("#divid").append("<button class='btn btn-primary' onclick='addMarks()'>Submit Mark</button><br><br>");
                }
            }
        },
        error: function (jqXHR, textStatus, err) {
            console.log(err);
            alert('text status ' + textStatus + ', err ' + err);
            window.location.replace("http://localhost:8080/application/welcome.html");
        }
    });
}

function getNumber(input) {
    if (input.value < 0) input.value = 0;
    if (input.value > 100) input.value = 100;
}

function addMarks() {
    var studentId = document.getElementById("inputadd").value;
    var arr = [];
    var obj = {};
    for (var i = 0; i < 5; ++i) {
        var id = "#addsub" + i;
        var sub = "#pdata" + i;
        var a = $(sub).text();
        if ($(id).val() == "") {
            obj[a] = 0;
        } else {
            obj[a] = $(id).val();
        }
    }
    var jsonData = JSON.stringify(arr);
    jsonData = JSON.stringify(obj);
    $.ajax({
        url: 'http://localhost:8080/application/api/v1/students/' + studentId + '/exams/marks',
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: jsonData,
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {
            if (data.status === "failed") {
                $('#lbl').text('Mark is not added ....');
            } else {
                $('#lbl').text('Mark is succesfully added ....');
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
