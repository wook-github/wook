<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="sub_wrap">

	<div class="inner">

	 	<div id="divWcBbs" class="sub_section">
	 	
	 		<div class="board_list">

	 			<div class="box-search">

					<div class="box-search-keyword">
						<label class="blind" for="selGubun">검색 구분</label>
						<select id ="selGubun" name="selGubun"  title="검색 구분">
							<option value="0">제목+내용</option>
							<option value="1">제목</option>
							<option value="2">내용</option>
						</select>
						<label class="blind" for="keyword">검색어</label>
						<input type="search" id="keyword" name="keyword" title="검색어" placeholder="검색어 입력" value="<c:out value='${info.keyword}' />">
						<button type="button" id="btnSearch" title="검색">검색</button>
					</div>

				</div>

		 		<div id="divTotalPage" class="totalPage"></div>

				<table id="tblList" class="wtp-list-tb">
					<caption>게시판 목록</caption>
					<colgroup>
						<col  style="width:10%;" />
						<col  style="width:50%;" />
						<col  style="width:15%;" />
						<col  style="width:15%;" />
						<col  style="width:10%;" />
					</colgroup>

					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>등록일</th>
							<th>아이피</th>
						</tr>
					</thead>
					<tbody>
						<tr class="nohover">
							<td colspan="5">&nbsp;</td>
						</tr>
					</tbody>
				</table>

				<div class="list_bottom">
					<button type="button" id="btnAdd" class="bp_btn" title="등록">등록</button>
				</div>

				<div id="divPaginator" class="paging" style="text-align:center;"></div>

			</div>

			<!-- 게시판 상세정보 -->
			<div id="divWcBbsDetail"></div>

		</div>

	</div>

</div>

<script>
var _cPage 	= 1;
var _rows 	= 10;
var _pager = null;


$(document).ready(function() {
	wook.app.common.movePageEventHandler();
	wook.app.common.keyEvent();

	invokeEvent();
	searchPage(1);
});

/*=======================
invoke event
=========================*/
function invokeEvent() {

	// 게시판 검색
	$("#btnSearch").click(function() {
		if($("#keyword").val() == null || $("#keyword").val() == "") {
			$("#selGubun option:eq(0)").prop("selected", true);
		}
		searchPage(1);
	});

	// 게시판 등록
	$("#btnAdd").click(function(){
		$("#tblList tbody tr").removeClass("selected");
		getWcBbsUpdate("", "01", "C");
	});
}

// 게시판 목록 serachPage
function searchPage(cPage) {
	_cPage = cPage;
	searchWcBbsList(cPage);
}

function searchWcBbsList(cPage) {
	$("#divPaginator").html("");
	$("#divTotalPage").html("");
	selectWcBbsList(cPage);

}

//게시판 상세 정보 화면
function getWcBbsDetail(dataValue) {
	$("#divWcBbsDetail").hide();
	
	var bbsSn	= dataValue[0];
	var bbsSe	= dataValue[1];

	var url = "<c:url value='/wc/wcBbsDetail.do'/>";
	url += "?bbsSn=" 	+ bbsSn;
	url += "&bbsSe="	+ bbsSe;

	$("#divWcBbsDetail").show().load(url);
}



// 게시판 등록(수정) 화면
function getWcBbsUpdate(bbsSn, bbsSe, pageMode) {
	$("#divWcBbsDetail").hide();

	var url = "<c:url value='/wc/wcBbsUpdate.do'/>";
	url += "?bbsSn=" + bbsSn;
	url += "&bbsSe=" + bbsSe;
	url += "&pageMode=" + pageMode;

	$("#divWcBbsDetail").show().load(url);
}

// 게시판 목록 조회
function selectWcBbsList(cPage) {
	$("#divWcBbsDetail").hide();
	
	$("#divWcBbs #tblList tbody").empty().append(wook.app.common.getWaitImg($("#divWcBbs #tblList tbody").width(), 5));
	
	$.ajax({
		url			: 	"<c:url value='/wc/selectWcBbsList.do'/>",
		data		:	{
							gubun		: $("#selGubun").val(),							// 검색구분
							keyword		: $("#keyword").val(),							// 검색어
							rows		: _rows,										// 페이지당 항목수
							page		: cPage,										//페이지
						},
		dataType	:	"json",
		type		:	"post",
		success		:	function(data, textStatus){
							var htmlStr = "";
							if (data.result.length > 0) {
								$.each(data.result,function(index,item) {
									var rownum		= wook.app.common.maskFormat(item.rownum);			/* 정렬순번 */
									var bbsSn		= wook.app.common.maskFormat(item.bbsSn);			/* 게시판순번 */
									var bbsSe		= wook.app.common.maskFormat(item.bbsSe);			/* 게시판구분 */
									var bbsTtl		= wook.app.common.maskFormat(item.bbsTtl);			/* 게시판제목 */
									var bbsCn		= wook.app.common.maskFormat(item.bbsCn);			/* 게시판내용 */
									var bbsFixYn 	= wook.app.common.maskFormat(item.bbsFixYn);		/* 고정여부 */
									var mdfcnYmd	= wook.app.common.dateToString(item.mdfcnYmd);		/* 작성일자 */
									var mdfrId		= wook.app.common.maskFormat(item.mdfrId);			/* 작성자ID */
									var mdfrIp		= wook.app.common.maskFormat(item.mdfrIp);			/* 작성자IP */

									htmlStr += "<tr	";
									htmlStr += "	data-bbssn=" + bbsSn + "";
									htmlStr += "	data-bbsse=" + bbsSe + "";
									htmlStr += ">";
									if ( bbsFixYn == "Y" ) {
										htmlStr += "	<td class='tc' style='color: red; font-weight: bold;'>고정</td>";
									} else {
										htmlStr += "	<td class='tc'>" + rownum + "</td>";
									}
									htmlStr += "	<td class='tl'><div class='nowrap' title='" + bbsTtl + "'>" + bbsTtl + "</div></td>";
									htmlStr += "	<td class='tc'><div class='nowrap' title='" + mdfrId + "'>" + mdfrId + "</div></td>";
									htmlStr += "	<td class='tc'><div class='nowrap' title='" + mdfcnYmd + "'>" + mdfcnYmd + "</div></td>";
									htmlStr += "	<td class='tc'><div class='nowrap' title='" + mdfrIp + "'>" + mdfrIp + "</div></td>";
									htmlStr += "</tr>";
								});
							} else {
								
								htmlStr = "<tr class='nohover'><td colspan='5' class='tc' style='color:#999999'>검색된 정보가 없습니다.</td></tr>";
							}

							var totalCount = (data.result.length == 0 ? 0 : data.result[0].totalCount);
							_pager = new Pager("divPaginator", "_pager", 1);	// global로 선언해함.  패러미터 :  divPaginator(페이징 div id),  _pager(페이저 객체를 담는 object),  1 (페이저 객체 number)
							_pager.makePaging(_cPage, totalCount, _rows, $("#divTotalPage"));

							$("#tblList tbody").html(htmlStr);

							if (data.result.length > 0) {
								wook.app.common.ListEventHandler("tblList", getWcBbsDetail, "bbssn,bbsse");
							}
						},
		beforeSend	:	function(XMLHttpRequest) {
							XMLHttpRequest.setRequestHeader("AJAX", "Yes");
						},
		error		: 	function(XMLHttpRequest, textStatus, errorThrown){
							if (XMLHttpRequest.status == 403 || XMLHttpRequest.status == 207) {
								wook.app.common.sessionTimeout();
							} else {
								var htmlStr = "<tr class='nohover'><td colspan='5' class='tc' style='color:#999999'>검색된 정보가 없습니다.</td></tr>";
								_pager = new Pager("divPaginator", "_pager", 1);	// global로 선언해함.  패러미터 :  divPaginator(페이징 div id),  _pager(페이저 객체를 담는 object),  1 (페이저 객체 number)
								_pager.makePaging(1, 0, _rows, $("#divTotalPage"));
								$("#tblList tbody").html(htmlStr);
							}
						}
	});
}
</script>