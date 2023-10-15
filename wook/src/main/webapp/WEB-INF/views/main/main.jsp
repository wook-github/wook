<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="ko">
<c:import url="/main/include.do" charEncoding="UTF-8" />
<body>
	<header id="header" class="Sec_header">
		<c:import url="/main/header.do" charEncoding="UTF-8" />
	</header>
	<section class="section">
		<h2 class="blind">잇(Eat)나우?</h2>
		<div class="wrap">
			<nav class="nav">
				<c:import url="/main/nav.do" charEncoding="UTF-8" />
			</nav>
			<article aria-label="article"></article>
			<div id="divMessage"	class="modal"></div>
			<div id="divPopup"		class="modal"></div>
		</div>
	</section>
	<c:import url="/main/search.do" charEncoding="UTF-8" />
	<c:import url="/main/footer.do" charEncoding="UTF-8" />
	<div class="black__dimmed"></div>
</body>
</html>