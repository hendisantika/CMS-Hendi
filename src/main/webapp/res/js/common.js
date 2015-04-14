$(document).ready(function() {
	resetButton();

	$('#menu-toggle').click(function(e) {
		toggleObject(e, '#wrapper');
	});

	$('#form-reset').click(function() {
		resetForm();
		resetButton();
	});
});

function toggleObject(e, selector) {
	e.preventDefault();
	$(selector).toggleClass('toggled');
}

function resetForm() {
	$('.btn-form').attr('disabled', 'disabled');
	$('input').val('');
	$('select').prop('selectedIndex', 0);
}

function getSpinner() {
	var options = {
		lines : 11,
		length : 10,
		width : 3,
		radius : 10,
		corners : 1,
		top : '50%',
		left : '50%',
		right : '50%'
	};

	return new Spinner(options);
}

function getAlertNotification(titleVal, textVal, typeVal, buttonVal, classVal) {
	sweetAlert({
		title : titleVal,
		text : textVal,
		type : typeVal,
		confirmButtonText : buttonVal,
		confirmButtonClass : classVal
	});
}

function genericFormatter(value) {
	if (value != '') {
		return value;
	} else {
		return 'N/A';
	}
}

function actionFormatter(value, row, index) {
	return [ '<a class="edit" href="javascript:void(0)" title="Edit">',
	         	'<i class="glyphicon glyphicon-edit yellow pull-left"></i>',
	         '</a>',
	         '<a class="delete" href="javascript:void(0)" title="Delete">',
	         	'<i class="glyphicon glyphicon-remove red pull-right"></i>',
	         '</a>' ].join('');
}

window.actionEvents = {
	'click .edit' : function(e, value, row, index) {
		var jsonObject = JSON.stringify(row);
		updateData(jsonObject);
		updateButton();
	},
	'click .delete' : function(e, value, row, index) {
		var jsonObject = JSON.stringify(row);
		deleteData(jsonObject);
	}
};

function refreshTable(selector) {
	$(selector).bootstrapTable('refresh');
}

function resetButton() {
	$('#form-save').show();
	$('#form-update').hide();
}

function updateButton() {
	$('#search-modal').modal('hide');
	$('#form-save').hide();
	$('#form-update').removeAttr('disabled');
	$('#form-update').show();
}