import com.sun.jdi.IntegerValue;
import javax.swing.event.ListDataEvent;
import java.sql.ClientInfoStatus;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Campaign {
    int  ID;
    String Nume_Campanie;
    String Descrierea_Campaniei;
    LocalDateTime data_start;
    LocalDateTime data_finish;
    int numar_total;
    int numar_curent;
    protected enum CampaignStatusType{NEW,STARTED,EXPIRED,CANCELLED}
    CampaignStatusType status;
    CampaignVoucherMap<String,ArrayList> camp_dict=new CampaignVoucherMap<String, ArrayList>();
    ArrayList<User>  observers=new ArrayList<User>();
    int cod_unic_cont=0;
    int contor_voucher=0;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    Campaign(int ID,String nume_Campanie,String descrierea_Campaniei,String time_1,String time_2,int nr_total,int nr_curent){
        this.ID= ID;
        this.Nume_Campanie=nume_Campanie;
        this.Descrierea_Campaniei=descrierea_Campaniei;
        this.data_start=LocalDateTime.parse(time_1,formatter);
        this.data_finish=LocalDateTime.parse(time_2,formatter);
        this.numar_total=nr_total;
        this.numar_curent=nr_curent;
        this.status=CampaignStatusType.NEW;
    }


    public List<Voucher> getVouchers(){
        List<Voucher> list_voucere = new ArrayList<Voucher>();


            for (int i=0; i<this.camp_dict.list.size();i++)
            {

                    ArrayList<Voucher> rez=this.camp_dict.list.get(i).getValue();
                    list_voucere.addAll(0,rez);

            }
        return list_voucere;
    }


    public Voucher getVoucher(String code){
        ArrayList<Voucher> l;
        for (int i=0; i<this.camp_dict.list.size();i++)
        {

            l=this.camp_dict.list.get(i).getValue();
            for (Voucher v : l)
            {
                if(v.Cod.equals(code))
                    return v;
            }
        }
        return null;
    }

    public void generateVoucher(String email, String voucherType,float value){

        for (User u : this.observers)
        {

            if(u.email_usr.compareTo(email)==0) {
                if (this.numar_curent < this.numar_total){
                    if ((voucherType.compareTo("GiftVoucher") == 0)) {
                        this.contor_voucher++;
                        String s = "" + this.contor_voucher;

                        Voucher v = new GiftVoucher(this.contor_voucher, s, email, this.ID, value);
                        u.map_user.addVoucher(v);
                        this.camp_dict.addVoucher(v);
                        this.numar_curent++;
                    } else if ((voucherType.compareTo("LoyalityVoucher") == 0) && (this.numar_curent <= this.numar_total)) {
                        this.contor_voucher++;
                        String s = "" + this.contor_voucher;
                        Voucher v = new LoyalityVoucher(this.contor_voucher, s, email, this.ID, value);
                        u.map_user.addVoucher(v);
                        this.camp_dict.addVoucher(v);
                        this.numar_curent++;
                    }
                }
            }
        }

    }

    public void redeemVoucher(String code, LocalDateTime date)
    {
        if(date.isAfter(this.data_start))
        {
            if (date.isBefore(this.data_finish))
            {
                if((this.status == CampaignStatusType.STARTED)||(this.status == CampaignStatusType.NEW))
                {
                    Voucher v = getVoucher(code);
                    if(v.status == Voucher.VoucherStatusType.UNUSED)
                    {
                        v.status= Voucher.VoucherStatusType.USED;
                        v.data_util_voucher=date;
                    }
                }
            }
        }
    }

    public ArrayList<User> getObservers() {
        return this.observers;
    }

    public void addObserver(User user) {
        this.observers.add(user);
    }

    public void removeObserver(User user){
        int i=0;
        for (User u : this.observers)
        {

            if(user.ID_usr==u.ID_usr)
               this.observers.remove(i);
            i++;
        }
    }

    public void notifyAllObservers(Notification notification){

        for (User u : this.observers)
        {
            u.update(notification);
        }

    }
}
