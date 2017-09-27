<html>
<head>
    <meta charset="utf-8">
    <title>卖家商品列表</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-bordered table-condensed">
                <thead>
                <tr>
                    <th>订单编号</th>
                    <th>买家姓名</th>
                    <th>手机号码</th>
                    <th>地址</th>
                    <th>金额</th>
                    <th>订单状态</th>
                    <th>支付状态</th>
                    <th>创建时间</th>
                    <th colspan="2">操作</th>
                </tr>
                </thead>
                <tbody>
                <#list orders.content as orderDTO>
                <tr>
                    <td> ${orderDTO.orderId}</td>
                    <td> ${orderDTO.buyerName}</td>
                    <td> ${orderDTO.buyerPhone}</td>
                    <td> ${orderDTO.buyerAddress}</td>
                    <td> ${orderDTO.orderAmount}</td>
                    <td> ${orderDTO.orderStatusMsg}</td>
                    <td> ${orderDTO.payStatusMsg}</td>
                    <td> ${orderDTO.createTime}</td>
                    <td> <a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                    <td>
                        <#if orderDTO.orderStatusMsg == "新订单">
                            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                        </#if>
                     </td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="col-md-12 column">
            <ul class="pagination pagination-sm pull-right">
            <#if currentPage lte 1>
                <li class="disabled"><a href="#">上一页</a></li>
            <#else>
                <li><a href="/sell/seller/order/list?page=${currentPage-1}&size=${size}">上一页</a></li>
            </#if>
            <#list 1..orders.getTotalPages() as index>
                <#if index == currentPage>
                    <li class="disabled"><a href="#">${currentPage}</a></li>
                <#else>
                    <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                </#if>
            </#list>
            <#if currentPage gte orders.getTotalPages()>
                <li class="disabled"><a href="#">下一页</a></li>
            <#else>
                <li><a href="/sell/seller/order/list?page=${currentPage+1}&size=${size}">下一页</a></li>
            </#if>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
