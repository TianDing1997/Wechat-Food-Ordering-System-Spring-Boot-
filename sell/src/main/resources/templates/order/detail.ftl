<html>
<#include  "../common/hearder.ftl">
<body>
    <div id="wrapper" class="toggled">

        <#--navigate bar-->
        <#include  "../common/nav.ftl">

        <#--main content-->
        <div id="page-content-wrapper">
            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-4 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Order Id</th>
                                <th>Order Amount</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.orderAmount}</td>
                            </tr>

                            </tbody>
                        </table>
                    </div>

                    <#--order detail data-->
                    <div class="col-md-12 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Product Id</th>
                                <th>Product Name</th>
                                <th>Unit Price</th>
                                <th>Amount</th>
                                <th>Total Price</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list orderDTO.orderDetailList as orderDetail>
                                <tr>
                                    <td>${orderDetail.productId}</td>
                                    <td>${orderDetail.productName}</td>
                                    <td>${orderDetail.productPrice}</td>
                                    <td>${orderDetail.productQuantity}</td>
                                    <td>${orderDetail.productQuantity * orderDetail.productPrice}</td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>

                    <#--Operation-->
                    <div class="col-md-12 column">
                        <#if orderDTO.getOrderStatusEnum().getMsg() == "new order">
                            <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}" type="button" type="button" class="btn btn-default btn-primary">Order Complete</a>
                            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" type="button" type="button" class="btn btn-default btn-danger">Order Cancel</a>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>