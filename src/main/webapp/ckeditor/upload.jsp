<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/common/tagCommon.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>upload</title>
</head>
<body>
<script type='text/javascript'>
 window.parent.CKEDITOR.tools.callFunction('${CKEditorFuncNum}', '/jfile/preview.do?fileId=${fileId}&fileSeq=1','업로드 완료 ');
</script>
</body>
</html>

