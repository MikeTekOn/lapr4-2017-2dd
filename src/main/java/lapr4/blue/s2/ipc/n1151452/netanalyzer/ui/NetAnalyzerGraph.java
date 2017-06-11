package lapr4.blue.s2.ipc.n1151452.netanalyzer.ui;

import javafx.application.Platform;
import javafx.geometry.Side;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficCount;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.watchdogs.TrafficCounterListener;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.util.CustomUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A java fx line graph component
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 10/06/17.
 */
public class NetAnalyzerGraph extends BorderPane implements TrafficCounterListener {

    private AtomicInteger tick = new AtomicInteger(0);
    private XYChart.Series<Number, Number> secInSeries;
    private XYChart.Series<Number, Number> unsecInSeries;
    private XYChart.Series<Number, Number> secOutSeries;
    private XYChart.Series<Number, Number> unsecOutSeries;
    private static final int MAX_DATA_POINTS = 30;
    private final NumberAxis xAxis = new NumberAxis(0, MAX_DATA_POINTS, 5);
    private final NumberAxis yAxis = new NumberAxis();
    private final LineChart<Number, Number> graph = new LineChart<>(xAxis, yAxis);

    /**
     * Build a network analyzer graph panel
     */
    NetAnalyzerGraph() {

        setCenter(createGraph());
    }

    /**
     * Creates the graph
     *
     * @return the graph
     */
    private Chart createGraph() {

        xAxis.setAnimated(true);
        xAxis.setLabel("Time");
        xAxis.setTickLabelFormatter(new TimeConverter()); // Set the unit formatter

        yAxis.setAnimated(true);
        yAxis.setLabel("Traffic");
        yAxis.setTickLabelFormatter(new ByteConverter()); // Set the unit formatter

        secInSeries = new XYChart.Series<>();
        secInSeries.setName("in[s]");
        unsecInSeries = new XYChart.Series<>();
        unsecInSeries.setName("in");

        secOutSeries = new XYChart.Series<>();
        secOutSeries.setName("out[s]");
        unsecOutSeries = new XYChart.Series<>();
        unsecOutSeries.setName("out");

        graph.setAnimated(true);
        graph.getData().add(unsecInSeries);
        graph.getData().add(unsecOutSeries);
        graph.getData().add(secInSeries);
        graph.getData().add(secOutSeries);

        graph.setCreateSymbols(false);
        graph.setLegendSide(Side.BOTTOM);

        return graph;
    }

    @Override
    public void fireNewTrafficCount(TrafficCount count) {
        Platform.runLater(() -> {
            int nextTick = tick.incrementAndGet();

            if (testMode) {
                testData(nextTick);
            } else {
                addData(count, nextTick);
            }

            updateShownData(nextTick);
        });
    }

    /**
     * Adds the data to the graph
     *
     * @param count    the traffic count object (the data)
     * @param nextTick the next tick (time)
     */
    private void addData(TrafficCount count, int nextTick) {

        secInSeries.getData().add(new XYChart.Data<>(nextTick, count.securedIncoming()));
        unsecInSeries.getData().add(new XYChart.Data<>(nextTick, count.unsecuredIncoming()));
        secOutSeries.getData().add(new XYChart.Data<>(nextTick, count.securedOutgoing()));
        unsecOutSeries.getData().add(new XYChart.Data<>(nextTick, count.unsecuredOutgoing()));
    }

    /**
     * Updates data show on graph.
     *
     * @param nextTick next ticker
     */
    private void updateShownData(int nextTick) {

        if (nextTick > MAX_DATA_POINTS) {
            secInSeries.getData().remove(0);
            unsecInSeries.getData().remove(0);
            secOutSeries.getData().remove(0);
            unsecOutSeries.getData().remove(0);
        }
        if (nextTick > MAX_DATA_POINTS - 1) {
            xAxis.setLowerBound(xAxis.getLowerBound() + 1);
            xAxis.setUpperBound(xAxis.getUpperBound() + 1);
        }
    }

    /**
     * test mode
     */
    private boolean testMode = false;

    /**
     * Set the graph in test mode
     */
    public void testModeON() {

        testMode = true;
    }

    /**
     * Set the graph in normal mode
     */
    public void testModeOFF() {

        testMode = false;
    }

    /**
     * Dummy data for test purposes
     *
     * @param nextTick the next tick (time)
     */
    private void testData(int nextTick) {

        // TESTING PURPOSES
        long max = 10, min = 0;
        if (nextTick % MAX_DATA_POINTS > 12) max = 5000;
        if (nextTick % MAX_DATA_POINTS > 15) max = 5000000;
        if (nextTick % MAX_DATA_POINTS > 20) max = 5000000000L;
        if (nextTick % MAX_DATA_POINTS > 25) max = 10;
        double range = Math.abs(max - min);
        secInSeries.getData().add(new XYChart.Data<>(nextTick, Math.random() * range + min));
        unsecInSeries.getData().add(new XYChart.Data<>(nextTick, Math.random() * range + min));
        secOutSeries.getData().add(new XYChart.Data<>(nextTick, Math.random() * range + min));
        unsecOutSeries.getData().add(new XYChart.Data<>(nextTick, Math.random() * range + min));
    }

    /**
     * Custom byte converter
     */
    private class ByteConverter extends StringConverter<Number> {

        @Override
        public String toString(Number object) {

            return CustomUtil.readableByteCount(object.longValue(), true);
        }

        @Override
        public Number fromString(String string) {
            return null;
        }
    }

    /**
     * Custom time converter
     */
    private class TimeConverter extends StringConverter<Number> {

        /**
         * Seconds in a minute
         */
        private final double MINUTE = 60;

        @Override
        public String toString(Number object) {
            double newValue = object.doubleValue();
            String unit = "s";
            if (newValue >= MINUTE) {
                double seconds = newValue % MINUTE;
                newValue /= MINUTE;
                unit = "m" + (int) seconds + "s";
            }
            return String.format("%.0f %s", newValue, unit);
        }

        @Override
        public Number fromString(String string) {
            return null;
        }
    }
}
