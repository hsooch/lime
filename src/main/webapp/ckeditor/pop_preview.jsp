<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/tagCommon.jsp" %>
<!doctype html>
<html lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="expires" content="now" />
<meta http-equiv="Cache-Control" content="private" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>이노디터(InnoDitor) 미리보기</title>
<link rel="stylesheet" type="text/css" href="/web/css/_preview.css" />


<script type="text/javascript" language="javascript">
//<![CDATA[
function fnLoadPreview()
{
    var strBackgroundColor = "";
    var strBackgroundImage = "";
    var strBackgroundRepeat = "";

    if((null != window.dialogArguments) && ("undefined" != window.dialogArguments))
    {
        var objPreviewParam = window.dialogArguments;
        //document.body.innerHTML = objPreviewParam.previewHTML;
        document.getElementById('wrap').innerHTML = objPreviewParam.previewHTML;

        strBackgroundColor = objPreviewParam.previewBody.style.backgroundColor;
        strBackgroundImage = objPreviewParam.previewBody.style.backgroundImage;
        strBackgroundRepeat = objPreviewParam.previewBody.style.backgroundRepeat;

        if((null != strBackgroundImage) && ("" != strBackgroundImage))
        {
            document.body.style.backgroundImage = strBackgroundImage;
        }
    }
    else
    {
        document.getElementById('wrap').innerHTML = opener.fnGetEditorHTML();

        strBackgroundColor = opener.fnGetBodyStyleValue(1, -1);
        strBackgroundImage = opener.fnGetBodyStyleValue(2, -1);
        strBackgroundRepeat = opener.fnGetBodyStyleValue(3, -1);

        if((null != strBackgroundImage) && ("" != strBackgroundImage))
        {
            document.body.style.backgroundImage = "url(" + strBackgroundImage + ")";
        }
    }


    if((null != strBackgroundColor) && ("" != strBackgroundColor))
    {
        document.body.style.backgroundColor = strBackgroundColor;
    }

    if((null != strBackgroundRepeat) && ("" != strBackgroundRepeat))
    {
        document.body.style.backgroundRepeat = strBackgroundRepeat;
    }
}
//]]>
</script>
</head>

<body onload="fnLoadPreview();" style="margin-left:11px;margin-top:10px;">
<div id="wrap"></div>
</body>
</html>
