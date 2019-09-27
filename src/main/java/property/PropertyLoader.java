package property;

import org.yaml.snakeyaml.Yaml;

import java.util.Map;

public class PropertyLoader {

    private PropertyLoader() {
    }

    public static PropertyLoader get() {
        return new PropertyLoader();
    }

    public static Map<String, String> getTestData() {
        Yaml yaml = new Yaml();
        Map<String, Object> root = yaml.load(PropertyLoader.class.getClassLoader().getResourceAsStream("configuration.yml"));

        Map<String, Object> data = (Map<String, Object>) root.get("test-data");
        Map<String, String> groceryCrud = (Map<String, String>) data.get("grocerycrud");
        return groceryCrud;
    }


    public static Map<String, String> getConfigSelenium() {
        Yaml yaml = new Yaml();
        Map<String, Object> root = yaml.load(PropertyLoader.class.getClassLoader().getResourceAsStream("configuration.yml"));

        Map<String, Object> selenium = (Map<String, Object>) root.get("selenium");
        Map<String, String> configuracao = (Map<String, String>) selenium.get("configuration");
        return configuracao;

    }

    public static Map<String, String> getGridData() {
        Yaml yaml = new Yaml();
        Map<String, Object> root = yaml.load(PropertyLoader.class.getClassLoader().getResourceAsStream("configuration.yml"));

        Map<String, Object> selenium = (Map<String, Object>) root.get("selenium");
        Map<String, String> grid = (Map<String, String>) selenium.get("grid");
        return grid;

    }

}
