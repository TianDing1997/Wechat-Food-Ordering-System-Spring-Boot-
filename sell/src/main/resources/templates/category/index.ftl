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
                <form role="form" method="post" action="/sell/seller/category/save">
                    <div class="form-group">
                        <label>Name</label>
                        <input name="categoryName" type="test" class="form-control" value="${(productCategory.categoryName)!''}"/>
                    </div>
                    <div class="form-group">
                        <label>Type</label>
                        <input name="categoryType" type="number" class="form-control" value="${(productCategory.categoryType)!''}"/>
                    </div>

                    <input hidden type = "text" name="categoryId" value="${(productCategory.categoryId)!''}">

                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>

</div>

</body>
</html>