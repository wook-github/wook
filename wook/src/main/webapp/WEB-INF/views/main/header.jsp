<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn"	uri="http://java.sun.com/jsp/jstl/functions" %>
<script>

$(document).ready(function(){
	headerInvokeEvent();
	$.ajaxSetup({
		beforeSend	:	function(xhr, settings) {
			xhr.setRequestHeader("AJAX", "Yes");
		},
		statusCode : {
			200: function() {
				//fnLoadingEnd();
	        },
			302: function() {
				//fnLoadingEnd();
				main();
	        },
			404: function(a, status, c) {
				//fnLoadingEnd();
				var message = "요청한 페이지가 없습니다.";
				if(this.type == "POST"){
					message = "요청 주소가 잘못됐습니다."
				}

				if(this.url == "<c:url value='/cm/messageBox.do'/>" && status == "error"){
					$.modal.close();
					alert('서버에서 오류가 발생했습니다. 잠시 후 다시 시도해 주시기 바랍니다.');
				}
				else {
					wook.app.common.messageBox(null, "페이지 오류", message, null);
				}
		    },
		    406: function() {
		    	//fnLoadingEnd();
	        	wook.app.common.messageBox(null, "권한없음", "해당 기능에 대한 권한이 없습니다.", null , 'N');

	        },
			500: function() {
				//fnLoadingEnd();
				var message = "요청한 페이지에 오류가 발생했습니다.";
				if(this.type == "POST"){
					message = "요청한 페이지에 오류가 발생했습니다."
				}
				wook.app.common.messageBox(null, "페이지 오류", message, null);
		    }
	    }
	});
});

//Header Invoke Event
function headerInvokeEvent() {
	topMenuEventHandler();				// Top Menu

	//로그아웃 버튼 이벤트
	//header.jsp 등록된 이벤트 호출
	$("#btnHeaderLogOut").on('click', logout);

	//개인정보 수정 버튼 이벤트
	//header.jsp 등록된 이벤트 호출
	$("#btnHeaderModifyMember").on('click', modifyMember);
}

//Top Menu Event Handler
function topMenuEventHandler() {
	
	// 클릭한 menu의 메인페이지
	$('.header_menu #gnb > li > a').on('click', function(e) {
		$(this).parent().siblings().children("a").removeClass("on");
		$(this).addClass("on");
		
		var mMenuId = $(this).data("mmenuid");
		var sMenuId = $(this).data("smenuid");

		changeSubmenu(mMenuId, sMenuId);
	});

}

// main 페이지 이동
function main() {
	window.top.location.href = "<c:url value='/main.do'/>";
}

// login 페이지 이동
function login() {
	window.top.location.href = "<c:url value='/login/login.do'/>";
}

// logout 처리
function logout() {
	window.location.href = "<c:url value='/login/logout.do'/>";
}

// 회원 수정 페이지 이동
function modifyMember() {
	window.location.href  ="<c:url value='/login/memberInfo/modifyMemberCheck.do'/>";
}

// Header 메뉴 화면 이동.
function changeSubmenu(mMenuId, sMenuId){
	var menuInx = mMenuId + "" + sMenuId;
	
	if (menuInx == "100") {				// MBTI
		location.href=wook.app.common.contextPath + "/wm/wmMbtiMa.do";
	} else if (menuInx == "210") {		// 검색
		location.href=wook.app.common.contextPath + "/ws/wsSrchMa.do";
	} else if (menuInx == "310") {		// 커뮤니티
		location.href=wook.app.common.contextPath + "/wt/wtCmntMa.do";
	} else if (menuInx == "410") {		// 고객센터 > 공지사항
		location.href=wook.app.common.contextPath + "/wc/wcBbsMa.do";
	} else if (menuInx == "420") {		// 고객센터 > 자주찾는질문(FAQ)
		location.href=wook.app.common.contextPath + "/wc/wcFaqMa.do";
	} else if (menuInx == "430") {		// 고객센터 > 1:1 문의
		location.href=wook.app.common.contextPath + "/wc/wcQnaMa.do";
	} else if (menuInx == "910") {		// 시스템관리
		location.href=wook.app.common.contextPath + "/sys/sysMa.do";
	}
}

</script>
<div class="Artc_header" style="min-height: 0px;">
	<div class="artc">
    	<div class="inner">
        	<h1 class="site-name">
            	<a href="../main.do" class="link">잇(Eat)나우?</a>
            </h1>
            <div class="header_area">
            	<div class="header_menu">
            		<ul id="gnb">
						<li>
							<a data-mmenuid='1' data-smenuid='10'>MBTI</a>
						</li>
						<li>
							<a data-mmenuid='2' data-smenuid='10'>검색</a>
						</li>
						<li>
							<a data-mmenuid='3' data-smenuid='10'>커뮤니티</a>
						</li>
						<li>
							<a data-mmenuid='4' data-smenuid='10'>고객센터</a>
						</li>
					</ul>
            	</div>
            </div>
            <nav class="nav-mine">
            	<a href="\mypage" class="btn-mypage">마이페이지</a>
            </nav>
    	</div>
	</div>
</div>