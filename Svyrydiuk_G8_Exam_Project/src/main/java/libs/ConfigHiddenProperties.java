package libs;

import org.aeonbits.owner.Config;

@Config.Sources(value = "file:./src/main/resources/hiddenConfig.properties")
public interface ConfigHiddenProperties extends Config {
    String login();
    String password();

}
