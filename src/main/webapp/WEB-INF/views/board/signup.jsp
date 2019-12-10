<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@include file="../includes/header.jsp"%>


<!DOCTYPE html>
<html lang="ko">
<head>


<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>

<title>Sign Up</title>
<script> 





	
</script>
</head>
<body>

  
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Join to Member</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">

				<div class="panel-heading">Board Register</div>
				<!-- /.panel-heading -->
				<div class="panel-body">

				<form role="form" action="/board/signupPost" method="post" autocomplete="off">
				
				<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
				
					<p>
						<label for="userid">아이디 :</label>
						<input type="text" id="userid" name="userid"/>
						<button type="button" class="idCheck">중복체크</button>
					</p>
					
					<p class="result">
						<span class="msg">아이디를 확인해 주십시오.</span>
					</p>
					
					<p>
						<label>비밀번호 : </label>
						<input type="password" id="userpw" name="userpw"/>
					</p>
					
					<p>
						<label>이름 : </label>
						<input type="text" id="username" name="username"/>
					</p>
					
					<p>
						
						<button type="submit" class="btn btn-info" id="submit"  disabled="disabled">가입</button>
					
					<button type="reset" class="btn btn-default">다시 입력</button>
					<a class="btn btn-info" href="board/list">홈으로</a></p>
				</form>

				</div>
				<!--  end panel-body -->
			</div>
			<!--  end panel-body -->
		</div>
		<!-- end panel -->
	</div>
	<!-- /.row -->
<script> 

$(".idCheck").click(function(){
 
 var query = {userid : $("#userid").val()};
 var token = $("meta[name='_csrf']").attr("content");
 var header = $("meta[name='_csrf_header']").attr("content");

$.ajax({
  url : "/board/idCheck",
  type : "post",
  data : query,
  dataType:"json",
  beforeSend:function(xhr){
	  xhr.setRequestHeader(header,token);
  },
  success : function(data) {
  console.log(data);
   if($.trim(data)==1) {
	   console.log("if1");
    $(".result .msg").text("이미 있는 아이디 입니다.");
    $(".result .msg").attr("style", "color:#f00");    
    
    $("#submit").attr("disabled", "disabled");
   } else {
	   console.log("if2");
    $(".result .msg").text("사용 가능합니다");
    $(".result .msg").attr("style", "color:#00f");
    
    $("#submit").removeAttr("disabled");
   }
  }
 });  // ajax 끝
 
});

$("#userid").keyup(function(){
	 $(".result .msg").text("중복체크를 먼저 해주십시오.");
	 $(".result .msg").attr("style", "color:#000");
	 
	 $("#submit").attr("disabled", "disabled");
	 
	});
	
	
 	//회원가입 유효성 검사
$("#submit").click(function(){
    
    var userid = $("#userid").val();
    var userpw = $("#userpw").val();
    var username = $("#username").val();
    
    if(userid.length == 0){
        alert("아이디를 입력해 주세요"); 
        $("#userid").focus();
        return false;
    }
    
    if(userpw.length == 0){
        alert("비밀번호를 입력해 주세요"); 
        $("#userpw").focus();
        return false;
    }
 
  
    if(username.length == 0){
        alert("이름을 입력해주세요");
        $("#username").focus();
        return false;
    }
    
    if(confirm("회원가입을 하시겠습니까?")){
        alert("회원가입을 축하합니다");
        return true;
    }
    
});

</script>
</body>
</html>
<%@include file="../includes/footer.jsp"%>