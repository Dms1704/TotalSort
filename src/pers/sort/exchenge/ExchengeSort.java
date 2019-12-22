package pers.sort.exchenge;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ExchengeSort extends Application {

    private TextArea putTextArea = new TextArea() ;      //输出文本框
    private int[] r = new int[100] ;

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

        //冒牌排序按钮
        Button bubbleSortButton = new Button("冒泡排序") ;
        bubbleSortButton.setOnMouseClicked(e-> bubbleSortButtonClick(textArea.getText()));     //添加事件
        hBox.getChildren().add(bubbleSortButton) ;

        //快排按钮
        Button quickSortButton = new Button("快速排序") ;
        quickSortButton.setOnMouseClicked(e-> quickSortButtonClick(textArea.getText()));
        hBox.getChildren().add(quickSortButton) ;

        borderPane.setCenter(hBox);
        scene = new Scene(borderPane, 500, 400) ;
        stage.setScene(scene);
        stage.setTitle("交换类排序");
        stage.show();
    }

    public void bubbleSortButtonClick(String text) {
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

            long startTime = System.nanoTime() ;    //ns
            put = bubbleSort(real_r) ;
            long endTime = System.nanoTime() ;
            put += "\n运行时间:"+ (endTime - startTime) + "ns\n时间复杂度:O(n)\n空间复杂度:O(1)" ;
            putTextArea.setText(put);
        }
    }

    public String bubbleSort(int[] r){
        String put = "" ;

        int i, j, t ;
        for (i=0; i<r.length; i++){
            for (j=0; j<r.length-i-1; j++){
                if(r[j] > r[j+1]){
                   t = r[j] ;
                   r[j] = r[j+1] ;
                   r[j+1] = t ;
                }
            }
        }

        for (i=0; i<r.length; i++)
            put += r[i] + " " ;
        put += "\n" ;

        return put ;
    }

    public void quickSortButtonClick(String text){
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

            long startTime = System.nanoTime() ;    //ns
            put = quickSort(real_r) ;
            long endTime = System.nanoTime() ;
            put += "\n运行时间:"+ (endTime - startTime) + "ns\n时间复杂度:O(nlogn)\n空间复杂度:O(log2n)" ;
            putTextArea.setText(put);
        }
    }

    public String quickSort(int[] r){
        String put = "" ;

        QKsort(r, 0, r.length-1);

        int i ;
        for (i=0; i<r.length; i++)
            put += r[i] + " " ;
        put += "\n" ;
        return put ;
    }

    public int QKpass(int[] r, int low, int high){ //一趟快速排序
        int t ;
        t = r[low] ;  //设置枢轴

        while (low < high){

            while (low < high && r[high] >= t)      //将小于t的移到左边
                high-- ;
            r[low] = r[high] ;

            while (low < high && r[low] <= t)       //将大于t的移到右边
                low++ ;
            r[high] = r[low] ;
        }
        r[low] = t ;
        return low ;    //返回分割断点
    }

    public void QKsort(int r[], int low, int high){
        int pos ;

        if(low < high) {
            pos = QKpass(r, low, high);
            QKsort(r, low, pos - 1);     //为啥不管pos？因为pos里的值已经和左右序列比较过了
            QKsort(r, pos + 1, high);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
