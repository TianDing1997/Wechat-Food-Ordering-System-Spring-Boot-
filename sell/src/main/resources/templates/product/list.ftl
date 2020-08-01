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
                            <th>Product Id</th>
                            <th>Name</th>
                            <th>Image</th>
                            <th>Unit Price</th>
                            <th>Stock</th>
                            <th>Description</th>
                            <th>Category</th>
                            <th>Create Time</th>
                            <th>Update Time</th>
                            <th colspan="2">Operation</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list productInfoPage.content as productInfo>
                            <tr>
                                <td>${productInfo.productId}</td>
                                <td>${productInfo.productName}</td>
                                <td><img height="100" width="100" src="${productInfo.productIcon}" alt=""></td>
                                <td>${productInfo.productPrice}</td>
                                <td>${productInfo.productStock}</td>
                                <td>${productInfo.productDescription}</td>
                                <td>${productInfo.categoryType}</td>
                                <td>${productInfo.createTime}</td>
                                <td>${productInfo.updateTime}</td>
                                <td>
                                    <a href="/sell/seller/product/index?productId=${productInfo.productId}">Update</a>
                                </td>
                                <td>
                                    <#if productInfo.getProductStatusEnum().message == "on sale">
                                        <a href="/sell/seller/product/off_sale?productId=${productInfo.productId}">OffSale</a>
                                    <#else>
                                        <a href="/sell/seller/product/on_sale?productId=${productInfo.productId}">OnSale</a>
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
                                <a href="/sell/seller/product/list?page=${curPage-1}&size=${size}">Prev</a>
                            </li>
                        </#if>

                        <#list 1..productInfoPage.getTotalPages() as index>
                            <#if curPage == index>
                                <li class="disabled">
                                    <a href="#">${index}<br></a>
                                </li>
                            <#else>
                                <li>
                                    <a href="/sell/seller/product/list?page=${index}&size=${size}">${index}<br></a>
                                </li>
                            </#if>
                        </#list>

                        <#if curPage gte productInfoPage.getTotalPages()>
                            <li class="disabled">
                                <a href="#">Next</a>
                            </li>
                        <#else>
                            <li>
                                <a href="/sell/seller/product/list?page=${curPage+1}&size=${size}">Next</a>
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