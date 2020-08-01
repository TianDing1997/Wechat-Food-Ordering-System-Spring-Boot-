<html>
    <#include  "../common/hearder.ftl">
    <body>
    <div id="wrapper" class="toggled">

        <#--navigate bar-->
        <#include  "../common/nav.ftl">

        <#--main content-->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered table-condensed">
                            <thead>
                            <tr>
                                <th>Order Id</th>
                                <th>Name</th>
                                <th>Phone Number</th>
                                <th>Address</th>
                                <th>Amount</th>
                                <th>Order Status</th>
                                <th>Payment Status</th>
                                <th>Create Time</th>
                                <th colspan="2">Operation</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list orderDTOPage.content as orderDTO>
                                <tr>
                                    <td>${orderDTO.orderId}</td>
                                    <td>${orderDTO.buyerName}</td>
                                    <td>${orderDTO.buyerPhone}</td>
                                    <td>${orderDTO.buyerAddress}</td>
                                    <td>${orderDTO.orderAmount}</td>
                                    <td>${orderDTO.getOrderStatusEnum().getMsg()}</td>
                                    <td>${orderDTO.getPayStatusEnum().getMsg()}</td>
                                    <td>${orderDTO.createTime}</td>
                                    <td>
                                        <a href="/sell/seller/order/detail?orderId=${orderDTO.getOrderId()}">Detail</a>
                                    </td>
                                    <td>
                                        <#if orderDTO.getOrderStatusEnum().getMsg() == "new order">
                                            <a href="/sell/seller/order/cancel?orderId=${orderDTO.getOrderId()}">Cancel</a>
                                        </#if>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>

                    <#--page-->
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                            <#if curPage lte 1>
                                <li class="disabled">
                                    <a href="#">Prev</a>
                                </li>
                            <#else>
                                <li>
                                    <a href="/sell/seller/order/list?page=${curPage-1}&size=${size}">Prev</a>
                                </li>
                            </#if>

                            <#list 1..orderDTOPage.getTotalPages() as index>
                                <#if curPage == index>
                                    <li class="disabled">
                                        <a href="#">${index}<br></a>
                                    </li>
                                <#else>
                                    <li>
                                        <a href="/sell/seller/order/list?page=${index}&size=${size}">${index}<br></a>
                                    </li>
                                </#if>
                            </#list>

                            <#if curPage gte orderDTOPage.getTotalPages()>
                                <li class="disabled">
                                    <a href="#">Next</a>
                                </li>
                            <#else>
                                <li>
                                    <a href="/sell/seller/order/list?page=${curPage+1}&size=${size}">Next</a>
                                </li>
                            </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

    </div>

    </body>
</html>