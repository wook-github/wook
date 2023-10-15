<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="container">

	<form id="bbsForm" name="bbsForm" method="post" enctype="multipart/form-data">
		<input type="hidden" 	id="bbsSe"		name="bbsSe" 		value="<c:out value="${info.bbsSe}" />">
		<input type="hidden" 	id="bbsSn" 		name="bbsSn" 		value="<c:out value="${wcBbsInfo.bbsSn}" />">
		<input type="hidden" 	id="bbsFixYn"	name="bbsFixYn" 	value="<c:out value="${wcBbsInfo.bbsFixYn eq null ? 'N' : wcBbsInfo.bbsFixYn}" />">
		<input type="hidden" 	id="pageMode"	name="pageMode" 	value="<c:out value="${info.pageMode}" />">
		<input type="hidden" 	id="fileSe"		name="fileSe" 		value="wc">
		
		<div id="wcBbsDetail" class="board_info">
			<!-- TIP -->
			<div class="div__tip">
				<h3 class="tip-label">TIP</h3>
				
				<dl class="tip-list">
					<dt><strong>제목, ${info.bbsSe eq 03 ? '질문' : '내용'}<c:if test="${info.bbsSe eq 01}">, 공지기한</c:if></strong>은 필수 입력 항목입니다.</dt>
					<c:if test="${info.bbsSe ne 03}"><dt><strong>첨부파일은 최소 1개 이상 등록해야 합니다.</strong></dt></c:if>
					<dt>첨부파일은 <strong>hwp, pdf, xls, xlsx, zip, egg의 형식만</strong> 업로드 가능합니다.</dt>
	
					<dt class="mandatory">
						<span class="symbol"></span>&nbsp;<span class="symbol2"></span>:필수 입력 항목
					</dt>
				</dl>
			</div>
			
			<table class="wtp-info-tb">
				<caption>상세정보</caption>
				<colgroup>
					<col  style="width:100px;" />
					<col  style="width:150px;" />
					<col  style="width:100px;" />
					<col  />
					<col  style="width:100px;" />
					<col  style="width:200px;" />
				</colgroup>
				<thead></thead>
				<tbody id="bbs_tbody">
					<tr>
						<th>제목</th>
						<c:choose>
							<c:when test="${info.pageMode eq 'C'}">
								<td colspan="5">
									<label class="blind" for="bbsTtl">제목</label>
									<input type="text" id="bbsTtl" name="bbsTtl" class="input_text required" title="제목" placeholder="제목 입력" validTxt="제목을 입력하십시오." value="<c:out value="${wcBbsInfo.bbsTtl}" />" onKeyUp="javascript:wook.app.common.chkBytes(this,'128')" />
								</td>
							</c:when>
							<c:otherwise>
								<td colspan="3">
									<label class="blind" for="bbsTtl">제목</label>
									<input type="text" id="bbsTtl" name="bbsTtl" class="input_text required" title="제목" placeholder="제목 입력" validTxt="제목을 입력하십시오." value="<c:out value="${wcBbsInfo.bbsTtl}" />" onKeyUp="javascript:wook.app.common.chkBytes(this,'128')" />
								</td>
								<th>작성자</th>
								<td>
									<label class="blind" for="mdfrId">작성자</label>
									<input type="text" id="mdfrId" name="mdfrId" class="input_text" title="작성자" value="<c:out value="${wcBbsInfo.mdfrId eq null ? '시스템관리자' : wcBbsInfo.mdfrId}" />" disabled />
								</td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<c:choose>
							<c:when test="${wcBbsInfo.bbsSe eq 03}">
								<th>질문</th>
							</c:when>
							<c:otherwise>
								<th>내용</th>
							</c:otherwise>
						</c:choose>
						<td colspan="5">
							<c:choose>
								<c:when test="${wcBbsInfo.bbsSe eq 03}">
									<label class="blind" for="bbsCn">질문</label>
								</c:when>
								<c:otherwise>
									<label class="blind" for="bbsCn">내용</label>
								</c:otherwise>
							</c:choose>
							<textarea id="bbsCn" name="bbsCn" class="input_text required bbs_content_edit" title="<c:out value="${wcBbsInfo.bbsSe eq 03 ? '질문' : '내용'}" />" placeholder="<c:out value="${wcBbsInfo.bbsSe eq 03 ? '질문 입력' : '내용 입력'}" />" validTxt="<c:out value="${wcBbsInfo.bbsSe eq 03 ? '질문' : '내용'}" />을 입력하십시오." onKeyUp="javascript:wook.app.common.chkBytes(this,'4000')"><c:out value="${wcBbsInfo.bbsCn}" /></textarea>
						</td>
					</tr>
					<tr>
						<th>비고</th>
						<td colspan="5">
							<input type="text" id="rmks" name="rmks" class="input_text" title="비고" placeholder="비고 입력" validTxt="비고를 입력하십시오." value="<c:out value="${wcBbsInfo.rmks}" />" onKeyUp="javascript:wook.app.common.chkBytes(this,'256')" />
						</td>
					</tr>
					<c:if test="${info.bbsSe eq 01}">
					<tr>
						<th>고정여부</th>
						<td>
							<input type="checkbox" id="fixYn" ${wcBbsInfo.bbsFixYn eq 'Y' ? 'checked' : ''}/>
							<label for="fixYn">고정여부</label>
						</td>
						<th>공지기한</th>
						<td colspan="3" class = "tl">
							<fmt:parseDate value="${wcBbsInfo.pstgBgngYmd}" pattern="yyyyMMdd" var="pstgBgngYmd"/>
							<fmt:parseDate value="${wcBbsInfo.pstgEndYmd}" pattern="yyyyMMdd" var="pstgEndYmd"/>
						
							<label class="blind" for="pstgBgngYmd">공지시작일자</label>
							<input type="text" id="pstgBgngYmd" name="pstgBgngYmd" class="required w200" title="공지시작일자" placeholder="공지시작일자" validTxt="공지시작일자를 선택하십시오." value="<fmt:formatDate value="${pstgBgngYmd}" pattern="yyyy-MM-dd" type="DATE" />" style="margin-right: 5px;"/>
							&nbsp;~&nbsp;
							<label class="blind" for="pstgEndYmd">공지종료일자</label>
							<input type="text" id="pstgEndYmd" name="pstgEndYmd" class="required w200" title="공지종료일자" placeholder="공지종료일자" validTxt="공지종료일자를 선택하십시오." value="<fmt:formatDate value="${pstgEndYmd}" pattern="yyyy-MM-dd" type="DATE" />" style="margin-right: 5px;"/>							
						</td>
					</tr>
					</c:if>
					<tr>
						<th>첨부파일</th>
						<td colspan="5" style="text-align: left;">
							<div id="divWcBbsFileList" class="div-file-list">
								<ul>
									<li>등록된 파일이 없습니다.</li>
								</ul>
							</div>
							<div id="divFileList" data-maxcnt="99"></div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div class="common_bottom">
			<c:if test="${wcBbsInfo.bbsSn eq null}">
				<button type="button" id="btnInsert" title="등록">등록</button>
			</c:if>
			<c:if test="${wcBbsInfo.bbsSn ne null}">
				<button type="button" id="btnUpdate" title="저장">저장</button>
				<button type="button" id="btnDelete" title="삭제">삭제</button>
			</c:if>
			<button type="button" id="btnCancel" title="취소">취소</button>
		</div>
	</form>
</div>
<script>
var today = $.datepicker.formatDate("yy-mm-dd", new Date());
var _wcBbsFile = new fileUpload("divFileList", "_wcBbsFile", null, null, null);
var _wcBbsFileTarget = null;

var menuName = null;

$(document).ready(function() {
	wook.app.common.movePageEventHandler();
	wook.app.common.datePickerFormToDate('pstgBgngYmd', 'pstgEndYmd');

	// 게시판 구분에 따른 messageBox 문구 선택
	if ($("#bbsSe").val() == 01) {
		menuName = '공지사항';
	} else if ($("#bbsSe").val() == 02) {
		menuName = '자료';
	} else if ($("#bbsSe").val() == 04) {
		menuName = '현재 내용';
	} else {
		menuName = '질문';
	}

	invokeEvent();
	getWcBbsFileList();
});

/*=======================
invoke event
=========================*/
function invokeEvent() {
	//게시판 구분이 자료실인 경우 게시글 입력(수정)화면일때 첨부파일 필수 CSS적용
	if($("#bbsSe").val() == "02") {
		$("#divWcBbsFileList").parents("td").attr("class", "required");
	}
	
	//게시시작일자 입력 제한
	if($("#pstgBgngYmd").val() == "") {
		$("#pstgBgngYmd").datepicker("option", "minDate", today);
	} else if($("#pstgBgngYmd").val() != null && $("#pstgBgngYmd").val() != "") {
		$("#pstgBgngYmd").datepicker("option", "minDate", $("#pstgBgngYmd").val());
	}
	
	//상세정보 닫기
	$("#divWcBbsDetail #btnCancel").on("click", function() {
		if($("#pageMode").val() == "C") {
			$("#divWcBbsDetail").hide();
			$("#tblList tbody tr").removeClass("selected");
		} else {
			var dataValue = [];
			dataValue[0] = $("#bbsSn").val();
			dataValue[1] = $("#bbsSe").val();
			
			getWcBbsDetail(dataValue);
		}
	});

	//상세정보 등록
	$("#bbsForm #btnInsert").on("click", function() {
		if ( !excuteFormValidate('#bbsForm')) {
			return false;
		}
		
		wook.app.common.messageBox(null, menuName + " 등록", menuName + "${info.bbsSe eq '02' ? '를' : '을'} 등록하시겠습니까?", insertWcBbs);
	});

	//상세정보 수정
	$("#bbsForm #btnUpdate").on("click", function() {
		if ( !excuteFormValidate('#bbsForm')) {
			return false;
		}
		
		wook.app.common.messageBox(null, menuName + " 수정", menuName + "${info.bbsSe eq '02' ? '를' : '을'} 수정하시겠습니까?", updateWcBbs);
	});

	//상세정보 삭제
	$("#bbsForm #btnDelete").on("click", function() {
		wook.app.common.messageBox(null, menuName + " 삭제", menuName + "${info.bbsSe eq '02' ? '를' : '을'} 삭제하시겠습니까?", deleteWcBbs);
	});

	// 선택한 파일 삭제
	$(".div-file-list #btnDeleteFile").click(function(e) {
		_wcBbsFileTarget = $(this);
		wook.app.common.messageBox(null, "첨부파일 삭제", "선택한 첨부파일을 삭제하시겠습니까?", deleteSyBbsFile);
	});

	//공지사항 고정여부
	$("#fixYn").click(function() {
		if($("#fixYn").is(":checked")) {
			$("#bbsFixYn").val("Y");
		} else {
			$("#bbsFixYn").val("N");
		}
	});
}

// 게시판 파일목록 조회
function getWcBbsFileList(){
	
	var fileUrl = "<c:url value='/wc/getWcBbsFileList.do' />";
	//첨부파일 조회 데이터 설정
	data = {
				bbsSn		:	$("#bbsSn").val(),
				fileSe		:	$("#fileSe").val(),
			};
	getFileList("divWcBbsFileList", fileUrl, data , "Y", "Y");
}

//첨부파일 삭제
function deleteWcBbsFile(){
	var wcBbsFileSnList = [];
	_wcBbsFileTarget.parent("div").find("input[type=checkbox]:checked").each(function() {
		wcBbsFileSnList.push($(this).val());
	});

	if(!wcBbsFileSnList.length > 0) {
		wook.app.common.messageBox(null, "첨부파일 삭제 실패", "선택된 첨부파일이 없습니다.", null);
		return false;
	}

	$.ajax({
		url			:	"<c:url value='/wc/deleteWcBbsFile.do' />",
		dataType	:	"json",
		type		:	"post",
		data		:	{
							wcBbsFileSnList	:	wcBbsFileSnList
						},
		success		:	function(data, textStatus) {
						if(data.result > 0) {
							wook.app.common.messageBox(null, "첨부파일 삭제 성공", "첨부파일 삭제를 성공하였습니다", getWcBbsFileList());
						} else {
							wook.app.common.messageBox(null, "첨부파일 삭제 실패", "첨부파일 삭제를 실패하였습니다", null);
						}

					},
		beforeSend	:	function(XMLHttpRequest) {
						XMLHttpRequest.setRequestHeader("AJAX", "Yes");
					},
		error		:	function(XMLHttpRequest, textStatus, errorThrown) {
						if (XMLHttpRequest.status == 403 || XMLHttpRequest.status == 207) {
							wook.app.common.sessionTimeout();
						} else {
							wook.app.common.messageBox(null, "첨부파일 삭제 실패", "첨부파일 삭제를 실패하였습니다", null);
						}
					}
	});
}

// 게시판 상세정보 등록
function insertWcBbs() {
	if ( !excuteFormValidate('#bbsForm')) {
		return false;
	}
	
	// 첨부파일 필수항목 체크
	if(!checkFileListLength('#bbsForm', null, _wcBbsFile)) {
		return false;
	}

	$("#divWcBbsDetail").hide();

	$("#bbsForm").ajaxForm({
		type 		: "post",
		dataType	: "json",
		url 		: "<c:url value='/wc/insertWcBbs.do' />",
		beforeSend	:	function(XMLHttpRequest, obj) {
							//첨부파일 formData 추가
							_wcBbsFile.beforeSend(obj);
							XMLHttpRequest.setRequestHeader("AJAX", "Yes");
		},
		success 	: 	function(data, textStatus, XMLHttpRequest){
							if (data.result.status == "success") {
								wook.app.common.messageBox(null, menuName + " 등록 성공", menuName + "${bbsInfo.bbsSe eq '02' ? '가' : '이'} 등록되었습니다.", null);
								searchPage(1);
							} else {
								wook.app.common.messageBox(null, menuName + " 등록 실패", menuName + " 등록에 실패했습니다.", null);
							}
						},
		error		: 	function(XMLHttpRequest, textStatus, errorThrown){
							if (XMLHttpRequest.status == 403 || XMLHttpRequest.status == 207) {
								wook.app.common.sessionTimeout();
							} else {
								wook.app.common.messageBox(null, menuName + " 등록 실패", menuName + " 등록에 실패했습니다.", null);
							}
						}
	}).submit();
}

// 게시판 상세정보 수정
function updateWcBbs() {
	if ( !excuteFormValidate('#bbsForm')) {
		return false;
	}
	
	// 첨부파일 필수항목 체크
	if(!checkFileListLength('#bbsForm', 3, _wcBbsFile)) {
		return false;
	}

	$("#divWcBbsDetail").hide();

	$("#bbsForm").ajaxForm({
		type 		: "post",
		dataType	: "json",
		url 		: "<c:url value='/wc/updateWcBbs.do' />",
		beforeSend	:	function(XMLHttpRequest, obj) {
							//첨부파일 formData 추가
							_wcBbsFile.beforeSend(obj);
							XMLHttpRequest.setRequestHeader("AJAX", "Yes");
						},
		success 	: 	function(data, textStatus, XMLHttpRequest){
							if (data.result.status == "success") {
								wook.app.common.messageBox(null, menuName + " 수정 성공", menuName + "${info.bbsSe eq '02' ? '가' : '이'} 수정되었습니다.", null);
								searchPage(1);
							} else {
								wook.app.common.messageBox(null, menuName + " 수정 실패", menuName + " 수정에 실패했습니다.", null);
							}
						},
		error		: 	function(XMLHttpRequest, textStatus, errorThrown){
							if (XMLHttpRequest.status == 403 || XMLHttpRequest.status == 207) {
								wook.app.common.sessionTimeout();
							} else {
								wook.app.common.messageBox(null, menuName + " 수정 실패", menuName + " 수정에 실패했습니다.", null);
							}
						}
	}).submit();
}

// 게시판 상세정보 삭제
function deleteWcBbs() {
	$("#divWcBbsDetail").hide();

	$.ajax({
		url 		: "<c:url value='/wc/deleteWcBbs.do' />",
		type 		: "post",
		data		: 	{
							bbsSn : $("#bbsSn").val()
						},
		dataType 	: "json",
		beforeSend	:	function(XMLHttpRequest) {
							XMLHttpRequest.setRequestHeader("AJAX", "Yes");
						},
		success 	: 	function(data, textStatus, XMLHttpRequest){
							if (data.result != 0) {
								wook.app.common.messageBox(null, menuName + " 삭제 성공", menuName + "${info.bbsSe eq '02' ? '가' : '이'} 삭제되었습니다.", null);
								searchPage(1);
							} else {
								wook.app.common.messageBox(null, menuName + " 삭제 실패", menuName + " 삭제에 실패했습니다.", null);
							}
						},
		error		: 	function(XMLHttpRequest, textStatus, errorThrown){
							if (XMLHttpRequest.status == 403 || XMLHttpRequest.status == 207) {
								wook.app.common.sessionTimeout();
							} else {
								wook.app.common.messageBox(null, menuName + " 삭제 실패", menuName + " 삭제에 실패했습니다.", null);
							}
						}
	});
}

</script>