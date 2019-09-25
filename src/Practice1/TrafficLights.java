/*Напишіть метод, який імітує переключення сигналу світлофора: червоний-жовтий-зелений-жовтий-…
Додайте часові затримки між переключеннями сигналу світлофора.*/

package Practice1;

public class TrafficLights {
    private int currState = 0;
    private int numStates = 4;
    private String[] states = {"red", "yellow", "green", "yellow"};
    private int[] delays = {10, 2, 10, 3};

    public static void main(String[] args) {
        TrafficLights tl = new TrafficLights();
        while (true) {
            tl.glow();
        }
    }

    public void glow() {
        System.out.println(states[currState]);
        for (int i = delays[currState]; i > 0; --i) {
            System.out.print(i);
            System.out.print(' ');
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        update();
    }

    public void update() {
        currState = (currState + 1) % numStates;
    }
}
// Thread.sleep(1000); delay for 1 second
