import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Test {


    public static void main(String[] args) {
        VMS vms= new VMS();     //instantiez VMS
        int ID;
        String nume;
        String descriere;
        String data_start;
        String data_final;
        int total;
        String strategia="";
        int ID_usr;
        String usr_name;
        String usr_pass;
        String mail_usr;
        String type;
        String data_curenta_apl="";
        String data_curenta_apl_evn="";
        String Utilizator_mail;
        String tip_voucher;
        float value;
        int voucher_id;


        //pathu-ri catre fisiere
        String path="/home/cristi/Documents/Anul 2/Sem 1/POO/Tema POO/Teste/test02/input/campaigns.txt";
        String s,path2="/home/cristi/Documents/Anul 2/Sem 1/POO/Tema POO/Teste/test02/input/users.txt";
        String path3="/home/cristi/Documents/Anul 2/Sem 1/POO/Tema POO/Teste/test02/input/events.txt";
        String path_out="/home/cristi/Documents/Anul 2/Sem 1/POO/Tema POO/Teste/test02/output_me.txt";

        // citire din fisierul de campanii
        try {
            RandomAccessFile camp = new RandomAccessFile(path, "r");    //citire cu randomaccessfile

            while ((s = camp.readLine()) != null) {                           //cat timp exista linii citeste cate o linie

               //prelucrez liniile citite
                int nr_campani = Integer.parseInt(s);
                s = camp.readLine();

                String[] arr = s.split(" ");
                data_curenta_apl = "";
                data_curenta_apl = arr[0] + " " + arr[1];
                for (int i = 1; i <= nr_campani; i++) {
                    s = camp.readLine();
                    String[] arr2 = s.split(";");
                    ID = Integer.parseInt(arr2[0]);

                    nume = null;
                    nume = arr2[1];

                    descriere = null;
                    descriere = arr2[2];

                    arr = arr2[3].split(" ");
                    data_start = "";
                    data_start = arr[0] + " " + arr[1];

                    arr = arr2[4].split(" ");
                    data_final = "";
                    data_final = arr[0] + " " + arr[1];

                    total = Integer.parseInt(arr2[5]);

                    strategia = arr2[6];
                    Campaign campaign = new Campaign(ID, nume, descriere, data_start, data_final, total, 0);
                    vms.addCampaign(campaign);

                }

            }
            camp.close();
        }

        catch (Exception e){
            e.printStackTrace();
        }

        // citesc din fisierul de useri si ii pun in lista de useri
        try{

            RandomAccessFile usr= new RandomAccessFile(path2,"r");
            while ((s=usr.readLine())!=null){
                //prelucriez fiecare linie cititia
                int nr_usr=Integer.parseInt(s);

                for (int i=1;i<=nr_usr;i++) {
                    s = usr.readLine();
                    String[] arr2 = s.split(";");
                    ID_usr=Integer.parseInt(arr2[0]);

                    usr_name=null;
                    usr_name=arr2[1];

                    usr_pass=null;
                    usr_pass=arr2[2];

                    mail_usr=null;
                    mail_usr=arr2[3];

                    type=null;
                    type=arr2[4];
                    User user=new User(ID_usr,usr_name,usr_pass,mail_usr,type);
                    vms.addUser(user);
                }

            }

            usr.close();
        }catch (Exception ee)
        {
            ee.printStackTrace();
        }


        //citesc din fisierul de evenimente
        try{
            RandomAccessFile evnt =new RandomAccessFile(path3,"r");
            FileWriter writer = new FileWriter(path_out);                           //fac un writer in care scriu outputul
            BufferedWriter bw = new BufferedWriter(writer);                         //fac un buffer care merge pe liniile
                                                                                    // fisierului in care scriu
            while ((s=evnt.readLine())!=null) {
                //citesc fiecare linie din fisieri si o prelucrez
                String[] arr = s.split(" ");
                data_curenta_apl_evn = "";
                data_curenta_apl_evn = arr[0] + " " + arr[1];

                s = evnt.readLine();
                int nr_evn = Integer.parseInt(s);

                for (int i = 1; i <= nr_evn; i++) {
                    s = evnt.readLine();
                    String[] arr2 = s.split(";");
                    int usr_id_ev = Integer.parseInt(arr2[0]);

                    // daca eventul este "addCampaign" adaug daca user-ul este admin campania
                    if (arr2[1].equals("addCampaign")) {
                        for (User j : vms.participantii) {
                            if (j.ID_usr == usr_id_ev) {
                                if (j.ur == User.UserType.ADMIN) {
                                    ID = Integer.parseInt(arr2[2]);

                                    nume = null;
                                    nume = arr2[3];

                                    descriere = null;
                                    descriere = arr2[4];
                                    data_start = "";

                                    arr = arr2[5].split(" ");
                                    data_start = arr[0] + " " + arr[1];

                                    arr = arr2[6].split(" ");
                                    data_final = "";
                                    data_final = arr[0] + " " + arr[1];

                                    total = Integer.parseInt(arr2[7]);

                                    strategia = arr2[8];

                                    //adaug campania
                                    Campaign campaign = new Campaign(ID, nume, descriere, data_start, data_final, total, 0);
                                    vms.addCampaign(campaign);

                                }
                            }
                        }
                        //daca event-ul este editCampaign editez campania indicata cu noile valori citie
                    } else if (arr2[1].equals("editCampaign")) {
                        for (User j : vms.participantii) {
                            if (j.ID_usr == usr_id_ev) {
                                if (j.ur == User.UserType.ADMIN) {
                                    ID = Integer.parseInt(arr2[2]);

                                    nume = null;
                                    nume = arr2[3];

                                    descriere = null;
                                    descriere = arr2[4];
                                    data_start = "";

                                    arr = arr2[5].split(" ");
                                    data_start = arr[0] + " " + arr[1];

                                    arr = arr2[6].split(" ");
                                    data_final = "";
                                    data_final = arr[0] + " " + arr[1];

                                    total = Integer.parseInt(arr2[7]);

                                    //creez noua campanie si modific campania indicata
                                    Campaign campaign = new Campaign(ID, nume, descriere, data_start, data_final, total, total);
                                    vms.Data_curenta=data_curenta_apl_evn;
                                    vms.updateCampaign(ID, campaign);
                                }
                            }
                        }

                        //daca event-ul este cancelCampaign inchd campania cu id -ul indicat
                    } else if (arr2[1].equals("cancelCampaign")) {
                        for (User j : vms.participantii) {
                            if (j.ID_usr == usr_id_ev) {
                                if (j.ur == User.UserType.ADMIN) {

                                    ID = Integer.parseInt(arr2[2]);
                                    vms.Data_curenta=data_curenta_apl_evn;
                                    boolean b = vms.cancelCampaign(ID);

                                }
                            }
                        }
                        //daca event-ul este generateVoucher daca user-ul este admin
                        //caut sa vad daca mail-ul indicat apartine unui user din vms daca da
                        //caut sa vad daca e si observer ,daca nn il adaug si generateVoucher
                    } else if (arr2[1].equals("generateVoucher")) {

                        for (User j : vms.participantii) {
                            if (j.ID_usr == usr_id_ev) {
                                if (j.ur == User.UserType.ADMIN) {
                                    ID = Integer.parseInt(arr2[2]);
                                    Utilizator_mail = arr2[3];

                                    for (User k : vms.participantii) {

                                        if (Utilizator_mail.equals(k.email_usr)) {
                                            for (Campaign c : vms.campanii) {

                                                if (c.ID == ID) {
                                                    if (c.numar_curent < c.numar_total)
                                                    {
                                                        if (!c.observers.contains(k)) {
                                                            c.addObserver(k);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    tip_voucher = arr2[4];
                                    value = Float.parseFloat(arr2[5]);
                                    Campaign c=vms.getCampaign(ID);
                                    c.generateVoucher(Utilizator_mail, tip_voucher, value);
                                }
                            }
                        }
                        //modific starea voucerul in used si pun data citita
                    } else if(arr2[1].equals("redeemVoucher")){
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        for (User j : vms.participantii) {
                            if (j.ID_usr == usr_id_ev) {
                                if (j.ur == User.UserType.ADMIN) {
                                    ID = Integer.parseInt(arr2[2]);
                                    voucher_id=Integer.parseInt(arr2[3]);
                                    String date[]=arr2[4].split(" ");
                                    data_curenta_apl=date[0]+" "+date[1];
                                    for (Campaign c : vms.campanii)
                                    {
                                        if(c.ID==ID) {
                                            List<Voucher> list_voucere = c.getVouchers();
                                            for (Voucher cont : list_voucere) {
                                                if (cont.ID == voucher_id) {
                                                    c.redeemVoucher(cont.Cod, LocalDateTime.parse(data_curenta_apl,formatter));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        //returnez voucerele indicate indicat
                    } else if (arr2[1].equals("getVouchers")) {
                        for (User j : vms.participantii) {
                            if (j.ID_usr == usr_id_ev) {
                                if (j.ur == User.UserType.GUEST) {
                                    System.out.print("[");
                                    bw.write("[");
                                    for (int k=0; k<j.map_user.list.size();k++)
                                    {

                                        ArrayList<Voucher> cont= j.map_user.list.get(k).getValue();
                                        for (Voucher v : cont) {
                                            bw.write("[");
                                            System.out.print("[");
                                            bw.write(v.toString());
                                            System.out.print(v.toString());
                                            bw.write("]");
                                            bw.write(",");

                                            System.out.print("]");
                                                System.out.print(",");

                                        }
                                    }
                                    bw.write("]");
                                    bw.newLine();
                                    System.out.print("]");
                                    System.out.println();
                                }
                            }
                        }

                        //returnez observeri dintr-o campanie
                    } else if (arr2[1].equals("getObservers")) {
                        for (User j : vms.participantii) {
                            if (j.ID_usr == usr_id_ev) {
                                if (j.ur == User.UserType.ADMIN) {
                                    ID = Integer.parseInt(arr2[2]);
                                    Campaign camp = vms.getCampaign(ID);
                                    ArrayList<User> vect_observers = camp.getObservers();

                                    for (User u : vect_observers) {
                                        bw.write("[");
                                        System.out.print("[");
                                        bw.write(u.toString());
                                        System.out.print(u.toString());
                                        bw.write("]");
                                        bw.write(",");
                                        System.out.print("]");
                                        System.out.print(",");

                                    }
                                    bw.write("]");
                                    System.out.print("]");
                                    bw.newLine();
                                    System.out.println();
                                }
                            }
                        }
                        //returnez notificariel unui guest
                    } else if (arr2[1].equals("getNotifications")) {
                        for (User j : vms.participantii) {
                            if (j.ID_usr == usr_id_ev) {
                                if (j.ur == User.UserType.GUEST) {
                                    for (Notification k : j.notificari)
                                    {
                                        bw.write("[");
                                        System.out.print("[");
                                        bw.write(k.toString());
                                        System.out.print(k.toString());
                                        bw.write("]");
                                        System.out.print("]");
                                    }
                                }
                            }
                        }



                    } else if (arr2[1].equals("getVoucher")){

                    }

                }
                bw.close();   // inchid fisierul in care am scris
            }
        }catch (Exception eee)
        {
            eee.printStackTrace();
        }
    }
}
