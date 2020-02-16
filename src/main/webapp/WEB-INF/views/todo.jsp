<html>
<head>
    <title>Login Page</title>
    <%@ page isELIgnored="false" %>
    <link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<div class="container">
    <form action="/add-todo" method="POST">
        <fieldset class="form-group">
            <label>Description</label>
            <input name="desc" type="text" class="form-control" required="required"/>
        </fieldset>
        <button type="submit" class="btn btn-success">Add</button>
    </form>
</div>
<script src="webjars/jquery/3.4.1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>