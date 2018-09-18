package com.java6310;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class MathExamlv2 {
	
    private static void calculate() {
        LinkedList<String> mList=new LinkedList<>();
        String[] postStr=sr.toString().split(" ");
        for (String s:postStr) {
            if (isOperator(s)){
                if (!mList.isEmpty()){
                    int N1=Integer.valueOf(mList.pop());
                    int N2=Integer.valueOf(mList.pop());
                    if (s.equals("/") && N1==0){
                    	answer="wrong";
                    	sr.setLength(0);
                        return;
                    }
                    if (s.equals("/")&& (N2%N1!=0)){
                    	answer="wrong";
                    	sr.setLength(0);
                        return;
                    }
                    if (s.equals("-")&& N2<=N1){
                    	answer="wrong";
                    	sr.setLength(0);
                        return;
                    }
                    int newNum=cal(N2,N1,s);
                    mList.push(String.valueOf(newNum));
                }
            }
            else {
                //������ѹ��ջ��
                mList.push(s);
            }
        }
        if (!mList.isEmpty()){
            answer=mList.pop();
        }
        sr.setLength(0);
    }
    private static void transferToPostfix(LinkedList<String> list) {
        Iterator<String> it=list.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if (isOperator(s)) {
                if (operators.isEmpty()) {
                    operators.push(s);
                }
                else {
                    if (priority(operators.peek())<priority(s)&&!s.equals(")")) {
                        operators.push(s);
                    }
                    else if(!s.equals(")")&&priority(operators.peek())>=priority(s)){
                        while (operators.size()!=0&&priority(operators.peek())>=priority(s)
                                &&!operators.peek().equals("(")) {
                            if (!operators.peek().equals("(")) {
                                String operator=operators.pop();
                                sr.append(operator).append(" ");
                                output.push(operator);
                            }
                        }
                        operators.push(s);
                    }
                    //�������Ĳ�������")"���򵯳���ջ����ʼ��һ��"("����֮ǰ�����в�����
                    else if (s.equals(")")) {
                        while (!operators.peek().equals("(")) {
                            String operator=operators.pop();
                            sr.append(operator).append(" ");
                            output.push(operator);
                        }
                        //����"("
                        operators.pop();
                    }
                }
            }
            //�����Ϊ�ǲ�����
            else {
                sr.append(s).append(" ");
                output.push(s);
            }
        }
        if (!operators.isEmpty()) {
            Iterator<String> iterator=operators.iterator();
            while (iterator.hasNext()) {
                String operator=iterator.next();
                sr.append(operator).append(" ");
                output.push(operator);
                iterator.remove();
            }
        }
        calculate();
        //Collections.reverse(output);
    }
    //�ж��Ƿ������
    private static boolean isOperator(String oper){
        if (oper.equals("+")||oper.equals("-")||oper.equals("/")||oper.equals("*")
                ||oper.equals("(")||oper.equals(")")) {
            return true;
        }
        return false;
    }
    //��������������ȼ�
    private static int priority(String s){
        switch (s) {
            case "+":return 1;
            case "-":return 1;
            case "*":return 2;
            case "/":return 2;
            case "(":return 3;
            case ")":return 3;
            default :return 0;
        }
    }
    //���м���
    private static int cal(int N1,int N2,String operator){
        switch (operator){
            case "+":return N1+N2;
            case "-":return N1-N2;
            case "*":return N1*N2;
            case "/":return N1/N2;
            default :return 0;
        }
    }
    //һ�꼶
	private static void gradeOne(int n) throws IOException, FileNotFoundException {
		int[] sum = new int[n + 1];// ����sum����
		char[] signSet = { '+', '-' };// ����sianSet����
		Random random = new Random();

		for (int i = 1; i <= n; i++) {
			String str = "(" + i + ")";
			int a = random.nextInt(100) + 1;
			int b = random.nextInt(100) + 1;
			int t = random.nextInt(signSet.length);
			char sign = signSet[t];

			switch (sign) {
			case '+':
				sum[i] = a + b;
				break;
			case '-':
				sum[i] = a - b;
				if (sum[i] < 0) {
					i = i - 1;
					continue;
				}
				break;
			default:
				;
			}
			str = str + " " + a + " " + sign + " " + b + " " + "=";
			out(str);
			if (i == n) {
				out("\r\n");
			}
		}

		BufferedReader br = in();

		for (int i = 1; i <= n; i++) {
			String str = br.readLine() + " " + sum[i];
			out(str);
			if (i == n) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy��-MM��-dd�� HH:mm");// �������ڸ�ʽ
				out("211606310 ��ΰ��" + " " + df.format(new Date()));
			}
		}
	}
	//���꼶
	private static void gradeTwo(int n) throws IOException, FileNotFoundException {
		String[] sum = new String[n + 1];// sumȡֵ
		char[] signSet = { '*', '/' };// sianSetȡֵ
		Random random = new Random();

		for (int i = 1; i <= n; i++) {
			String str = "(" + i + ")";
			int a = random.nextInt(100) + 1;
			int b = random.nextInt(100) + 1;
			int t = random.nextInt(signSet.length);
			char sign = signSet[t];

			switch (sign) {
			case '*':
				sum[i] = String.valueOf(a * b);
				sign = '��';
				break;
			case '/':
				if (b == 0 && a > b) {
					i = i - 1;
					continue;
				}
				sum[i] = String.valueOf(a / b);
				if (a % b != 0) {
					sum[i] = sum[i] + "..." + String.valueOf(a % b);
				}
				sign = '��';
				break;
			default:
				;
			}
			str = str + " " + a + " " + sign + " " + b + " " + "=";
			out(str);
			if (i == n) {
				out("\r\n");
			}
		} 

		BufferedReader br = in();

		for (int i = 1; i <= n; i++) {
			String str = br.readLine() + " " + sum[i];
			out(str);
			if (i == n) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy��-MM��-dd�� HH:mm");// �������ڸ�ʽ
				out("211606310 ��ΰ��" + " " + df.format(new Date()));
			}
		} 
	}
	//���꼶
	private static void gradeThree(int n) throws IOException, FileNotFoundException {
		String[] sum = new String[n];
		Random random = new Random();
		char[] signSet = { '*', '/' ,'+','-'};
		for(int i=0;i<n;i++) {
			int sumOfSign=random.nextInt(2)+2;
			int flag=0;
			if(sumOfSign>1) {
				flag=random.nextInt(2);//���������������������һ����ʱ���ж���֮һ�ĸ��ʳ���
			}
			int number[] = new int[4];
			for(int j=0;j<4;j++) {
				number[j] = random.nextInt(1000) + 1;
			}
			String str= "(" + i + ")" + " " + number[0];
			for(int k=0;k<sumOfSign;k++) {
				str=str + " " + signSet[random.nextInt(4)] + " " + number[k+1];
				if(k==sumOfSign-1) {
					str=str+" ";
				}
			}
			int jiafa=0,jianfa=0,chengfa=0,chufa=0;
			for(int w=0;w<str.length();w++) {
				char c = str.charAt(w);
				switch(c) {
				case '+':;jiafa++;break;
				case '-':;jianfa++;break;
				case '*':;chengfa++;break;
				case '/':;chufa++;break;
				default:;
				}
			}
			if(jiafa>1 || jianfa>1 || chengfa>1 || chufa>1) {
				i=i-1;
				jiafa=0;
				jianfa=0;
				chengfa=0;
				chufa=0;
				continue;
			}
			jiafa=0;
			jianfa=0;
			chengfa=0;
			chufa=0;
			if((str.contains("-") || str.contains("+")) &&(str.contains("*") || str.contains("/"))) {
				if(flag==1) {
					int t=str.indexOf("-");
					if(t==-1) {
						t=str.indexOf("+");
					}
					int right=str.indexOf(" ", t+2);
					int left=str.indexOf(" ",t-5);
					if(str.charAt(left)==' ' && !Character.isDigit(str.charAt(left+1)) ) {
						left=left+2;
					}
					StringBuffer sr = new StringBuffer(str);
					sr.insert(right, " )");
					sr.insert(left+1, "( ");
					str=sr.toString();
				}
			}
			LinkedList<String> list=new LinkedList<>();
			String[] arr = str.split(" ");
	        for(int q=1;q<arr.length;q++) {
	        	list.add(arr[q]);
	        }
			transferToPostfix(list);	
			if(answer=="wrong") {
				i=i-1;
				continue;
			}
			sum[i]=answer;
			list.clear();
			arr=null;
			str=str.replace('*', '��');
			str=str.replace('/', '��');
			out(str);
		}
		out("\r\n");
		BufferedReader br = in();

		for (int i = 0; i < n; i++) {
			String str = br.readLine() + "=" + " " + sum[i];
			out(str);
			if (i == n) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy��-MM��-dd�� HH:mm");// �������ڸ�ʽ
				out("211606310 ��ΰ��" + " " + df.format(new Date()));
			}
		} // ���forѭ��������ȷ��Ҫд�뵽out.txt�еĵ�ֵ��Ҳ����Ҫ������Ŀ�Ĵ�
	}
	//���뷽��
	private static BufferedReader in() throws FileNotFoundException {
		File file = new File("out.txt");
		InputStreamReader in = new InputStreamReader(new FileInputStream(file));// ����һ���������Ķ���
		BufferedReader br = new BufferedReader(in);// ����һ��������󣬰��ļ�����ת���ɼ������ʶ��Ķ���
		return br;
	}
	//�������
	private static void out(String str) throws IOException {
		File file = new File("out.txt");// ����һ���ļ���������ָ��һ���ļ�λ��
		file.createNewFile();// ��ָ��λ�ô���һ���ļ�
		BufferedWriter br = new BufferedWriter(new FileWriter(file, true));// ����һ��������󣬰��ļ�����ת���ɼ������ʶ��Ķ���
		br.write(str + "\r\n");
		br.flush();
		br.close();
	}
	//������
	private static void wrongJudgement(String[] args, int n, int grade) throws IOException, FileNotFoundException {
		if (args.length < 4) {
			System.out.println("���ϸ��ո�ʽ����");
			return;
		}

		switch (args[0]) {
		case "-n": {
			if (args[1].length() > 8) {
				System.out.println("��Ŀ����������Χ");
			}
			n = Integer.parseInt(args[1]);
			break;
		}
		case "-grade": {
			grade = Integer.parseInt(args[1]);
			if (grade < 1 || grade > 3) {
				System.out.println("1<=grade>=3");
			}
			break;
		}
		default:
			System.out.println("���ϸ��ո�ʽ����");
			break;
		}

		switch (args[2]) {
		case "-n": {
			if (args[1].length() > 8) {
				System.out.println("��Ŀ����������Χ");
			}
			n = Integer.parseInt(args[3]);
			break;
		}
		case "-grade": {
			grade = Integer.parseInt(args[3]);
			if (grade < 1 || grade > 3) {
				System.out.println("1<=grade>=3");
			}
			break;
		}
		default:
			System.out.println("���ϸ��ո�ʽ����");
			break;
		}
		if (n != -1 && grade != -1) {
			if (grade == 1) {
				gradeOne(n);
			}
			if (grade == 2) {
				gradeTwo(n);
			}
			if (grade == 3) {
				gradeThree(n);
			}
		}
	}
	public static String answer="";
    //��¼����
    private static LinkedList<String> operators=new LinkedList<>();
    //���
    private static LinkedList<String> output=new LinkedList<>();
    //��׺���ʽ
    private static StringBuilder sr=new StringBuilder();
    
    //��׺���ʽ��Ϊ��׺���ʽ

	public static void main(String[] args) throws FileNotFoundException, IOException {
		int n = -1, grade = -1;
		// ͨ���жϵ����鳤�����жϲ����ĸ����Ƿ��������
		wrongJudgement(args, n, grade);
	}
}