package tfidf;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TfIdf {

    public static void main(String[] args) throws IOException {

        String a = "";
        Vector<String> yeye = new Vector();
        Vector<String> yuyu = new Vector();
        Vector<Integer> jumlah = new Vector();
        Vector<Double> tf = new Vector<Double>();
        Vector<Double> tf2 = new Vector<Double>();
        Vector<Double> idf = new Vector<Double>();
        Vector<Double> tfidf = new Vector<Double>();
        Vector<String> document = new Vector();
        Vector<String> document1 = new Vector();

        Document doc = Jsoup.connect("http://uinsby.ac.id").get();
        Elements links = doc.select("body");
        String x = links.text();

        for (int w = 0; w < 2; w++) {
            if (w == 0) {
                a = "aku adalah anak sistem informasi aku";
            } else {
                a = "aku adalah anak lamongan city";
            }

            String ss;
            ss = a.replaceAll("dan", "").replaceAll("tapi", "").replaceAll("dengan", "").replaceAll("yang", "").
                    replaceAll("untuk", "").replaceAll("namun", "").replaceAll("karena", "").replaceAll("jika", "").
                    replaceAll("agar", "").replaceAll("&", "").replaceAll("-", "").replaceAll(":", "").replaceAll("adalah", "");

            StringTokenizer b = new StringTokenizer(ss, " ");

            while (b.hasMoreTokens()) {
                yeye.add(b.nextToken());
            }
//            for (int i = 0; i < yeye.size(); i++) {
//                System.out.println(yeye.get(i));
//            }
            for (int i = 0; i < yeye.size(); i++) {
                yuyu.add(yeye.get(i));
            }

            for (int i = 0; i < yeye.size(); i++) {
                for (int j = 0; j < i; j++) {
                    if (i != j && yeye.get(i).equals(yeye.get(j))) {
                        yuyu.remove(yeye.get(i));
                        break;
                    }
                }
            }

//            for (int i = 0; i < yuyu.size(); i++) {
//                System.out.println(yuyu.get(i));
//            }
            //menghitung jumlah kata
            for (Object key : yuyu) {
                int jum = 0;
                for (String t : yeye) {
                    if (key.equals(t)) {
                        jum++;
                    }
                }
                jumlah.add(jum);
            }
//            for (int i = 0; i < jumlah.size(); i++) {
//                System.out.println(jumlah.get(i));
//            }

            int k = yeye.size();
            double tff;
            for (int i = 0; i < yuyu.size(); i++) {
                tff = (double) jumlah.get(i) / k;
                tf.add(tff);
            }

            int hitungDoc = w + 1;
            System.out.println("\nDoc :" + hitungDoc);
            for (int i = 0; i < yuyu.size(); i++) {
                document.add(yuyu.get(i));
                tf2.add(tf.get(i));
                System.out.println("Tf --- > " + yuyu.get(i) + " : " + tf.get(i));
            }

            yeye.clear();
            yuyu.clear();
            tf.clear();
            jumlah.clear();
            System.out.println("==========================");
        }

        for (int i = 0; i < document.size(); i++) {
            document1.add(document.get(i));
        }
//        for (int i = 0; i < document.size(); i++) {
//            System.out.println(document.get(i));
//        }

        for (int i = 0; i < document.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (i != j && document.get(i).equals(document.get(j))) {
                    document1.remove(document.get(i));
                    break;
                }
            }
        }
//        System.out.println("===========");
//        for (int i = 0; i < document1.size(); i++) {
//            System.out.println(document1.get(i));
//        }

        for (Object key : document1) {
            int jum = 0;
            for (String t : document) {
                if (key.equals(t)) {
                    jum++;
                }
            }
            jumlah.add(jum);
        }
//        for (int i = 0; i < document1.size(); i++) {
//            System.out.println(document1.get(i)+" : "+jumlah.get(i));
//        }

        for (int i = 0; i < document1.size(); i++) {
            double idff = Math.log10(2 / jumlah.get(i));
            idf.add(idff);
        }
//        for (int i = 0; i < document1.size(); i++) {
//            System.out.println(document1.get(i)+" : "+jumlah.get(i)+"---> IDF : "+idf.get(i));
//        }
        for (int i = 0; i < document1.size(); i++) {
            double tfidff = tf2.get(i) * idf.get(i);
            tfidf.add(tfidff);
        }
        for (int i = 0; i < document1.size(); i++) {
            System.out.println(document1.get(i) + " : " + jumlah.get(i) + " " + "\t--> IDF \t: " + idf.get(i) + "\n \t \t--> TF-IDF \t: " + tfidf.get(i));
        }
    }
}
