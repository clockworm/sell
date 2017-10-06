<div class="col-md-12 column">
    <ul class="pagination pagination-sm pull-right">
    <#if currentPage lte 1>
        <li class="disabled"><a href="#">上一页</a></li>
    <#else>
        <li><a href="/sell/seller/${controller}/list?page=${currentPage-1}&size=${size}">上一页</a></li>
    </#if>
    <#list 1..data.getTotalPages() as index>
        <#if index == currentPage>
            <li class="disabled"><a href="#">${currentPage}</a></li>
        <#else>
            <li><a href="/sell/seller/${controller}/list?page=${index}&size=${size}">${index}</a></li>
        </#if>
    </#list>
    <#if currentPage gte data.getTotalPages()>
        <li class="disabled"><a href="#">下一页</a></li>
    <#else>
        <li><a href="/sell/seller/${controller}/list?page=${currentPage+1}&size=${size}">下一页</a></li>
    </#if>
    </ul>
</div>