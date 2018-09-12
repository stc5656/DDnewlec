<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<style>
/* --- reset ------------------------------------ */
body {
	margin: 0px;
}

h1 {
	margin: 0px;
}

/* --- common style --------------------------------------------- */
.hidden {
	display: none;
}

/* --- component style ------------------------------------------------------- */
.button {
	width: 24px;
	height: 24px;
	overflow: hidden;
	text-indent: -999px;
	border: 0px;
	padding: 0px;
}

.find-button {
	background: url("/resources/images/icons.png") no-repeat -24px 0px;
}

.hamburger-button {
	background: url("/resources/images/icons.png") no-repeat 0px 0px;
}

.photo {
	width: 150px;
	height: 150px;
	border: 1px solid #e9e9e9;
	border-radius: 75px;
}

/* --- header -------------------------------- */
#header {
	width: 100%;
	height: 45px;
	line-height: 45px;
	position: relative;
	border-bottom: 1px solid #a9a9a9;
}

#header>h1 {
	display: inline-block;
	position: absolute;
	left: 10px;
	top: 10px;
}

#header:after {
	content: "";
	display: block;
	position: fixed;
	top: 0px;
	left: 0px;
	width: 100%;
	height: 0%;
	background: #000;
	opacity: 0;
	transition: 500ms opacity ease;
}

#header #header-buttons {
	position: absolute;
	top: 0px;
	right: 0px;
	height: 100%;
	line-height: 45px;
	padding-right: 10px;
}

#header #login-info {
	position: fixed;
	top: 0px;
	right: -80%;
	width: 80%;
	height: 100%;
	background: #fff;
	z-index: 10;
	text-align: center;
	transition: 500ms right ease;
}

#header #login-info .photo {
	display: inline-block;
}

#header.menu-show #login-info {
	right: 0%;
}

#header.menu-show:after {
	opacity: 0.5;
	height: 100%;
}

@media all and (min-width: 700px) {
	#header {
		background: pink;
	}
}
</style>

<!-- 방이나 가구는 id로 부여해서 사용함 -->
<!-- <header id="header">	
	
	
	
		프로젝트명 지우고 properties에서 web project /로 바꾸고 서버 재실행하면 
		프로젝트명 안써도 됨
		절대경로 쓸때 맨앞에 프로젝트명 꼭 써줘야함
		<h1><img src="/resources/academy/sist/images/logo.png" alt=쌍용강북교육센터/></h1>
		
	<section id = "header-buttons">
		<h1 class = "hidden">헤더 버튼</h1>
		<input class = "button find-button" type="button" value = "검색"/>	
		<input class = "button hamburger-button" type="button" value = "메뉴보기"/>			
	</section>
	
		
	<section id="main-menu">
		<h1 class = "hidden">메인메뉴</h1>
		<ul>
			<li><a href="">문제관리</a></li>
			<li><a href="">시험관리</a></li>
			<li><a href="">일정관리</a></li>
			<li><a href="">수업관리</a></li>
		</ul>
	</section>
</header> -->


<header id="header">
	<h1>
		<a href="/index"> <picture> <img
				src="/resources/academy/sist/images/logo.png" alt="쌍용강북교육센터" /> </picture>
		</a>
	</h1>
	<section id="header-buttons">
		<h1 class="hidden">헤더버튼</h1>
		<input class="button find-button" type="button" value="검색" /> <input
			class="button hamburger-button" type="button" value="메뉴보기" />
	</section>
	<aside id="login-info">
		<h1 class="hidden">로그인 정보</h1>
		<section id="profile">
			<h1 class="hidden">프로필</h1>
			<div>
				<div class="photo"
					style="background: url('https://interactive-examples.mdn.mozilla.net/media/cc0-images/Painted_Hand--298x332.jpg') no-repeat center; background-size: cover"></div>
				<div class="uid">
					<span>newlec</span>
				</div>
				<%--  <%=
            	request.getUserPrincipal().getName()
            %>
           		 ${request.userPrincipal.name} --%>

				<%--  <c:if test="${empty pageContext.request.userPrincipal}">
            <div class="auth-status"><a href="/member/login">로그인</a></div>
            </c:if> --%>

				<!-- https://docs.spring.io/spring-security/site/docs/current/reference/html/el-access.html -->

				<div class="auth-status">
					<security:authorize access="!isAuthenticated()">
						<a href="/member/login">로그인</a>
					</security:authorize>



					  <%-- <c:if test="${not empty pageContext.request.userPrincipal}">
		            <div class="auth-status"><a href="/member/logout">로그아웃</a></div>
		            </c:if> --%>

					<security:authorize access="!isAuthenticated()">
						<a href="/member/logout"> <security:authentication
								property="name" /> 님 로그아웃
						</a>
						</security:authorize>
						<a href="/member/join-reg">회원가입</a>						
				</div>			

			

			<security:authorize access="hasRole('TEACHER')">
				<div class="notice">
					<span>강사공지 : </span><a href="">3</a>
				</div>
				<!-- <div class="notice"><span>강사공지 : </span><a href="">3</a></div> -->
			</security:authorize>
			</div>
		</section>


		<section id="teacher-menu">

			<h1 class="hidden">강사메뉴</h1>
			<ul>
				<li><a href="/teacher/question/type">문제관리</a></li>
				<li><a href="">시험관리</a></li>
				<li><a href="">일정관리</a></li>
				<li><a href="">수업관리</a></li>
			</ul>
		</section>
	</aside>
</header>

<script>
	window.addEventListener("load", function(event) {
		var header = document.querySelector("#header");
		var hamburgerButton = header.querySelector(".hamburger-button");

		header.onclick = function(e) {
			if (e.target.nodeName == "HEADER")
				header.classList.remove("menu-show");

		};

		hamburgerButton.onclick = function(e) {

			header.classList.add("menu-show");

			// 다음 이벤트 전파를 막는거
			e.stopPropagation();
		}

	});
</script>