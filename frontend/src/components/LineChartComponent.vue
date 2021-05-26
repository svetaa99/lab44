<script>
import { Line, mixins } from 'vue-chartjs'

export default {
  extends: Line,
  props: ['chartdata','options'],
  mixins: [ mixins.reactiveProp ],
  mounted () {
    this.renderChart(this.chartdata, this.options)
  }
}
</script>
