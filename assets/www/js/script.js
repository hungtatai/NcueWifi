
/*
 * 原本要做自動登入，不過後來沒時間改這個了XD
 * 等待有緣人(?)改完它吧    by Honda
 */

LOGIN_URL = "http://securelogin.arubanetworks.com/auth/index.html/u";
LOGOUT_URL = "http://securelogin.arubanetworks.com/cgi-bin/login?cmd=logout"


function refreshCheckbox() {
	$.event.special.swipe.horizontalDistanceThreshold = 130;
	$('input[type="checkbox"]').checkboxradio("refresh"); 
	$.storage.set('autologin',  $('#autologin').attr('checked') == 'checked');
	$.storage.set('persistent', $('#persistent').attr('checked') == 'checked');
}


document.addEventListener("deviceready", onDeviceReady, false);
function onDeviceReady(){
	document.addEventListener("pause", onDevicePause, false);
	init();
};

function onDevicePause() {
	if ( $('#persistent').attr('checked') != 'checked') {
		$.storage.set('account', '');
		$.storage.set('password', '');
		$('#account').val($.storage.get('account', ''));
		$('#password').val($.storage.get('password', ''));
	}
}

function init(){
	
	$('#autologin').attr('checked', $.storage.get('autologin', false));
	$('#persistent').attr('checked', $.storage.get('persistent', false));
	$('input[type="checkbox"]').checkboxradio("refresh"); 
	
	
	
	if ( $('#persistent').attr('checked') != 'checked') {
		$.storage.set('account', '');
		$.storage.set('password', '');
	}
	else {
		$('#account').val($.storage.get('account', ''));
		$('#password').val($.storage.get('password', ''));
	}
	
	$('#autologin').click(function(){
	
		if( $('#autologin').attr('checked') == 'checked' && $('#persistent').attr('checked') != 'checked' ) {
			$('#persistent').attr('checked', true);
		}
		refreshCheckbox();
	});
	
	$('#persistent').click(function(){
		
		if( $('#autologin').attr('checked') == 'checked' && $('#persistent').attr('checked') != 'checked' ) {
			$('#autologin').attr('checked', false); 
		}
		refreshCheckbox();
	});
	
	$('#loginbtn').click(function() {
	
		var postData = {
			'user': $.storage.get('account',''),
			'password': $.storage.get('password',''),
			'cmd': 'authenticate'
		};
		
		if ( navigator.network.connection.type != Connection.WIFI )
		{
			navigator.notification.alert('Wifi連線未開啟，請再次確認。', null, '連線狀態通知');
			return;
		}
		if( postData.user == '' || postData.password == '')
		{
			navigator.notification.alert('帳號或密碼未輸入', null, '訊息');
			return;
		}
		
	
		$.blockUI({message:null});
		$.mobile.showPageLoadingMsg();
		
		$.post(LOGIN_URL, postData).done(function(){
			$.unblockUI();
			$.mobile.hidePageLoadingMsg();
			navigator.notification.progressStop();
			navigator.notification.alert('登入成功，已可開始使用校園網路！', null, '訊息');
		}).fail(function(){
			$.unblockUI();
			$.mobile.hidePageLoadingMsg();
			navigator.notification.progressStop();
			navigator.notification.alert('登入失敗！', null, '訊息');
		});
	});
	
	$('#logoutbtn').click(function() {
		if ( navigator.network.connection.type != Connection.WIFI )
		{
			navigator.notification.alert('Wifi連線未開啟，請再次確認。', null, '連線狀態通知');
			return;
		}	
	
	
		$.blockUI({message:null});
		$.mobile.showPageLoadingMsg();
		$.get(LOGOUT_URL).done(function(){
			$.unblockUI();
			$.mobile.hidePageLoadingMsg();
			navigator.notification.alert('登出成功！', null, '訊息');
		});
	});
	
	$('#account').keyup(function() {
		$.storage.set('account', $(this).val());
	});
	
	$('#password').keyup(function() {
		$.storage.set('password', $(this).val());
	});
	
}