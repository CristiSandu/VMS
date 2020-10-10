import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class User implements Observers{
    int ID_usr;
    String Nume;
    String email_usr;
    String parola;
   enum UserType{ADMIN,GUEST};
    UserType ur;
    UserVoucherMap<Integer, ArrayList> map_user ;
    List<Notification> notificari;

    User(int ID,String nume,String parl,String mail,String type){
        this.ID_usr=ID;
        this.Nume=nume;
        this.email_usr=mail;
        this.parola=parl;
        if(type.compareTo("ADMIN")==0)
            this.ur=UserType.ADMIN;
        else
            this.ur=UserType.GUEST;
        this.map_user=new UserVoucherMap<Integer, ArrayList>();
        this.notificari= new LinkedList<Notification>();
    }

    public ArrayList<String> getListofCodes(Campaign c)
    {
        ArrayList<String> lista_coduri= new ArrayList<String>();

            for (int i=0; i<map_user.list.size();i++)
            {
                if (map_user.list.get(i).getKey()==c.ID)
                {
                    ArrayList<Voucher> rez=map_user.list.get(i).getValue();
                    for (Voucher v : rez)
                    {
                        lista_coduri.add(v.Cod);
                    }

                }
            }

        return lista_coduri;
    }


    @Override
    public void update(Notification notification) {
        this.notificari.add(notification);
    }

    @Override
    public String toString() {
        return ID_usr+";"+Nume+";"+email_usr+";"+ur;
    }
}
