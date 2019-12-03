<template>
  <div class="d-flex justify-center align-center mt-10">
    <v-progress-circular
      :size="170"
      :width="12"
      color="purple"
      indeterminate
      v-if="loader"
    ></v-progress-circular>
    <div id="chart" v-else>
      <apexchart type=donut width=500 :options="chartOptions" :series="series" />
    </div>
  </div>
</template>

<script>
export default {
  name: 'IncomeChart',
  props: ['dataChart'],
  data: () => ({
    loader: true,
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
            position: 'bottom',
            horizontalAlign: 'left'
          }
        }
      }]
    }
  }),
  watch: {
    dataChart () {
      this.loader = false
      this.series = this.dataChart.series
      this.chartOptions = { ...this.chartOptions, labels: this.dataChart.labels }
    }
  }
}
</script>

<style scoped>

</style>
