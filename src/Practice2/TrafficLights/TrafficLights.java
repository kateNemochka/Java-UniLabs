package Practice2.TrafficLights;

public enum TrafficLights {
    RED(10, "Red"),
    YELLOW1(2, "Yellow"),
    GREEN(9, "Green"),
    YELLOW2(3, "Yellow");

    private final int time;
    private final String stateName;

    TrafficLights(int time, String name) {
        this.time = time;
        this.stateName = name;
    }

    public void glow() {
        System.out.print(stateName.concat(": "));
        for (int i = time; i > 0; --i) {
            System.out.printf("%d ", i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        for (TrafficLights state : TrafficLights.values()) {
            state.glow();
        }
    }
}
