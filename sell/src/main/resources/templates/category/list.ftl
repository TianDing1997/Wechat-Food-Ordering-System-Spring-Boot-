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
                        <th>Category Id</th>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Create Time</th>
                        <th>Update Time</th>
                        <th>Operation</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list categoryList as category>
                        <tr>
                            <td>${category.categoryId}</td>
                            <td>${category.categoryName}</td>
                            <td>${category.categoryType}</td>
                            <td>${category.createTime}</td>
                            <td>${category.updateTime}</td>
                            <td>
                                <a href="/sell/seller/category/index?categoryId=${category.categoryId}">update</a>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</div>

</body>
</html>