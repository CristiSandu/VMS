import javax.swing.event.ListDataEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CampaignVoucherMap<K,V> extends ArrayMap<String, ArrayList> {

    CampaignVoucherMap() {
        super();
    }

    public boolean addVoucher(Voucher v) {

        ArrayList<Voucher> l = null;
        if (!containsKey(v.email_dist)) {

            l = new ArrayList<Voucher>();
            l.add(v);
            if(this.put(v.email_dist, l) == null)
                return true ;

        } else {

            l = get(v.email_dist);
            l.add(v);
            if(this.put(v.email_dist, l) == null)
                return true;
        }
    return false;
    }
}
