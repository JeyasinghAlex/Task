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

function addDepartment() {
	var deptName = document.getElementById("dept").value;
	var sub1 = document.getElementById("sub1").value;
	var sub2 = document.getElementById("sub2").value;
	var sub3 = document.getElementById("sub3").value;
	var sub4 = document.getElementById("sub4").value;
	var sub5 = document.getElementById("sub5").value;
	var deptData = JSON.stringify({
		"department": deptName,
		"subjects": [sub1, sub2, sub3, sub4, sub5]
	});
	$.ajax({
		url: 'http://localhost:8080/application/api/v1/department',
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: deptData,
		xhrFields: { withCredentials: true },
		success: function (data) {
			console.log(data);
			if (data.status === "failed") {
				$('#lbl').text('Department is not successfully added ....');
			} else {
				$('#lbl').text('Department is Successfully Added ...');
			}
		},
		error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			window.location.replace("http://localhost:8080/application/welcome.html");
		}
	});
}

var numberOfSubjects = 0;
function showSubjects() {

	$.ajax({
		url: 'http://localhost:8080/application/api/v1/subjects',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json',
		xhrFields: { withCredentials: true },
		success: function (data) {
			console.log(data);
			numberOfSubjects = data.length;
			var i = 0;
			document.getElementById("checkboxs").innerHTML = '';
			if (numberOfSubjects > 0) {
				$.each(data, function () {
					$("#checkboxs").append($("<label>").text(data[i]).prepend(
						$("<input type = 'checkbox' id = 'chkbox" + i + "' style = 'margin-left:16px' name = 'boxname'>").attr('type', 'checkbox').val(data[i])));

					if (i == data.length - 1) {
						$("#checkboxs").append("<br><br><button onclick='addStaff()'>Submit</button><br><br>");
					}
					++i;
				});
			}
			$('input[type=checkbox]').on('click', function () {
				var length = $('input[type=checkbox]:checked').length
				if (length > 5) {
					$(this).prop('checked', false);
					alert('maximum subject 5');
				}
			});
		},
		error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			window.location.replace("http://localhost:8080/application/welcome.html");
		}
	});
}

function addStaff() {

	var staffName = document.getElementById("stf").value;
	var subjects = [];
	for (var i = 0; i < numberOfSubjects; ++i) {
		var id = "#chkbox" + i;
		if ($(id).is(":checked")) {
			subjects.push($(id).val())
		}
	}
	var subjectData = JSON.stringify({
		"name": staffName,
		"subjects": subjects
	});
	$.ajax({
		url: 'http://localhost:8080/application/api/v1/staff',
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: subjectData,
		xhrFields: { withCredentials: true },
		success: function (data) {
			console.log(data);
			if (data.status === "failed") {
				$('#lbl').text('Staff is not successfully added with subjects ....');
			} else {
				$('#lbl').text('Staff is Successfully Added with subject ...');
			}
		},
		error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			window.location.replace("http://localhost:8080/application/welcome.html");
		}
	});
}

function getDepartments() {
	$.ajax({
		url: 'http://localhost:8080/application/api/v1/departments',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json',
		xhrFields: { withCredentials: true },
		success: function (data) {
			console.log(data);
			document.getElementById("dropdown").innerHTML = '';
			for (var i = 0; i < data.length; i++) {
				$('#dropdown').append('<option value = ' + data[i] + '>' + data[i] + '</option');
			}
		}
		, error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			window.location.replace("http://localhost:8080/application/welcome.html");
		}
	});
}

function addStudent() {

	var studentName = document.getElementById("studnt").value;
	var department = document.getElementById("dropdown").value;
	var student = {
		"name": studentName,
		"department": department
	}
	$.ajax({
		url: 'http://localhost:8080/application/api/v1/student',
		type: 'POST',
		dataType: 'json',
		contentType: "application/x-www-form-urlencoded",
		data: student,
		xhrFields: { withCredentials: true },
		success: function (data) {
			console.log(data);
			if (data.status === "failed") {
				$('#lbl').text('Student is not successfully added ....');
			} else {
				$('#lbl').text('Student is Successfully added  ...');
			}
		}
		, error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			window.location.replace("http://localhost:8080/application/welcome.html");
		}
	});
}

function getResults() {

	$.ajax({
		url: 'http://localhost:8080/application/api/v1/exams/results',
		type: 'GET',
		dataType: 'json',
		contentType: "application/json",
		xhrFields: { withCredentials: true },
		success: function (data) {
			console.log(data);
			document.getElementById("rslttblbdy").innerHTML = '';
			for (var i = 0; i < data.length; i += 1) {
				$("#rslttbl").append("<tr>" + '<td>' + data[i].rank + "</td>" + '<td>' + data[i].name + "</td>" + '<td>' + data[i].department + "</td>" + "</tr>");
			}
			if (data.length == 0) {
				$('#lbl').text('Result is not updated ...');
			}
		}
		, error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			window.location.replace("http://localhost:8080/application/welcome.html");
		}
	});
}

function getCollegeTopResult() {
	var limit = document.getElementById("limit").value;
	if (limit == "") {
		return;
	}
	$.ajax({
		url: 'http://localhost:8080/application/api/v1/exams/results/limits?limit=' + limit,
		type: 'GET',
		dataType: 'json',
		contentType: "application/json",
		xhrFields: { withCredentials: true },
		success: function (data) {
			console.log(data);
			document.getElementById("toptbdy").innerHTML = '';
			for (var i = 0; i < data.length; i += 1) {
				$("#toptbl").append("<tr>" + '<td>' + data[i].rank + "</td>" + '<td>' + data[i].name + "</td>" + '<td>' + data[i].department + "</td>" + "</tr>");
			}
			if (data.length == 0) {
				$('#lbl').text('Result is not updated ...');
			}
		}
		, error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			window.location.replace("http://localhost:8080/application/welcome.html");
		}
	});
}

function getDepartmentsTopResults() {
	var limit = document.getElementById("toplimit").value;
	if (limit == "") {
		return;
	}
	$.ajax({
		url: 'http://localhost:8080/application/api/v1/departments/exams/results/limits?limit=' + limit,
		type: 'GET',
		dataType: 'json',
		contentType: "application/json",
		xhrFields: { withCredentials: true },
		success: function (data) {
			console.log(data);
			document.getElementById("topdepttbdy").innerHTML = '';
			for (var i = 0; i < data.length; i += 1) {
				$("#toptdeptbl").append("<tr>" + '<td>' + data[i].rank + "</td>" + '<td>' + data[i].name + "</td>" + '<td>' + data[i].department + "</td>" + "</tr>");
			}
			if (data.length == 0) {
				$('#lbl').text('Result is not updated ...');
			}
		}
		, error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			window.location.replace("http://localhost:8080/application/welcome.html");
		}
	});
}

function getSubjectsHighestMark() {
	$.ajax({
		url: 'http://localhost:8080/application/api/v1/departments/subjects/exams/results/mark',
		type: 'GET',
		dataType: 'json',
		contentType: "application/json",
		xhrFields: { withCredentials: true },
		success: function (data) {
			console.log(data);
			var html = "<table class = 'table'>";
			for (var i = 0; i < data.length; ++i) {
				html += "<tr>";
				for (var j = 0; j < data[i].length; ++j) {
					html += "<td>" + data[i][j] + "</td>";
				}
				html += "</tr>";
			}
			html += "</table>";
			document.getElementById("demo").innerHTML = html;
			if (data.length == 0) {
				$('#lbl').text('Result is not updated ...');
			}
		}
		, error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			window.location.replace("http://localhost:8080/application/welcome.html");
		}
	});
}

function getNthRankStudent() {
	var rank = document.getElementById("inputrank").value;
	if (rank == "") {
		return;
	}

	$.ajax({
		url: 'http://localhost:8080/application/api/v1/exams/results/' + rank,
		type: 'GET',
		dataType: 'json',
		contentType: "application/json",
		xhrFields: { withCredentials: true },
		success: function (data) {
			console.log(data);
			document.getElementById("ranktbdy").innerHTML = '';
			for (var i = 0; i < data.length; i += 1) {
				$("#ranktbl").append("<tr>" + '<td>' + data[i].rank + "</td>" + '<td>' + data[i].name + "</td>" + '<td>' + data[i].department + "</td>" + "</tr>");
			}
			if (data.length == 0) {
				$('#lbl').text('Rank is not available ...');
			}
		}
		, error: function (jqXHR, textStatus, err) {
			alert('text status ' + textStatus + ', err ' + err);
			window.location.replace("http://localhost:8080/application/welcome.html");
		}
	});
}

function removeStudent() {
	var studentId = document.getElementById("inputremove").value;
	if (studentId == "") {
		return;
	}

	$.ajax({
		url: 'http://localhost:8080/application/api/v1/student/' + studentId,
		type: 'DELETE',
		dataType: 'json',
		contentType: "application/json",
		xhrFields: { withCredentials: true },
		success: function (data) {
			console.log(data);
			if (data.status === "failed") {
				$('#lbl').text('Invalid Id, Please enter valid Id...');
			} else {
				$('#lbl').text('Successfully Id deleted...');
			}
		}
		, error: function (jqXHR, textStatus, err) {
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
