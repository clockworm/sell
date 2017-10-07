<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#--边栏-->
<#include "../common/nav.ftl">
<#--主要内容-->
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" action="/sell/seller/product/saveOrupdate" method="post">
                        <div class="form-group">
                            <label>商品名称</label>
                            <input type="text" class="form-control" name="productName" value="${(data.productName)!''}"/>
                        </div>

                        <div class="form-group">
                            <label>商品价格</label>
                            <input type="text" class="form-control" name="productPrice" value="${(data.productPrice)!''}"/>
                        </div>

                        <div class="form-group">
                            <label>商品库存</label>
                            <input type="number" class="form-control" name="productStock" value="${(data.productStock)!''}"/>
                        </div>

                        <div class="form-group">
                            <label>商品描述</label>
                            <input type="text" class="form-control" name="productDescription" value="${(data.productDescription)!''}"/>
                        </div>

                        <div class="form-group">
                            <label>商品简图</label>
                            <img height="100" width="100" src="${(data.productIcon)!''}" alt="">
                            <input type="text" class="form-control" name="productIcon" value="${(data.productIcon)!''}"/>
                        </div>

                        <div class="form-group">
                            <label>商品状态</label>
                            <select name="productStatus" class="form-control">
                                <option value="">请选择</option>
                            <#list productStatusEnum as productStatus>
                                <option value="${productStatus.code}"
                                        <#if (data.productStatus) ?? && data.productStatus == productStatus.code>selected</#if>
                                >${productStatus.message}</option>
                            </#list>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>商品类目</label>
                            <select name="categoryType" class="form-control">
                                <option value="">请选择</option>
                                <#list productCategorys as categoryTypes>
                                        <option value="${categoryTypes.categoryType}"
                                                <#if (data.categoryType) ?? && data.categoryType == categoryTypes.categoryType>selected</#if>
                                        >${categoryTypes.categoryName}</option>
                                </#list>
                            </select>
                        </div>
                        <input type="hidden" value="${(data.productId)!''}" name="productId">
                        </div> <button type="submit" class="btn btn-default">保存</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
