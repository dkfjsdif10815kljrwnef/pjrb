!(function (o) {
  "use strict";
  function e() {
    (this.$body = o("body")), (this.charts = []);
  }
  (e.prototype.initCharts = function () {
    window.Apex = {
      chart: { parentHeightOffset: 0, toolbar: { show: !1 } },
      grid: { padding: { left: 0, right: 0 } },
      colors: ["#727cf5", "#0acf97", "#fa5c7c", "#ffbc00"],
    };
    var e = ["#727cf5", "#0acf97", "#fa5c7c", "#ffbc00"];
    (r = o("#revenue-chart").data("colors")) && (e = r.split(","));
    var t = {
      chart: {
        height: 364,
        type: "line",
        dropShadow: { enabled: !0, opacity: 0.2, blur: 7, left: -7, top: 7 },
      },
      dataLabels: { enabled: !1 },
      stroke: { curve: "smooth", width: 4 },
      series: [
        { name: "Current Week", data: [10, 20, 15, 25, 20, 30, 20] },
        { name: "Previous Week", data: [0, 15, 10, 30, 15, 35, 25] },
      ],
      colors: e,
      zoom: { enabled: !1 },
      legend: { show: !1 },
      xaxis: {
        type: "string",
        categories: ["월", "화", "수", "목", "금", "토", "일"],
        tooltip: { enabled: !1 },
        axisBorder: { show: !1 },
      },
      yaxis: {
        labels: {
          formatter: function (e) {
            return e + "k";
          },
          offsetX: -15,
        },
      },
    };
    new ApexCharts(document.querySelector("#revenue-chart"), t).render();
    e = ["#727cf5", "#e3eaef"];
    (r = o("#high-performing-product").data("colors")) && (e = r.split(","));
    t = {
      chart: { height: 257, type: "bar", stacked: !0 },
      plotOptions: { bar: { horizontal: !1, columnWidth: "20%" } },
      dataLabels: { enabled: !1 },
      stroke: { show: !0, width: 2, colors: ["transparent"] },
      series: [
        {
          name: "Actual",
          data: [65, 59, 80, 81, 56, 89, 40, 32, 65, 59, 80, 81],
        },
        {
          name: "Projection",
          data: [89, 40, 32, 65, 59, 80, 81, 56, 89, 40, 65, 59],
        },
      ],
      zoom: { enabled: !1 },
      legend: { show: !1 },
      colors: e,
      xaxis: {
        categories: [
          "1월",
          "2월",
          "3월",
          "4월",
          "5월",
          "6월",
          "7월",
          "8월",
          "9월",
          "10월",
          "11월",
          "12월",
        ],
        axisBorder: { show: !1 },
      },
      yaxis: {
        labels: {
          formatter: function (e) {
            return e + "k";
          },
          offsetX: -15,
        },
      },
      fill: { opacity: 1 },
      tooltip: {
        y: {
          formatter: function (e) {
            return "$" + e + "k";
          },
        },
      },
    };    
  }),
})(window.jQuery),
  (function (t) {
    "use strict";
    t(document).ready(function (e) {
      t.Dashboard.init();
    });
  })(window.jQuery)

