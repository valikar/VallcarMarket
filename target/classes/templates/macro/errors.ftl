[#ftl]
<ul class="nav navbar-nav navbar-right">
[#if errors??]
    [#list errors as error]
        <ul class="list-group">
            <li class="list-group-item list-group-item-danger">[#if error.field??]${error.field}: [/#if]${error.defaultMessage}</li>
        </ul>
    [/#list]
    [/#if]
</ul>

