package com.newlecture.di.ui;

import java.util.Scanner;

import com.newlecture.di.entity.Exam;

public class GridExamConsole implements ExamConsole{

   private Exam exam;

   public GridExamConsole() {
      exam = new Exam();
   }

   public void input() {
      Scanner scan = new Scanner(System.in);
      int kor;
      int eng;
      int math;

      System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
      System.out.println("弛                  Exam Input              弛");
      System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");

      System.out.print("kor : ");
      kor = scan.nextInt();
      System.out.print("eng : ");
      eng = scan.nextInt();
      System.out.print("math : ");
      math = scan.nextInt();

      exam.setKor(kor);
      exam.setEng(eng);
      exam.setMath(math);
   }

   public void print() {
      int kor = exam.getKor();
      int eng = exam.getEng();
      int math = exam.getMath();
      int total = exam.total();
      float avg = exam.avg();

      System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
      System.out.println("弛                Exam Print                弛");
      System.out.println("戍式式式式式式式式成式式式式式式式成式式式式式式式成式式式式式式式成式式式式式式式式式扣");
      System.out.println("弛   kor  弛  eng  弛  math 弛 total 弛   avg   弛");
      System.out.println("戍式式式式式式式式托式式式式式式式托式式式式式式式托式式式式式式式托式式式式式式式式式扣");
      System.out.printf("弛   %3d  弛  %3d  弛  %3d  弛  %3d  弛  %6.2f 弛\n", kor, eng, math, total, avg);
      System.out.println("戌式式式式式式式式扛式式式式式式式扛式式式式式式式扛式式式式式式式扛式式式式式式式式式戎");
   }
}