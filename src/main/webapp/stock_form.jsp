<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>SpringMVC Stock</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script>
            String.prototype.format = function () {
                var args = arguments;
                return this.replace(/\{(\d+)\}/g, function (m, i, o, n) {
                    return args[i];
                });
            };
            
            function query() {
                $.ajax({
                    url: './mvc/stock_controller/query/stock',
                    type: 'GET',
                    success: function (data, status) {
                        console.log("Data: " + JSON.stringify(data) + "\nStatus: " + status);
                        var html = '';
                        // jQuery 分析 Json[] 語法
                        $.each(data, function (i, stock) {
                            html += '<tr><td style="cursor:hand" title="按我一下">{0}</td><td>{1}</td><td>{2}</td></tr>'.format(stock.stockId, stock.stockCode, stock.stockName);
                        });
                        $("#stockTbody").empty();
                        $("#stockTbody").append(html);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(jqXHR);
                    }
                });
            }
            
            function add() {
                console.log($('#stock_form').serialize());
                $.ajax({
                    url: './mvc/stock_controller/add/stock',
                    type: 'POST',
                    data: $('#stock_form').serialize(),
                    success: function (data, status) {
                        console.log("Data: " + JSON.stringify(data) + "\nStatus: " + status);
                        query();
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(jqXHR);
                    }
                });
            }
            
            $(document).ready(function () {
                $("#query_button").click(function () {
                    query();
                });
                
                $("#post_button").click(function () {
                    add();
                });
                
                $("#stockTbody").on('click', 'td:nth-child(1)', function () {
                    var id = $(this).text();
                    console.log(id);
                });

                $("#fund_span").on('click', function () {
                    //window.location.href = 'fund_form.jsp';
                });
                $("#investor_span").on('click', function () {
                    //window.location.href = 'investor_form.jsp';
                });

            });

        </script>
    </head>
    <body style="padding: 30px">

        <table class="pure-table pure-table-bordered">
            <td valign="top">
                <form id="stock_form" class="pure-form" method="post" action="">
                    <fieldset>
                        <legend><b>Stock</b> | <span id="fund_span" style="cursor:hand">Fund</span>| <span id="investor_span" style="cursor:hand">Investor</span></legend>
                        <input type="text" placeholder="序號" name="stockId" value="" readonly><p/>
                        <input type="text" placeholder="請輸入股票代號" name="stockCode" value=""><p/>
                        <input type="text" placeholder="請輸入股票名稱" name="stockName" value=""><p/>
                        <button type="button" id="query_button" class="pure-button pure-button-primary">Query</button>
                        <button type="button" id="post_button" class="pure-button pure-button-primary">POST</button>
                        <button type="button" id="update_button" class="pure-button pure-button-primary">PUT</button>
                        <button type="button" id="delete_button" class="pure-button pure-button-primary">DELETE</button>
                    </fieldset>
                </form>
                <table class="pure-table pure-table-bordered">
                    <thead>
                        <tr>
                            <th>stockId</th>
                            <th>stockCode</th>
                            <th>stockName</th>
                        </tr>
                    </thead>
                    <tbody id="stockTbody">

                    </tbody>
                </table>
            </td>
            <td valign="top">
                <!-- chart -->
                
            </td>
        </table>

    </body>
</html>

