package fi.tuni.tiko.bloggingsite;

/**
 * An object containing the description and specific command of a curl command.
 *
 * @author Anton Höglund
 */
public class CurlCommand {

    /**
     * The description of the command.
     */
    private String name;

    /**
     * The command itself.
     */
    private String command;

    /**
     * Empty constructor, does nothing.
     */
    public CurlCommand() {
    }

    /**
     * Constructs a curl command with name and command.
     *
     * @param name the name of the curl command.
     * @param command the command itself.
     */
    public CurlCommand(String name, String command) {
        this.name = name;
        this.command = command;
    }

    /**
     * Returns the name of the curl command.
     * @return the name of the curl command.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for the curl command.
     * @param name the name for the curl command.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the command of the curl command.
     * @return the command of the curl command.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets the command for the curl command.
     * @param command the command for the curl command.
     */
    public void setCommand(String command) {
        this.command = command;
    }
}
