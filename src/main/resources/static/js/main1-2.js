      document.addEventListener("DOMContentLoaded", function() {
      var chrt = document.getElementById("myFacility").getContext("2d");
      var chartId = new Chart(chrt, {
         type: 'polarArea',
         data: {
            labels: ["원료계량", "세척기", "추출", "혼합", "충진", "검사"],
            datasets: [{
               label: "설비 가동 현황",
               data: [20, 40, 15, 35, 25, 38],
               backgroundColor: ['yellow', 'aqua', 'pink', 'lightgreen', 'gold', 'lightblue'],
            }],
         },
         options: {
            responsive: false,
            beginAtZero: true,
            max : 100
         },
      })
  });