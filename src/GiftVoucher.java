public class GiftVoucher extends Voucher {
    float suma_disponibila;

    GiftVoucher(int id, String cod,String mail, int id_campanie , float suma) {
        super(id, cod, mail, id_campanie);
        this.suma_disponibila= suma;
    }

    @Override
    public String toString() {
        return super.toString()+";"+suma_disponibila+";"+ID_campanie+";"+data_util_voucher;
    }
}
