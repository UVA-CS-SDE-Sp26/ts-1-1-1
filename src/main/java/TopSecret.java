/**
 *  See the docs directory located under project root for project details.
 */
void main(String[] args) {
    FileHandler filehandler = new FileHandler();
    UserInterface ui = new UserInterface(filehandler);
    ui.run(args);
}
