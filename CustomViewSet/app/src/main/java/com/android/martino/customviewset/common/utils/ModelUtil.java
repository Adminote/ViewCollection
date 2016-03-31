package com.android.martino.customviewset.common.utils;

import com.android.martino.customviewset.recycleview.model.House;

import java.util.ArrayList;
import java.util.List;

public class ModelUtil {

    public static List<House> getInitHouse() {
        List<House> houses  = new ArrayList();

        House house1 = new House();
        house1.setBlock("虹桥1");
        house1.setPrice("10000/平");
        house1.setTitle("热门楼盘，你值得拥有1~~~");
        house1.setType("1室1厅1卫");
        house1.setUrl("http://img0.imgtn.bdimg.com/it/u=3762573229,3411496083&fm=21&gp=0.jpg");

        House house2 = new House();
        house2.setBlock("虹桥2");
        house2.setPrice("20000/平");
        house2.setTitle("热门楼盘，你值得拥有2~~~");
        house2.setType("2室2厅2卫");
        house2.setUrl("http://www.bz55.com/uploads/allimg/120901/1-120Z1091135.jpg");

        House house3 = new House();
        house3.setBlock("虹桥3");
        house3.setPrice("30000/平");
        house3.setTitle("热门楼盘，你值得拥有3~~~");
        house3.setType("3室3厅3卫");
        house3.setUrl("http://download.pchome.net/wallpaper/pic-5465-1-1680x1050.jpg");

        House house4 = new House();
        house4.setBlock("虹桥4");
        house4.setPrice("40000/平");
        house4.setTitle("热门楼盘，你值得拥有4~~~");
        house4.setType("4室4厅4卫");
        house4.setUrl("http://www.bz55.com/uploads/allimg/141218/139-14121Q10R3.jpg");

        House house5 = new House();
        house5.setBlock("虹桥5");
        house5.setPrice("50000/平");
        house5.setTitle("热门楼盘，你值得拥有5~~~");
        house5.setType("5室5厅5卫");
        house5.setUrl("http://www.bz55.com/uploads/allimg/150210/139-150210134410.jpg");

        House house6 = new House();
        house6.setBlock("虹桥6");
        house6.setPrice("60000/平");
        house6.setTitle("热门楼盘，你值得拥有6~~~");
        house6.setType("6室6厅6卫");
        house6.setUrl("http://pic.desk.chinaz.com/file/11.03.10/2/llmeils7.jpg");

        House house7 = new House();
        house7.setBlock("虹桥7");
        house7.setPrice("70000/平");
        house7.setTitle("热门楼盘，你值得拥有7~~~");
        house7.setType("7室7厅7卫");
        house7.setUrl("http://b.hiphotos.baidu.com/image/pic/item/08f790529822720ea5d058ba7ccb0a46f21fab50.jpg");

        House house8 = new House();
        house8.setBlock("虹桥8");
        house8.setPrice("80000/平");
        house8.setTitle("热门楼盘，你值得拥有8~~~");
        house8.setType("8室8厅8卫");
        house8.setUrl("http://www.bz55.com/uploads/allimg/150608/139-15060Q60Z3-50.jpg");

        House house9 = new House();
        house9.setBlock("虹桥9");
        house9.setPrice("90000/平");
        house9.setTitle("热门楼盘，你值得拥有9~~~");
        house9.setType("9室9厅9卫");
        house9.setUrl("http://www.bz55.com/uploads/allimg/150407/139-15040GUF8-51.jpg");

        House house10 = new House();
        house10.setBlock("虹桥10");
        house10.setPrice("100000/平");
        house10.setTitle("热门楼盘，你值得拥有10~~~");
        house10.setType("10室10厅10卫");
        house10.setUrl("http://www.bz55.com/uploads/allimg/141128/139-14112Q04002-50.jpg");

        houses.add(house1);
        houses.add(house2);
        houses.add(house3);
        houses.add(house4);
        houses.add(house5);
        houses.add(house6);
        houses.add(house7);
        houses.add(house8);
        houses.add(house9);
        houses.add(house10);

        return houses;
    }
}
