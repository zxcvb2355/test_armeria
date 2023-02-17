package product.main.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class selectSeeData {
    private Long pnum;
    private String pname;
    private String pimage;
    private int ppay;
}
