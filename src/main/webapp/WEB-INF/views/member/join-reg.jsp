<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<main>
	<section id="form-section">
		<h1>회원 가입 페이지</h1>
		<form method="post">
			<table>
				<tr>
					<td><label>사진</label> <img src="" /> <input type="file"
						hidden="true" value="사진선택" /> <span>사진선택</span></td>
					<td><label>아이디 : </label> <input name="id" /> <input
						type="button" class="id-check-button" value="중복확인"
						required="required" /> // 중복확인하는 방법</td>
					<td><label>비밀번호 : </label> <input name="pwd"
						required="required" /></td>
					<td>< label>이름 : </label> <input name="name" required="required" />
					</td>
					<td><label>이메일 : </label> <input name="email"
						readonly="readonly" /></td>
				</tr>
			</table>
		</form>
	
		생년월일 전화번호 닉네임 성별
	
	
	</section>
	</main>
	
	<
	<script type="text/javascript">
		window.addEventListener("load", function(){
			var formSection = document.querySelector("#form-section");			
			var idCheckButton = formSection.querySelector(".id-check-button");							
			var idInput = formSection.querySelector("input[name='id']");
			
			idCheckButton.onclick = function(e){
				
				// alert("hello");
				// ajax -> 협력자 백엔드에게 연락해서 알아봐야함.
				// /member/is-id-duplicated
				// 요청
				
			String id = idInput.value;	
				
			XmlHttpRequest request = new XmlHttpRequest();
			request.onload = function(e){
			
			};
				
			request.open("GET", "is-id-duplicated?id="+id, true);
			request.send();
			}					
			
		});		

	</script>
