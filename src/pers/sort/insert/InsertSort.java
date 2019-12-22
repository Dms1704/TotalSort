package pers.sort.insert;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.Date;

public class InsertSort extends Application {

    private TextArea putTextArea = new TextArea() ;      //输出文本框
    private int[] r = new int[100] ;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene ;
        BorderPane borderPane = new BorderPane() ;
        HBox hBox = new HBox() ;

        //hBox设置属性
        hBox.setStyle("-fx-text-alignment: center; -fx-alignment: center");
        hBox.setPadding(new Insets(5, 5, 5, 5));
        hBox.setSpacing(20);    //设置控件间间距

        //程序输入框
        TextArea textArea = new TextArea() ;
        textArea.setEditable(true); // 设置多行输入框能否编辑
        textArea.setPromptText("请输入数字进行排序"); // 设置多行输入框的提示语
        textArea.setWrapText(true); // 设置多行输入框是否支持自动换行。true表示支持，false表示不支持。
        textArea.setStyle("-fx-min-height: 150 ;");
        borderPane.setTop(textArea) ;

        //输出框
        putTextArea.setEditable(true);
        putTextArea.setWrapText(true);
        putTextArea.setStyle("-fx-min-height: 150 ;");
        borderPane.setBottom(putTextArea);

        //直接插入排序按钮
        Button DirectInsertSortButton = new Button("直接插入排序") ;
        DirectInsertSortButton.setOnMouseClicked(e-> DirectInsertSortButtonClick(textArea.getText()));     //添加事件
        hBox.getChildren().add(DirectInsertSortButton) ;

        //折半插入排序按钮
        Button discountInsertSortButton = new Button("折半插入排序") ;
        discountInsertSortButton.setOnMouseClicked(e-> discountInsertSortButtonClick(textArea.getText()));
        hBox.getChildren().add(discountInsertSortButton) ;

        //希尔排序按钮
        Button shellSortButton = new Button("希尔排序") ;
        shellSortButton.setOnMouseClicked(e->shellSortButtonClick(textArea.getText()));
        hBox.getChildren().add(shellSortButton) ;

        borderPane.setCenter(hBox);
        scene = new Scene(borderPane, 500, 400) ;
        stage.setScene(scene);
        stage.setTitle("插入类排序");
        stage.show();
    }

    public void DirectInsertSortButtonClick(String text) {
        String put = "" ;
        if(text.toCharArray() == null){
            put += "error1: convert chararray." ;
            putTextArea.setText(put);
        }
        else {
            int i ;
            char[] t = text.toCharArray() ;
            int[] r = new int[t.length] ;

            int count = 0 ;
            for(i=0; i<t.length; i++){

                String in = "" ;

                //跳过空格
                while (t[i] == ' ')
                    ++i ;

                //碰到数字
                if(t[i] <= '9' && t[i] >= '0'){

                    in += t[i] ;
                    if (i+1 < t.length)
                        ++i ;
                    else {
                        r[count++] = Integer.parseInt(in) ;
                        continue;
                    }

                    while (t[i] <= '9' && t[i] >= '0'){
                        in += t[i] ;
                        if (i+1 < t.length)
                            ++i ;
                        else {
                            break;
                        }
                    }
                }
                //System.out.println(Integer.parseInt(in));
                r[count++] = Integer.parseInt(in) ;
            }
            int[] real_r = new int[count] ; //真正传给算法进去的数组

//            System.out.println("传进去的数组：");
            for (i=0; i<real_r.length; i++){
                real_r[i] = r[i] ;
//                System.out.printf("%d ", real_r[i]);
            }
//            System.out.println();

            long startTime = System.nanoTime() ;    //ns
            put = directInsertSort(real_r) ;
            long endTime = System.nanoTime() ;
            put += "\n运行时间:"+ (endTime - startTime) + "ns\n时间复杂度:O(n)\n空间复杂度:O(1)" ;
            putTextArea.setText(put);
        }
    }

    public String directInsertSort(int[] r){

        String p = "" ;
        int i, j, t ;
        /*t = a ;
         * a = b ;
         * b = t ;*/
        for(i=1; i<r.length; i++){

            if (r[i] < r[i-1]) {        //如果顺序不变

                //设置监视哨,其作用是一个暂存值的变量（相当于交换两个值的中间变量）并监视r[i]的值,相当于t = a
                t = r[i];
                //r[i]是当前需要插入的值

                //相当于a = b，从插入的位置统一向后移一位，给b腾一个地方
                for (j = i - 1; j >= 0 && t < r[j]; j--) {
                    r[j + 1] = r[j];
                }

                //相当于b = t
                r[j + 1] = t;
            }
        }

        for (i=0; i<r.length; i++)
            p += r[i] + " " ;
        p += "\n" ;
        return p ;
    }

    public void discountInsertSortButtonClick(String text){
        String put = "" ;
        if(text.toCharArray() == null){
            put += "error1: convert chararray." ;
            putTextArea.setText(put);
        }
        else {
            int i ;
            char[] t = text.toCharArray() ;
            int[] r = new int[t.length] ;

            int count = 0 ;
            for(i=0; i<t.length; i++){

                String in = "" ;

                //跳过空格
                while (t[i] == ' ')
                    ++i ;

                //碰到数字
                if(t[i] <= '9' && t[i] >= '0'){

                    in += t[i] ;
                    if (i+1 < t.length)
                        ++i ;
                    else {
                        r[count++] = Integer.parseInt(in) ;
                        continue;
                    }

                    while (t[i] <= '9' && t[i] >= '0'){
                        in += t[i] ;
                        if (i+1 < t.length)
                            ++i ;
                        else {
                            break;
                        }
                    }
                }
                r[count++] = Integer.parseInt(in) ;
            }
            int[] real_r = new int[count] ; //真正传给算法进去的数组
            for (i=0; i<count; i++) {
                real_r[i] = r[i] ;
            }

            long startTime = System.nanoTime() ;    //ns
            put = discountInsertSort(real_r) ;
            long endTime = System.nanoTime() ;
            put += "\n运行时间:"+ (endTime - startTime) + "ns\n时间复杂度:O(n)\n空间复杂度:O(1)" ;
            putTextArea.setText(put);
        }
    }

    public String discountInsertSort(int[] r){  //在寻找插入位置的过程中使用了折半查找，性能提高不大
        String put = "" ;

        int i, j, t, low, high, mid ;
        for(i=1; i<r.length; i++){

            if(r[i] < r[i-1]) {
                t = r[i];

                low = 0;
                high = i - 1;

                while (low <= high) {           //寻找插入位置

                    mid = (low + high) / 2;

                    if (r[i] < r[mid])
                        high = mid - 1;
                    else if (r[i] == r[mid]) {     //找到插入位置了
                        low = mid + 1;
                        break;
                    }
                    else low = mid + 1;
                }

                for(j=i-1; j>=low; j--){        //从找到的low位置向后移一位，覆盖掉r[i]
                    r[j+1] = r[j] ;
                }

                r[low] = t ;
            }
        }

        for (i=0; i<r.length; i++)
            put += r[i] + " " ;
        put += "\n" ;

        return put ;
    }

    public void shellSortButtonClick(String text){
        String put = "" ;
        if(text.toCharArray() == null){
            put += "error1: convert chararray." ;
            putTextArea.setText(put);
        }
        else {
            int i ;
            char[] t = text.toCharArray() ;
            int[] r = new int[t.length] ;

            int count = 0 ;
            for(i=0; i<t.length; i++){

                String in = "" ;

                //跳过空格
                while (t[i] == ' ')
                    ++i ;

                //碰到数字
                if(t[i] <= '9' && t[i] >= '0'){

                    in += t[i] ;
                    if (i+1 < t.length)
                        ++i ;
                    else {
                        r[count++] = Integer.parseInt(in) ;
                        continue;
                    }

                    while (t[i] <= '9' && t[i] >= '0'){
                        in += t[i] ;
                        if (i+1 < t.length)
                            ++i ;
                        else {
                            break;
                        }
                    }
                }
                r[count++] = Integer.parseInt(in) ;
            }
            int[] real_r = new int[count] ; //真正传给算法进去的数组

            for (i=0; i<real_r.length; i++){
                real_r[i] = r[i] ;
            }

            //确定增量序列
            int[] dlta = new int[real_r.length/2] ;
            int p ;
            p = real_r.length ;

            for(i=0; i<dlta.length; i++){
                if(dlta.length == 1){
                    dlta[0] = 1 ;
                    break;
                }

                p /= 2 ;
                dlta[i] = p ;
            }

            long startTime = System.nanoTime() ;    //ns
            put = shellSort(real_r, dlta) ;
            long endTime = System.nanoTime() ;
            put += "\n运行时间:"+ (endTime - startTime) + "ns\n时间复杂度:O(n)\n空间复杂度:O(1)" ;
            putTextArea.setText(put);
        }
    }

    public String shellSort(int[] r, int[] rlta){       //rlta是增量序列，存放增量大小
        String put = "" ;

        int i ;
        for (i=0; i<rlta.length; i++)
            r = shellInsert(r, rlta[i]);

        for (i=0; i<r.length; i++)
            put += r[i] + " " ;
        put += "\n" ;

        return put ;
    }

    public int[] shellInsert(int[] r, int dk){   //dk代表增量的大小
        int i, j, t ;
        for (i=0+dk; i<r.length; i++){
            if (r[i] < r[i-dk]){
                t = r[i] ;

                //r[j]如果大于t说明逆序，需要后移
                for (j=i-dk; j>=0 && r[j]>t; j-=dk){
                    r[j+dk] = r[j] ;
                }
                r[j+dk] = t ;
            }
        }
        return r ;
    }
}

