import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Notification {
    enum NotificationType{EDIT,CANCEL};
    LocalDateTime data_trimiterii;
    int ID_camp;
    ArrayList<String> list_coucher;
    NotificationType notific;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    Notification(int ID,String data_trimiterii,ArrayList<String> list,String tip){
        this.ID_camp=ID;
        this.data_trimiterii=LocalDateTime.parse(data_trimiterii,formatter);
        this.list_coucher= list;
        if(tip.compareTo("EDIT")==0)
            this.notific=NotificationType.EDIT;
        else
            this.notific=NotificationType.CANCEL;
    }

    public String returnListaCod() {
        String str="";
        for (String s : list_coucher){
            str+=s+" ";

        }
        return str;
    }
    @Override
    public String toString() {
        return ID_camp+";"+"["+returnListaCod()+"]"+";"+data_trimiterii+";"+notific;
    }
}
