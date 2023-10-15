<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script>
$(document).ready(function(){
	foorterInvokeEvent();
});

function foorterInvokeEvent() {
	footerLinkEventHandler();				// Top Menu
}

function footerLinkEventHandler() {
	$('.footer__links .container > ul > li').each(function() {
		$(this).addClass('has-depth1');
	});
	
	//클릭한 menu로 이동
	$('.footer__links .container > ul.depth1 > li a').on('click', function(e) {
		$('.footer__links .container > li.has-depth1 > a').removeClass("on");
		$(this).addClass("on");
		var mMenuId = $(this).data("mmenuid").toString();
		var sMenuId = $(this).data("smenuid").toString();
	
		$("#breadcrumb ul.list #depth1 > a").prop("text", "시스템 안내");
		$("#breadcrumb ul.list #depth2 > a").prop("text", $(this).text());
		
		moveSubmenu(mMenuId + sMenuId);
	});
}
</script>

<footer id="footer" class="footer">
    <div class="inner_footer">
    	<section class="section_service">
    		<h2 class="blind">하단 메뉴</h2>
    		<div class="wrap_service first_wrap">
    			<strong class="tit_service">
    				<a href="javascript:;" data-mmenuid="1" data-smenuid="10">MBTI</a>
    			</strong>
    		</div>
    		<div class="wrap_service">
    			<strong class="tit_service">
    				<a href="javascript:;" data-mmenuid="2" data-smenuid="10">검색</a>
    			</strong>
    		</div>
    		<div class="wrap_service">
    			<strong class="tit_service">
    				<a href="javascript:;" data-mmenuid="3" data-smenuid="10">커뮤니티</a>
    			</strong>
    		</div>
    		<div class="wrap_service">
    			<strong class="tit_service">
    				<a href="javascript:;" data-mmenuid="4" data-smenuid="10">고객센터</a>
    			</strong>
    			<ul class="list_service">
    				<li>
    					<a href="javascript:;" data-mmenuid="4" data-smenuid="10">공지사항</a>
    				</li>
    				<li>
    					<a href="javascript:;" data-mmenuid="4" data-smenuid="20">자주찾는질문(FAQ)</a>
    				</li>
    				<li>
    					<a href="javascript:;" data-mmenuid="4" data-smenuid="30">1:1 문의</a>
    				</li>
    			</ul>
    		</div>
    	</section>
    	<section class="section_relation">
    		<h2 class="blind">서비스 이용정보</h2>
    		<div class="group_info">
    			<div class="wrap_info">
    				<a href="javascript:;" data-mmenuid="8" data-smenuid="10" class="link_info">시스템 소개</a>
    			</div>
    			<div class="wrap_info">
    				<a href="javascript:;" data-mmenuid="8" data-smenuid="20" class="link_info">이용약관</a>
    			</div>
    			<div class="wrap_info">
    				<a href="javascript:;" data-mmenuid="8" data-smenuid="30" class="link_info link_emph">개인정보처리방침</a>
    			</div>
    			<div class="wrap_info">
    				<a href="javascript:;" data-mmenuid="8" data-smenuid="40" class="link_info">운영정책</a>
    			</div>
    			<div class="wrap_info">
    				<a href="javascript:;" data-mmenuid="8" data-smenuid="50" class="link_info">Contact Us</a>
    			</div>
    		</div>
    		<small class="txt_copyright">© Wook Corp. All rights reserved.</small>
    	</section>
    </div>
</footer>