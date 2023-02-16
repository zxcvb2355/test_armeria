package product.main.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@TableGenerator(
        name = "PRODUCT_SEQ_GENERATOR",
        table = "PRO_SEQ",
        pkColumnValue = "PNUM", allocationSize = 1)
public class ProductVO {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE ,generator = "PRODUCT_SEQ_GENERATOR")
    @Column(name="PNUM")
    private Long pnum;
    @Column(name="PNAME")
    private String pname;
    @Column(name="PIMAGE")
    private String pimage;
    @Column(name="PPAY")
    private int ppay;

    @Column(name="DEL_YN")
    private String del_yn;


}