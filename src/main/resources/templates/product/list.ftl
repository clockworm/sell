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
                            <th>商品编号</th>
                            <th>商品名称</th>
                            <th>商品图鉴</th>
                            <th>商品单价</th>
                            <th>商品库存</th>
                            <th>商品描述</th>
                            <th>商品状态</th>
                            <th>商品类目</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list data.content as productDTO>
                        <tr>
                            <td> ${productDTO.productId}</td>
                            <td> ${productDTO.productName}</td>
                            <td> ${productDTO.productIcon}</td>
                            <td> ${productDTO.productPrice}</td>
                            <td> ${productDTO.productStock}</td>
                            <td> ${productDTO.productDescription}</td>
                            <td>${productDTO.getProductStatusMsg()}</td>
                            <td> ${productDTO.categoryType}</td>
                            <td> ${productDTO.createTime}</td>
                            <td> ${productDTO.updateTime}</td>
                            <td>
                                <a href="/sell/seller/order/detail?orderId=${productDTO.productId}">修改</a>
                            </td>
                            <td>
                                <#if productDTO.getProductStatusMsg()=="上架">
                                    <a href="/sell/seller/product/offOronSale?productId=${productDTO.productId}&status=0">下架</a>
                                    <#else>
                                        <a href="/sell/seller/product/offOronSale?productId=${productDTO.productId}&status=1">上架</a>
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
