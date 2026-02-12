public class TopSecret {

    public static void main(String[] args) {
        FileHandler filehandler = new FileHandler();
        UserInterface userinterface = new UserInterface(filehandler);
        userinterface.run(args);
    }
}
