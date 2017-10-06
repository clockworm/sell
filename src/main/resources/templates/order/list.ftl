<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#--边栏-->
    <#include "../common/nav.ftl">
    <#--主要内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
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
                        <#list data.content as orderDTO>
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
            <#include "../common/page.ftl">
            </div>
        </div>
    </div>
</div>
</body>
</html>
