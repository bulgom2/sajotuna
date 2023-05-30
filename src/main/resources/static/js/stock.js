// 1. 엑셀 저장
function convertTableToCSV() {
    var table = document.getElementById('dataTable');
    var rows = table.getElementsByTagName('tr');
    var csv = '';

    // 헤더 부분 추가
    var headerRow = rows[0];
    var headerCells = headerRow.getElementsByTagName('th');
    for (var h = 0; h < headerCells.length; h++) {
        var headerCell = headerCells[h].innerText.replace(/"/g, '""');
        csv += '"' + headerCell + '",';
    }
    csv += '\n';

    // 행 데이터 추가
    for (var i = 1; i < rows.length; i++) {
        var row = rows[i];
        var cells = row.getElementsByTagName('td');

        for (var j = 0; j < cells.length; j++) {
            var cell = cells[j].innerText.replace(/"/g, '""');
            csv += '"' + cell + '",';
        }

        csv += '\n';
    }
    return csv;
}

$(document).ready(function() {
    $('#downloadButton').on('click', function() {
        var csvData = '\uFEFF' + convertTableToCSV();
        var blob = new Blob([csvData], {type: 'text/csv;charset=utf-8;'});
        var downloadLink = document.createElement('a');
        downloadLink.setAttribute('href', URL.createObjectURL(blob));
        downloadLink.setAttribute('download', '재고내역.csv');
        downloadLink.style.display = 'none';
        document.body.appendChild(downloadLink);
        downloadLink.click();
        document.body.removeChild(downloadLink);
    });
});



// 2. 부트스트랩 테이블 페이징 없애고 스크롤바 생성 및 오름차순 정렬
    $(document).ready(function() {
    var table = $('#dataTable').DataTable({
    "paging": false,
    "scrollY": "850px", // Adjust the height as per your requirement
});

    // 오름차순 정렬
    table.order([0, 'asc']).draw();

});
    <!--     dataTable-->

