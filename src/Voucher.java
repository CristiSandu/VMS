import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

public abstract class Voucher {
    int  ID;
    String Cod;
    enum VoucherStatusType {USED, UNUSED}
    VoucherStatusType status;
    LocalDateTime data_util_voucher;
    String email_dist;
    int ID_campanie;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    Voucher(int id, String cod,String mail,int id_campanie) {
        this.ID = id;
        this.Cod = cod;
        this.status = VoucherStatusType.UNUSED;
        this.email_dist = mail;
        this.ID_campanie = id_campanie;
    }

    @Override
    public String toString() {
        return ""+ID+";"+status+";"+email_dist;
    }
}