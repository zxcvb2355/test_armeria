package product.main.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class ProductVO {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="PNUM")
    private String pnum;
    @Column(name="PNAME")
    private String pname;
    @Column(name="PIMAGE")
    private String pimage;
    @Column(name="PPAY")
    private int ppay;

    public ProductVO(String pnum, String pname, String pimage, int ppay) {
        this.pnum = pnum;
        this.pname = pname;
        this.pimage = pimage;
        this.ppay = ppay;
    }

}