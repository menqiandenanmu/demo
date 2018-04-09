<#--未完成-->
<#macro fck name,content,validate>
${stack.findValue("@com.mall.butler.util.FckeditorUtil@getFckeditorHtml(#request,#name,#validate,#content)")}
${stack.findValue("@com.mall.butler.util.FckeditorUtil@getTest(\"123\")")}
</#macro>
<#macro fck name>
${(name)!}
</#macro>
