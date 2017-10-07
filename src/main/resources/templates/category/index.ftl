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
                    <form role="form" action="/sell/seller/category/saveOrupdate" method="post">
                        <div class="form-group">
                            <label>类目名称</label>
                            <input type="text" class="form-control" name="categoryName" value="${(data.categoryName)!''}"/>
                        </div>

                        <div class="form-group">
                            <label>类目Type</label>
                            <input type="number" class="form-control" name="categoryType" value="${(data.categoryType)!''}"/>
                        </div>
                        <input type="hidden" value="${(data.categoryId)!''}" name="categoryId">
                </div> <button type="submit" class="btn btn-default">保存</button>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
