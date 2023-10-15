<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn"	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=0,maximum-scale=10,user-scalable=yes">
    <!--[if lt IE 7]><meta http-equiv="imagetoolbar" content="no"><![endif]-->
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <%--
	<link rel="shortcut icon" href="<c:url value='/resources/images/icons/favicon.ico'/>" type="image/x-icon"><!-- 브라우져 파비콘 -->
 	--%>
	<title>잇(Eat)나우?</title>

    <link rel="stylesheet" href="<c:url value='/resources/css/jquery-ui.css'/>" >
    <link rel="stylesheet" href="<c:url value='/resources/css/wook.app.main.css?ver=20210915'/>" >
    <link rel="stylesheet" href="<c:url value='/resources/framework/jquery/jquery-modal/jquery.modal.css'/>" >
    <link rel="stylesheet" href="https://hangeul.pstatic.net/hangeul_static/css/nanum-gothic.css" >

	<script src="<c:url value='/resources/framework/jquery/jquery-1.12.4.min.js'/>"></script>
	<script src="<c:url value='/resources/framework/jquery/jquery-ui.js' />"></script>
	<script src="<c:url value='/resources/framework/jquery/jquery.metadata.js'/>"></script>
	<script src="<c:url value='/resources/framework/jquery/jquery.form.min.js'/>"></script>
	<script src="<c:url value='/resources/framework/jquery/jquery-modal/jquery.modal.js'/>"></script>
	<script src="<c:url value='/resources/framework/jquery/slick.js' />"></script>
	<script src="<c:url value='/resources/framework/jquery/jquery.serialize-object.js'/>"></script>
	<script src="<c:url value='/resources/js/wook.app.common.js?ver=20210915'/>"></script>
	<script src="<c:url value='/resources/js/wook.app.paginator.js'/>"></script>
	<script src="<c:url value='/resources/js/wook.app.main.js' />"></script>
	<script src="<c:url value='/resources/js/wook.app.fileupload.js' />"></script>

</head>
