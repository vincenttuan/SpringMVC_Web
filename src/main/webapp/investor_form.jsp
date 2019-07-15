<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>SpringMVC Investor</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script>
            google.charts.load('current', {'packages': ['corechart']});

            function drawChart1(data) {

                var options = {
                    title: '投資配置',
                    titleTextStyle: {
                        fontSize: 20
                    }
                };

                var chart = new google.visualization.PieChart(document.getElementById('chart_div1'));

                chart.draw(data, options);
            }
            
            function drawChart2(data, total) {

                var options = {
                    title: '投資損益 $' + total,
                    titleTextStyle: {
                        fontSize: 20
                    }
                };

                var chart = new google.visualization.BarChart(document.getElementById('chart_div2'));

                chart.draw(data, options);
            }

            function pushData(data) {
                var dataArray1 = [];
                $.each(data, function (i, investor) {
                    var subtotal = investor.units * investor.netValue;
                    dataArray1[i] = [investor.fund.name, subtotal];
                });
                var drawData1 = google.visualization.arrayToDataTable(dataArray1, true);
                drawChart1(drawData1);
                
                var dataArray2 = [];
                var total = 0;
                $.each(data, function (i, investor) {
                    var subtotal = (investor.fund.fundNet.value - investor.netValue) * investor.units;
                    total += subtotal;
                    dataArray2[i] = [investor.fund.name, subtotal];
                });
                var drawData2 = google.visualization.arrayToDataTable(dataArray2, true);
                drawChart2(drawData2, total);
            }

            String.prototype.format = function () {
                var args = arguments;
                return this.replace(/\{(\d+)\}/g, function (m, i, o, n) {
                    return args[i];
                });
            };
            
            function query() {
                $.ajax({
                    url: './mvc/stock_controller/query/investor',
                    type: 'GET',
                    success: function (data, status) {
                        console.log("Data: " + JSON.stringify(data) + "\nStatus: " + status);
                        var markup = '';
                        $.each(data, function (i, investor) {
                            markup += '<tr><td>{0}</td><td>{1}</td><td>{2}</td><td>{3}</td><td>{4}</td></tr>'
                                    .format(investor.investorId, investor.name, investor.units, investor.netValue, investor.fund.name);
                        });
                        $("#investorTbody").empty();
                        $("#investorTbody").append(markup);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(jqXHR);
                    }
                });
            }
                
            function queryFund() {
                $.ajax({
                    url: './mvc/stock_controller/query/fund',
                    type: 'GET',
                    success: function (data, status) {
                        console.log("Data: " + JSON.stringify(data) + "\nStatus: " + status);
                        $("#fundId option").remove();
                        $.each(data, function (i, fund) {
                            $("#fundId").append(new Option(fund.fundId + ' ' + fund.name + ' $' + fund.fundNet.value, fund.fundId));
                        });

                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(jqXHR);
                    }
                });
            }

            
            $(document).ready(function () {
                queryFund();
                
                $("#query_button").click(function () {
                    query();
                });
                
                $("#post_button").click(function () {
                    console.log($('#investor_form').serialize());
                    $.ajax({
                        url: './mvc/stock_controller/add/investor',
                        type: 'POST',
                        data: $('#investor_form').serialize(),
                        success: function (data, status) {
                            console.log("Data: " + JSON.stringify(data) + "\nStatus: " + status);
                            query();    
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log(jqXHR);
                        }
                    });
                });
                
                $("#investorTbody").on('dblclick', 'td:nth-child(2)', function () {
                    console.log($(this).text());
                    var name = $(this).text();
                    var path = './mvc/stock_controller/query/investor/' + name;
                    $.ajax({
                        url: path,
                        type: 'GET',
                        success: function (data, status) {
                            console.log("Data: " + JSON.stringify(data) + "\nStatus: " + status);
                            pushData(data);
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log(jqXHR);
                        }
                    });
                });
                
                $("#stock_span").on('click', function () {
                    window.location.href = 'stock_form.jsp';
                });
                
                $("#fund_span").on('click', function () {
                    window.location.href = 'fund_form.jsp';
                });

            });

        </script>
    </head>
    
    <body style="padding: 30px">
        <table class="pure-table pure-table-bordered">
            <td valign="top">
                <form id="investor_form" class="pure-form" method="post" action="">
                    <fieldset>
                        <legend><span id="stock_span" style="cursor:hand">Stock</span> | <span id="fund_span" style="cursor:hand">Fund</span> | <b>Investor</b></legend>
                        <input type="hidden" name="_method" id="_method" value="POST"/>
                        <input type="text" placeholder="序號" name="investorId" value="" readonly><p/>
                        <input type="text" placeholder="請輸入客戶名稱" name="name" value=""><p/>
                        <input type="text" placeholder="請輸入買入單位數" name="units" value=""><p/>
                        <select id="fundId" name="fundId" style="width: 150px"></select><p/>
                        <button type="button" id="query_button" class="pure-button pure-button-primary">Query</button>
                        <button type="button" id="post_button" class="pure-button pure-button-primary">POST</button>
                    </fieldset>
                </form>
                <table class="pure-table pure-table-bordered">
                    <thead>
                        <tr>
                            <th>investorId</th>
                            <th>name</th>
                            <th>units</th>
                            <th>netvalue</th>
                            <th>fundName</th>
                        </tr>
                    </thead>
                    <tbody id="investorTbody">

                    </tbody>
                </table>
            </td>
            <td valign="top">
                <div id="chart_div1" style="width: 900px; height: 500px;"></div>
                <div id="chart_div2" style="width: 900px; height: 500px;"></div>
            </td>
        </table>

    </body>
</html>

