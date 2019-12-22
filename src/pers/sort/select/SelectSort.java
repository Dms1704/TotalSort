package pers.sort.select;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SelectSort extends Application {

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
        Button easySelectSortButton = new Button("简单选择排序") ;
        easySelectSortButton.setOnMouseClicked(e-> easySelectSortButtonClick(textArea.getText()));     //添加事件
        hBox.getChildren().add(easySelectSortButton) ;

        //树形选择排序按钮
        Button treeSelectSort = new Button("树形选择排序") ;
        treeSelectSort.setOnMouseClicked(e-> treeSelectSortButtonClick(textArea.getText()));
        hBox.getChildren().add(treeSelectSort) ;

        //树形选择排序按钮
        Button heapSortButton = new Button("堆排序") ;
        heapSortButton.setOnMouseClicked(e-> heapSortButtonClick(textArea.getText()));
        hBox.getChildren().add(heapSortButton) ;

        borderPane.setCenter(hBox);
        scene = new Scene(borderPane, 500, 400) ;
        stage.setScene(scene);
        stage.setTitle("选择类排序");
        stage.show();
    }

    public void easySelectSortButtonClick(String text) {
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
            put = easySelectSort(real_r) ;
            long endTime = System.nanoTime() ;
            put += "\n运行时间:"+ (endTime - startTime) + "ns\n时间复杂度:O(n)\n空间复杂度:O(1)" ;
            putTextArea.setText(put);
        }
    }

    /**
     * 简单选择排序
     */
    public String easySelectSort(int[] r){
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

    public void treeSelectSortButtonClick(String text) {
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
            put = treeSelectSort(real_r) ;
            long endTime = System.nanoTime() ;
            put += "\n运行时间:"+ (endTime - startTime) + "ns\n时间复杂度:O(nlogn)\n空间复杂度:O(n)" ;
            putTextArea.setText(put);
        }
    }

    /**
     * 树形选择排序
     * @param r 输入待排数组
     * @return 返回排好序的数组的字符串
     */
    public String treeSelectSort(int[] r){
        String put = "" ;

        int MinValue = -10000 ;              //极值
        int[]tree = new int[r.length * 4] ;  //树的大小(非真实长度)
        int baseSize = 1 ;                   //求树的长度用的辅助变量
        int n = r.length ;                   //待排数的个数
        int max ;                            //记录树结构中最大值的变量
        int maxIndex ;                       //记录树结构中最大值的下标变化的变量
        int treeSize ;                       //树的真实长度

        int i ;
        while (baseSize < n) {    //求叶子节点为n的完全二叉树的节点个数
            baseSize *= 2 ;
        }
        treeSize = baseSize * 2 - 1 ;

        //从后往前存放叶子节点
        for (i = 0; i < n; i++) {
            tree[treeSize - i] = r[i] ;
        }

        //此时i=n，给剩余的一个叶子节点赋一个空值
        for (; i < baseSize; i++) {
            tree[treeSize - i] = MinValue ;
        }

        //构造树的非叶子节点,0位数组不用
        for (i = treeSize; i > 1; i -= 2) {
            tree[i / 2] = (tree[i] > tree[i - 1] ? tree[i] : tree[i - 1]) ;
        }

        n -= 1 ;
        while (n != -1)
        {
            max = tree[1] ;
            r[n--] = max ;
            maxIndex = treeSize ;

            //寻找叶子节点中最大值的数组下标
            while (tree[maxIndex] != max) {
                maxIndex-- ;
            }
            tree[maxIndex] = MinValue ;      //赋极小值

            while (maxIndex > 1) {

                if (maxIndex % 2 == 0) {      //是左节点，为啥要分左右？因为maxIndex奇偶性不确定
                    tree[maxIndex / 2] = (tree[maxIndex] > tree[maxIndex + 1] ? tree[maxIndex] : tree[maxIndex + 1]) ;
                }
                else {
                    tree[maxIndex / 2] = (tree[maxIndex] > tree[maxIndex - 1] ? tree[maxIndex] : tree[maxIndex - 1]) ;
                }
                maxIndex /= 2 ;
            }
        }

        for (i=0; i<r.length; i++)
            put += r[i] + " " ;
        put += "\n" ;

        return put ;
    }

    public void heapSortButtonClick(String text) {
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
            put = heapSort(real_r) ;
            long endTime = System.nanoTime() ;
            put += "\n运行时间:"+ (endTime - startTime) + "ns\n时间复杂度:O(nlogn)\n空间复杂度:O(1)" ;
            putTextArea.setText(put);
        }
    }

    /**
     * 堆排序
     * @param r 待排序数组
     */
    public String heapSort(int[] r){
        String put = "" ;
        int i ;

        //这里元素的索引是从0开始的,所以最后一个非叶子结点r.length/2 - 1,若是1开始，则为r.length/2
        //第一次建堆开始
        //这里是经过优化的，如果从叶子结点r.length-1开始建堆也是可以的
        for (i = r.length / 2 - 1; i >= 0; i--) {
            //i指向第一个非叶子节点
            adjustHeap(r, i, r.length);
        }

        // 下面，开始后续的排序和建堆
        for (int j = r.length - 1; j > 0; j--) {
            //将建好堆的根节点（最大值）放到队尾，完成一次排序
            swap(r, 0, j);
            //再次建堆，除去已经排好序的末尾节点
            adjustHeap(r, 0, j);
        }

        for (i=0; i<r.length; i++)
        put += r[i] + " " ;
        put += "\n" ;

        return put ;
    }

    /**
     * 整个堆排序最关键的地方,对以i为根节点的树建大顶堆
     * @param r 待组堆
     * @param i 为以i为根节点的树建堆
     * @param length 堆的长度
     */
    public static void adjustHeap(int[] r, int i, int length) {
        // 先把当前元素取出来，当前元素需要一直从根比较到叶子结点
        int temp = r[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {  //2*i+1为左子树i的左子树(因为i是从0开始的),2*k+1为k的左子树
            // 让k先指向子节点中最大的节点
            if (k + 1 < length && r[k] < r[k + 1]) {  //如果有右子树,并且右子树大于左子树
                k++;
            }
            //如果发现结点(左右子结点)大于根结点，则进行值的交换
            if (r[k] > temp) {
                swap(r, i, k);
                // 如果子节点更换了，那么，以子节点为根的子树会受到影响,所以，循环对子节点所在的树继续进行判断
                i  =  k;
            } else {  //不用交换，直接终止循环
                break;
            }
        }
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
