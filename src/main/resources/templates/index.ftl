<#include base.ftl>
<#macro page_head>
<title>Chart</title>
</#macro>

<#macro titulo>
<h2>Chart</h2>
</#macro>

<#macro page_body>
<canvas class="my-4" id="chart" width="900" height="380"></canvas>


            <br>
            <br>


            <canvas class="my-4" id="humedad" width="900" height="380"></canvas>
<script src="/echarts.min.js"></script>
<script>
    let chartTemperatura, chartHumedad;
    let dataTemperatura = [], dataHumedad = [];
    let ejeXTemperatura = [], ejeXHumedad = [];
    let webSocket;
    let tiempoReconectar = 5000;
    $(document).ready(function () {
        grafico1();
        grafico2();
    });
    function grafico1() {
        let option;
        option = {
            title: {
                text: 'Temperatura'
            },
            tooltip: {
                trigger: 'axis',
                // formatter: function (params) {
                //
                //     // console.log(params);
                //     return params[0].axisValue + ' ' + params[0].value[1];
                // }
            },
            xAxis: {
                type: 'category',
                data: ejeXTemperatura,
                splitLine: {
                    show: false
                }
            },
            yAxis: {
                type: 'value',
                boundaryGap: [0, '100%'],
                splitLine: {
                    show: false
                }
            },
            series: [{
                data: [0],
                type: 'line',
                hoverAnimation: false,
            }],
            color: ['rgb(102,0,153)'],
        };
        chartTemperatura = echarts.init(document.getElementById("chart"));
        chartTemperatura.setOption(option);
    }
    function grafico2() {
        let option;
        option = {
            title: {
                text: 'Humedad'
            },
            tooltip: {
                trigger: 'axis',
            },
            xAxis: {
                type: 'category',
                data: ejeXHumedad,
                splitLine: {
                    show: false
                }
            },
            yAxis: {
                type: 'value',
                boundaryGap: [0, '100%'],
                splitLine: {
                    show: false
                }
            },
            series: [{
                data: [0],
                type: 'line',
                hoverAnimation: false,
            }],
            color: ['rgb(0,77,230)'],
        };
        chartHumedad = echarts.init(document.getElementById("humedad"));
        chartHumedad.setOption(option);
    }
    function nuevoValor(datos) {
        let nuevo = JSON.parse(datos);
        // console.log(nuevo);
        // data.shift();
        dataTemperatura.push(nuevo.temperatura);
        dataHumedad.push(nuevo.humedad);
        // ejeX.shift();
        ejeXTemperatura.push(nuevo.fecha);
        ejeXHumedad.push(nuevo.fecha);
        chartTemperatura.setOption({
            xAxis: {
                data: ejeXTemperatura
            },
            series: [{
                data: dataTemperatura
            }]
        });
        chartHumedad.setOption({
            xAxis: {
                data: ejeXHumedad
            },
            series: [{
                data: dataHumedad
            }]
        });
    }
    function conectar() {
        webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/nuevoMensaje");
        webSocket.onmessage = function (datos) {
            // console.log(datos);
            nuevoValor(datos.data);
        };
    }
    function verificarConexion() {
        if (!webSocket || webSocket.readyState === 3) {
            conectar();
        }
    }
    setInterval(verificarConexion, tiempoReconectar);
</script>
</#macro>