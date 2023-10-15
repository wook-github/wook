<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script>
$(document).ready(function() {
	subMenuChangeEventHandler();
	menuInvokeEvent();
});

function subMenuChangeEventHandler() {

	// 서브메뉴 클릭 이벤트
	$("#lnb ul.depth2 > li > a").on("click", function(){
		$(this).parent('li').addClass('on');
		$(this).parent('li').siblings('li.on').removeClass('on');
		
		var mMenuId = $(this).data("mmenuid");
		var sMenuId = $(this).data("smenuid");

		moveSubmenu(mMenuId + "" + sMenuId);
	});

}

function menuInvokeEvent() {
	var mMenuId = "";
	var sMenuId = "";
	
	var targetsel = $(".depth2 a[data-url='"+ this.location.pathname +"']");
	
	if(targetsel.length == 0) {
		$("#lnb").hide();
	} else {
		mMenuId = $(targetsel).data('mmenuid');
		sMenuId = $(targetsel).data('smenuid');
		
		topMenuSet(mMenuId, sMenuId);
	}
}

//서브메뉴 타이틀 변경 함수
function topMenuSet(mMenuId, sMenuId){
	
	if( mMenuId != undefined ) {
		$('.header_menu ul#gnb > li').find("a").removeClass("on");
		$('.header_menu ul#gnb > li').find("a[data-mmenuid='"+ mMenuId +"']").addClass("on");
	}
	
	var title 	= $('.header_menu ul#gnb > li > a.on').text();

	if(mMenuId == 8) {
		$("#lnb ul#nav" + mMenuId).show();
	}
	
 	$("#lnb div.title").text(title);
}

// 서브메뉴를 이용한 메뉴 이동
function moveSubmenu(menuInx){
	if (menuInx == "110") {				// MBTI
		location.href=wook.app.common.contextPath + "/wm/wmMbtiMa.do";
	} else if (menuInx == "210") {		// 검색
		location.href=wook.app.common.contextPath + "/ws/wsSrchMa.do";
	} else if (menuInx == "310") {		// 커뮤니티
		location.href=wook.app.common.contextPath + "/wt/wtCmntMa.do";
	} else if (menuInx == "811") {		// 고객센터 > 공지사항
		location.href=wook.app.common.contextPath + "/wc/wcBbsMa.do";
	} else if (menuInx == "821") {		// 고객센터 > 자주찾는질문(FAQ)
		location.href=wook.app.common.contextPath + "/wc/wcFaqMa.do";
	} else if (menuInx == "831") {		// 고객센터 > 1:1 문의
		location.href=wook.app.common.contextPath + "/wc/wcQnaMa.do";
	} else if (menuInx == "910") {		// 시스템관리
		location.href=wook.app.common.contextPath + "/sys/sysMa.do";
	}
}

</script>
<div id="lnb">
	<div class="title h2"></div>
	<!-- 1. 푸드 MBTI -->
	<ul id="nav1" class="depth2" style="display:none;">
		<li style="display: none;">
			<a href="javascript:;" data-mmenuid="1" data-smenuid="10" data-url ="<c:url value = '/wm/wmMbtiMa.do'/>" style="background: none;">푸드 MBTI</a>
		</li>
	</ul>

	<!-- 2. 푸드 검색 -->
	<ul id="nav2" class="depth2" style="display:none;">
		<li style="display: none;">
			<a href="javascript:;" data-mmenuid="2" data-smenuid="10" data-url ="<c:url value = '/ws/wsSrchMa.do'/>" style="background: none;">푸드 검색</a>
		</li>
	</ul>

	<!-- 3. 푸드 커뮤니티 -->
	<ul id="nav3" class="depth2" style="display:none;">
		<li style="display: none;">
			<a href="javascript:;" data-mmenuid="3" data-smenuid="10" data-url ="<c:url value = '/wt/wtCmntMa.do'/>" style="background: none;">푸드 커뮤니티</a>
		</li>
	</ul>

	<!-- 8. 고객센터 -->
	<ul id="nav8" class="depth2" style="display:none;">
		<li>
			<a href="javascript:;" data-mmenuid="8" data-smenuid="11" data-url ="<c:url value = '/wc/wcBbsMa.do'/>">공지사항</a>
		</li>
		<li>
			<a href="javascript:;" data-mmenuid="8" data-smenuid="21" data-url ="<c:url value = '/wc/wcFaqMa.do'/>">자주찾는질문(FAQ)</a>
		</li>
		<li>
			<a href="javascript:;" data-mmenuid="8" data-smenuid="31" data-url ="<c:url value = '/wc/wcQnaMa.do'/>">1:1 문의</a>
		</li>
	</ul>
</div>

