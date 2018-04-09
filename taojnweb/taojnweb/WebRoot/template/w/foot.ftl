<footer>
    <ul class="footer-list">
        <li <#if menu="index"> class="cur"</#if>><a href="${base}/index.htm?love"><i class="footer-icon icon-home"></i>主页</a></li>
        <li <#if menu="recharge"> class="cur"</#if>><a href="${base}/user/toRecharge.htm?love"><i class="footer-icon icon-money"></i>充值</a></li>
        <li <#if menu="transInfo"> class="cur"</#if>><a href="${base}/user/transInfo.htm?love"><i class="footer-icon icon-bill"></i>账单</a></li>
        <li <#if menu="userInfo"> class="cur"</#if>><a href="${base}/user/index.htm?love"><i class="footer-icon icon-my"></i>我的</a></li>
    </ul>
</footer>