package dao;

import util.JDBCUtil;
import vo.GoodsVo;

import java.util.List;


public class GoodsDao {
    private static GoodsDao instance;

    private GoodsDao() {

    }

    public static GoodsDao getInstance() {
        if (instance == null) {
            instance = new GoodsDao();
        }
        return instance;
    }
    JDBCUtil jdbc = JDBCUtil.getInstance();

    public List<GoodsVo> goodsList() {
        String sql = "SELECT NO, NAME, CONTENT, GOODS_TYPE, PRICE\n" +
                "FROM GOODZ";
        return jdbc.selectList(sql, GoodsVo.class);
    }

    public List<GoodsVo> goodsList(List<Object> param) {
        String sql = "SELECT NO, NAME, CONTENT, GOODS_TYPE, PRICE\n" +
                     "FROM GOODZ\n" +
                     "WHERE CONTENT LIKE '%'|| ? ||'%'";
        return jdbc.selectList(sql, param, GoodsVo.class);
    }

    public GoodsVo goodsDetail(List<Object> param) {
        String sql = "SELECT *\n" +
                     "FROM GOODZ\n" +
                     "WHERE NO = ?";
        return jdbc.selectOne(sql, param, GoodsVo.class);
    }

    public void goodsInsert(List<Object> param) {
        String sql = "INSERT INTO GOODZ\n" +
                     "VALUES ((SELECT NVL(MAX(NO),0)+1 FROM GOODZ), ?, ?, ?, ?)";

        jdbc.update(sql, param);
    }

    public void goodsUpdate(List<Object> param) {
        String sql = "UPDATE GOODZ\n" +
                     "SET NAME = ?,\n" +
                     "    CONTENT = ?,\n" +
                     "    GOODS_TYPE = ?,\n" +
                     "    PRICE = ?\n" +
                     "WHERE NO = ?";

        jdbc.update(sql, param);
    }

    public void goodsDelete(List<Object> param) {

        String sql = " DELETE GOODZ " +
                "      WHERE NO = ?";

        jdbc.update(sql, param);
    }
}