public class LoyalityVoucher extends Voucher{
    float reducere;
    LoyalityVoucher(int id, String cod, String mail, int id_campanie, float red) {
        super(id, cod, mail, id_campanie);
        this.reducere=red;
    }
}
