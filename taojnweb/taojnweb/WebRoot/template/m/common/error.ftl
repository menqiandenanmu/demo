<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>江南钱包账户管理系统</title>
<link href="${base}/style/m/css/reset.css" rel="stylesheet" type="text/css">
<link href="${base}/style/m/css/common.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/page.css"/>
<!--[if lte IE 8]>
<link rel="stylesheet" type="text/css" href="${base}/style/m/css/ie8.css" />
<![endif]-->
<script src="${base}/scripts/require.js" data-main="scripts/main"></script>
</head>
<body>
    <!--表单正文开始-->
            <div class="page-main">
                <div class="page-item page-title">系统错误</div>
                <div class="page-item form-panel">
    <ul>
      <li><span class="lab-title">很抱歉，操作失败！：</span>
            <div class="form-item">
              ${message?default("")}
            </div>
        </li>
          </ul>
    </div>
</div>
<!--列表正文结束-->
</body>
</html>