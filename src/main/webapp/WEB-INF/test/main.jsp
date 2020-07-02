<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript">
	$(function(){
		$('#btn1').click(function(){
			
			//serialize으로 바로 전송
			/* $.ajax({
				url:"process.do",
				type:"post",
				data:$('#frm').serialize(),
				success:function(data){
					$('#result').html(data);
				},
				error:function(){
					alert("실패");
				}
			}); */

			var form = {
	                name: "test",
	                age: 23
	        }
			$.ajax({
				url:"process.do2",
				type:"post",
				dataType:'json',
				data:JSON.stringify(form),	
				contentType: "application/json; charset=utf-8;",
				success:function(data){
						$('#result').html(data.name+"--"+data.age);		
					
				},
				error:function(){
					alert("실패");
				}
			});
			
			
			//list 받기
			/* $.ajax({
				url:"restcontroller.do",
				type:"post",
				dataType:'json',
				data:JSON.stringify(form),	
				contentType: "application/json; charset=utf-8;",
				success:function(data){
					for(var i=0;i<data.length;i++){
						$('#result').append(data[i]+"<br>");		
					}
				},
				error:function(){
					alert("실패");
				}
			}); */
			
		});
	});
</script>
</head>
<body>
<h1>MainForm</h1>
<form id="frm">
이름:<input type="text" name="name" id="name"><br>
나이:<input type="text" name="age" id="age"><br>
<button id="btn1" type="button">전송</button>
</form>
<div id="result">

</div>

</body>
</html>