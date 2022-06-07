package example;
import java.util.logging.Logger;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args){
        LOGGER.info("Play music by artist");
        Computer computer = new Computer(new MusicStrategyByArtist());
        computer.playMusic();

        LOGGER.info("Play music by date");
        computer.setNewStrategy(new MusicStrategyByDate());
        computer.playMusic();


        Computer functionalComputer = new Computer(
                () -> LOGGER.info("Execute some code and get some output")
        );
        functionalComputer.playMusic();
    }
}