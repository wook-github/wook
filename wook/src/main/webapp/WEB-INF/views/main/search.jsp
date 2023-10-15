<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="searchArea">
	<p style="font-size: 3.5em; text-align: center; color: #444; font-weight: bold;">오늘 점심 무엇을 먹을지 고민되시나요?<br>잇나우에서 찾아보세요!</p>
	<div class="searchForm">
		<div class="inline_block">
			<label class="blind" for="selSrchSe"></label>
			<select id="selSrchSe" name="selSrchSe" title="검색구분">
				<option value="01">지도 검색</option>
				<option value="02">매장 검색</option>
				<option value="03">음식 검색 </option>
			</select>
		</div>
		<div class="inline_block">
			<label class="blind" for="keyword">검색어</label>
			<input type="text" id="keyword" name="keyword" title="검색어" placeholder="검색어 입력" />
			<button type="button" id="searchBtn" class="searchBtn"><img class="btnImg" src=" <c:url value='/resources/images/icons/search_icon.jpg' /> " /></button>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
	invokeEvent();
});

function invokeEvent() {
	$("#searchBtn").on("click", function() {
		if($("#keyword").val() != null && $("#keyword").val() != '') {
			var srchSe = $("#selSrchSe option:selected").val();
			var url = "";
				if(srchSe == "01") {
					url = "<c:url value='/wc/wcBbsMa.do'/>";
				} else if (srchSe == "02") {
					url = "<c:url value='/wc/wcBbsMa.do'/>";
				} else {
					url = "<c:url value='/wc/wcBbsMa.do'/>";
				}
			url += "?keyword=" 	+ $("#keyword").val();
			
			location.href = url;
		} else {
			wook.app.common.messageBox(null, "검색어 미입력", "검색어를 입력해주시기 바랍니다.", null);
		}
	});
}
</script>