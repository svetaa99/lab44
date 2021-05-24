<script>
import { Line } from 'vue-chartjs'

export default {
  extends: Line,
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
