import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		int n;
		double [] p;
		double [] pi;
		double [] log;
		int [] k;
		String [] ma;
		Scanner sc=new Scanner(System.in);
		double sum;
		do{
			n=sc.nextInt();
			p=new double [n];
			pi=new double [n];
			log=new double [n];
			k=new int [n];
			ma=new String [n];
			for(int i=0;i<p.length;i++){
				p[i]=sc.nextDouble();
			}
			Arrays.sort(p);
			sum=0;
			for(int i=p.length-1;i>=0;i--){
				pi[p.length-1-i]=sum;
				sum=p[i]+sum;
			}
			if(sum!=1){
				System.out.println("您输入有误，请重新输入！");
			}
		}while(sum!=1);
		NumberFormat nf=NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		for(int i=0;i<p.length;i++){
			log[i]=-Math.log10(p[p.length-1-i])/Math.log10(2);
			if(log[i]==(int)log[i]){
				k[i]=(int)log[i];
			}else{
				k[i]=(int) log[i]+1;
			}
		}
		for(int i=0;i<pi.length;i++){
			ma[i]=Main.doubleToBinary(k[i], pi[i]);
		}
		for(int i=0;i<p.length;i++){
//			if(i==0){
//				System.out.println("信源消息符号ai\t"+"符号概率p(ai)\t"+"累加概率Pi\t"+"-logp(ai)\t"+"码字长度Ki\t"+"码字");
//			}
			System.out.println("a"+(i+1)+"\t"+nf.format(p[p.length-1-i])+"\t"+nf.format(pi[i])+"\t"+nf.format(log[i])+"\t"+k[i]+"\t"+ma[i]);
		}
		double kp=0;
		double hx=0;
		for(int i=0;i<p.length;i++){
			kp+=p[p.length-1-i]*k[i];
			hx+=p[p.length-1-i]*log[i];
		}
		double r=hx/kp;
		System.out.println("平均码长："+nf.format(kp));
		System.out.println("平均信道传输速率："+nf.format(r));
	}
	public static String doubleToBinary(int bit,double pi){
		String binary="";
		for(int i=1;i<=bit;i++){
			pi=pi*2;
			if(pi>=1){
				binary=binary+1;
				pi=pi-1;
			}else{
				binary=binary+0;
			}
		}
		return binary;
		
	}
	

}
