package org.koreait;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Article {
    int lastId = 0;
    Scanner sc = new Scanner(System.in);
    List<ArticleList> articles = new ArrayList<>();
    String cmd;
    int system = 1;

    String[] cmdBits = null;
    String head = "";
    String body = "";
    String tail = "";

    public void run() {

        while (system == 1) {
            System.out.printf("명령어 ) ");
            cmd = sc.nextLine();

            if (cmd.length() == 0) {
                System.out.println("명령어를 입력해주세요");
            }

            if (cmd.equals("exit")) {
                system = 0;
            }

            if (cmd.equals("article write")) {
                write();
            } else if (cmd.contains("article list")) {
                rq();
                list();
            } else if (cmd.contains("article detail")) {
                rq();
                detail();
            } else if (cmd.contains("article delete")) {
                rq();
                delete();
            } else if (cmd.contains("article modify")) {
                rq();
                modify();
            }
        }
    }

    public void write() {
        int id = lastId + 1;
        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String body = sc.nextLine();
        ArticleList article = new ArticleList(id, title, body);
        articles.add(article);
        System.out.println(id + "번 게시글이 작성되었습니다");
        lastId++;
        run();
    }

    public void list() {
        String titleCut = "";


        if(cmdBits.length == 2) {
            for (int i = 0; i < articles.size(); i++) {
                titleCut += articles.get(i).getTitle();

                if(articles.get(i).title.length() > 3) {
                    titleCut = articles.get(i).getTitle().substring(0, 3);
                }

                System.out.println("번호 /   제목   /  내용");
                System.out.printf("%d   /   %s   /   %s   \n",articles.get(i).id, titleCut, articles.get(i).body);
            }
            run();
        }
        if(cmdBits.length == 3) {
            for (int i = 0; i < articles.size(); i++) {
                titleCut += articles.get(i).getTitle();

                if(articles.get(i).title.contains(tail)) {
                    if(articles.get(i).title.length() > 3) {
                        titleCut = articles.get(i).getTitle().substring(0, 3);
                    }

                    System.out.println("번호   /   제목   /  내용");
                    System.out.printf("%d   /   %s   /   %s   \n",articles.get(i).id, titleCut, articles.get(i).body);
                }
            }
            run();
        }


    }

    public void detail() {
        for (int i = 0; i < articles.size(); i++) {
            if(tail.equals(String.valueOf(articles.get(i).id))) {
                System.out.println(articles.get(i).id + "번글");
                System.out.println("제목 : " + articles.get(i).title);
                System.out.println("내용 : " + articles.get(i).body);
                run();
            }
        }

        System.out.println("검색하신 게시글은 없습니다");
    }

    public void delete() {
        for (int i = 0; i < articles.size(); i++) {
            if(tail.equals(String.valueOf(articles.get(i).id))) {
                System.out.println(articles.get(i).id + "번글이 삭제되었습니다");
                articles.remove(i);
                run();
            }
        }

        System.out.println("삭제할 게시글이 없습니다");
    }

    public void modify() {
        for (int i = 0; i < articles.size(); i++) {
            if(tail.equals(String.valueOf(articles.get(i).id))) {
                System.out.println(articles.get(i).id + "번글");
                System.out.println("수정 전 제목 : " + articles.get(i).title);
                System.out.printf("수정(제목) : ");
                String title = sc.nextLine();
                articles.get(i).title = title;
                System.out.println("수정 전 내용 : " + articles.get(i).body);
                System.out.printf("수정(내용) : ");
                String body = sc.nextLine();
                articles.get(i).body = body;
                run();
            }
        }
        System.out.println("수정할 게시글이 없습니다");
    }

    public void rq() {
        cmdBits = cmd.split(" ");
        head = "";
        body = "";
        tail = "";

        if (cmdBits.length == 2) {
            head = cmdBits[0];
            body = cmdBits[1];
        } else if (cmdBits.length == 3) {
            head = cmdBits[0];
            body = cmdBits[1];
            tail = cmdBits[2];
        }
    }
}