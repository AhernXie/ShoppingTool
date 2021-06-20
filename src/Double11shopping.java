import java.util.Scanner;

public class Double11shopping {
    // items商品价格，n商品个数, w表示满减条件，比如200
    public static void double11advance(int[] items, int n, int w) {
        boolean[][] states = new boolean[n][2*w+1];//超过2倍就没有薅羊毛的价值了
        states[0][0] = true;  // 第一行的数据要特殊处理
        if (items[0] <= 2*w) {
            states[0][items[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = 0; j <= 2*w; ++j) {// 不购买第i个商品
                if (states[i-1][j] == true) states[i][j] = states[i-1][j];
            }
            for (int j = 0; j <= 2*w-items[i]; ++j) {//购买第i个商品
                if (states[i-1][j]==true) states[i][j+items[i]] = true;
            }
        }

        int j;
        for (j = w; j < 2*w+1; ++j) {
            if (states[n-1][j] == true) break; // 输出结果大于等于w的最小值
        }
        if (j == 2*w+1) return; // 没有可行解
        System.out.println("花费："+j+"元");
        for (int i = n-1; i >= 1; --i) { // i表示二维数组中的行，j表示列
            if(j-items[i] >= 0 && states[i-1][j-items[i]] == true) {
                System.out.print(items[i] + " "); // 购买这个商品
                j = j - items[i];
            } // else 没有购买这个商品，j不变。
        }
        if (j != 0) System.out.print(items[0]);
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("输入商品总数:");
        int n=sc.nextInt();
        int[] items=new int[n];
        System.out.println("输入商品价格序列:");
        for(int i=0;i<n;i++){
            items[i]=sc.nextInt();
        }
        System.out.print("输入须达到的满减金额:");
        int w=sc.nextInt();
        double11advance(items,n,w);

    }
}
