<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<div class="container">
<h1>로그인(JSP + EL + JSTL)</h1>
<form action='login' method='post'>
이메일: <input name='email' type='email' value='${email}'>
<input type='checkbox' name='saveEmail'> 이메일 저장해두기<br>
암호: <input name='password' type='password'><br>
<button>로그인</button>
<fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
</fb:login-button>
</form>
</div>

<script>
window.fbAsyncInit = function() {
	  console.log("window.fbAsyncInit() 호출됨!");
	  FB.init({
	    appId      : '178404756776711', // 개발자가 등록한 앱 ID
	    cookie     : true,  
	    xfbml      : true,  
	    version    : 'v7.0' 
	  });
	  FB.AppEvents.logPageView();
	};
	

(function(d, s, id) {
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) return;
	  js = d.createElement(s); js.id = id;
	  js.src = "https://connect.facebook.net/en_US/sdk.js";
	  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));


function checkLoginState() {
    FB.getLoginStatus(function(response) { 
        if (response.status === 'connected') { // 로그인이 정상적으로 되었을 때,
            autoServerLogin(response.authResponse.accessToken);
        
        } else { // 로그인이 되지 않았을 때,
            console.log("로그인 되지 않았음");
        }
    });
}

function autoServerLogin(accessToken) {
	alert('서버에 로그인 요청');
	location.href = "facebookLogin?accessToken=" + accessToken;
}

</script>
    