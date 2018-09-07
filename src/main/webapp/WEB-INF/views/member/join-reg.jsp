<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<main>
	<section>
		<h1>회원 가입 페이지</h1>
	
		<form>
			<table>
				<tr>
					<td>
						<label>사진</label> 
						<img src="" />
						<input type="file" hidden="true" value="사진선택"/>
						<span>사진선택</span>
					</td>
					<td>
						<label>아이디</label> 
						<input name="id" value="중복확인"/>
					</td>
					<td>
						<label>비밀번호</label> 
						<input name="pwd" />
					</td>
					<td><
						label>이름</label> 
						<input name="name" />
					</td>
					<td>
						<label>이메일</label> 
						<input name="email" readonly="readonly" />
					</td>
				</tr>
			</table>
		</form>
	
		생년월일 전화번호 닉네임 성별
	
	
	</section>
	</main>