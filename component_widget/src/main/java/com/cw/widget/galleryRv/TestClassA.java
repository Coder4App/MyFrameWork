package com.cw.widget.galleryRv;

import com.cw.widget.BuildConfig;
import com.cw.widget.FirstFragment;

/**
 * Author: chenwei
 * E-mail: chenwei@qtshe.com
 * Date: 2021/12/4 上午9:58
 * <p>
 * Description:
 */
public class TestClassA {
    private int p1 = 6;

    public void test(){
        System.out.println("----->");
       boolean ss =  BuildConfig.DEBUG;
       new FirstFragment();
    }

    public void testB(){
        System.out.println("----->");
        new TestClassB().print();
    }


    public void testC(){
        System.out.println("----->");
    }


    public void testD(){
        System.out.println("----->");
    }
}
