package com.hspedu.furns.dao.impl;

import com.hspedu.furns.dao.BasicDAO;
import com.hspedu.furns.dao.FurnDao;
import com.hspedu.furns.entity.Furn;

import java.util.List;

public class FurnDaoImpl extends BasicDAO<Furn> implements FurnDao {
    @Override
    public List<Furn> queryFurns() {
        //这里不用 * 是为了解决前面遗留的问题
        //前面我们定义的属性名是imgPath，而其对应的列名是img_path
        //这样在我们查询时虽然得到img_path的信息，但imgPath属性接收不到，仍都为null
        //因此要在sql查询语句上做修改————加别名
        //遇到属性名和表的列名不一致时就可以这么做！
        String sql = "select `id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path` imgPath from furn";
        return queryMulti(sql, Furn.class);
    }

    @Override
    public int add(Furn furn) {
        //添加家具，返回-1则添加失败
        String sql = "INSERT INTO furn (`name`,maker,price,sales,stock,img_path) VALUES (?,?,?,?,?,?);";
        return update(sql, furn.getName(), furn.getMaker(), furn.getPrice(), furn.getSales(), furn.getStock(), furn.getImgPath());
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM furn WHERE id=?;";
        return update(sql, id);
    }

    @Override
    //查询获取
    public Furn getFurn(int id) {
        String sql = "select `id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path` imgPath from furn " +
                "where `id`=?";
        return querySingle(sql, Furn.class, id);
    }

    @Override
    public int update(Furn furn) {
        //修改家具信息
        String sql = "UPDATE furn SET `name`=?,maker=?,price=?,sales=?,stock=?,img_path=? " +
                "WHERE `id`=?;";
        return update(sql, furn.getName(), furn.getMaker(), furn.getPrice(),
                furn.getSales(), furn.getStock(), furn.getImgPath(), furn.getId());
    }

    @Override
    public int getTotalRow() {
        String sql = "SELECT COUNT(*) from furn";
        //return (Integer)queryScalar(sql); 这里获取到的数据类型是Long，因此转为Integer会报错
        //而把方法返回值改为Long又不划算，因为只取一个数而已
        //所以这里先转为Number再转为int
        return ((Number)queryScalar(sql)).intValue();
    }

    @Override
    public List<Furn> getPageItems(int begin, int pageSize) {
        String sql = "select `id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path` imgPath from furn " +
                "LIMIT ?,?";
        return queryMulti(sql, Furn.class, begin, pageSize);
    }

}
