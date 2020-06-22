import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String input = "A B H" + "\n"
                        +"B A D" + "\n"
                        +"C D" + "\n"
                        + "D B C I K" + "\n"
                        +"E K F" + "\n"
                        +"F G E" + "\n"
                        +"G H K F" + "\n"
                        +"H A I J K G" + "\n"
                        +"I H J D" + "\n"
                        +"J H I K" + "\n"
                        +"K D J H G E";
        GraphColoring graph = new GraphColoring(input);
        String [] colors = new String[3];
        colors[0] = "Red";
        colors[1] = "Blue";
        colors[2] = "Yellow";

        Map<String, String> colored = graph.paint(colors);

    }
}
