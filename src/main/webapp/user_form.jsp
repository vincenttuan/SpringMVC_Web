<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User form</title>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
    </head>
    <body style="padding: 20px">
        <form class="pure-form" method="post" action="http://localhost:8080/SpringMVC_Web/mvc/user/">
            <fieldset>
                <legend>User form</legend>
                <input type="hidden" name="_method" value="delete">    
                <button type="submit" class="pure-button pure-button-primary">Submit</button>
            </fieldset>
        </form>
    </body>
</html>
