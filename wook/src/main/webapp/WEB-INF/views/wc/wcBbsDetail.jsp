<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="container">

	<div id="wcBbsDetail" class="board_info">
		<input type="hidden" id="bbsSn" name="bbsSn" value="<c:out value="${wcBbsInfo.bbsSn}" />" >
		<input type="hidden" id="bbsSe" name="bbsSe" value="<c:out value="${wcBbsInfo.bbsSe}" />" >
		<input type="hidden" id="fileSe" name="fileSe" value="wc" />
		
		<table class="wtp-info-tb">
			<caption>공지사항 상세정보</caption>
			<colgroup>
				<col style="13%" />
				<col style="20%" />
				<col style="13%" />
				<col style="20%" />
				<col style="13%" />
				<col style="20%" />
			</colgroup>
			<thead></thead>
			<tbody>
				<tr>
					<th>제목</th>
					<td class="tl" title="제목">
						<div class="bbs_text">
							<c:out value="${wcBbsInfo.bbsTtl}" />
						</div>
					</td>
					<th>작성자</th>
					<td class="tc" title="작성자"><c:out value="${wcBbsInfo.mdfrId}" /></td>
					<th>조회수</th>
					<td class="tc" title="조회수"><c:out value="${wcBbsInfo.bbsHits}" /></td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="5" class="tl" title="내용">
						<div class="bbs_contents">
							<c:out value="${wcBbsInfo.bbsCn}" />
						</div>
					</td>
				</tr>
				<tr>
					<th>고정여부</th>
					<td class="tl" title="bbsFixYn">
						<input type="checkbox" id="bbsFixYn" title="고정여부" disabled <c:if test="${wcBbsInfo.bbsFixYn eq 'Y'}" >checked</c:if>>
						<label for="bbsFixYn">고정여부</label>
					</td>
					<th>게시일자</th>
					<td colspan="3" class="tl" title="게시일">
						<fmt:parseDate value="${wcBbsInfo.pstgBgngYmd}" pattern="yyyyMMdd" var="pstgBgngYmd"/>
						<fmt:parseDate value="${wcBbsInfo.pstgEndYmd}" pattern="yyyyMMdd" var="pstgEndYmd"/>
						
						<fmt:formatDate value="${pstgBgngYmd}" pattern="yyyy-MM-dd" type="DATE" /> ~ <fmt:formatDate value="${pstgEndYmd}" pattern="yyyy-MM-dd" type="DATE" />
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="5" style="" title="첨부파일">
						<div id="divWcBbsFileList" class="div-file-list">
							<ul>
								<li>등록된 파일이 없습니다.</li>
							</ul>
						</div>
					</td>
				</tr>
				<tr>
					<th>비고</th>
					<td colspan="5" class="tl" title="rmks">
						<div class="bbs_text">
							<c:out value="${wcBbsInfo.rmks}" />
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		
		<div class="common_bottom">
			<button type="button" id="btnUpdate" title="수정">수정</button>
			<%-- 
			<sec:authorize>
				<button type="button" id="" title="수정">수정(관리자)</button>
			</sec:authorize>
			 --%>
			<button type="button" id="btnClose" title="닫기">닫기</button>
		</div>

	</div>

</div>

<script>
$(document).ready(function() {
	wook.app.common.movePageEventHandler();
	
	invokeEvent();
	getWcBbsFileList();
});

/*=======================
invoke event
=========================*/
function invokeEvent() {
	
	// 상세정보 닫기
	$("#wcBbsDetail #btnClose").on("click", function() {
		$("#divWcBbsDetail").hide();
		$("#tblList tbody tr").removeClass("selected");
	});

	// 상세정보 수정화면 열기
	$("#wcBbsDetail #btnUpdate").on("click", function(){
		getWcBbsUpdate($("#bbsSn").val(), $("#bbsSe").val(), "U");
	});
}

// 해당 게시글 파일목록 조회
function getWcBbsFileList() {
	
	var fileUrl = "<c:url value='/wc/getWcBbsFileList.do' />";
	//첨부파일 조회 데이터 설정
	data = {
				bbsSn		:	$("#bbsSn").val(),
				fileSe		:	$("#fileSe").val(),
			};
	getFileList("divWcBbsFileList", fileUrl, data , null, null);
	
}
</script>