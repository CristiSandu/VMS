import java.util.ArrayList;
import java.util.List;

public class VMS {

    ArrayList<Campaign> campanii;                                           //pun campaniile din vms intr-un arraylist
    ArrayList<User> participantii;                                          //la fel si useri din campanie

    String Data_curenta="";                                                 // transmit data curenta a aplicatie
    static VMS t=null;                                                      //implementarea desingpatern-ului

    VMS(){
        this.campanii=new ArrayList<Campaign>();
        this.participantii= new ArrayList<User>();
    }

    public ArrayList<Campaign> getCampaigns(){
        return this.campanii;
    }

    public Campaign getCampaign(Integer id){
        for (Campaign entry : this.campanii)
        {
            if (entry.ID==id)
                return entry;
        }
        return null;
    }

    public void addCampaign(Campaign campaign){
        this.campanii.add(campaign);
    }

    public void updateCampaign(Integer id, Campaign campaign)
    {
        Campaign c=getCampaign(id);
        if(c.status == Campaign.CampaignStatusType.NEW)
        {
            c.ID=campaign.ID;
            c.Nume_Campanie=campaign.Nume_Campanie;
            c.Descrierea_Campaniei=campaign.Nume_Campanie;
            c.data_start=campaign.data_start;
            c.data_finish=campaign.data_finish;

            if(c.numar_curent <=campaign.numar_total)
                c.numar_total=campaign.numar_total;

                for (User usr : c.observers) {
                    Notification notification = new Notification(c.ID, Data_curenta, usr.getListofCodes(c), "EDIT");
                    usr.update(notification);
                }

        }else
            if(c.status == Campaign.CampaignStatusType.STARTED)
            {
                if(campaign.numar_total>c.numar_curent)
                {
                    c.numar_total=campaign.numar_total;
                }
                c.data_finish=campaign.data_finish;

                for (User usr : c.observers) {
                    Notification notification = new Notification(c.ID, Data_curenta, usr.getListofCodes(c), "EDIT");
                    usr.update(notification);
                }
            }
    }

    public boolean cancelCampaign(Integer id){
        for (Campaign entry : this.campanii) {
            if (entry.ID == id) {
                entry.status = Campaign.CampaignStatusType.CANCELLED;

                for (User usr : entry.observers) {
                    Notification notification = new Notification(entry.ID, Data_curenta, usr.getListofCodes(entry), "CANCEL");
                    usr.update(notification);
                }
                return true;
            }

        }
        return false;
    }

    public ArrayList<User> getUsers(){
        return this.participantii;
    }

    public void addUser(User user){
        this.participantii.add(user);
    }

    public ArrayList<String> getListofCodes(Campaign c)
    {
        ArrayList<String> lista_coduri= new ArrayList<String>();
        for (User u : c.observers)
        {
            for (int i=0; i<u.map_user.list.size();i++)
            {
                if (u.map_user.list.get(i).getKey()==c.ID)
                {
                    ArrayList<Voucher> rez=u.map_user.list.get(i).getValue();
                    for (Voucher v : rez)
                    {
                        lista_coduri.add(v.Cod);
                    }

                }
            }
        }
        return lista_coduri;
    }

    public static VMS getInstance()
    {
        if (t == null)
            t = new VMS();

        return t;
    }

}
