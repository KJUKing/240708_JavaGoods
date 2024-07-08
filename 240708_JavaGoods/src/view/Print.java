package view;

import vo.GoodsVo;

import java.util.List;

public class Print {

    public void var() {
        System.out.println("==================================");

    }

    public void var(String name) {
        System.out.println("============="+name+"=============");
    }

    public void printLn(int num) {
        for (int i = 0; i < num; i++) {
            System.out.println();
        }
    }

    public void detailPrint(GoodsVo goods) {
        var();
        System.out.println(goods);
        System.out.println("1. 상품 수정");
        System.out.println("2. 상품 삭제");
        System.out.println("3. 상품 리스트");
        printLn(3);
        var();
    }
    public void listPrint(List<GoodsVo> goodsList) {
        for (GoodsVo goodsVo : goodsList) {

            int no = goodsVo.getNo();
            String title = goodsVo.getName();
            String content = goodsVo.getContent();
            String type = goodsVo.getGoods_type();
            int price = goodsVo.getPrice();
            System.out.println(no+"\t"+title+"\t"+content+"\t"+type+"\t"+price);
        }
        var();

        System.out.println("1. 상품 상세 조회");
        System.out.println("2. 상품 검색");
        System.out.println("3. 메인 메뉴");
        var();
    }
    public void mainPrint() {
        var("메인 메뉴");
        System.out.println("1. 상품 전체 조회");
        System.out.println("2. 상품 등록");
        printLn(5);
        var();
    }

}
