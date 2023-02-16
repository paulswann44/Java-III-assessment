<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="partials/head.jsp">
        <jsp:param name="title" value="Add A Product" />
    </jsp:include>
</head>
<body>
<jsp:include page="partials/navbar.jsp" />

<div class="container">
    <form class="col-md-8 offset-md-2" method="post" action="/products/create">
        <h1 class="my-4">Add a Product</h1>
        <div class="form-group">
            <label for="name">Name</label>
            <input class="form-control" type="text" id="name" name="name" />
        </div>
        <div class="form-group">
            <label for="price">Price</label>
            <input class="form-control" type="number" step="0.01" id="price" name="price" />
        </div>
        <div class="form-group">
            <label for="quantity">Quantity</label>
            <div class="input-group">
                <span class="input-group-btn">
                    <button type="button" class="btn btn-default btn-number" disabled="disabled" data-type="minus" data-field="quantity">
                        <span class="glyphicon glyphicon-minus"></span>
                    </button>
                </span>
                <input type="text" id="quantity" name="quantity" class="form-control input-number" value="1" min="1" max="100">
                <span class="input-group-btn">
                    <button type="button" class="btn btn-default btn-number" data-type="plus" data-field="quantity">
                        <span class="glyphicon glyphicon-plus"></span>
                    </button>
                </span>
            </div>
        </div>
        <input class="btn btn-block btn-primary" type="submit" value="Submit" />
    </form>
</div>