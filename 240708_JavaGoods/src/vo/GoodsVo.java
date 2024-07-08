package vo;

import lombok.Data;

@Data
public class GoodsVo {

    //상품게시판 번호 이름 내용 타입 가격
    private int no;
    private String name;
    private String content;
    private String goods_type;
    private int price;

}
