package it.unibo.geosurv.debug;

public class DebugManager {
    private DebugFunction debugFunction;

    public void setDebugFunction(DebugFunction debugFunction) {
        this.debugFunction = debugFunction;
    }

    public void executeDebugCode() {
        if (debugFunction != null) {
            debugFunction.execute();
        }
    }
}