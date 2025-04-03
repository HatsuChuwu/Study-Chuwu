package com.example.listview;

import java.util.ArrayList;
import java.util.List;

/**
 * 水果数据管理类，提供静态方法获取水果列表数据
 */
public class FruitData {

    private static List<Fruit> fruitList;

    /**
     * 获取水果列表数据
     * @return 水果列表
     */
    public static List<Fruit> getFruitList() {
        if (fruitList == null) {
            fruitList = new ArrayList<>();
            initFruitData();
        }
        return fruitList;
    }

    /**
     * 初始化水果数据
     */
    private static void initFruitData() {
        // 菠萝
        String pineappleDesc = "菠萝（学名：Ananas comosus (Linn.) Merr.），是热带水果之一，为凤梨科凤梨属植物，原产于南美洲巴西、巴拉圭的亚马逊河流域一带。" + 
                "菠萝是一种热带水果，外观呈圆柱形或椭圆形，表面有鳞片状的外皮，顶部有一簇绿色的叶子。果肉为黄色或金黄色，口感酸甜多汁，富含维生素C和菠萝蛋白酶等营养物质。" + 
                "菠萝可以生食、制作果汁、罐头或用于烹饪。它具有消炎、助消化的功效，但食用过多可能导致口腔不适。";
        String[] pineappleComments = {
                "菠萝很甜，但是吃多了嘴会麻，要小心。",
                "我很喜欢菠萝，特别是夏天吃，非常解渴。",
                "菠萝炒饭是我的最爱，香甜可口。",
                "吃菠萝前用盐水泡一下，可以减少舌头发麻的感觉。"
        };
        fruitList.add(new Fruit(R.drawable.boluo, "菠萝", pineappleDesc, pineappleComments));

        // 草莓
        String strawberryDesc = "草莓（学名：Fragaria × ananassa Duch.），是蔷薇科草莓属的植物，多年生草本。" + 
                "草莓是一种红色的小型浆果，表面有小颗粒状的种子。果肉鲜红多汁，味道甜美，富含维生素C、膳食纤维和抗氧化物质。" + 
                "草莓可以生食、制作果酱、蛋糕装饰或冰淇淋调味。它具有美容养颜、降血压的功效，但过敏体质者应谨慎食用。";
        String[] strawberryComments = {
                "草莓是我最喜欢的水果，酸甜可口。",
                "草莓奶油蛋糕是我的最爱！",
                "草莓要洗干净再吃，农药残留比较多。",
                "国产草莓比进口的要甜很多。"
        };
        fruitList.add(new Fruit(R.drawable.caomei, "草莓", strawberryDesc, strawberryComments));

        // 橙子
        String orangeDesc = "橙子（学名：Citrus sinensis），是芸香科柑橘属植物，常绿小乔木。" + 
                "橙子是一种圆形的柑橘类水果，外皮橙黄色，内部分瓣，果肉多汁。味道酸甜，富含维生素C、柠檬酸和类黄酮等营养物质。" + 
                "橙子可以生食、榨汁或用于烹饪。它具有提高免疫力、预防感冒的功效，适合大多数人食用。";
        String[] orangeComments = {
                "橙子的维生素C含量很高，经常吃对皮肤好。",
                "鲜榨橙汁是早餐的最佳搭配。",
                "冬天吃橙子可以预防感冒。",
                "橙子皮别扔，可以泡茶或者做橙皮糖。"
        };
        fruitList.add(new Fruit(R.drawable.chengzi, "橙子", orangeDesc, orangeComments));

        // 哈密瓜
        String hemiDesc = "哈密瓜（学名：Cucumis melo var. saccharinus），是葫芦科甜瓜属植物，一年生蔓生草本。" + 
                "哈密瓜是一种椭圆形的瓜果，外皮呈网纹状，果肉为橙黄色或绿色。口感香甜多汁，富含胡萝卜素、维生素C和矿物质。" + 
                "哈密瓜通常生食或制作水果沙拉。它具有生津止渴、利尿消肿的功效，但糖尿病患者应适量食用。";
        String[] hemiComments = {
                "新疆哈密瓜是最好吃的，香甜可口。",
                "夏天吃冰镇哈密瓜，简直是人间美味。",
                "哈密瓜不要买太软的，容易不甜。",
                "哈密瓜和西瓜是夏天必备水果。"
        };
        fruitList.add(new Fruit(R.drawable.hamigua, "哈密瓜", hemiDesc, hemiComments));

        // 金桔
        String kumquatDesc = "金桔（学名：Fortunella margarita），是芸香科金柑属植物，常绿灌木或小乔木。" + 
                "金桔是一种小型的柑橘类水果，外形椭圆，皮薄而光滑，橙黄色。果肉酸甜，可连皮食用，富含维生素C和类胡萝卜素。" + 
                "金桔可以生食、制作蜜饯或泡茶。它具有理气化痰、生津止咳的功效，适合秋冬季节食用。";
        String[] kumquatComments = {
                "金桔可以连皮一起吃，很特别的水果。",
                "金桔泡茶喝对嗓子很好。",
                "金桔蜜饯是我小时候的零食。",
                "冬天吃金桔可以预防感冒。"
        };
        fruitList.add(new Fruit(R.drawable.jinju, "金桔", kumquatDesc, kumquatComments));

        // 芒果
        String mangoDesc = "芒果（学名：Mangifera indica L.），是漆树科杧果属植物，常绿大乔木。" + 
                "芒果是一种热带水果，外形椭圆或肾形，成熟时呈黄色或橙红色。果肉橙黄色，口感香甜多汁，富含维生素A、C和膳食纤维。" + 
                "芒果可以生食、制作果汁、冰沙或用于烹饪。它具有润肠通便、美容养颜的功效，但过敏体质者应谨慎食用。";
        String[] mangoComments = {
                "芒果是夏天的必备水果，香甜可口。",
                "芒果冰沙是解暑的最佳饮品。",
                "泰国小芒果酸甜可口，很开胃。",
                "芒果有点过敏，但我还是忍不住要吃。"
        };
        fruitList.add(new Fruit(R.drawable.mangguo, "芒果", mangoDesc, mangoComments));

        // 猕猴桃
        String kiwiDesc = "猕猴桃（学名：Actinidia chinensis Planch.），是猕猴桃科猕猴桃属植物，落叶藤本。" + 
                "猕猴桃是一种椭圆形的水果，外皮褐色有绒毛，果肉为绿色，中间有黑色小籽。口感酸甜多汁，富含维生素C、膳食纤维和抗氧化物质。" + 
                "猕猴桃通常去皮生食或制作果汁、果酱。它具有促进消化、美容养颜的功效，适合大多数人食用。";
        String[] kiwiComments = {
                "猕猴桃维生素C含量超高，经常吃对皮肤好。",
                "猕猴桃配酸奶是健康早餐的好选择。",
                "金色果肉的猕猴桃比绿色的要甜。",
                "猕猴桃不要买太硬的，放几天自然熟更好吃。"
        };
        fruitList.add(new Fruit(R.drawable.mihoutao, "猕猴桃", kiwiDesc, kiwiComments));

        // 葡萄
        String grapeDesc = "葡萄（学名：Vitis vinifera L.），是葡萄科葡萄属植物，木质藤本。" + 
                "葡萄是一种小型浆果，成串生长，颜色有绿色、紫色、红色等。果肉多汁，味道甜美，富含葡萄糖、有机酸和多酚类物质。" + 
                "葡萄可以生食、制作果汁、葡萄酒或干制成葡萄干。它具有补血益气、滋阴润肺的功效，但糖尿病患者应适量食用。";
        String[] grapeComments = {
                "无籽葡萄吃起来更方便，很喜欢。",
                "夏黑葡萄是我的最爱，皮薄多汁。",
                "葡萄要洗干净再吃，农药残留比较多。",
                "红酒葡萄和食用葡萄是不同的品种。"
        };
        fruitList.add(new Fruit(R.drawable.putao, "葡萄", grapeDesc, grapeComments));

        // 香蕉
        String bananaDesc = "香蕉（学名：Musa nana Lour.），是芭蕉科芭蕉属植物，多年生草本。" + 
                "香蕉是一种长条形的水果，外皮黄色，果肉乳白色或淡黄色。口感绵软香甜，富含钾、维生素B6和膳食纤维。" + 
                "香蕉通常生食或用于烘焙、冰淇淋调味。它具有通便润肠、降压安神的功效，适合大多数人食用。";
        String[] bananaComments = {
                "香蕉是运动后补充能量的最佳水果。",
                "香蕉不要放冰箱，会变黑。",
                "香蕉皮别扔，可以用来擦皮鞋。",
                "香蕉助眠效果很好，晚上吃一根有助于睡眠。"
        };
        fruitList.add(new Fruit(R.drawable.xiangjiao, "香蕉", bananaDesc, bananaComments));

        // 油桃
        String nectarineDesc = "油桃（学名：Prunus persica var. nectarina），是蔷薇科桃属植物，落叶小乔木。" + 
                "油桃是一种光滑无毛的桃子变种，外皮光滑，颜色鲜艳，多为红色或黄色。果肉多汁，味道甜美，富含维生素C、胡萝卜素和矿物质。" + 
                "油桃通常生食或用于制作果酱、甜点。它具有生津止渴、润肺止咳的功效，但过敏体质者应谨慎食用。";
        String[] nectarineComments = {
                "油桃比普通桃子更好吃，不用剥皮。",
                "油桃要挑软一点的吃，太硬的不甜。",
                "夏天吃冰镇油桃，简直是人间美味。",
                "油桃和黄桃是不同的品种，口感也不同。"
        };
        fruitList.add(new Fruit(R.drawable.youtao, "油桃", nectarineDesc, nectarineComments));

        // 柚子
        String pomelloDesc = "柚子（学名：Citrus maxima），是芸香科柑橘属植物，常绿小乔木。" + 
                "柚子是一种大型的柑橘类水果，外形圆形或梨形，外皮厚实，果肉分瓣，颜色有白色、粉红色等。口感甜中带酸，富含维生素C、类黄酮和膳食纤维。" + 
                "柚子通常剥皮生食或制作果汁、蜜饯。它具有理气化痰、润肺清肠的功效，但服用某些药物者应避免同时食用。";
        String[] pomelloComments = {
                "中秋节必备水果，一家人一起吃柚子。",
                "柚子皮可以做成柚子茶，很香。",
                "红心柚比白心柚甜，更好吃。",
                "柚子不要和某些药物一起吃，会影响药效。"
        };
        fruitList.add(new Fruit(R.drawable.youzi, "柚子", pomelloDesc, pomelloComments));
    }
}