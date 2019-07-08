<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User form</title>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
    </head>
    <script>
        function send(m) {
            var userForm = document.getElementById('userForm');
            if(m == 'get') {
                userForm.method = 'get';
            } else {
                userForm.method = 'post';
            }
            document.getElementById('_method').value = m;
            userForm.submit();
        }
    </script>
    <body style="padding: 20px">
        <form id="userForm" class="pure-form" method="post" action="http://localhost:8080/SpringMVC_Web/mvc/user/" target="myframe">
            <fieldset>
                <legend>User form</legend>
                <input type="hidden" name="_method" id="_method" value="post"> 
                <input type="text" placeholder="user name" name="uname" value="Vincent"><p>
                <input type="text" placeholder="user age" name="uage" value="10"><p>
                <input type="text" placeholder="num id" name="num.id" value="3"><p>
                <button type="button" class="pure-button pure-button-primary" onclick="send('post')">POST 新增</button>
                <button type="button" class="pure-button pure-button-primary" onclick="send('put')">PUT 修改</button>
                <button type="button" class="pure-button pure-button-primary" onclick="send('delete')">Delete 刪除</button>
                <button type="button" class="pure-button pure-button-primary" onclick="send('get')">Get 查詢</button>
            </fieldset>
        </form>
        <iframe name="myframe" width="500" frameBorder="0"></iframe>
    </body>
    
</html>
