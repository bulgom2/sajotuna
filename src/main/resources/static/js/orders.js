// 1. 모달창 새로고침
function closeModal() {
    // 페이지 새로고침
    window.location.reload();
}


// 2. 부트스트랩 테이블 페이징 없애고 스크롤바 생성 및 오름차순 정렬
    $(document).ready(function() {
    var table = $('#dataTable').DataTable({
    "paging": false,
    "scrollY": "850px", // Adjust the height as per your requirement
});

    // 오름차순 정렬
    table.order([7, 'asc']).draw();

});
    <!--     dataTable-->




    // 엑셀 저장
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
        downloadLink.setAttribute('download', '수주내역.csv');
        downloadLink.style.display = 'none';
        document.body.appendChild(downloadLink);
        downloadLink.click();
        document.body.removeChild(downloadLink);
    });
});





// 수주 확정 및 삭제 버튼
$(document).ready(function() {
    var selectedRow;

    // 이벤트 바인딩을 별도의 함수로 이동합니다.
    function bindRowClickEvent() {
        $('#dataTable tbody tr').off('click');
        $(document).on('click', '#dataTable tbody tr', function() {
    if (selectedRow) {
        selectedRow.removeClass('selected');
    }
    if (selectedRow !== $(this)) {
        selectedRow = $(this);
        selectedRow.addClass('selected');
        $('#delete').prop('disabled', false); // 선택된 행이 있을 때 삭제 버튼 활성화
    } else {
        selectedRow = null;
        $('#delete').prop('disabled', true); // 선택된 행이 없을 때 삭제 버튼 비활성화
    }
});
}

        // 초기에 이벤트 바인딩을 실행합니다.
        bindRowClickEvent();

        // 수주 확정 버튼 클릭 이벤트 처리
        $(document).on('click', '#confirmed', function(e) {
        e.preventDefault();
        if (!selectedRow) {
        swal('알림', '선택된 행이 없습니다.', 'warning');
        return;
    }

        var statusCell = selectedRow.find('td:eq(7)');
        var status = statusCell.text().trim();
        if (status === '확정') {
        swal('알림', '이미 확정된 수주입니다.', 'warning');
        return;
    }

        //statusCell.text('확정');
        var ordersNo = selectedRow.find('td:eq(1)').text().trim();
        if (ordersNo === '') {
        swal('알림', '수주 번호를 가져올 수 없습니다.', 'warning');
        return;
    }

        swal({
        title: '확정',
        text: '수주를 확정하시겠습니까?',
        icon: 'info',
        buttons: ['취소', '확정'],
        dangerMode: false,
    }).then((value) => {
        if (value) {
        $.ajax({
        url: '/confirm',
        method: 'POST',
        data: JSON.stringify({ ordersNo: ordersNo }),
        contentType: 'application/json',
        success: function(response) {
        console.log('수주가 확정되었습니다.');
        console.log('response : '+response);

        statusCell.text('확정'); // 테이블 값을 업데이트
        selectedRow.removeClass('selected');
        selectedRow = null;
        $('#delete').prop('disabled', true);
        swal('성공', '수주가 확정되었습니다.', 'success');

    },
        error: function(xhr, status, error) {
        console.error('값 업데이트 중 오류가 발생했습니다.');
        swal('알림', '수주 확정에 실패했습니다.', 'warning');
    }
    });
    } else {
        console.log("확정이 취소되었습니다")
    }
    });
    });

// 수주 삭제 버튼 클릭 이벤트 처리
        $(document).on('click', '#delete', function(e) {
        e.preventDefault();
        if (!selectedRow) {
        swal('알림', '선택된 행이 없습니다.', 'warning');
        return;
    }

        var statusCell = selectedRow.find('td:eq(7)');
        var status = statusCell.text().trim();
        if (status === '확정') {
        swal('알림', '이미 확정된 수주는 삭제할 수 없습니다.', 'warning');
        return;
    }

        swal({
        title: '삭제',
        text: '선택한 수주를 삭제하시겠습니까?',
        icon: 'warning',
        buttons: ['취소', '삭제'],
        dangerMode: true,
    }).then((confirmed) => {
        if (confirmed) {
        var ordersNo = selectedRow.find('td:eq(1)').text().trim();
        if (ordersNo === '') {
        swal('알림', '수주 번호를 가져올 수 없습니다.', 'warning');
        return;
    }

        deleteOrder(ordersNo);
    }
    });
    });


        // AJAX 요청을 통해 수주 삭제 처리
        function deleteOrder(ordersNo) {
        $.ajax({
        type: 'POST',
        url: '/orders/delete',
        data: JSON.stringify({ ordersNo: ordersNo }),
        contentType: 'application/json',
        success: function() {
        swal({
        title: '성공',
        text: '수주가 삭제되었습니다.',
        icon: 'success'
    }).then(function() {
        location.reload();
    });
    },
        error: function() {
        // 오류 처리 로직 추가
    }
    });
    }

        // 초기에 "수주 삭제" 버튼을 비활성화 상태로 설정
        $('#delete').prop('disabled', true);
    });

