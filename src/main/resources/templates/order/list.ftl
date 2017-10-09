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
<!--websocket-->
<div class="modal fade" id="message_model" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                你有新的订单
            </div>
            <div class="modal-footer">
                <button onclick="javascript:document.getElementById('notice').pause();" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <button onclick="location.reload()" type="button" class="btn btn-primary">查看新订单</button>
        </div>
        </div>
    </div>
</div>
<!--播放音乐-->
<audio id="notice" loop="loop">
    <source src="/sell/mp3/song.mp3" type="audio/mpeg">
</audio>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    var websocket = null;
    if('WebSocket' in  window){
        websocket = new WebSocket('ws://sell.mynatapp.cc/sell/webSocket');
    }else{
        alert("该浏览器不支持WebSocket!");
    }
    websocket.onopen = function (event) {
        console.log('建立连接');
    }

    websocket.onclose = function (event) {
        console.log('连接关闭');
    }

    websocket.onmessage = function (event) {
        //处理事件 弹窗 播放音乐
        $('#message_model').modal('show');
        document.getElementById('notice').play();
        console.log("收到消息:" + event.data);
    }

    websocket.onerror = function (event) {
        console.log("发生异常");
        alert('websocket通信发生错误!');
    }

    websocket.onbeforeunload = function (event) {
        websocket.close();
    }

</script>

</body>
</html>
