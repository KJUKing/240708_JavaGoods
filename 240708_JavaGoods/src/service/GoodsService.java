package service;

import dao.GoodsDao;
import vo.GoodsVo;

import java.util.ArrayList;
import java.util.List;

public class GoodsService {

    private static GoodsService instance;

    private GoodsService() {

    }

    public static GoodsService getInstance() {
        if (instance == null) {
            instance = new GoodsService();
        }
        return instance;
    }
    GoodsDao dao = GoodsDao.getInstance();


    public GoodsVo goodsDetail(List<Object> param) {
        return dao.goodsDetail(param);
    }

    public List<GoodsVo> goodsList() {
        return dao.goodsList();
    }

    public List<GoodsVo> goodsList(List<Object> param) {
        return dao.goodsList(param);
    }

    public void goodsInsert(List<Object> param) {
        dao.goodsInsert(param);
    }

    public void goodsUpdate(List<Object> param) {

        int i= 0;
        String name = (String) param.get(i++);
        String content =(String) param.get(i++);
        String type =(String) param.get(i++);
        int price = (int) param.get(i++);
        int    no = (int) param.get(i++);

        List<Object> p = new ArrayList<>();
        p.add(no);
        GoodsVo goods = dao.goodsDetail(p);

        if (name.equals("")) name = goods.getName();
        if (content.equals("")) content = goods.getContent();
        if (type.equals("")) type = goods.getGoods_type();

        dao.goodsUpdate(param);
    }

    public void goodsDelete(List<Object> param) {
        dao.goodsDelete(param);
    }
}
