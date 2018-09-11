<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<main>
	<section id="form-section">
		<h1>회원 가입 페이지</h1>
		<form method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>
						<label>사진 : </label> 
						<img class="photo" src="" /> 
						<input type="file" hidden="true" name="photo-file" value="사진선택" /> 
						<span class="photo-button">사진선택</span>
					</td>
					<td>
						<label>아이디 : </label> 
						<input name="id" /> 
						<input type="button" class="id-check-button" value="중복확인" required="required" /> 
						// 중복확인하는 방법
					
					</td>
					<td>
						<label>비밀번호 : </label> 
						<input name="pwd" required="required" />
					</td>
					<td>
						<label>이름 : </label> 
						<input name="name" required="required" />
					</td>
					<td>
						<label>이메일 : </label> 
						<input name="email"	readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="회원가입"/>
					</td>
				</tr>
			</table>
		</form>
	
		생년월일 전화번호 닉네임 성별
	
	
	</section>
	</main>
	
	
	<script type="text/javascript">
		window.addEventListener("load", function(){
			var formSection = document.querySelector("#form-section");			
			var idCheckButton = formSection.querySelector(".id-check-button");							
			var idInput = formSection.querySelector("input[name='id']");
			var joinButton = formSection.querySelector("input[type='submit']");			
			var photoButton = formSection.querySelector(".photo-button");
			var fileButton = formSection.querySelector("input[type='file']");
			var photo = formSection.querySelector(".photo");	

			var idOk = false;

			idCheckButton.onclick = function(e){
				
				// alert("hello");
				// ajax -> 협력자 백엔드에게 연락해서 알아봐야함.
				// /member/is-id-duplicated
				// 요청
				
			var id = idInput.value;	
				
			var request = new XmlHttpRequest();
			request.onload = function(e){
				
			var duplicated = JSON.parse(request.responseText);
				
				if(duplicated){
					 alert("이미 사용중인 아이디입니다.");	
					return;
			
				}
			};
			
			// 중복확인 안하고 넘어가려고 할때
			joinButton.onclick = function(e){
							
				if(!idOk){
					alert("아이디 중복 검사를 하지 않았거나 유효하지 않은 아이디입니다.");
					e.preventDefault();
				}
			}
					
				
			request.open("GET", "is-id-duplicated?id="+id, true);
			request.send();
			};	
					
				// 트리거 사용법
				photoButton.onclick=function(){
				var photoEvent = new MouseEvent("click", {
					'view' : window,
					'bubble' : true,
					'cancelable' : true
				});
				
				fileButton.dispatchEvent(photoEvent);
			};	

				fileButton.onchange = function(e){

					var file = fileBUtton.files[0];

					// 파일 용량, 파일 확장자 제한해야돼
									
					if (file.type.indexOf("image/") < 0) {
						alert("이미지 형식이 아닙니다.");
						
						return;				
					}

					if(file.size > 1024 * 1024 *10){
						alert("이미지 용량은 10MB를 초과할 수 없습니다.")
						
						return;
					}

					// .찍었을 때 특성들 뽑아보는 방법
					// for(var p in file)
					// 	alert("p");


					var reader = new FileReader();
					reader.onload = function(evt){

						photo.src = evt.target.result;

					};

					reader.readAsDataURL(file);
					
					



				}


			
		});		

	</script>
