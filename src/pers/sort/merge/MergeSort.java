package pers.sort.merge;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MergeSort extends Application {

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

        //简单选择排序
        Button twoWayMergeButton = new Button("二路归并排序") ;
        twoWayMergeButton.setOnMouseClicked(e-> twoWayMergeButtonClick(textArea.getText()));     //添加事件
        hBox.getChildren().add(twoWayMergeButton) ;

/*
        //树形选择排序按钮
        Button treeSelectSort = new Button("树形选择排序") ;
        treeSelectSort.setOnMouseClicked(e-> treeSelectSortButtonClick(textArea.getText()));
        hBox.getChildren().add(treeSelectSort) ;
*/

        borderPane.setCenter(hBox);
        scene = new Scene(borderPane, 500, 400) ;
        stage.setScene(scene);
        stage.setTitle("归并类排序");
        stage.show();
    }

    /**
     * 二路归并排序
     */
    public void twoWayMergeButtonClick(String text) {
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
            put = twoWayMergeSort(real_r) ;
            long endTime = System.nanoTime() ;
            put += "\n运行时间:"+ (endTime - startTime) + "ns\n时间复杂度:O(n)\n空间复杂度:O(1)" ;
            putTextArea.setText(put);
        }
    }

    /**
     * 简单选择排序
     */
    public String twoWayMergeSort(int[] r){
        String put = "" ;

        int i, j, t, k ;
        for (i=0; i<r.length; i++){

            k = i ;
            //找最小值的下标
            for (j=i+1; j<r.length; j++){
                if (r[j] <= r[k]){
                    k = j ;
                }
            }
            if (k != i){
                t = r[k] ;
                r[k] = r[i] ;
                r[i] = t ;
            }

        }

        for (i=0; i<r.length; i++)
            put += r[i] + " " ;
        put += "\n" ;

        return put ;
    }

}
