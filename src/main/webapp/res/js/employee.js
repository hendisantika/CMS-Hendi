$(document).ready(function() {
	openDatepicker('#birthdate-toggle');

	$('#phone').numeric({
		allowPlus : true,
		allowMinus : false,
		allowThouSep : false,
		allowDecSep : false
	});
});

$('#name').keyup(function() {
	firstInput();
});

$('#form-save').click(function(e) {
	e.preventDefault();
	saveorupdateData();
});

$('#form-update').click(function(e) {
	e.preventDefault();
	saveorupdateData();
});

$('#modal-toggle').click(function(e) {
	toggleObject(e, '#search-modal');
	getEmployeeData('#employee-data');
});

function firstInput() {
	if ($('#name').val() != '') {
		$('.btn-form').removeAttr('disabled');
	} else {
		$('.btn-form').attr('disabled', 'disabled');
	}
}

function openDatepicker(selector) {
	$(selector).datepicker({
		autoclose : true,
		format : 'd MM yyyy'
	});
}

function getDateFormat(param, dateFormat) {
	if (param != '') {
		return moment(param).format(dateFormat);
	}
}

function saveorupdateData() {
	var birthdate = $('#birthdate').val();
	var birthdateDB = getDateFormat(birthdate, 'YYYY-MM-DD');
	var jsonObject = {
		'id' : $('#employeeId').val(),
		'name' : $('#name').val(),
		'gender' : $('#gender').val(),
		'birthdate' : birthdateDB,
		'phone' : $('#phone').val(),
		'email' : $('#email').val()
	};

	var spinner = getSpinner();
	var request = $.ajax({
		type : 'POST',
		url : path + '/master/employee/saveorupdate',
		contentType : 'application/json; charset=UTF-8',
		data : JSON.stringify(jsonObject),
		beforeSend : function() {
			var target = $('body').get(0);
			spinner.spin(target);
		}
	});

	request.success(function(data) {
		spinner.stop();
		switch (data.response) {
		case 'success': {
			getAlertNotification('Success!', 'Data successfully saved!', 'success', 'OK', 'btn-success');
			resetForm();
			resetButton();
			break;
		}
		case 'fail': {
			getAlertNotification('Failed!', 'Failed saving data!', 'error', 'Cancel', 'btn-danger');
			break;
		}
		}
	});

	request.error(function(textStatus, errorThrown) {
		spinner.stop();
		getAlertNotification(errorThrown, textStatus, 'warning', 'Dismiss', 'btn-warning');
	});
}

function updateData(jsonObject) {
	var employees = JSON.parse(jsonObject);
	var birthdate = birthdateFormatter(employees.birthdate, 'form');

	$('#employeeId').val(employees.id);
	$('#name').val(employees.name);
	$('#birthdate').val(birthdate);
	$('#gender').val(employees.gender);
	$('#phone').val(employees.phone);
	$('#email').val(employees.email);
}

function deleteData(jsonObject) {
	var spinner = getSpinner();
	var request = $.ajax({
		type : 'POST',
		url : path + '/master/employee/delete',
		contentType : 'application/json; charset=UTF-8',
		data : jsonObject,
		beforeSend : function() {
			var target = $('#employee-data').get(0);
			spinner.spin(target);
		}
	});

	request.success(function(data) {
		spinner.stop();
		switch (data.response) {
		case 'success': {
			getAlertNotification('Success!', 'Data successfully deleted!', 'success', 'OK', 'btn-success');
			refreshTable('#employee-data');
			break;
		}
		case 'fail': {
			getAlertNotification('Failed!', 'Failed deleting data!', 'error', 'Cancel', 'btn-danger');
			break;
		}
		}
	});

	request.error(function(textStatus, errorThrown) {
		spinner.stop();
		getAlertNotification(errorThrown, textStatus, 'warning', 'Dismiss', 'btn-warning');
	});
}

function getEmployeeData(selector) {
	$(selector).bootstrapTable('refresh').bootstrapTable({
		height : 400,
		method : 'GET',
		pagination : true,
		search : true,
		searchAlign : 'left',
		showRefresh : true,
		url : path + '/master/employee/search',

		columns : [ {
			align : 'center',
			field : 'action',
			formatter : 'actionFormatter',
			events : 'actionEvents',
			title : 'Action'
		}, {
			field : 'id',
			visible : false
		}, {
			align : 'left',
			field : 'name',
			title : 'Name'
		}, {
			align : 'center',
			field : 'gender',
			title : 'Gender'
		}, {
			align : 'left',
			field : 'birthdate',
			formatter : 'birthdateFormatter',
			title : 'Birthdate'
		}, {
			align : 'center',
			field : 'phone',
			formatter : 'genericFormatter',
			title : 'Phone Number'
		}, {
			align : 'left',
			field : 'email',
			formatter : 'genericFormatter',
			title : 'Email'
		} ]
	});
}

function birthdateFormatter(value, type) {
	if (value != null) {
		return getDateFormat(value, 'D MMMM YYYY');
	} else {
		switch (type) {
		case 'form': {
			return '';
		}
		default: {
			return 'N/A';
		}
		}
	}
}