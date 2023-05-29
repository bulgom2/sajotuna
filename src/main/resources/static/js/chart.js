google.charts.load('current', {'packages':['gantt']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {

    var data = new google.visualization.DataTable();
    data.addColumn('string', '공정ID');
    data.addColumn('string', '공정명');
    data.addColumn('string', '수주번호');
    data.addColumn('date', '시작일');
    data.addColumn('date', '마감일');
    data.addColumn('number', '작업시간');
    data.addColumn('number', '진행률');
    data.addColumn('string', '의존성');

    data.addRows([
        ['원료계량', '원료계랑', 'SJ202205291615',
            new Date(2014, 12, 22), new Date(2014, 12, 23), null, 100, null],
        ['세척', '세척', 'SJ202205291615',
            new Date(2014, 12, 23), new Date(2014, 12, 25), null, 100, null],
        ['추출', '추출', 'SJ202205291615',
            new Date(2014, 12, 25), new Date(2014, 12, 26), null, 100, null],
        ['혼합', '혼합', 'SJ202205291615',
            new Date(2014, 12, 26), new Date(2014, 12, 28), null, 100, null],
        ['충진', '충진', 'SJ202205291615',
            new Date(2014, 12, 29), new Date(2014, 12, 31), null, 50, null],
        ['검사', '검사', 'SJ202205291615',
            new Date(2014, 12, 31), new Date(2015, 1, 1), null, 0, null],
        ['식힘', '식힘', 'SJ202205291615',
            new Date(2015, 1, 1), new Date(2015, 1, 3), null, 0, null],
        ['포장', '포장', 'SJ202205291615',
            new Date(2015, 1, 3), new Date(2015, 1, 4), null, 0, null]
    ]);

    var options = {
        height: 400,
        gantt: {
            trackHeight: 30
        }
    };

    var chart = new google.visualization.Gantt(document.getElementById('chart_div'));

    chart.draw(data, options);
}
