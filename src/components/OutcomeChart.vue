<template>
  <div>
    <div id="chart">
      <apexchart type=donut width=500 :options="chartOptions" :series="series" />
    </div>
  </div>

</template>

<script>
export default {
  name: 'OutcomeChart',
  props: ['dataChart'],
  data: () => ({
    series: [],
    chartOptions: {
      dataLabels: {
        enabled: true
      },
      labels: [],
      fill: {
        type: 'gradient'
      },
      legend: {
        position: 'right',
        formatter: function (val, opts) {
          return val + ' - ' + opts.w.globals.series[opts.seriesIndex]
        }
      },
      responsive: [{
        breakpoint: 480,
        options: {
          chart: {
            width: 200
          },
          legend: {
            position: 'bottom'
          }
        }
      }]
    }
  }),
  watch: {
    dataChart () {
      this.series = this.dataChart.series
      this.chartOptions = { ...this.chartOptions, labels: this.dataChart.labels }
    }
  }
}
</script>

<style scoped>

</style>
