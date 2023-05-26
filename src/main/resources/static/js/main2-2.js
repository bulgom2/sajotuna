
document.addEventListener("DOMContentLoaded", function() {
    // 차트를 그릴 영역을 dom 요소로 가져온다.
    var chartArea = document.getElementById('myWip').getContext('2d');
    // 차트를 생성한다.
    var myChart = new Chart(chartArea, {
      // 차트의 종류(String)
      type: 'bar',
      // 차트의 데이터(Object)
      data: {
        // x축에 들어갈 이름들(Array)
        labels: ['양배추 농축액', '흑마늘 농축액', '석류농축액', '매실농축액' , '양배추' , '흑마늘'],
        // 실제 차트에 표시할 데이터들(Array), dataset 객체들을 담고 있다.
        datasets: [
          {
            // dataset의 이름(String)
            label: 'Product1',
            // dataset 값(Array)
            data: [80, 60, 75, 70 , 100 , 75],
            // dataset의 배경색(rgba값을 String으로 표현)
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            // dataset의 선 색(rgba값을 String으로 표현)
            borderColor: 'rgba(255, 99, 132, 1)',
            // dataset의 선 두께(Number)
            borderWidth: 1
          },
          {
            // dataset의 이름(String)
            label: 'Product2',
            // dataset 값(Array)
            data: [60, 40, 55, 90 , 60 , 75],
            // dataset의 배경색(rgba값을 String으로 표현)
            backgroundColor: 'rgba(54, 162, 235, 0.2)',
            // dataset의 선 색(rgba값을 String으로 표현)
            borderColor: 'rgba(54, 162, 235, 1)',
            // dataset의 선 두께(Number)
            borderWidth: 1,
            type : 'line'
          }
        ]
      },
      // 차트의 설정(Object)
      options: {
        // 축에 관한 설정(Object)
        scales: {
          // y축에 대한 설정(Object)
          y: {
            // 시작을 0부터 하게끔 설정(최소값이 0보다 크더라도)(boolean)
            beginAtZero: true,
            max : 100
          }
        }
      }
    })
  });