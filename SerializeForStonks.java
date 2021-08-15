package MiscClasses;

import ITEC3150.Stonk;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SerializationUtils;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SerializeForStonks {
    private List<Stonk> stonks;

    public SerializeForStonks() {
        this.stonks = new ArrayList<>();
        Stonk bb = new Stonk("BB", 0, 0.0);
        Stonk amc = new Stonk("AMC", 0, 0.0);
        Stonk nok = new Stonk("NOK", 0, 0.0);
        Stonk gme = new Stonk("GME", 0, 0.0);
        stonks.add(bb);
        stonks.add(amc);
        stonks.add(nok);
        stonks.add(gme);

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
        } catch (Exception e) {

        }
    }
}
