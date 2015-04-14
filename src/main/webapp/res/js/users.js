$('#username').keyup(function() {
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
	getUserData('#user-data');
});

function firstInput() {
	if ($('#username').val() != '') {
		$('.btn-form').removeAttr('disabled');
	} else {
		$('.btn-form').attr('disabled', 'disabled');
	}
}

function saveorupdateData() {
	var jsonObject = {
		'id' : $('#userId').val(),
		'username' : $('#username').val(),
		'role' : $('#role').val(),
		'password' : $('#password').val()
	};

	var spinner = getSpinner();
	var request = $.ajax({
		type : 'POST',
		url : path + '/master/users/saveorupdate',
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