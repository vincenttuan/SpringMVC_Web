<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>SpringMVC Fund</title>
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

            function queryStock() {
                $.ajax({
                    url: './mvc/stock_controller/query/stock',
                    type: 'GET',
                    success: function (data, status) {
                        console.log("Data: " + JSON.stringify(data) + "\nStatus: " + status);
                        $("#selectStock option").remove();
                        $.each(data, function (i, stock) {
                            $("#selectStock").append(new Option(stock.stockCode + ' ' + stock.stockName, stock.stockId));
                        });

                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(jqXHR);
                    }
                });
            }

            function query() {
                $.ajax({
                    url: './mvc/stock_controller/query/fund',
                    type: 'GET',
                    success: function (data, status) {
                        console.log("Data: " + JSON.stringify(data) + "\nStatus: " + status);
                        var markup = '';
                        $.each(data, function (i, fund) {
                            var traderName = '';
                            if (fund.traders != null) {
                                for (var i = 0; i < fund.traders.length; i++) {
                                    traderName = fund.traders[i].name;
                                    break;
                                }
                            }
                            markup += '<tr><td>{0}</td><td>{1}</td><td>{2}</td><td>{3}</td><td>{4}</td><td>{5}</td></tr>'.format(fund.fundId, fund.name, fund.desc, fund.fundNet.value, traderName, fund.stocks.length);
                        });
                        $("#fundTbody").empty();
                        $("#fundTbody").append(markup);
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
                    console.log($('#fund_form').serialize());
                    
                    $.ajax({
                        url: './mvc/stock_controller/add/fund',
                        type: 'POST',
                        data: $('#fund_form').serialize(),
                        success: function (data, status) {
                            console.log("Data: " + JSON.stringify(data) + "\nStatus: " + status);
                            query();    
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log(jqXHR);
                        }
                    });

                });
                
                $("#update_button").click(function () {
                    console.log($('#fund_form').serialize());
                    var id = $('#fund_form').find('input[name="fundId"]').val();
                    var path = './mvc/stock_controller/update/fund/' + id;
                    $.ajax({
                        url: path,
                        type: 'POST',
                        data: $('#fund_form').serialize(),
                        success: function (data, status) {
                            console.log("Data: " + JSON.stringify(data) + "\nStatus: " + status);
                            query();
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log(jqXHR);
                        }
                    });

                });
                
                $("#updateFundStock_button").click(function () {
                    var id = $("#fundId").val();
                    
                    // 將項目內容全選
                    $('#selectedStock option').prop('selected', true);
                    console.log($('#fundStock_form').serialize());
                    console.log(id);
                      
                    $.ajax({
                        url: './mvc/stock_controller/update/fund_stock/' + id,
                        type: 'POST',
                        data: $('#fundStock_form').serialize(),
                        success: function (data, status) {
                            console.log("Data: " + JSON.stringify(data) + "\nStatus: " + status);
                            query();        
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log(jqXHR);
                        }
                    });
                    

                });

                $("#selectStock").on('dblclick', function () {
                    var stockName = $("#selectStock").find(":selected").text();
                    var stockCode = $("#selectStock").find(":selected").val();
                    $("#selectedStock").append(new Option(stockName, stockCode));
                    $("#selectStock").find(":selected").remove();
                });
                $("#selectedStock").on('dblclick', function () {
                    var stockName = $("#selectedStock").find(":selected").text();
                    var stockCode = $("#selectedStock").find(":selected").val();
                    $("#selectStock").append(new Option(stockName, stockCode));
                    $("#selectedStock").find(":selected").remove();
                });
                
                $("#fundTbody").on('click', 'td:nth-child(1)', function () {
                    console.log($(this).text());
                    var id = $(this).text();
                    var path = './mvc/stock_controller/get/fund/' + id;
                    $.ajax({
                        url: path,
                        type: 'GET',
                        success: function (data, status) {
                            console.log("Data: " + JSON.stringify(data) + "\nStatus: " + status);
                            for (var i in data) {
                                $('#fund_form').find('input[name="' + i + '"]').val(data[i]);
                            }
                            $('#fund_form').find('input[name="value"]').val(data.fundNet.value);
                            $('#fund_form').find('input[name="traderName"]').val(data.traders[0].name);
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log(jqXHR);
                        }
                    });
                });
                
                $("#fundTbody").on('dblclick', 'tr', function () {
                    queryStock();
                    var fundId = $(this).find("td:eq(0)").text();
                    $("#fundId").val(fundId);
                    var path = './mvc/stock_controller/get/fund/' + fundId;
                    $.ajax({
                        url: path,
                        type: 'GET',
                        success: function (data, status) {
                            console.log("Data: " + JSON.stringify(data) + "\nStatus: " + status);
                            $("#selectedStock option").remove();
                            $.each(data.stocks, function (i, stock) {
                                $("#selectedStock").append(new Option(stock.stockCode + ' ' + stock.stockName, stock.stockId));
                                $("#selectStock option[value='" + stock.stockId + "']").remove();
                            });
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log(jqXHR);
                        }
                    });
                });
                $("#stock_span").on('click', function () {
                    window.location.href = 'stock_form.jsp';
                });
                $("#investor_span").on('click', function () {
                    window.location.href = 'investor_form.jsp';
                });
            });
        </script>
    </head>
    <body style="padding: 30px">

        <table class="pure-table pure-table-bordered">
            <td valign="top">
                <form id="fund_form" class="pure-form" method="post" action="">
                    <fieldset>
                        <legend><span id="stock_span" style="cursor:hand">Stock</span> | <b>Fund</b> | <span id="investor_span" style="cursor:hand">Investor</span></legend>
                        <input type="hidden" name="_method" id="_method" value="POST"/>
                        <input type="text" placeholder="基金序號" name="fundId" value="" readonly><p/>
                        <input type="text" placeholder="請輸入基金名稱" name="name" value=""><p/>
                        <input type="text" placeholder="請輸入投資敘述" name="desc" value=""><p/>
                        <input type="text" placeholder="請輸入基金價格" name="value" value=""><p/>
                        <input type="text" placeholder="請輸入基金經理人姓名" name="traderName" value=""><p/>
                        <button type="button" id="query_button" class="pure-button pure-button-primary">Query</button>
                        <button type="button" id="post_button" class="pure-button pure-button-primary">POST</button>
                        <button type="button" id="update_button" class="pure-button pure-button-primary">PUT</button>
                    </fieldset>
                </form>
                <table class="pure-table pure-table-bordered">
                    <thead>
                        <tr>
                            <th>fundId</th>
                            <th>fundName</th>
                            <th>fundDesc</th>
                            <th>fundValue</th>
                            <th>traders</th>
                            <th>stocks</th>
                        </tr>
                    </thead>
                    <tbody id="fundTbody">

                    </tbody>
                </table>
            </td>
            <td valign="top">
                <form class="pure-form">
                    <legend>基金序號</legend>
                    <input type="text" placeholder="基金序號" id="fundId" name="fundId" value="" readonly><p/>
                </form>
                <form id="fundStock_form" class="pure-form" method="post" action="">
                    <legend>選擇成分股</legend>
                    <table>
                        <tr>
                            <th>待選</th>
                            <th>已選</th>
                        </tr>
                        <tr>
                            <td>
                                <select id="selectStock" multiple style="width: 150px"></select><p/>
                            </td>
                            <td>
                                <select id="selectedStock" name="stockIds" multiple style="width: 150px"></select><p/>
                            </td>
                        </tr>    
                    </table><p/>   
                    <button type="button" id="updateFundStock_button" class="pure-button pure-button-primary">UPDATE</button>    
                </form>
            </td>
        </table>

    </body>
</html>

