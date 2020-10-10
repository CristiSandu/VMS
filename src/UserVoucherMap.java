import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class UserVoucherMap<K,V> extends ArrayMap<Integer, ArrayList> {

    UserVoucherMap() {
        super();
    }

    public boolean addVoucher(Voucher v) {

        ArrayList<Voucher> l = null;
        if (!containsKey(v.ID_campanie)) {

            l = new ArrayList<Voucher>();
            l.add(v);
            if(this.put(v.ID_campanie, l) == null)
                return true;

        } else {

            l = get(v.ID_campanie);
            l.add(v);
            if((this.put(v.ID_campanie, l) == null))
                return true;

        }
        return false;
    }
}