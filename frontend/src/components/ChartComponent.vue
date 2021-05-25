<script>
import { Bar } from 'vue-chartjs'

export default {
  extends: Bar,
  props: ['chartdata', 'options'],
  data() {
    return {
      dataToShow: null
    }
  },
  mounted () {
    this.dataToShow = this.chartdata
    this.renderChart(this.dataToShow, this.options)
  }
}
</script>
