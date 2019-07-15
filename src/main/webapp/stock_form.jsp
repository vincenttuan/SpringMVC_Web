<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>SpringMVC Stock</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script>
            // google chart init load
            google.charts.load('current', {'packages': ['corechart']});
            function drawChart(data, title) {
                
                var options = {
                    title: title,
                    vAxis: {title: 'Price'},
                    hAxis: {title: 'Day'},
                    legend: 'none',
                    titleTextStyle: {
                        fontSize: 20
                    },
                    seriesType: "candlesticks",
                    series: {
                        1: {type: "line"}
                    }
                };

                var chart = new google.visualization.CandlestickChart(document.getElementById('chart_div'));

                chart.draw(data, options);
            }
            
            function pushData(data) {
                var dataArray = [];
                $.each(data, function (i, s) {
                    var d = s.date.month + '/' + s.date.dayOfMonth
                    var high = s.high;
                    var close = s.close;
                    var open = s.open;
                    var low = s.low;
                    var avg = (high + close + open + low) / 4;
                    dataArray[i] = [d, high, close, open, low, avg]; // 高收開低
                });
                var drawData = google.visualization.arrayToDataTable(dataArray, true);
                drawChart(drawData, data[0].symbol);
            }
            
            function getStockHistQuotes(symbol) {
                $.ajax({
                    url: './mvc/yahoo_controller/get/stock/' + symbol,
                    type: 'GET',
                    success: function (data, status) {
                        console.log("Data: " + JSON.stringify(data) + "\nStatus: " + status);
                        pushData(data);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(jqXHR);
                    }
                });
            }

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
                            html += '<tr><td style="cursor:hand" title="按我一下">{0}</td><td style="cursor:hand" title="按我二下">{1}</td><td>{2}</td></tr>'.format(stock.stockId, stock.stockCode, stock.stockName);
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

            function getById(id) {
                $.ajax({
                    url: './mvc/stock_controller/get/stock/' + id,
                    type: 'GET',
                    success: function (data, status) {
                        console.log("Data: " + JSON.stringify(data) + "\nStatus: " + status);
                        for (var i in data) {
                            $('#stock_form').find('input[name="' + i + '"]').val(data[i]);
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(jqXHR);
                    }
                });
            }
            
            

            function update() {
                console.log($('#stock_form').serialize());
                var id = $('#stock_form').find('input[name="stockId"]').val();
                console.log(id);
                $.ajax({
                    url: './mvc/stock_controller/update/stock/' + id,
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

            function deleteById(id) {
                $.ajax({
                    url: './mvc/stock_controller/delete/stock/' + id,
                    type: 'GET',
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

                $("#update_button").click(function () {
                    update();
                });

                $("#delete_button").click(function () {
                    var id = $('#stock_form').find('input[name="stockId"]').val();
                    console.log(id);
                    deleteById(id);
                });

                $("#stockTbody").on('click', 'td:nth-child(1)', function () {
                    var id = $(this).text();
                    console.log(id);
                    getById(id);
                });
                
                $("#stockTbody").on('dblclick', 'td:nth-child(2)', function () {
                    var symbol = $(this).text();
                    console.log(symbol);
                    getStockHistQuotes(symbol);
                });

                $("#fund_span").on('click', function () {
                    window.location.href = 'fund_form.jsp';
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
                <div id="chart_div" style="width: 900px; height: 500px;"></div>
            </td>
        </table>

    </body>
</html>

