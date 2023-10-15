<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles"%>

<!doctype html>
<html lang="ko">
<tiles:insertAttribute name="include"/>
<body>
	<header id="header" class="Sec_header">
		<tiles:insertAttribute name="header"/>
	</header>
	<section>
		<h2 class="blind">잇(Eat)나우?</h2>
		<div class="wrap">
			<article aria-label="article">
				<tiles:insertAttribute name="body"/>
			</article>
			<div id="divMessage"	class="modal"></div>
			<div id="divPopup"		class="modal"></div>
		</div>
	</section>
	<tiles:insertAttribute name="footer"/>
	<div class="black__dimmed"></div>
</body>
</html>
