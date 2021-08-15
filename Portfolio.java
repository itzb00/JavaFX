package ITEC3150;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SerializationUtils;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class Portfolio {
    private List<Stonk> stonks;

    public Portfolio(List<Stonk> stonks) {
        this.stonks = stonks;
    }

    public List<Stonk> getStonks() {
        return stonks;
    }

    public void setStonks(List<Stonk> stonks) {
        this.stonks = stonks;
    }

    public void saveStonks() {
        try {
            byte[] data = SerializationUtils.serialize((Serializable) stonks);
            FileUtils.writeByteArrayToFile(new File("stonks.ser"), data);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
