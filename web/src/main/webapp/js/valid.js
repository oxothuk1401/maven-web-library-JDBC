function validName() {
    var pattern = /[a-zA-zа-яА-я]{2,20}/;
    var registrName = $('#registrName');
    if (registrName.val() != '') {
        if (registrName.val().search(pattern) == 0) {
            return $('#registrLogin').attr('disabled', false)
        } else {
            return $('#registrLogin').attr('disabled', true);
        }
    } else {
        return $('#registrLogin').attr('disabled', true);
    }
}
function validLogin() {
    var pattern = /[a-zA-Z0-9]{4,20}/;
    var registrLogin = $('#registrLogin');
    if (registrLogin.val() != '') {
        if (registrLogin.val().search(pattern) == 0) {
            return $('#registrPass').attr('disabled', false)
        } else {
            return $('#registrPass').attr('disabled', true);
        }
    } else {
        return $('#registrPass').attr('disabled', true);
    }
}
function validPassword() {
    var pattern = /[a-zA-Z0-9]{4,20}/;
    var registrPass = $('#registrPass');
    if (registrPass.val() != '') {
        if (registrPass.val().search(pattern) == 0) {
            $('#registrEmail').attr('disabled', false);
            return $('#registrRepeatPass').attr('disabled', false);
        } else {
            $('#registrEmail').attr('disabled', true);
            return $('#registrRepeatPass').attr('disabled', true);
        }
    } else {
        $('#registrEmail').attr('disabled', true);
        return $('#registrRepeatPass').attr('disabled', true);
    }
}
function validRepeatPassword() {
    var registrRepeatPass = $('#registrRepeatPass').val();
    var registrPass = $('#registrPass').val();
    var message2 = $('#message2');
    if (registrPass != registrRepeatPass) {
        message2.show();
    } else {
        message2.hide();
    }


}

function validEmail() {
    var pattern = /^[a-z0-9_-]+@[a-z0-9-]+\.[a-z]{2,6}$/i;
    var registrEmail = $('#registrEmail');
    if (registrEmail.val() != '') {
        if (registrEmail.val().search(pattern) == 0) {
            return $('#submit').attr('disabled', false)
        } else {
            return $('#submit').attr('disabled', true);
        }
    } else {
        return $('#submit').attr('disabled', true);
    }
}

function getResString(newVal) {
    var res = '';
    for (var i = 0; i < newVal.length; i++) {
        res += newVal.charAt(i);
    }
    return res;
}

function clearValName(val, limit) {
    var newVal = val.replace(/[^a-zA-zа-яА-я]/, '');
    if (newVal == '') {
        return false;
    } else {
        return newVal.substring(0, limit);
    }
}

$(function () {
    $('#registrName').on('input', function () {
        var val = $(this).val(),
            limit = 20;
        if (val == '') return;
        var newVal = clearValName(val, limit);
        if (!newVal) {
            $(this).val('');
            return;
        }
        var res = getResString(newVal);
        $(this).val(res);
    });
});

function clearValLoginAndPass(val, limit) {
    var newVal = val.replace(/[^a-zA-z0-9]/, '');
    if (newVal == '') {
        return false;
    } else {
        return newVal.substring(0, limit);
    }
}
$(function () {
    $('#registrLogin').on('input', function () {

        var val = $(this).val(),
            limit = 20;
        if (val == '') return;
        var newVal = clearValLoginAndPass(val, limit);
        if (!newVal) {
            $(this).val('');
            return;
        }
        var res = getResString(newVal);
        $(this).val(res);
    });
});
$(function () {
    $('#registrPass').on('input', function () {
        var val = $(this).val(),
            limit = 20;
        if (val == '') return;
        var newVal = clearValLoginAndPass(val, limit);
        if (!newVal) {
            $(this).val('');
            return;
        }
        var res = getResString(newVal);
        $(this).val(res);
    });
});